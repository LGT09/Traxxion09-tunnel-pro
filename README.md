# Traxxion09 Tunnel PRO VPN

## 📱 App Information

**App Name:** Traxxion09 Tunnel PRO  
**Developer:** Vincent Ganiza (Lil Gaga Traxx09)  
**Version:** 1.0.0  
**Package:** com.lilgagatraxx09.traxxion09tunnelpro

## 📞 Contact Information

- **Email:** vincentganiza9@gmail.com
- **Phone:** +263780078177 | +263716857999
- **Location:** Zimbabwe 🇿🇼

## 🛡️ Description

Traxxion09 Tunnel PRO is an offline-capable VPN app built for blazing fast, secure, and anonymous browsing – specially optimized for Zimbabwean servers like Econet and NetOne.

With support for V2Ray, SSH, TCP, UDP, SlowDNS, and more, Traxxion09 Tunnel PRO ensures flexible and powerful connectivity — even in tough network conditions.

## 🚀 Features

### Core Features
- 🌐 **Offline VPN Launch** (No internet needed for app to load)
- ⏱️ **10-second Launch Screen** with "Created by Lil Gaga Traxx09"
- 🇿🇼 **Zimbabwe Servers**: Econet, NetOne, and more
- 🛠️ **Protocol Support**: V2Ray, SSH, TCP, UDP, SlowDNS
- 🧠 **Smart Config Import/Export**
- 🎨 **Theme Modes**: Dark, Light, and System default
- 🛑 **Kill Switch** to protect data on VPN disconnect
- 🔐 **Encryption** for traffic and payloads

### Diagnostic Tools
- 🔍 **IP Checker**
- 📱 **SIM Card Information**
- 🔑 **Host Key Viewer**
- 🆔 **HWID Display**
- 📊 **Data Usage Tracking**

### Payload Configurations
- `econet_social_payload.txt` - Optimized for social media
- `netone_whatsapp_payload.txt` - WhatsApp-specific configuration

### Extras
- 🔍 **V2URI Decoder**
- 🔐 **Host Checker & Port Scanner**
- 💽 **Logs Viewer** (Data usage, status)
- 📲 **SIM Tweaks** (For global & Zim-specific tweaks)

## 🧑‍💻 Branding

> Created by Vincent Ganiza a.k.a Lil Gaga Traxx09  
> Copyright ©️ 2025  
> "Gaga is the King"

## 📦 Build Instructions

### Prerequisites
- Android Studio or AIDE
- Android SDK (API 21+)
- Gradle 7.4.2+

### Building in AIDE
1. Open AIDE and navigate to the project folder
2. The project structure is already configured for AIDE
3. Build the project using the built-in Gradle wrapper
4. The APK will be generated in `app/build/outputs/apk/debug/`

### Building in Android Studio
1. Open the project in Android Studio
2. Sync Gradle files
3. Build → Build Bundle(s) / APK(s) → Build APK(s)
4. The APK will be generated in `app/build/outputs/apk/debug/`

### Command Line Build
```bash
# Navigate to project directory
cd /path/to/traxxion09-tunnel-pro

# Build the APK
./gradlew assembleDebug

# The APK will be in app/build/outputs/apk/debug/app-debug.apk
```

## 🔧 Project Structure

```
app/
├── src/main/
│   ├── java/com/lilgagatraxx09/traxxion09tunnelpro/
│   │   ├── MainActivity.java          # Main VPN interface
│   │   ├── SplashActivity.java        # 10-second splash screen
│   │   ├── VpnService.java           # VPN service implementation
│   │   ├── SettingsActivity.java      # App settings
│   │   ├── ImportExportActivity.java  # Config import/export
│   │   └── AboutActivity.java         # About page
│   ├── res/
│   │   ├── layout/                    # UI layouts
│   │   ├── values/                    # Strings, colors, styles
│   │   ├── drawable/                  # Icons and graphics
│   │   └── menu/                      # Menu resources
│   ├── assets/                        # Payload configurations
│   └── AndroidManifest.xml           # App permissions and components
├── build.gradle                      # App-level build configuration
└── proguard-rules.pro               # Code obfuscation rules

build.gradle                         # Project-level build configuration
settings.gradle                      # Project settings
gradle.properties                    # Gradle properties
```

## 🔐 Permissions

The app requires the following permissions:
- `BIND_VPN_SERVICE` - For VPN functionality
- `INTERNET` - For network connectivity
- `ACCESS_NETWORK_STATE` - For network status monitoring
- `ACCESS_WIFI_STATE` - For WiFi connectivity
- `WRITE_EXTERNAL_STORAGE` - For config import/export
- `READ_EXTERNAL_STORAGE` - For config import/export
- `FOREGROUND_SERVICE` - For VPN service
- `SYSTEM_ALERT_WINDOW` - For VPN interface

## 🎨 Themes

The app supports three theme modes:
- **Light Theme** - Default light appearance
- **Dark Theme** - Dark mode for low-light environments
- **System Default** - Follows system theme setting

## 📊 Diagnostic Features

The app includes comprehensive diagnostic tools:
- **IP Information**: Local and public IP addresses
- **SIM Information**: Carrier details and network info
- **Device Information**: HWID, model, Android version
- **Data Usage**: VPN and total data consumption
- **Network Status**: Connection quality and latency

## 🔧 Configuration

### Server Configuration
The app includes pre-configured Zimbabwean servers:
- Econet Zimbabwe (Harare, Bulawayo, Mutare, Masvingo)
- NetOne Zimbabwe (Harare, Bulawayo, Gweru, Chinhoyi)

### Payload Files
- `econet_social_payload.txt`: Optimized for social media access
- `netone_whatsapp_payload.txt`: WhatsApp-specific configuration

## 🚀 Usage

1. **Launch**: App starts with 10-second splash screen
2. **Select Server**: Choose from Zimbabwean servers
3. **Connect**: Tap CONNECT to establish VPN
4. **Monitor**: Use diagnostic tools to monitor connection
5. **Configure**: Access settings for customization

## 📱 Compatibility

- **Minimum SDK**: API 21 (Android 5.0)
- **Target SDK**: API 33 (Android 13)
- **Architecture**: ARM, ARM64, x86, x86_64

## 🔒 Security Features

- **Kill Switch**: Blocks traffic when VPN disconnects
- **Encryption**: Traffic encryption for privacy
- **Offline Mode**: Works without internet for app launch
- **Secure Storage**: Encrypted configuration storage

## 📞 Support

For technical support or feature requests, contact:
- **Email**: vincentganiza9@gmail.com
- **Phone**: +263780078177 | +263716857999

---

**"Gaga is the King"** - Vincent Ganiza (Lil Gaga Traxx09)