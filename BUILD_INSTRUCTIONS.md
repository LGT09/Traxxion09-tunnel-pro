# Traxxion 09 tunnel VPN - Build Instructions

## Overview
This guide will help you build the **Traxxion 09 tunnel VPN** Android application created by **LilGagaTraxx09**.

## Prerequisites

Before building the app, make sure you have the following installed:

### 1. Java Development Kit (JDK)
- **JDK 8 or higher** (JDK 11 recommended)
- Download from: https://adoptium.net/ or https://www.oracle.com/java/technologies/javase-downloads.html

### 2. Android SDK (Optional but recommended)
- **Android Studio** (includes Android SDK): https://developer.android.com/studio
- Or **Android SDK Command Line Tools**: https://developer.android.com/studio/command-line

### 3. Environment Variables
Set the following environment variables:
```bash
export JAVA_HOME=/path/to/your/jdk
export ANDROID_HOME=/path/to/your/android/sdk
export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools
```

## Build Methods

### Method 1: Using Gradle Wrapper (Recommended)

#### For Linux/macOS:
```bash
# Navigate to the project directory
cd /path/to/traxxion09-tunnel-vpn

# Make gradlew executable (if not already)
chmod +x gradlew

# Clean the project
./gradlew clean

# Build debug APK
./gradlew assembleDebug

# Build release APK (unsigned)
./gradlew assembleRelease
```

#### For Windows:
```cmd
# Navigate to the project directory
cd C:\path\to\traxxion09-tunnel-vpn

# Clean the project
gradlew.bat clean

# Build debug APK
gradlew.bat assembleDebug

# Build release APK (unsigned)
gradlew.bat assembleRelease
```

### Method 2: Using Android Studio

1. **Open Android Studio**
2. **Import Project**: File → Open → Select the project folder
3. **Wait for sync**: Let Android Studio sync the project
4. **Build APK**: Build → Build Bundle(s) / APK(s) → Build APK(s)

## Output Locations

After successful build, you'll find the APK files in:

- **Debug APK**: `app/build/outputs/apk/debug/app-debug.apk`
- **Release APK**: `app/build/outputs/apk/release/app-release-unsigned.apk`

## App Features

The built app includes:

### ✅ Core Features
- **10-second splash screen** with loading animations
- **Zimbabwe server support** (Econet & NetOne)
- **Offline mode capability**
- **Import/Export configuration**
- **Multiple server locations**:
  - Econet Zimbabwe - Harare
  - Econet Zimbabwe - Bulawayo
  - NetOne Zimbabwe - Harare
  - NetOne Zimbabwe - Bulawayo
  - Econet Zimbabwe - Mutare
  - NetOne Zimbabwe - Gweru
  - Econet Zimbabwe - Masvingo
  - NetOne Zimbabwe - Chinhoyi

### ✅ Creator Information
- **Created by**: Vincent Ganiza LilGagaTraxx09
- **Contact**: 263780078177, 263716857999

## Troubleshooting

### Common Issues

#### 1. "JAVA_HOME is not set"
```bash
# Set JAVA_HOME environment variable
export JAVA_HOME=/path/to/your/jdk
```

#### 2. "SDK location not found"
Create `local.properties` file in the root directory:
```properties
sdk.dir=/path/to/your/android/sdk
```

#### 3. "Permission denied: ./gradlew"
```bash
chmod +x gradlew
```

#### 4. Build fails with dependency issues
```bash
./gradlew clean
./gradlew --refresh-dependencies assembleDebug
```

## Signing the APK (For Release)

To create a signed release APK:

1. **Generate a keystore**:
```bash
keytool -genkey -v -keystore traxxion09.keystore -alias traxxion09 -keyalg RSA -keysize 2048 -validity 10000
```

2. **Add signing config to `app/build.gradle`**:
```gradle
android {
    signingConfigs {
        release {
            storeFile file('traxxion09.keystore')
            storePassword 'your_password'
            keyAlias 'traxxion09'
            keyPassword 'your_password'
        }
    }
    buildTypes {
        release {
            signingConfig signingConfigs.release
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}
```

3. **Build signed APK**:
```bash
./gradlew assembleRelease
```

## Installation

After building, you can install the APK on your Android device:

```bash
# Install debug APK
adb install app/build/outputs/apk/debug/app-debug.apk

# Install release APK
adb install app/build/outputs/apk/release/app-release.apk
```

## Additional Commands

```bash
# Run tests
./gradlew test

# Generate build report
./gradlew build --scan

# List all available tasks
./gradlew tasks

# Build and install on connected device
./gradlew installDebug
```

## Support

For any build issues or questions, contact:
- **Vincent Ganiza LilGagaTraxx09**
- **Phone**: 263780078177, 263716857999

---

**Note**: This VPN app is designed specifically for Zimbabwe and includes servers from major local providers (Econet & NetOne). The app supports offline mode and includes comprehensive configuration import/export features.