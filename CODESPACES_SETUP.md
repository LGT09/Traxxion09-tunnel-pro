# 🚀 Traxxion09 tunnel pro - GitHub Codespaces Setup

## Quick Setup Instructions

### Step 1: Open in Codespaces
1. Go to your GitHub repository
2. Click **"Code"** → **"Codespaces"** → **"Create codespace on main"**
3. Wait for the environment to set up (takes 2-3 minutes)

### Step 2: Build Your APK
Once Codespaces loads, run this command in the terminal:

```bash
chmod +x build_apk.sh && ./build_apk.sh
```

### Step 3: Download APK
1. The APK will be created at: `app/build/outputs/apk/debug/app-debug.apk`
2. Right-click the APK file in VS Code file explorer
3. Select **"Download..."**
4. Save to your computer

### Step 4: Install on Android Device
1. Transfer the APK to your Android phone
2. Enable **"Unknown Sources"** in Settings
3. Tap the APK file to install
4. Enjoy your **Traxxion09 tunnel pro** VPN!

## Manual Build Commands

If the automatic script doesn't work, try these commands:

```bash
# Set up environment
export ANDROID_HOME=/opt/android-sdk
export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools

# Install Gradle
sudo apt-get update && sudo apt-get install -y gradle

# Build APK
gradle assembleDebug
```

## Troubleshooting

### Problem: "Gradle not found"
**Solution:**
```bash
sudo apt-get update
sudo apt-get install -y gradle openjdk-17-jdk
```

### Problem: "Android SDK not found"
**Solution:**
```bash
# Run the setup script
bash .devcontainer/setup-android.sh
```

### Problem: "Build failed"
**Solution:**
```bash
# Clean and rebuild
gradle clean
gradle assembleDebug
```

## App Features Reminder

Your **Traxxion09 tunnel pro** includes:
- ✅ 10-second splash screen with "Created by LilGagaTraxx09"
- ✅ 8 Zimbabwean servers (Econet & NetOne)
- ✅ Offline mode functionality
- ✅ Import/Export configuration
- ✅ Contact info: 263780078177, 263716857999
- ✅ Professional UI with Material Design

## Need Help?

Contact the developer:
- **Vincent Ganiza LilGagaTraxx09**
- **Phone:** 263780078177 or 263716857999