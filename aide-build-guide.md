# 🚀 AIDE Build Guide for Traxxion09 Tunnel PRO VPN

## 📱 App Information

**App Name:** Traxxion09 Tunnel PRO  
**Developer:** Vincent Ganiza (Lil Gaga Traxx09)  
**Version:** 1.0.0  
**Package:** com.lilgagatraxx09.traxxion09tunnelpro

## 📞 Contact Information

- **Email:** vincentganiza9@gmail.com
- **Phone:** +263780078177 | +263716857999
- **Location:** Zimbabwe 🇿🇼

## 🛡️ App Description

Traxxion09 Tunnel PRO is an offline-capable VPN app built for blazing fast, secure, and anonymous browsing – specially optimized for Zimbabwean servers like Econet and NetOne.

With support for V2Ray, SSH, TCP, UDP, SlowDNS, and more, Traxxion09 Tunnel PRO ensures flexible and powerful connectivity — even in tough network conditions.

## 📦 Complete Gradle Files Added

### ✅ Gradle Configuration Files:
- ✅ `build.gradle` (Root project)
- ✅ `app/build.gradle` (App module)
- ✅ `settings.gradle` (Project settings)
- ✅ `gradle.properties` (Gradle properties)
- ✅ `gradle/wrapper/gradle-wrapper.properties`
- ✅ `gradlew` (Unix/Linux wrapper script)
- ✅ `gradlew.bat` (Windows wrapper script)
- ✅ `app/proguard-rules.pro` (Code obfuscation)

### ✅ Build Scripts:
- ✅ `build-apk.sh` (Simple build script)
- ✅ `build-with-gradle.sh` (Gradle build script)
- ✅ `local.properties.template` (SDK configuration template)

### ✅ Documentation:
- ✅ `README.md` (Project overview)
- ✅ `AIDE_BUILD_GUIDE.md` (AIDE specific guide)
- ✅ `gradle-tasks.md` (Gradle tasks documentation)

## 🔧 Building in AIDE

### Step 1: Open Project in AIDE
1. Launch AIDE on your Android device
2. Navigate to the project folder
3. Open the project in AIDE

### Step 2: Project Recognition
AIDE will automatically recognize this as a Gradle project due to:
- ✅ `build.gradle` files present
- ✅ `settings.gradle` configured
- ✅ `gradle.properties` set up
- ✅ Gradle wrapper scripts available

### Step 3: Build Process
1. In AIDE, tap the **Build** button
2. Select **Build APK** or **Build Bundle**
3. Wait for the compilation to complete
4. The APK will be generated in `app/build/outputs/apk/debug/`

### Step 4: Install APK
1. Navigate to the generated APK location
2. Tap the APK file to install
3. Grant necessary permissions when prompted

## 🎨 App Features Implemented

### ✅ Core Features:
- 🌐 **Offline VPN Launch** (No internet needed for app to load)
- ⏱️ **10-second Launch Screen** with "Created by Lil Gaga Traxx09"
- 🇿🇼 **Zimbabwe Servers**: Econet, NetOne, and more
- 🛠️ **Protocol Support**: V2Ray, SSH, TCP, UDP, SlowDNS
- 🧠 **Smart Config Import/Export**
- 🎨 **Theme Modes**: Dark, Light, and System default
- 🛑 **Kill Switch** to protect data on VPN disconnect
- 🔐 **Encryption** for traffic and payloads

### ✅ Diagnostic Tools:
- 🔍 **IP Checker**
- 📱 **SIM Card Information**
- 🔑 **Host Key Viewer**
- 🆔 **HWID Display**
- 📊 **Data Usage Tracking**

### ✅ Payload Configurations:
- `econet_social_payload.txt` - Optimized for social media
- `netone_whatsapp_payload.txt` - WhatsApp-specific configuration

## 📊 Server Configuration

