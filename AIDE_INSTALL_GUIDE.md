# 📱 AIDE Installation Guide for Traxxion09 Tunnel PRO

## 🛡️ Quick Start Guide

### Step 1: Install AIDE
1. Download AIDE from Google Play Store
2. Open AIDE app
3. Grant necessary permissions

### Step 2: Open Project
1. In AIDE, tap the folder icon
2. Navigate to the Traxxion09TunnelPRO folder
3. Tap to open the project
4. Wait for AIDE to index the project

### Step 3: Build APK
1. Tap the hammer icon (build button)
2. Wait for the build process to complete
3. If successful, you'll see "Build completed successfully"

### Step 4: Install APK
1. Tap "Install" when prompted
2. Or find the APK in: `app/build/outputs/apk/debug/app-debug.apk`
3. Install the APK manually if needed

## 🔧 Troubleshooting

### Build Errors
- **SDK not found**: AIDE will automatically download required SDK components
- **Permission errors**: Grant storage permissions to AIDE
- **Memory issues**: Close other apps to free up RAM

### Common Issues
1. **"Gradle sync failed"**
   - Tap "Sync Project with Gradle Files"
   - Wait for sync to complete

2. **"Build failed"**
   - Check that all files are present
   - Try cleaning and rebuilding

3. **"Permission denied"**
   - Grant all requested permissions to AIDE
   - Enable "Install unknown apps" in settings

## 📋 Project Structure Verification

Before building, ensure these files exist:
```
Traxxion09TunnelPRO/
├── app/
│   ├── build.gradle ✓
│   ├── src/main/
│   │   ├── AndroidManifest.xml ✓
│   │   ├── java/com/lilgagatraxx09/traxxion09tunnelpro/
│   │   │   ├── MainActivity.java ✓
│   │   │   ├── SplashActivity.java ✓
│   │   │   ├── VpnService.java ✓
│   │   │   ├── SettingsActivity.java ✓
│   │   │   ├── AboutActivity.java ✓
│   │   │   └── ImportExportActivity.java ✓
│   │   ├── res/
│   │   │   ├── layout/ ✓
│   │   │   ├── values/ ✓
│   │   │   └── drawable/ ✓
│   │   └── assets/
│   │       ├── econet_social_payload.txt ✓
│   │       └── netone_whatsapp_payload.txt ✓
├── build.gradle ✓
├── settings.gradle ✓
└── gradle/ ✓
```

## 🎯 Features Included

### Core VPN Features
- ✅ Offline VPN Launch
- ✅ Zimbabwe Server Support (Econet, NetOne)
- ✅ Multiple Protocol Support (V2Ray, SSH, TCP, UDP, SlowDNS)
- ✅ Kill Switch Protection
- ✅ Smart Config Import/Export

### UI/UX Features
- ✅ 10-second Splash Screen
- ✅ Dark/Light Theme Support
- ✅ Modern Material Design
- ✅ Responsive Layout

### Diagnostic Tools
- ✅ IP Checker
- ✅ SIM Card Information
- ✅ Host Key Viewer
- ✅ HWID Display
- ✅ V2URI Decoder
- ✅ Logs Viewer

## 📞 Support

If you encounter issues:
- **Email**: vincentganiza9@gmail.com
- **Phone**: +263780078177 | +263716857999
- **Location**: Zimbabwe 🇿🇼

## 🎨 Branding

> Created by Vincent Ganiza a.k.a Lil Gaga Traxx09  
> Copyright ©️ 2025  
> "Gaga is the King"

---

**Note**: This APK is specifically optimized for Zimbabwean networks and includes offline capabilities for enhanced user experience.