# Traxxion09 tunnel pro

A premium VPN application designed specifically for Zimbabwe, providing secure and fast connections through dedicated Econet and NetOne servers.

## Features

- **Multiple Zimbabwean Servers**: Choose from 8 different servers across Zimbabwe
  - Econet Zimbabwe (Harare, Bulawayo, Mutare, Masvingo)
  - NetOne Zimbabwe (Harare, Bulawayo, Gweru, Chinhoyi)
- **Offline Mode**: Works without internet connection
- **10-Second Splash Screen**: Professional loading experience
- **Import/Export Configuration**: Save and load VPN settings
- **Auto-Connect**: Automatically connect on startup
- **Auto-Reconnect**: Automatically reconnect on disconnect
- **Real-time Statistics**: Monitor connection speed and latency
- **Secure Protocols**: OpenVPN UDP/TCP, IKEv2, and WireGuard support

## Developer Information

**Created by:** Vincent Ganiza LilGagaTraxx09  
**Contact:** 
- 263780078177
- 263716857999

## Technical Requirements

- **Minimum SDK**: Android 5.0 (API level 21)
- **Target SDK**: Android 13 (API level 33)
- **Permissions Required**:
  - VPN Service binding
  - Internet access
  - Network state access
  - Storage access (for import/export)
  - Foreground service

## Build Instructions

### Prerequisites
- Android Studio Arctic Fox (2020.3.1) or later
- Java 8 or later
- Android SDK with API level 33

### Setup
1. Clone or download the project
2. Open the project in Android Studio
3. Let Android Studio download and install any missing dependencies
4. Build the project using `Build > Make Project`

### Running the App
1. Connect an Android device or start an emulator
2. Click `Run > Run 'app'` or press `Shift + F10`
3. The app will install and launch with a 10-second splash screen

## App Structure

```
app/
├── src/main/
│   ├── java/com/lilgagatraxx09/traxxion09tunnelpro/
│   │   ├── SplashActivity.java       # 10-second loading screen
│   │   ├── MainActivity.java         # Main VPN interface
│   │   ├── VpnService.java          # VPN service implementation
│   │   ├── ImportExportActivity.java # Configuration management
│   │   ├── SettingsActivity.java     # App settings
│   │   └── AboutActivity.java        # About & contact info
│   ├── res/
│   │   ├── layout/                   # UI layouts
│   │   ├── values/                   # Strings, colors, styles
│   │   ├── drawable/                 # Icons and graphics
│   │   └── menu/                     # Menu definitions
│   └── AndroidManifest.xml           # App permissions & components
├── build.gradle                      # App-level build configuration
└── proguard-rules.pro                # ProGuard configuration
```

## Key Components

### VPN Service
- Handles VPN connection establishment
- Manages traffic routing
- Provides notification for connection status
- Supports multiple VPN protocols

### Server Management
- Pre-configured Zimbabwean servers
- Automatic server selection
- Latency monitoring
- Connection status tracking

### Configuration Import/Export
- Save VPN settings to external storage
- Load configurations from files
- Backup and restore functionality
- Cross-device configuration sharing

### Offline Mode
- Works without internet connection
- Local server simulation
- Cached configuration management

## Usage

1. **Launch**: App starts with 10-second loading screen
2. **Server Selection**: Choose from available Zimbabwean servers
3. **Connect**: Tap the connect button or use the toggle switch
4. **Monitor**: View real-time connection statistics
5. **Settings**: Configure auto-connect, protocols, and timeouts
6. **Import/Export**: Backup or restore VPN configurations

## Troubleshooting

### Common Issues
- **VPN Permission Denied**: Grant VPN permission when prompted
- **Connection Failed**: Check server availability and network connection
- **Import/Export Issues**: Ensure storage permissions are granted

### Support
For support, contact the developer:
- Phone: 263780078177 or 263716857999
- App Name: Traxxion09 tunnel pro
- Developer: Vincent Ganiza LilGagaTraxx09

## License

This is proprietary software developed by LilGagaTraxx09. All rights reserved.

## Version History

- **v1.0.0**: Initial release
  - Core VPN functionality
  - Zimbabwean server support
  - Import/export features
  - Offline mode support