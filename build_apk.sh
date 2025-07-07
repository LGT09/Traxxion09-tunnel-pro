#!/bin/bash

echo "🚀 Building Traxxion09 tunnel pro APK..."

# Set environment variables
export ANDROID_HOME=/opt/android-sdk
export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools

# Install Gradle if not present
if ! command -v gradle &> /dev/null; then
    echo "📦 Installing Gradle..."
    sudo apt-get update
    sudo apt-get install -y gradle
fi

# Build the APK
echo "🔨 Building debug APK..."
gradle assembleDebug

# Check if APK was created
if [ -f "app/build/outputs/apk/debug/app-debug.apk" ]; then
    echo "✅ APK built successfully!"
    echo "📱 APK location: app/build/outputs/apk/debug/app-debug.apk"
    echo "📥 Download this file to install on your Android device"
else
    echo "❌ APK build failed. Check the output above for errors."
fi