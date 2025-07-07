# Traxxion09 - Zimbabwe SIM Generation Service

**Created by Vincent Ganiza (Lil Gaga Traxx09)**

A comprehensive web application for generating Zimbabwe SIM numbers starting with +26309, featuring real-time messaging, WhatsApp notifications, and call handling.

## Features

✨ **SIM Number Generation**
- Generate unlimited Zimbabwe SIM numbers
- Support for both +26309 and 26309 formats
- Instant generation with secure unique numbers

🔐 **Authentication**
- Email/Password registration and login
- Google OAuth integration
- Secure JWT token-based authentication

📱 **Real-time Messaging**
- Send and receive SMS messages
- WhatsApp integration for pair codes
- Call notifications and handling
- Real-time updates using Socket.IO

📊 **Dashboard & Analytics**
- User-friendly dashboard with statistics
- SIM usage tracking
- Message history and analytics

🎨 **Modern UI/UX**
- Beautiful gradient design
- Responsive mobile-friendly interface
- 10-second loading screen with branding
- Smooth animations and transitions

## Quick Setup

### Prerequisites
- Node.js (v14 or higher)
- MongoDB (local or cloud)
- Google Developer Account (for OAuth)

### Installation

1. **Clone and Install Dependencies**
```bash
git clone <repository-url>
cd traxxion09-website
npm install
```

2. **Environment Configuration**
```bash
cp .env.example .env
```

Edit `.env` file with your configurations:
```env
NODE_ENV=production
PORT=5000
MONGODB_URI=mongodb://localhost:27017/traxxion09
JWT_SECRET=your-super-secret-jwt-key
SESSION_SECRET=your-session-secret

# Google OAuth (Required for Google Login)
GOOGLE_CLIENT_ID=your-google-client-id
GOOGLE_CLIENT_SECRET=your-google-client-secret

# Email Configuration (Optional)
EMAIL_HOST=smtp.gmail.com
EMAIL_PORT=587
EMAIL_USER=your-email@gmail.com
EMAIL_PASS=your-app-password
```

3. **Database Setup**
```bash
# If using local MongoDB
mongod

# The application will automatically create the database and collections
```

4. **Google OAuth Setup** (Optional but recommended)
   - Go to [Google Cloud Console](https://console.cloud.google.com/)
   - Create a new project or select existing
   - Enable Google+ API
   - Create OAuth 2.0 credentials
   - Set authorized redirect URI: `http://localhost:5000/auth/google/callback`
   - Copy Client ID and Secret to `.env`

5. **Start the Application**
```bash
# Development mode
npm run dev

# Production mode
npm start
```

6. **Access the Website**
   - Open browser: `http://localhost:5000`
   - Wait for 10-second loading screen
   - Create account or login with Google

## Usage Guide

### Getting Started
1. **Registration**: Create account with email or use Google login
2. **Generate SIM**: Go to "Generate SIM" section and select format
3. **Messaging**: Use generated SIM numbers to send/receive messages
4. **Dashboard**: Monitor your SIM usage and statistics

### SIM Number Formats
- **International**: +26309XXXXXXX (10 digits total)
- **Local**: 26309XXXXXXX (9 digits total)

### Real-time Features
- Instant message notifications
- WhatsApp verification codes
- Call alerts and logs
- Live dashboard updates

### Demo Features
- Simulate incoming messages for testing
- Real-time message handling
- Notification system

## API Endpoints

### Authentication
- `POST /auth/register` - Create new account
- `POST /auth/login` - Login with email/password
- `GET /auth/google` - Google OAuth login
- `GET /api/verify-token` - Verify JWT token

### SIM Management
- `POST /api/generate-sim` - Generate new SIM number
- `GET /api/my-sims` - Get user's SIM numbers

### Messaging
- `GET /api/messages/:simNumber` - Get messages for SIM
- `POST /api/send-message` - Send message from SIM

## Developer Information

**Developer**: Vincent Ganiza (Lil Gaga Traxx09)  
**Contact**: 
- Mobile: +263 780 078 177
- Alternative: +263 716 857 999

**Project**: Zimbabwe SIM Generation Service  
**Service**: Traxxion09  

## Technical Stack

- **Backend**: Node.js, Express.js, MongoDB, Socket.IO
- **Frontend**: HTML5, CSS3, JavaScript (Vanilla)
- **Authentication**: Passport.js, JWT, Google OAuth
- **Database**: MongoDB with Mongoose
- **Real-time**: Socket.IO for live updates
- **UI/UX**: Modern gradient design, responsive layout

## File Structure

```
traxxion09-website/
├── server.js              # Main server file
├── package.json           # Dependencies and scripts
├── .env                   # Environment variables
├── public/                # Frontend files
│   ├── index.html        # Main HTML file
│   ├── styles.css        # CSS styling
│   └── app.js            # Frontend JavaScript
├── app/                  # Legacy Android files
└── README.md             # This file
```

## Features Breakdown

### 🎯 Core Features
- ✅ 10-second loading screen with "Created by Lil Gaga Traxx09"
- ✅ Google and Email authentication
- ✅ Zimbabwe SIM generation (+26309 format)
- ✅ Real-time messaging system
- ✅ WhatsApp integration ready
- ✅ Call handling system
- ✅ Beautiful modern UI

### 📱 Mobile Features
- ✅ Responsive design for all devices
- ✅ Touch-friendly interface
- ✅ Mobile-optimized navigation

### 🔒 Security Features
- ✅ JWT token authentication
- ✅ Password hashing with bcrypt
- ✅ Secure session management
- ✅ Input validation and sanitization

## Deployment Options

### Local Development
```bash
npm run dev
```

### Production Deployment
```bash
npm start
```

### Docker Deployment (Optional)
```dockerfile
FROM node:16
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 5000
CMD ["npm", "start"]
```

### Cloud Deployment
- **Heroku**: Add MongoDB Atlas connection
- **Vercel**: Configure environment variables
- **DigitalOcean**: Use MongoDB droplet
- **AWS**: Use EC2 + MongoDB Atlas

## Troubleshooting

### Common Issues

1. **MongoDB Connection Error**
   ```bash
   # Check MongoDB is running
   mongod
   # Or use MongoDB Atlas cloud connection
   ```

2. **Google OAuth Not Working**
   - Verify Client ID and Secret in `.env`
   - Check redirect URI in Google Console
   - Ensure Google+ API is enabled

3. **Port Already in Use**
   ```bash
   # Change port in .env file
   PORT=3000
   ```

4. **Missing Dependencies**
   ```bash
   # Reinstall dependencies
   rm -rf node_modules
   npm install
   ```

## License

MIT License - Feel free to use for educational and commercial purposes.

## Support

For support or custom development:
- Email: Contact via phone numbers provided
- Phone: +263 780 078 177 / +263 716 857 999

**Created with ❤️ by Lil Gaga Traxx09**

---

*This project represents a complete Zimbabwe SIM generation service with modern web technologies and real-time capabilities.*