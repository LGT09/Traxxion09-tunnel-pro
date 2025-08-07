# Traxxion09 Tunnel PRO VPN

🛡️ **Offline-Capable VPN App for Zimbabwean Networks**

## 📱 App Information

**App Name:** Traxxion09 Tunnel PRO  
**Developer:** Vincent Ganiza (Lil Gaga Traxx09)  
**Contact:** 
- 📧 vincentganiza9@gmail.com
- 📞 +263780078177 | +263716857999
- 🌍 Zimbabwe 🇿🇼

## 🚀 Features

- 🌐 **Offline VPN Launch** (No internet needed for app to load)
- ⏱️ **10-second Launch Screen** with "Created by Lil Gaga Traxx09"
- 🇿🇼 **Zimbabwe Servers**: Econet, NetOne, and more
- 🛠️ **Protocol Support**: V2Ray, SSH, TCP, UDP, SlowDNS
- 🧠 **Smart Config Import/Export**
- 🎨 **Theme Modes**: Dark, Light, and System default
- 🛑 **Kill Switch** to protect data on VPN disconnect
- 🔐 **Encryption** for traffic and payloads

## 🧪 Diagnostic Tools

- IP Checker
- SIM Card Information
- Host Key Viewer
- HWID Display

## 📦 Included Payload Configurations

- `econet_social_payload.txt`
- `netone_whatsapp_payload.txt`

## 📁 Extras

- 🔍 V2URI Decoder
- 🔐 Host Checker & Port Scanner
- 💽 Logs Viewer (Data usage, status)
- 📲 SIM Tweaks (For global & Zim-specific tweaks)

## 🏗️ Building the APK

### Prerequisites

- Android Studio or AIDE
- Java Development Kit (JDK) 8 or higher
- Android SDK

### Build Instructions

1. **Clone or download the project**
   ```bash
   git clone <repository-url>
   cd Traxxion09TunnelPRO
   ```

2. **Open in AIDE or Android Studio**
   - Open the project in AIDE
   - Or import into Android Studio

3. **Build the APK**
   ```bash
   # Using Gradle wrapper
   ./gradlew assembleRelease
   
   # Or using AIDE's build feature
   # Just press the build button in AIDE
   ```

4. **Find the APK**
   - The APK will be generated at: `app/build/outputs/apk/release/app-release.apk`

### Build in AIDE

1. Open AIDE app
2. Navigate to the project folder
3. Open the project
4. Click the build button (hammer icon)
5. Wait for the build to complete
6. Install the generated APK

## 🔧 Project Structure

```
Traxxion09TunnelPRO/
├── app/
│   ├── src/main/
│   │   ├── java/com/lilgagatraxx09/traxxion09tunnelpro/
│   │   │   ├── MainActivity.java
│   │   │   ├── SplashActivity.java
│   │   │   ├── VpnService.java
│   │   │   ├── SettingsActivity.java
│   │   │   ├── AboutActivity.java
│   │   │   └── ImportExportActivity.java
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   ├── values/
│   │   │   └── drawable/
│   │   └── assets/
│   │       ├── econet_social_payload.txt
│   │       └── netone_whatsapp_payload.txt
│   └── build.gradle
├── build.gradle
├── settings.gradle
└── gradle/
```

## 🛡️ Permissions

The app requires the following permissions:
- `BIND_VPN_SERVICE` - For VPN functionality
- `INTERNET` - For network access
- `ACCESS_NETWORK_STATE` - For network monitoring
- `WRITE_EXTERNAL_STORAGE` - For config import/export
- `READ_EXTERNAL_STORAGE` - For config import/export
- `FOREGROUND_SERVICE` - For VPN service
- `SYSTEM_ALERT_WINDOW` - For VPN overlay

## 🎨 Branding

> Created by Vincent Ganiza a.k.a Lil Gaga Traxx09  
> Copyright ©️ 2025  
> "Gaga is the King"

## 📄 License

This project is proprietary software created by Vincent Ganiza. All rights reserved.

## 🆘 Support

For support or questions, contact:
- Email: vincentganiza9@gmail.com
- Phone: +263780078177 | +263716857999

---

**Note:** This app is specifically optimized for Zimbabwean networks (Econet, NetOne) and includes offline capabilities for enhanced user experience.