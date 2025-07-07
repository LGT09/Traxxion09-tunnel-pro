#!/bin/bash

echo "🚀 Building Traxxion09 tunnel pro WITHOUT Android Studio"
echo "======================================================="

# Step 1: Install Java JDK
echo "📦 Step 1: Installing Java JDK..."
if command -v java >/dev/null 2>&1; then
    echo "✅ Java already installed: $(java -version 2>&1 | head -n 1)"
else
    echo "Installing Java JDK 17..."
    # Ubuntu/Debian
    if command -v apt-get >/dev/null 2>&1; then
        sudo apt-get update
        sudo apt-get install -y openjdk-17-jdk
    # macOS
    elif command -v brew >/dev/null 2>&1; then
        brew install openjdk@17
    # Windows (if using Git Bash with Chocolatey)
    elif command -v choco >/dev/null 2>&1; then
        choco install openjdk17
    fi
fi

# Step 2: Download Android SDK Command Line Tools
echo "📦 Step 2: Setting up Android SDK..."
export ANDROID_HOME="$HOME/android-sdk"
mkdir -p $ANDROID_HOME

# Download command line tools
cd /tmp
if [[ "$OSTYPE" == "linux-gnu"* ]]; then
    TOOLS_URL="https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip"
elif [[ "$OSTYPE" == "darwin"* ]]; then
    TOOLS_URL="https://dl.google.com/android/repository/commandlinetools-mac-9477386_latest.zip"
elif [[ "$OSTYPE" == "msys" || "$OSTYPE" == "cygwin" ]]; then
    TOOLS_URL="https://dl.google.com/android/repository/commandlinetools-win-9477386_latest.zip"
fi

echo "Downloading Android SDK tools..."
wget $TOOLS_URL -O commandlinetools.zip
unzip -q commandlinetools.zip -d $ANDROID_HOME
rm commandlinetools.zip

# Organize SDK structure
mv $ANDROID_HOME/cmdline-tools $ANDROID_HOME/latest
mkdir -p $ANDROID_HOME/cmdline-tools
mv $ANDROID_HOME/latest $ANDROID_HOME/cmdline-tools/

# Step 3: Set environment variables
echo "📦 Step 3: Setting environment variables..."
export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools

# Add to shell profile for persistence
echo "export ANDROID_HOME=$ANDROID_HOME" >> ~/.bashrc
echo "export PATH=\$PATH:\$ANDROID_HOME/cmdline-tools/latest/bin:\$ANDROID_HOME/platform-tools" >> ~/.bashrc

# Step 4: Accept licenses and install SDK components
echo "📦 Step 4: Installing Android SDK components..."
yes | $ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager --licenses
$ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager "platform-tools" "platforms;android-33" "build-tools;33.0.0"

# Step 5: Build the APK
echo "🔨 Step 5: Building Traxxion09 tunnel pro APK..."
cd "$(dirname "$0")"

# Make gradlew executable
chmod +x gradlew

# Build debug APK
./gradlew assembleDebug

# Check if build was successful
if [ -f "app/build/outputs/apk/debug/app-debug.apk" ]; then
    echo ""
    echo "🎉 SUCCESS! APK built successfully!"
    echo "📱 APK Location: app/build/outputs/apk/debug/app-debug.apk"
    echo "📏 APK Size: $(du -h app/build/outputs/apk/debug/app-debug.apk | cut -f1)"
    echo ""
    echo "📥 Next Steps:"
    echo "1. Copy the APK to your Android device"
    echo "2. Enable 'Unknown Sources' in Android Settings"
    echo "3. Install the APK"
    echo "4. Enjoy Traxxion09 tunnel pro!"
else
    echo "❌ Build failed. Check the output above for errors."
    exit 1
fi