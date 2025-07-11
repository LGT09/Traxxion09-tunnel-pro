#!/bin/bash

echo "☁️ Building Traxxion 09 tunnel VPN in GitHub Codespaces..."
echo "Created by: Vincent Ganiza LilGagaTraxx09 (263780078177, 263716857999)"
echo ""

# Check if we're in Codespaces
if [ "$CODESPACES" = "true" ]; then
    echo "✅ Running in GitHub Codespaces"
    echo "🔧 Environment: $CODESPACE_NAME"
else
    echo "ℹ️  Not in Codespaces (running locally)"
fi

echo ""
echo "🧹 Cleaning previous builds..."
./gradlew clean

echo ""
echo "🔨 Building debug APK..."
./gradlew assembleDebug

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ Build successful!"
    echo "📱 APK location: app/build/outputs/apk/debug/app-debug.apk"
    echo ""
    echo "📥 To download APK:"
    echo "1. Navigate to app/build/outputs/apk/debug/ in VS Code file explorer"
    echo "2. Right-click on app-debug.apk"
    echo "3. Select 'Download'"
    echo ""
    
    # Copy APK to workspace root for easy access
    cp app/build/outputs/apk/debug/app-debug.apk ./traxxion09-tunnel-vpn-debug.apk
    echo "📋 Copied APK to workspace root: traxxion09-tunnel-vpn-debug.apk"
    
    echo ""
    echo "🎉 Your Traxxion 09 tunnel VPN is ready!"
    echo "📱 Features included:"
    echo "   ✅ 10-second splash screen"
    echo "   ✅ Zimbabwe servers (Econet & NetOne)"
    echo "   ✅ Offline mode capability"
    echo "   ✅ Import/Export configurations"
    echo "   ✅ Professional VPN interface"
else
    echo ""
    echo "❌ Build failed!"
    echo "🔍 Check the error messages above"
    echo "💡 Try running: ./gradlew assembleDebug --info"
fi

echo ""
echo "📞 Contact: Vincent Ganiza LilGagaTraxx09"
echo "📱 Phone: 263780078177, 263716857999"
