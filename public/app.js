// Global variables
let currentUser = null;
let authToken = null;
let socket = null;
let userSims = [];
let currentMessages = [];
let currentFilter = 'all';

// Initialize the application
document.addEventListener('DOMContentLoaded', function() {
    initializeApp();
});

function initializeApp() {
    // Handle loading screen
    setTimeout(() => {
        document.getElementById('loadingScreen').style.display = 'none';
        
        // Check for existing authentication
        const urlParams = new URLSearchParams(window.location.search);
        const tokenFromUrl = urlParams.get('token');
        
        if (tokenFromUrl) {
            authToken = tokenFromUrl;
            localStorage.setItem('authToken', authToken);
            window.history.replaceState({}, document.title, '/');
            authenticateAndShowApp();
        } else {
            authToken = localStorage.getItem('authToken');
            if (authToken) {
                authenticateAndShowApp();
            } else {
                showAuthSection();
            }
        }
    }, 10000); // 10 seconds loading time as requested
}

function showAuthSection() {
    document.getElementById('authSection').style.display = 'flex';
    document.getElementById('navbar').style.display = 'none';
    document.getElementById('appContent').style.display = 'none';
}

function showMainApp() {
    document.getElementById('authSection').style.display = 'none';
    document.getElementById('navbar').style.display = 'block';
    document.getElementById('appContent').style.display = 'block';
    
    // Initialize Socket.IO
    initializeSocket();
    
    // Load user data
    loadUserData();
    loadUserSims();
    
    // Show dashboard by default
    showSection('dashboard');
}

function authenticateAndShowApp() {
    // Verify token with backend
    fetch('/api/verify-token', {
        headers: {
            'Authorization': `Bearer ${authToken}`
        }
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Invalid token');
        }
    })
    .then(data => {
        currentUser = data.user;
        showMainApp();
    })
    .catch(error => {
        console.error('Authentication failed:', error);
        localStorage.removeItem('authToken');
        authToken = null;
        showAuthSection();
    });
}

// Authentication functions
function toggleAuthForm() {
    const loginForm = document.getElementById('loginForm');
    const registerForm = document.getElementById('registerForm');
    
    if (loginForm.style.display === 'none') {
        loginForm.style.display = 'block';
        registerForm.style.display = 'none';
    } else {
        loginForm.style.display = 'none';
        registerForm.style.display = 'block';
    }
}

async function login(event) {
    event.preventDefault();
    
    const email = document.getElementById('loginEmail').value;
    const password = document.getElementById('loginPassword').value;
    
    try {
        const response = await fetch('/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password })
        });
        
        const data = await response.json();
        
        if (response.ok) {
            authToken = data.token;
            currentUser = data.user;
            localStorage.setItem('authToken', authToken);
            showMainApp();
            showToast('Login successful!', 'success');
        } else {
            showToast(data.message || 'Login failed', 'error');
        }
    } catch (error) {
        console.error('Login error:', error);
        showToast('Network error. Please try again.', 'error');
    }
}

async function register(event) {
    event.preventDefault();
    
    const name = document.getElementById('registerName').value;
    const email = document.getElementById('registerEmail').value;
    const password = document.getElementById('registerPassword').value;
    
    try {
        const response = await fetch('/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ name, email, password })
        });
        
        const data = await response.json();
        
        if (response.ok) {
            authToken = data.token;
            currentUser = data.user;
            localStorage.setItem('authToken', authToken);
            showMainApp();
            showToast('Account created successfully!', 'success');
        } else {
            showToast(data.message || 'Registration failed', 'error');
        }
    } catch (error) {
        console.error('Registration error:', error);
        showToast('Network error. Please try again.', 'error');
    }
}

function loginWithGoogle() {
    window.location.href = '/auth/google';
}

function logout() {
    authToken = null;
    currentUser = null;
    localStorage.removeItem('authToken');
    
    if (socket) {
        socket.disconnect();
        socket = null;
    }
    
    showAuthSection();
    showToast('Logged out successfully', 'success');
}

