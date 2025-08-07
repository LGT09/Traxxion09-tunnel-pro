# 🔧 Gradle Tasks for Traxxion09 Tunnel PRO VPN

## 📱 Project Information
- **App Name**: Traxxion09 Tunnel PRO
- **Developer**: Vincent Ganiza (Lil Gaga Traxx09)
- **Contact**: vincentganiza9@gmail.com
- **Phone**: +263780078177 | +263716857999
- **Location**: Zimbabwe 🇿🇼

## 🚀 Available Gradle Tasks

### 📋 Information Tasks
```bash
# Print app information
./gradlew printAppInfo

# Show all available tasks
./gradlew tasks

# Show project dependencies
./gradlew dependencies
```

### 🧹 Clean Tasks
```bash
# Clean all build outputs
./gradlew clean

# Clean specific build types
./gradlew cleanDebug
./gradlew cleanRelease
```

### 🔨 Build Tasks
```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Build all variants
./gradlew assemble

# Build and install debug APK
./gradlew installDebug
```

### 📊 Testing Tasks
```bash
# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest

# Run all tests
./gradlew check
```

### 🔍 Analysis Tasks
```bash
# Run lint checks
./gradlew lint

# Run lint for debug build
./gradlew lintDebug

# Run lint for release build
./gradlew lintRelease
```

### 📦 Distribution Tasks
```bash
# Generate signed APK
./gradlew assembleRelease

# Generate APK bundle
./gradlew bundleRelease

# Generate debug APK
./gradlew assembleDebug
```

### 🛠️ Development Tasks
```bash
# Show project structure
./gradlew projects

# Show build scan
./gradlew buildScan

# Show build info
./gradlew buildInfo
```

## 🎯 Quick Build Commands

### For AIDE Development:
```bash
# Quick debug build
./gradlew assembleDebug

# Clean and rebuild
./gradlew clean assembleDebug

# Build with app info
./gradlew printAppInfo assembleDebug
```

### For Release Build:
```bash
# Build release APK
./gradlew assembleRelease

# Build with lint checks
./gradlew lintRelease assembleRelease
```

## 📱 APK Locations

After successful build, APK files will be located at:
- **Debug APK**: `app/build/outputs/apk/debug/app-debug.apk`
- **Release APK**: `app/build/outputs/apk/release/app-release.apk`

## 🔧 Build Configuration

### App Configuration:
- **Package**: com.lilgagatraxx09.traxxion09tunnelpro
- **Version**: 1.0.0 (1)
- **Min SDK**: API 21 (Android 5.0)
- **Target SDK**: API 33 (Android 13)
- **Compile SDK**: API 33

### Build Features:
- ✅ View Binding enabled
- ✅ Build Config enabled
- ✅ ProGuard rules configured
- ✅ Lint checks configured
- ✅ DEX optimization enabled

## 🚀 Features Included

### ✅ Core Features:
- 🌐 Offline VPN Launch
- ⏱️ 10-second Splash Screen
- 🇿🇼 Zimbabwe Servers
- 🛠️ Protocol Support (V2Ray, SSH, TCP, UDP, SlowDNS)
- 🧠 Smart Config Import/Export
- 🎨 Theme Modes (Dark, Light, System)
- 🛑 Kill Switch
- 🔐 Encryption

### ✅ Diagnostic Tools:
- 🔍 IP Checker
- 📱 SIM Card Information
- 🔑 Host Key Viewer
- 🆔 HWID Display
- 📊 Data Usage Tracking

### ✅ Payload Configurations:
- `econet_social_payload.txt`
- `netone_whatsapp_payload.txt`

## 📞 Support

For technical support or feature requests:
- **Email**: vincentganiza9@gmail.com
- **Phone**: +263780078177 | +263716857999

## 🧑‍💻 Branding

> Created by Vincent Ganiza a.k.a Lil Gaga Traxx09  
> Copyright ©️ 2025  
> "Gaga is the King"

---

**Ready to build! Use these Gradle tasks to compile your Traxxion09 Tunnel PRO VPN app.** 🚀