# 📱 Termux Quick Setup - Traxxion 09 tunnel VPN

## 🚀 Build Your VPN App on Your Android Phone!

### 📥 Step 1: Install Termux
- Download from **F-Droid**: https://f-droid.org/packages/com.termux/
- ⚠️ **DON'T use Google Play version** (it's broken)

### 🔧 Step 2: Copy & Paste Setup Commands

Open Termux and run these commands **one by one**:

```bash
# Update Termux packages
pkg update && pkg upgrade -y
```

```bash
# Install Java and tools
pkg install -y openjdk-17 git wget curl unzip
```

```bash
# Setup Android SDK directory
mkdir -p ~/android-sdk && cd ~/android-sdk
```

```bash
# Download Android SDK tools (120MB download)
wget https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip
```

```bash
# Extract and organize SDK
unzip commandlinetools-linux-9477386_latest.zip
mkdir -p cmdline-tools/latest
mv cmdline-tools/* cmdline-tools/latest/
```

```bash
# Set environment variables
export ANDROID_HOME=~/android-sdk
export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin
```

```bash
# Make settings permanent
echo 'export ANDROID_HOME=~/android-sdk' >> ~/.bashrc
echo 'export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin' >> ~/.bashrc
```

```bash
# Install Android SDK components
yes | sdkmanager --licenses
sdkmanager "platform-tools" "platforms;android-33" "build-tools;33.0.1"
```

### 📁 Step 3: Get Your Project Files

```bash
# Create workspace
mkdir -p ~/workspace && cd ~/workspace
```

**Now copy your Traxxion 09 tunnel VPN project files to** `~/workspace/`

### 🔨 Step 4: Build the App

```bash
# Navigate to your project
cd ~/workspace

# Configure Android SDK location
echo "sdk.dir=$HOME/android-sdk" > local.properties
```

```bash
# Make build script executable
chmod +x gradlew
```

```bash
# Build your VPN app
./gradlew clean && ./gradlew assembleDebug
```

### 📱 Step 5: Find Your APK

Your built app will be at:
```
~/workspace/app/build/outputs/apk/debug/app-debug.apk
```

Copy to phone storage:
```bash
termux-setup-storage
cp app/build/outputs/apk/debug/app-debug.apk ~/storage/shared/
```

## 🎯 Quick Build Script (All-in-One)

Run this **single command** to set up everything:

```bash
pkg update && pkg upgrade -y && pkg install -y openjdk-17 git wget curl unzip && mkdir -p ~/android-sdk && cd ~/android-sdk && wget https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip && unzip commandlinetools-linux-9477386_latest.zip && mkdir -p cmdline-tools/latest && mv cmdline-tools/* cmdline-tools/latest/ && export ANDROID_HOME=~/android-sdk && export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin && echo 'export ANDROID_HOME=~/android-sdk' >> ~/.bashrc && echo 'export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin' >> ~/.bashrc && yes | sdkmanager --licenses && sdkmanager "platform-tools" "platforms;android-33" "build-tools;33.0.1" && echo "✅ Setup complete! Copy your project to ~/workspace/ then run: cd ~/workspace && echo 'sdk.dir=$HOME/android-sdk' > local.properties && chmod +x gradlew && ./gradlew assembleDebug"
```

## 🏗️ Your VPN App Features

✅ **Name**: Traxxion 09 tunnel VPN  
✅ **Creator**: Vincent Ganiza LilGagaTraxx09 (263780078177, 263716857999)  
✅ **10-second splash screen** with "Created by LilGagaTraxx09"  
✅ **Zimbabwe servers**: Econet & NetOne (8 locations)  
✅ **Offline mode** support  
✅ **Import/Export** configurations  
✅ **Professional VPN interface**  

## 🛠️ Common Issues & Solutions

### ❌ "Permission denied"
```bash
chmod +x gradlew
```

### ❌ "SDK not found"
```bash
echo "sdk.dir=$HOME/android-sdk" > local.properties
```

### ❌ "Not enough memory"
```bash
echo "org.gradle.jvmargs=-Xmx1024m" >> gradle.properties
```

### ❌ Can't access files
```bash
termux-setup-storage
```

## 📲 Install Your Built APK

1. Copy APK to phone storage (command above)
2. Open file manager → Find `app-debug.apk`
3. Tap to install → Allow "Install from unknown sources"
4. ✅ **Your VPN app is installed!**

## 💾 System Requirements

- **Android 7+** (API 24+)
- **2GB+ free storage**
- **Stable internet** (for downloads)
- **Termux from F-Droid** (not Play Store)

## 🎉 Success!

You've built **Traxxion 09 tunnel VPN** directly on your Android phone using Termux!

**Contact**: Vincent Ganiza LilGagaTraxx09  
**Phone**: 263780078177, 263716857999