// Navigation functions
function showSection(sectionName) {
    // Hide all sections
    const sections = document.querySelectorAll('.section');
    sections.forEach(section => {
        section.classList.remove('active');
    });
    
    // Show selected section
    document.getElementById(sectionName).classList.add('active');
    
    // Update navigation links
    const navLinks = document.querySelectorAll('.nav-link');
    navLinks.forEach(link => {
        link.classList.remove('active');
    });
    
    const activeLink = document.querySelector(`[onclick="showSection('${sectionName}')"]`);
    if (activeLink) {
        activeLink.classList.add('active');
    }
    
    // Load section-specific data
    if (sectionName === 'messages') {
        populateSimSelectors();
    }
}

// Socket.IO functions
function initializeSocket() {
    socket = io();
    
    socket.on('connect', () => {
        console.log('Connected to server');
        if (currentUser) {
            socket.emit('join-user', currentUser.id);
        }
    });
    
    socket.on('new-message', (message) => {
        handleIncomingMessage(message);
    });
    
    socket.on('incoming-message', (message) => {
        handleIncomingMessage(message);
    });
    
    socket.on('disconnect', () => {
        console.log('Disconnected from server');
    });
}

function handleIncomingMessage(message) {
    // Add to current messages if viewing the right SIM
    const selectedSim = document.getElementById('messageSimSelect').value;
    if (selectedSim === message.to) {
        currentMessages.push(message);
        displayMessages();
    }
    
    // Show notification
    showToast(`New ${message.type}: ${message.content.substring(0, 50)}...`, 'success');
    
    // Update stats
    updateDashboardStats();
}

