const express = require('express');
const mongoose = require('mongoose');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
const passport = require('passport');
const GoogleStrategy = require('passport-google-oauth20').Strategy;
const LocalStrategy = require('passport-local').Strategy;
const session = require('express-session');
const cors = require('cors');
const http = require('http');
const socketIo = require('socket.io');
require('dotenv').config();

const app = express();
const server = http.createServer(app);
const io = socketIo(server, {
  cors: {
    origin: "*",
    methods: ["GET", "POST"]
  }
});

// Middleware
app.use(cors());
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(express.static('public'));
app.use(session({
  secret: process.env.SESSION_SECRET || 'traxxion09-secret-key',
  resave: false,
  saveUninitialized: true
}));
app.use(passport.initialize());
app.use(passport.session());

// MongoDB Connection
mongoose.connect(process.env.MONGODB_URI || 'mongodb://localhost:27017/traxxion09', {
  useNewUrlParser: true,
  useUnifiedTopology: true
});

// User Schema
const userSchema = new mongoose.Schema({
  email: { type: String, unique: true, sparse: true },
  password: String,
  googleId: String,
  name: String,
  joinDate: { type: Date, default: Date.now },
  simNumbers: [{ 
    number: String, 
    format: String, 
    createdAt: { type: Date, default: Date.now },
    isActive: { type: Boolean, default: true }
  }]
});

const User = mongoose.model('User', userSchema);

// SIM Number Schema
const simSchema = new mongoose.Schema({
  number: { type: String, unique: true },
  userId: { type: mongoose.Schema.Types.ObjectId, ref: 'User' },
  format: String,
  createdAt: { type: Date, default: Date.now },
  lastActivity: { type: Date, default: Date.now },
  messages: [{
    type: String,
    content: String,
    timestamp: { type: Date, default: Date.now },
    from: String,
    to: String
  }]
});

const SimNumber = mongoose.model('SimNumber', simSchema);

// Passport Configuration
passport.use(new GoogleStrategy({
  clientID: process.env.GOOGLE_CLIENT_ID || 'your-google-client-id',
  clientSecret: process.env.GOOGLE_CLIENT_SECRET || 'your-google-client-secret',
  callbackURL: "/auth/google/callback"
}, async (accessToken, refreshToken, profile, done) => {
  try {
    let user = await User.findOne({ googleId: profile.id });
    if (!user) {
      user = new User({
        googleId: profile.id,
        name: profile.displayName,
        email: profile.emails[0].value
      });
      await user.save();
    }
    return done(null, user);
  } catch (error) {
    return done(error, null);
  }
}));

passport.use(new LocalStrategy({ usernameField: 'email' }, async (email, password, done) => {
  try {
    const user = await User.findOne({ email });
    if (!user) return done(null, false);
    
    const isMatch = await bcrypt.compare(password, user.password);
    if (!isMatch) return done(null, false);
    
    return done(null, user);
  } catch (error) {
    return done(error);
  }
}));

passport.serializeUser((user, done) => done(null, user.id));
passport.deserializeUser(async (id, done) => {
  try {
    const user = await User.findById(id);
    done(null, user);
  } catch (error) {
    done(error);
  }
});

// Generate Zimbabwe SIM Number
function generateZimbabweSIM(format = '+26309') {
  const prefix = format === '+26309' ? '+26309' : '26309';
  const remainingDigits = 10 - prefix.length;
  let number = '';
  
  for (let i = 0; i < remainingDigits; i++) {
    number += Math.floor(Math.random() * 10);
  }
  
  return prefix + number;
}

// Authentication Middleware
const authenticateToken = (req, res, next) => {
  const authHeader = req.headers['authorization'];
  const token = authHeader && authHeader.split(' ')[1];

  if (!token) {
    return res.status(401).json({ message: 'Access token required' });
  }

  jwt.verify(token, process.env.JWT_SECRET || 'jwt-secret', (err, user) => {
    if (err) return res.status(403).json({ message: 'Invalid token' });
    req.user = user;
    next();
  });
};

// Routes
app.get('/', (req, res) => {
  res.sendFile(__dirname + '/public/index.html');
});

// Token verification route
app.get('/api/verify-token', authenticateToken, async (req, res) => {
  try {
    const user = await User.findById(req.user.id);
    if (!user) {
      return res.status(404).json({ message: 'User not found' });
    }
    res.json({ user: { id: user._id, email: user.email, name: user.name, joinDate: user.joinDate } });
  } catch (error) {
    res.status(500).json({ message: 'Server error' });
  }
});

// Auth Routes
app.get('/auth/google', passport.authenticate('google', { scope: ['profile', 'email'] }));

app.get('/auth/google/callback', 
  passport.authenticate('google', { failureRedirect: '/login' }),
  (req, res) => {
    const token = jwt.sign({ id: req.user._id }, process.env.JWT_SECRET || 'jwt-secret');
    res.redirect(`/?token=${token}`);
  }
);

