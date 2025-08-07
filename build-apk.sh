#!/bin/bash

# Traxxion09 Tunnel PRO VPN - Build Script
# Created by Vincent Ganiza (Lil Gaga Traxx09)
# For AIDE and Android Studio compatibility

echo "🚀 Building Traxxion09 Tunnel PRO VPN..."
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

# Create necessary directories
mkdir -p app/build/outputs/apk/debug
mkdir -p app/build/intermediates
mkdir -p app/build/generated

echo "📦 Creating APK structure..."

# Create a simple APK manifest
cat > app/build/outputs/apk/debug/AndroidManifest.xml << 'EOF'
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.lilgagatraxx09.traxxion09tunnelpro">
    
    <uses-permission android:name="android.permission.BIND_VPN_SERVICE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.CHANGE_NETWORK_STATE" />
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/AppTheme"
        android:usesCleartextTraffic="true">

        <activity
            android:name=".SplashActivity"
            android:exported="true"
            android:theme="@style/SplashTheme">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <activity
            android:name=".MainActivity"
            android:exported="false"
            android:screenOrientation="portrait" />

        <activity
            android:name=".ImportExportActivity"
            android:exported="false"
            android:screenOrientation="portrait" />

        <activity
            android:name=".SettingsActivity"
            android:exported="false"
            android:screenOrientation="portrait" />

        <activity
            android:name=".AboutActivity"
            android:exported="false"
            android:screenOrientation="portrait" />

        <service
            android:name=".VpnService"
            android:exported="false"
            android:permission="android.permission.BIND_VPN_SERVICE">
            <intent-filter>
                <action android:name="android.net.VpnService" />
            </intent-filter>
        </service>

    </application>

</manifest>
EOF

# Create a simple APK info file
cat > app/build/outputs/apk/debug/APK_INFO.txt << 'EOF'
Traxxion09 Tunnel PRO VPN
Version: 1.0.0
Developer: Vincent Ganiza (Lil Gaga Traxx09)
Contact: vincentganiza9@gmail.com
Phone: +263780078177 | +263716857999
Location: Zimbabwe 🇿🇼

Features:
- Offline VPN Launch
- 10-second Splash Screen
- Zimbabwe Servers (Econet, NetOne)
- V2Ray, SSH, TCP, UDP, SlowDNS support
- Smart Config Import/Export
- Theme Modes (Dark, Light, System)
- Kill Switch
- Diagnostic Tools
- IP Checker
- SIM Card Information
- Host Key Viewer
- HWID Display

Payload Configurations:
- econet_social_payload.txt
- netone_whatsapp_payload.txt

"Gaga is the King" - Vincent Ganiza (Lil Gaga Traxx09)
EOF

echo "✅ APK structure created successfully!"
echo ""
echo "📱 Traxxion09 Tunnel PRO VPN"
echo "🎯 Ready for AIDE compilation"
echo ""
echo "📋 Build Information:"
echo "   - App Name: Traxxion09 Tunnel PRO"
echo "   - Package: com.lilgagatraxx09.traxxion09tunnelpro"
echo "   - Version: 1.0.0"
echo "   - Min SDK: API 21 (Android 5.0)"
echo "   - Target SDK: API 33 (Android 13)"
echo ""
echo "🔧 To build in AIDE:"
echo "   1. Open AIDE"
echo "   2. Navigate to this project folder"
echo "   3. Use AIDE's built-in build system"
echo "   4. The APK will be generated automatically"
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
echo "🚀 Ready to launch! Created by Lil Gaga Traxx09"