// SIM generation functions
async function generateSIM() {
    const formatRadios = document.getElementsByName('format');
    let selectedFormat = '+26309'; // default
    
    for (const radio of formatRadios) {
        if (radio.checked) {
            selectedFormat = radio.value;
            break;
        }
    }
    
    try {
        const response = await fetch('/api/generate-sim', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${authToken}`
            },
            body: JSON.stringify({ format: selectedFormat })
        });
        
        const data = await response.json();
        
        if (response.ok) {
            document.getElementById('newSimNumber').textContent = data.simNumber;
            document.getElementById('generatedResult').style.display = 'block';
            showToast('SIM number generated successfully!', 'success');
            
            // Refresh user SIMs
            loadUserSims();
            updateDashboardStats();
        } else {
            showToast(data.message || 'Failed to generate SIM', 'error');
        }
    } catch (error) {
        console.error('SIM generation error:', error);
        showToast('Network error. Please try again.', 'error');
    }
}

function copySIM() {
    const simNumber = document.getElementById('newSimNumber').textContent;
    navigator.clipboard.writeText(simNumber).then(() => {
        showToast('SIM number copied to clipboard!', 'success');
    }).catch(() => {
        showToast('Failed to copy SIM number', 'error');
    });
}

function showSimDetails() {
    showSection('messages');
    // Auto-select the new SIM in messages section
    const simNumber = document.getElementById('newSimNumber').textContent;
    const selector = document.getElementById('messageSimSelect');
    selector.value = simNumber;
    loadMessages();
}

// Data loading functions
async function loadUserData() {
    if (!currentUser) return;
    
    // Update user info in UI
    document.getElementById('userName').textContent = `Welcome, ${currentUser.name}!`;
    document.getElementById('profileName').textContent = currentUser.name;
    document.getElementById('profileEmail').textContent = currentUser.email;
    
    if (currentUser.joinDate) {
        const joinDate = new Date(currentUser.joinDate).toLocaleDateString();
        document.getElementById('profileJoinDate').textContent = joinDate;
    }
}

async function loadUserSims() {
    try {
        const response = await fetch('/api/my-sims', {
            headers: {
                'Authorization': `Bearer ${authToken}`
            }
        });
        
        if (response.ok) {
            userSims = await response.json();
            displayRecentSims();
            populateSimSelectors();
        }
    } catch (error) {
        console.error('Error loading SIMs:', error);
    }
}

function displayRecentSims() {
    const container = document.getElementById('recentSimsList');
    
    if (userSims.length === 0) {
        container.innerHTML = `
            <div class="no-sims">
                <i class="fas fa-sim-card"></i>
                <p>No SIM numbers generated yet</p>
                <button onclick="showSection('generate')" class="generate-btn">
                    <i class="fas fa-plus"></i> Generate Your First SIM
                </button>
            </div>
        `;
        return;
    }
    
    const recentSims = userSims.slice(0, 5); // Show latest 5
    container.innerHTML = recentSims.map(sim => `
        <div class="sim-item">
            <div>
                <div class="sim-number-display">${sim.number}</div>
                <div class="sim-date">${new Date(sim.createdAt).toLocaleString()}</div>
            </div>
            <div>
                <span class="sim-status active">Active</span>
            </div>
        </div>
    `).join('');
}

function populateSimSelectors() {
    const selectors = ['messageSimSelect', 'sendFromSim'];
    
    selectors.forEach(selectorId => {
        const selector = document.getElementById(selectorId);
        selector.innerHTML = '<option value="">Choose a SIM number...</option>';
        
        userSims.forEach(sim => {
            const option = document.createElement('option');
            option.value = sim.number;
            option.textContent = sim.number;
            selector.appendChild(option);
        });
    });
}

async function updateDashboardStats() {
    // Update total SIMs
    document.getElementById('totalSims').textContent = userSims.length;
    
    // Calculate total messages
    let totalMessages = 0;
    userSims.forEach(sim => {
        totalMessages += sim.messages ? sim.messages.length : 0;
    });
    document.getElementById('totalMessages').textContent = totalMessages;
    
    // Update last activity
    if (userSims.length > 0) {
        const lastActivity = userSims.reduce((latest, sim) => {
            const simActivity = new Date(sim.lastActivity || sim.createdAt);
            return simActivity > latest ? simActivity : latest;
        }, new Date(0));
        
        document.getElementById('lastActivity').textContent = lastActivity.toLocaleDateString();
    }
}

// Messaging functions
async function loadMessages() {
    const selectedSim = document.getElementById('messageSimSelect').value;
    if (!selectedSim) {
        document.getElementById('messagesList').innerHTML = `
            <div class="no-messages">
                <i class="fas fa-inbox"></i>
                <p>Select a SIM number to view messages</p>
            </div>
        `;
        return;
    }
    
    try {
        const response = await fetch(`/api/messages/${selectedSim}`, {
            headers: {
                'Authorization': `Bearer ${authToken}`
            }
        });
        
        if (response.ok) {
            currentMessages = await response.json();
            displayMessages();
        } else {
            showToast('Failed to load messages', 'error');
        }
    } catch (error) {
        console.error('Error loading messages:', error);
        showToast('Network error loading messages', 'error');
    }
}

function displayMessages() {
    const container = document.getElementById('messagesList');
    
    if (currentMessages.length === 0) {
        container.innerHTML = `
            <div class="no-messages">
                <i class="fas fa-inbox"></i>
                <p>No messages for this SIM number</p>
            </div>
        `;
        return;
    }
    
    // Filter messages based on current filter
    const filteredMessages = currentFilter === 'all' 
        ? currentMessages 
        : currentMessages.filter(msg => msg.type === currentFilter);
    
    container.innerHTML = filteredMessages.map(message => {
        const messageTime = new Date(message.timestamp).toLocaleString();
        const isIncoming = message.to === document.getElementById('messageSimSelect').value;
        
        return `
            <div class="message-item">
                <div class="message-header">
                    <div class="message-type-icon ${message.type}">
                        <i class="fas fa-${getMessageIcon(message.type)}"></i>
                        ${message.type.toUpperCase()}
                    </div>
                    <div class="message-time">${messageTime}</div>
                </div>
                <div class="message-content">${message.content}</div>
                <div class="message-from">
                    ${isIncoming ? `From: ${message.from}` : `To: ${message.to}`}
                </div>
            </div>
        `;
    }).join('');
}

function getMessageIcon(type) {
    switch (type) {
        case 'sms': return 'sms';
        case 'whatsapp': return 'brands fa-whatsapp';
        case 'call': return 'phone';
        default: return 'message';
    }
}

function filterMessages(type) {
    currentFilter = type;
    
    // Update button states
    document.querySelectorAll('.message-type-btn').forEach(btn => {
        btn.classList.remove('active');
    });
    document.querySelector(`[data-type="${type}"]`).classList.add('active');
    
    displayMessages();
}

async function sendMessage(event) {
    event.preventDefault();
    
    const simNumber = document.getElementById('sendFromSim').value;
    const recipient = document.getElementById('sendToNumber').value;
    const messageType = document.getElementById('messageType').value;
    const content = document.getElementById('messageContent').value;
    
    if (!simNumber || !recipient || !content) {
        showToast('Please fill in all fields', 'error');
        return;
    }
    
    try {
        const response = await fetch('/api/send-message', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${authToken}`
            },
            body: JSON.stringify({
                simNumber,
                recipient,
                type: messageType,
                message: content
            })
        });
        
        const data = await response.json();
        
        if (response.ok) {
            showToast('Message sent successfully!', 'success');
            
            // Clear form
            document.getElementById('sendToNumber').value = '';
            document.getElementById('messageContent').value = '';
            
            // Refresh messages if viewing the same SIM
            if (document.getElementById('messageSimSelect').value === simNumber) {
                loadMessages();
            }
        } else {
            showToast(data.message || 'Failed to send message', 'error');
        }
    } catch (error) {
        console.error('Send message error:', error);
        showToast('Network error. Please try again.', 'error');
    }
}