app.post('/auth/register', async (req, res) => {
  try {
    const { email, password, name } = req.body;
    
    const existingUser = await User.findOne({ email });
    if (existingUser) {
      return res.status(400).json({ message: 'User already exists' });
    }

    const hashedPassword = await bcrypt.hash(password, 10);
    const user = new User({ email, password: hashedPassword, name });
    await user.save();

    const token = jwt.sign({ id: user._id }, process.env.JWT_SECRET || 'jwt-secret');
    res.json({ token, user: { id: user._id, email: user.email, name: user.name } });
  } catch (error) {
    res.status(500).json({ message: 'Server error' });
  }
});

app.post('/auth/login', async (req, res) => {
  try {
    const { email, password } = req.body;
    
    const user = await User.findOne({ email });
    if (!user) {
      return res.status(400).json({ message: 'Invalid credentials' });
    }

    const isMatch = await bcrypt.compare(password, user.password);
    if (!isMatch) {
      return res.status(400).json({ message: 'Invalid credentials' });
    }

    const token = jwt.sign({ id: user._id }, process.env.JWT_SECRET || 'jwt-secret');
    res.json({ token, user: { id: user._id, email: user.email, name: user.name } });
  } catch (error) {
    res.status(500).json({ message: 'Server error' });
  }
});

// SIM Number Routes
app.post('/api/generate-sim', authenticateToken, async (req, res) => {
  try {
    const { format } = req.body;
    const userId = req.user.id;
    
    let simNumber;
    let isUnique = false;
    
    // Generate unique SIM number
    while (!isUnique) {
      simNumber = generateZimbabweSIM(format);
      const existing = await SimNumber.findOne({ number: simNumber });
      if (!existing) isUnique = true;
    }
    
    const newSim = new SimNumber({
      number: simNumber,
      userId,
      format
    });
    
    await newSim.save();
    
    // Add to user's sim numbers
    await User.findByIdAndUpdate(userId, {
      $push: { simNumbers: { number: simNumber, format } }
    });
    
    res.json({ simNumber, message: 'SIM number generated successfully!' });
  } catch (error) {
    res.status(500).json({ message: 'Error generating SIM number' });
  }
});

app.get('/api/my-sims', authenticateToken, async (req, res) => {
  try {
    const userId = req.user.id;
    const sims = await SimNumber.find({ userId }).sort({ createdAt: -1 });
    res.json(sims);
  } catch (error) {
    res.status(500).json({ message: 'Error fetching SIM numbers' });
  }
});

app.post('/api/send-message', authenticateToken, async (req, res) => {
  try {
    const { simNumber, message, type, recipient } = req.body;
    const userId = req.user.id;
    
    const sim = await SimNumber.findOne({ number: simNumber, userId });
    if (!sim) {
      return res.status(404).json({ message: 'SIM number not found' });
    }
    
    const newMessage = {
      type, // 'sms', 'whatsapp', 'call'
      content: message,
      from: simNumber,
      to: recipient
    };
    
    sim.messages.push(newMessage);
    sim.lastActivity = new Date();
    await sim.save();
    
    // Emit to socket for real-time updates
    io.to(userId).emit('new-message', newMessage);
    
    res.json({ message: 'Message sent successfully!' });
  } catch (error) {
    res.status(500).json({ message: 'Error sending message' });
  }
});

app.get('/api/messages/:simNumber', authenticateToken, async (req, res) => {
  try {
    const { simNumber } = req.params;
    const userId = req.user.id;
    
    const sim = await SimNumber.findOne({ number: simNumber, userId });
    if (!sim) {
      return res.status(404).json({ message: 'SIM number not found' });
    }
    
    res.json(sim.messages);
  } catch (error) {
    res.status(500).json({ message: 'Error fetching messages' });
  }
});

// Socket.IO for real-time features
io.on('connection', (socket) => {
  console.log('User connected:', socket.id);
  
  socket.on('join-user', (userId) => {
    socket.join(userId);
  });
  
  socket.on('simulate-incoming-message', async (data) => {
    const { simNumber, message, type, from } = data;
    
    try {
      const sim = await SimNumber.findOne({ number: simNumber });
      if (sim) {
        const newMessage = {
          type,
          content: message,
          from,
          to: simNumber,
          timestamp: new Date()
        };
        
        sim.messages.push(newMessage);
        await sim.save();
        
        io.to(sim.userId.toString()).emit('incoming-message', newMessage);
      }
    } catch (error) {
      console.error('Error handling incoming message:', error);
    }
  });
  
  socket.on('disconnect', () => {
    console.log('User disconnected:', socket.id);
  });
});

const PORT = process.env.PORT || 5000;
server.listen(PORT, () => {
  console.log(`Traxxion09 server running on port ${PORT}`);
  console.log('Created by Lil Gaga Traxx09');
});