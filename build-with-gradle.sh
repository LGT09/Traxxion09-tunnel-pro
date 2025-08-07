#!/bin/bash

# Traxxion09 Tunnel PRO VPN - Gradle Build Script
# Created by Vincent Ganiza (Lil Gaga Traxx09)
# For AIDE and Android Studio compatibility

echo "🚀 Building Traxxion09 Tunnel PRO VPN with Gradle..."
echo "📱 App: Traxxion09 Tunnel PRO"
echo "👨‍💻 Developer: Vincent Ganiza (Lil Gaga Traxx09)"
echo "📧 Contact: vincentganiza9@gmail.com"
echo "📞 Phone: +263780078177 | +263716857999"
echo "🌍 Location: Zimbabwe 🇿🇼"
echo ""

# Check if we're in the right directory
if [ ! -f "app/build.gradle" ]; then
    echo "❌ Error: app/build.gradle not found!"
    echo "Please run this script from the project root directory."
    exit 1
fi

# Check if gradlew exists
if [ ! -f "gradlew" ]; then
    echo "❌ Error: gradlew not found!"
    echo "Please ensure the Gradle wrapper is present."
    exit 1
fi

# Make gradlew executable
chmod +x gradlew

echo "📦 Setting up Gradle build environment..."

# Create necessary directories
mkdir -p app/build/outputs/apk/debug
mkdir -p app/build/intermediates
mkdir -p app/build/generated

echo "🔧 Running Gradle tasks..."

# Clean previous builds
echo "🧹 Cleaning previous builds..."
./gradlew clean

# Print app information
echo "📋 App Information:"
./gradlew printAppInfo

# Build debug APK
echo "🔨 Building debug APK..."
./gradlew assembleDebug

# Check if build was successful
if [ $? -eq 0 ]; then
    echo ""
    echo "✅ Build completed successfully!"
    echo ""
    echo "📱 APK Information:"
    echo "   - App Name: Traxxion09 Tunnel PRO"
    echo "   - Package: com.lilgagatraxx09.traxxion09tunnelpro"
    echo "   - Version: 1.0.0"
    echo "   - Min SDK: API 21 (Android 5.0)"
    echo "   - Target SDK: API 33 (Android 13)"
    echo ""
    echo "📦 APK Location:"
    echo "   - Debug: app/build/outputs/apk/debug/app-debug.apk"
    echo ""
    echo "🎨 Features included:"
    echo "   ✅ 10-second splash screen"
    echo "   ✅ Zimbabwe server selection"
    echo "   ✅ VPN connection interface"
    echo "   ✅ Kill switch functionality"
    echo "   ✅ Diagnostic tools"
    echo "   ✅ Theme support (Dark/Light/System)"
    echo "   ✅ Import/Export functionality"
    echo "   ✅ Settings and About pages"
    echo "   ✅ Payload configurations"
    echo ""
    echo "🔧 To install in AIDE:"
    echo "   1. Copy the APK to your device"
    echo "   2. Enable 'Install from unknown sources'"
    echo "   3. Tap the APK file to install"
    echo "   4. Grant necessary permissions"
    echo ""
    echo "🚀 Ready to launch! Created by Lil Gaga Traxx09"
else
    echo ""
    echo "❌ Build failed! Please check the error messages above."
    echo ""
    echo "🔧 Troubleshooting:"
    echo "   1. Ensure Android SDK is properly installed"
    echo "   2. Check that all dependencies are available"
    echo "   3. Verify Gradle wrapper is executable"
    echo "   4. Check internet connection for dependency downloads"
    exit 1
fi