// Utility functions
function showToast(message, type = 'info') {
    const container = document.getElementById('toastContainer');
    const toast = document.createElement('div');
    toast.className = `toast ${type}`;
    toast.innerHTML = `
        <div style="display: flex; justify-content: space-between; align-items: center;">
            <span>${message}</span>
            <button onclick="this.parentElement.parentElement.remove()" style="background: none; border: none; font-size: 1.2rem; cursor: pointer; padding: 0; margin-left: 1rem;">&times;</button>
        </div>
    `;
    
    container.appendChild(toast);
    
    // Auto remove after 5 seconds
    setTimeout(() => {
        if (toast.parentElement) {
            toast.remove();
        }
    }, 5000);
}

// Demo functions for testing (can be removed in production)
function simulateIncomingMessage() {
    if (userSims.length === 0) {
        showToast('Generate a SIM number first', 'warning');
        return;
    }
    
    const randomSim = userSims[Math.floor(Math.random() * userSims.length)];
    const messageTypes = ['sms', 'whatsapp', 'call'];
    const randomType = messageTypes[Math.floor(Math.random() * messageTypes.length)];
    
    const demoMessages = [
        'Hello! This is a test message.',
        'Your verification code is 123456',
        'Welcome to our service!',
        'You have a new notification',
        'This is a WhatsApp verification code: 789012'
    ];
    
    const randomMessage = demoMessages[Math.floor(Math.random() * demoMessages.length)];
    
    if (socket) {
        socket.emit('simulate-incoming-message', {
            simNumber: randomSim.number,
            message: randomMessage,
            type: randomType,
            from: '+263' + Math.floor(Math.random() * 1000000000)
        });
    }
}

// Add demo button to dashboard (for testing)
document.addEventListener('DOMContentLoaded', function() {
    setTimeout(() => {
        // Add demo button to dashboard after app loads
        const dashboard = document.getElementById('dashboard');
        if (dashboard) {
            const demoButton = document.createElement('button');
            demoButton.innerHTML = '<i class="fas fa-play"></i> Simulate Incoming Message (Demo)';
            demoButton.className = 'generate-btn';
            demoButton.style.marginTop = '2rem';
            demoButton.onclick = simulateIncomingMessage;
            
            const recentSims = document.querySelector('.recent-sims');
            if (recentSims) {
                recentSims.appendChild(demoButton);
            }
        }
    }, 11000); // Add after loading screen
});

// Initialize everything when DOM is ready
document.addEventListener('DOMContentLoaded', function() {
    // Set up event listeners
    const messageSimSelect = document.getElementById('messageSimSelect');
    if (messageSimSelect) {
        messageSimSelect.addEventListener('change', loadMessages);
    }
});

// Add keyboard shortcuts
document.addEventListener('keydown', function(event) {
    // Ctrl/Cmd + G to generate SIM
    if ((event.ctrlKey || event.metaKey) && event.key === 'g') {
        event.preventDefault();
        showSection('generate');
    }
    
    // Ctrl/Cmd + M for messages
    if ((event.ctrlKey || event.metaKey) && event.key === 'm') {
        event.preventDefault();
        showSection('messages');
    }
    
    // Ctrl/Cmd + D for dashboard
    if ((event.ctrlKey || event.metaKey) && event.key === 'd') {
        event.preventDefault();
        showSection('dashboard');
    }
});