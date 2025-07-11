#!/bin/bash

echo "🚀 Setting up Android SDK for Traxxion 09 tunnel VPN..."

# Set environment variables
export ANDROID_HOME=/home/codespace/android-sdk
export ANDROID_SDK_ROOT=/home/codespace/android-sdk
export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools

# Create Android SDK directory
mkdir -p $ANDROID_HOME
cd $ANDROID_HOME

# Download Android SDK command line tools
echo "📥 Downloading Android SDK Command Line Tools..."
wget -q https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip

# Extract and organize SDK tools
echo "📦 Extracting Android SDK..."
unzip -q commandlinetools-linux-9477386_latest.zip
mkdir -p cmdline-tools/latest
mv cmdline-tools/* cmdline-tools/latest/ 2>/dev/null || true

# Accept licenses and install SDK components
echo "📜 Accepting Android SDK licenses..."
yes | cmdline-tools/latest/bin/sdkmanager --licenses > /dev/null 2>&1

echo "📱 Installing Android SDK components..."
cmdline-tools/latest/bin/sdkmanager "platform-tools" "platforms;android-33" "build-tools;33.0.1" > /dev/null 2>&1

# Set up local.properties for the project
cd /workspaces/*/
echo "sdk.dir=$ANDROID_HOME" > local.properties

# Make gradlew executable
chmod +x gradlew

# Add environment variables to bashrc
echo "export ANDROID_HOME=$ANDROID_HOME" >> ~/.bashrc
echo "export ANDROID_SDK_ROOT=$ANDROID_SDK_ROOT" >> ~/.bashrc
echo "export PATH=\$PATH:\$ANDROID_HOME/cmdline-tools/latest/bin:\$ANDROID_HOME/platform-tools" >> ~/.bashrc

echo "✅ Android SDK setup complete!"
echo "🏗️ Ready to build Traxxion 09 tunnel VPN!"
echo ""
echo "Quick commands:"
echo "  🔨 Build debug APK: ./gradlew assembleDebug"
echo "  📦 Build release APK: ./gradlew assembleRelease"
echo "  🧹 Clean project: ./gradlew clean"
echo ""
echo "📱 APK output: app/build/outputs/apk/"
echo ""
echo "Created by: Vincent Ganiza LilGagaTraxx09"
echo "Contact: 263780078177, 263716857999"
