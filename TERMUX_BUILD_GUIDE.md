# 🚀 Build Traxxion 09 tunnel VPN in Termux

## 📱 Build Your Android VPN App Directly on Your Phone!

This guide shows you how to build **Traxxion 09 tunnel VPN** using Termux on your Android device.

## 📋 Prerequisites

### Step 1: Install Termux
Download **Termux** from F-Droid (recommended) or GitHub:
- **F-Droid**: https://f-droid.org/packages/com.termux/
- **GitHub**: https://github.com/termux/termux-app/releases

⚠️ **Don't use Google Play Store version** - it's outdated!

### Step 2: Initial Termux Setup
```bash
# Update package lists
pkg update && pkg upgrade -y

# Install essential packages
pkg install -y git wget curl unzip
```

## ⚙️ Install Build Dependencies

### Step 3: Install Java (OpenJDK)
```bash
# Install OpenJDK 17 (recommended for Android development)
pkg install -y openjdk-17

# Verify Java installation
java -version
```

### Step 4: Install Android SDK
```bash
# Create directories
mkdir -p ~/android-sdk
cd ~/android-sdk

# Download Android SDK Command Line Tools
wget https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip

# Extract SDK tools
unzip commandlinetools-linux-9477386_latest.zip
mkdir -p cmdline-tools/latest
mv cmdline-tools/* cmdline-tools/latest/
```

### Step 5: Set Environment Variables
```bash
# Add to ~/.bashrc
echo 'export ANDROID_HOME=~/android-sdk' >> ~/.bashrc
echo 'export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin' >> ~/.bashrc
echo 'export PATH=$PATH:$ANDROID_HOME/platform-tools' >> ~/.bashrc

# Reload environment
source ~/.bashrc
```

### Step 6: Install Android SDK Components
```bash
# Accept licenses
yes | sdkmanager --licenses

# Install required SDK components
sdkmanager "platform-tools" "platforms;android-33" "build-tools;33.0.1"
```

## 🛠️ Build the VPN App

### Step 7: Clone/Copy Your Project
```bash
# Create workspace directory
mkdir -p ~/workspace
cd ~/workspace

# If you have the project on GitHub:
# git clone https://github.com/yourusername/traxxion09-tunnel-vpn.git
# cd traxxion09-tunnel-vpn

# Or if you have project files, copy them to ~/workspace/
```

### Step 8: Configure SDK Location
```bash
# Create local.properties file
echo "sdk.dir=$HOME/android-sdk" > local.properties
```

### Step 9: Make Gradlew Executable
```bash
# Make gradle wrapper executable
chmod +x gradlew
```

### Step 10: Build the APK
```bash
# Clean previous builds
./gradlew clean

# Build debug APK (for testing)
./gradlew assembleDebug

# Build release APK (for distribution)
./gradlew assembleRelease
```

## 📱 Complete Build Script

Copy and paste this entire script in Termux:

```bash
#!/bin/bash
# Traxxion 09 tunnel VPN - Termux Build Script

echo "🚀 Setting up Termux for Android development..."

# Update Termux
pkg update && pkg upgrade -y

# Install dependencies
pkg install -y git wget curl unzip openjdk-17

# Setup Android SDK
mkdir -p ~/android-sdk
cd ~/android-sdk

# Download and setup SDK
if [ ! -f "commandlinetools-linux-9477386_latest.zip" ]; then
    wget https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip
    unzip commandlinetools-linux-9477386_latest.zip
    mkdir -p cmdline-tools/latest
    mv cmdline-tools/* cmdline-tools/latest/ 2>/dev/null || true
fi

# Set environment variables
export ANDROID_HOME=~/android-sdk
export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin
export PATH=$PATH:$ANDROID_HOME/platform-tools

# Add to bashrc for persistence
echo 'export ANDROID_HOME=~/android-sdk' >> ~/.bashrc
echo 'export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin' >> ~/.bashrc
echo 'export PATH=$PATH:$ANDROID_HOME/platform-tools' >> ~/.bashrc

# Install SDK components
yes | sdkmanager --licenses
sdkmanager "platform-tools" "platforms;android-33" "build-tools;33.0.1"

echo "✅ Termux setup complete!"
echo "📱 Now navigate to your project directory and run:"
echo "./gradlew assembleDebug"
```

## 🔧 Build Commands

Once everything is set up:

```bash
# Navigate to your project
cd ~/workspace/traxxion09-tunnel-vpn

# Configure SDK location
echo "sdk.dir=$HOME/android-sdk" > local.properties

# Build debug APK
./gradlew clean
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease
```

## 📍 APK Output Location

Your built APKs will be located at:
```bash
# Debug APK
~/workspace/traxxion09-tunnel-vpn/app/build/outputs/apk/debug/app-debug.apk

# Release APK  
~/workspace/traxxion09-tunnel-vpn/app/build/outputs/apk/release/app-release-unsigned.apk
```

## 📱 Install the APK

```bash
# Copy APK to accessible location
cp app/build/outputs/apk/debug/app-debug.apk ~/storage/shared/

# Install using a file manager or:
am start -a android.intent.action.VIEW -d file:///storage/emulated/0/app-debug.apk -t application/vnd.android.package-archive
```

## 🛠️ Troubleshooting

### "Permission denied" errors
```bash
chmod +x gradlew
```

### "JAVA_HOME not set"
```bash
export JAVA_HOME=$PREFIX
```

### "SDK not found"
```bash
echo "sdk.dir=$HOME/android-sdk" > local.properties
```

### Memory issues during build
```bash
# Add to gradle.properties
echo "org.gradle.jvmargs=-Xmx1024m" >> gradle.properties
```

### Storage permission issues
```bash
# Grant storage permission to Termux
termux-setup-storage
```

## 🎯 Quick Build (One Command)

After initial setup, build with one command:

```bash
cd ~/workspace/traxxion09-tunnel-vpn && echo "sdk.dir=$HOME/android-sdk" > local.properties && ./gradlew clean assembleDebug
```

## 📱 App Features (Your VPN App Includes)

✅ **App Name**: Traxxion 09 tunnel VPN  
✅ **Creator**: Vincent Ganiza LilGagaTraxx09  
✅ **Contact**: 263780078177, 263716857999  
✅ **10-second splash screen** with loading  
✅ **Zimbabwe servers**: Econet & NetOne (8 locations)  
✅ **Offline mode** capability  
✅ **Import/Export** configuration  
✅ **Professional VPN interface**  

## 💡 Pro Tips

1. **Use storage**: Run `termux-setup-storage` for file access
2. **Save battery**: Close other apps during build
3. **Free space**: Ensure 2GB+ free storage
4. **Stable connection**: Use WiFi for SDK downloads
5. **Backup APK**: Copy to external storage after build

## 🚀 Advanced: Sign Your APK

```bash
# Generate keystore
keytool -genkey -v -keystore traxxion09.keystore -alias traxxion09 -keyalg RSA -keysize 2048 -validity 10000

# Sign APK
jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore traxxion09.keystore app-release-unsigned.apk traxxion09

# Verify signature
jarsigner -verify app-release-unsigned.apk
```

## 📞 Support

**Created by**: Vincent Ganiza LilGagaTraxx09  
**Contact**: 263780078177, 263716857999

---

**🎉 Success!** You've built your Android VPN app directly on your phone using Termux!