### Zimbabwe Servers Included:
- Econet Zimbabwe - Harare
- Econet Zimbabwe - Bulawayo
- NetOne Zimbabwe - Harare
- NetOne Zimbabwe - Bulawayo
- Econet Zimbabwe - Mutare
- NetOne Zimbabwe - Gweru
- Econet Zimbabwe - Masvingo
- NetOne Zimbabwe - Chinhoyi

## 🔐 Permissions Required

The app requires these permissions:
- `BIND_VPN_SERVICE` - For VPN functionality
- `INTERNET` - For network connectivity
- `ACCESS_NETWORK_STATE` - For network status monitoring
- `ACCESS_WIFI_STATE` - For WiFi connectivity
- `WRITE_EXTERNAL_STORAGE` - For config import/export
- `READ_EXTERNAL_STORAGE` - For config import/export
- `FOREGROUND_SERVICE` - For VPN service
- `SYSTEM_ALERT_WINDOW` - For VPN interface
- `READ_PHONE_STATE` - For SIM information
- `ACCESS_FINE_LOCATION` - For location-based features
- `ACCESS_COARSE_LOCATION` - For location-based features

## 🎯 Usage Instructions

1. **Launch**: App starts with 10-second splash screen
2. **Select Server**: Choose from Zimbabwean servers
3. **Connect**: Tap CONNECT to establish VPN
4. **Monitor**: Use diagnostic tools to monitor connection
5. **Configure**: Access settings for customization

## 🔒 Security Features

- **Kill Switch**: Blocks traffic when VPN disconnects
- **Encryption**: Traffic encryption for privacy
- **Offline Mode**: Works without internet for app launch
- **Secure Storage**: Encrypted configuration storage

## 📱 Compatibility

- **Minimum SDK**: API 21 (Android 5.0)
- **Target SDK**: API 33 (Android 13)
- **Architecture**: ARM, ARM64, x86, x86_64

## 🚀 Quick Build Commands

### Using AIDE's Built-in System:
1. Open project in AIDE
2. Tap Build → Build APK
3. Wait for completion
4. Install generated APK

### Using Build Scripts:
```bash
# Simple build
./build-apk.sh

# Gradle build (if available)
./build-with-gradle.sh
```

## 📞 Support & Contact

For technical support or feature requests:
- **Email**: vincentganiza9@gmail.com
- **Phone**: +263780078177 | +263716857999

## 🧑‍💻 Branding

> Created by Vincent Ganiza a.k.a Lil Gaga Traxx09  
> Copyright ©️ 2025  
> "Gaga is the King"

## 📋 Project Structure

```
Traxxion09TunnelPro/
├── app/
│   ├── src/main/
│   │   ├── java/com/lilgagatraxx09/traxxion09tunnelpro/
│   │   │   ├── MainActivity.java          # Main VPN interface
│   │   │   ├── SplashActivity.java        # 10-second splash screen
│   │   │   ├── VpnService.java           # VPN service implementation
│   │   │   ├── SettingsActivity.java      # App settings
│   │   │   ├── ImportExportActivity.java  # Config import/export
│   │   │   └── AboutActivity.java         # About page
│   │   ├── res/
│   │   │   ├── layout/                    # UI layouts
│   │   │   ├── values/                    # Strings, colors, styles
│   │   │   ├── drawable/                  # Icons and graphics
│   │   │   └── menu/                      # Menu resources
│   │   ├── assets/                        # Payload configurations
│   │   └── AndroidManifest.xml           # App permissions and components
│   ├── build.gradle                      # App-level build configuration
│   └── proguard-rules.pro               # Code obfuscation rules
├── build.gradle                         # Project-level build configuration
├── settings.gradle                      # Project settings
├── gradle.properties                    # Gradle properties
├── gradle/wrapper/                      # Gradle wrapper files
├── build-apk.sh                        # Build script
├── build-with-gradle.sh                # Gradle build script
├── local.properties.template            # SDK configuration template
└── README.md                           # Project documentation
```

---

**✅ All Gradle files have been added and the project is ready for AIDE compilation!** 🚀