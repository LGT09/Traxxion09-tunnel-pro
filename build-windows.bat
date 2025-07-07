@echo off
echo 🚀 Building Traxxion09 tunnel pro on Windows
echo ============================================

:: Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Java not found! Please install Java JDK 17
    echo Download from: https://adoptium.net/
    pause
    exit /b 1
)

echo ✅ Java found

:: Set Android SDK path
set ANDROID_HOME=%USERPROFILE%\android-sdk
set PATH=%PATH%;%ANDROID_HOME%\cmdline-tools\latest\bin;%ANDROID_HOME%\platform-tools

:: Create Android SDK directory
if not exist "%ANDROID_HOME%" mkdir "%ANDROID_HOME%"

:: Download Android SDK command line tools (requires manual download)
if not exist "%ANDROID_HOME%\cmdline-tools" (
    echo 📦 Please download Android SDK Command Line Tools:
    echo 1. Go to: https://developer.android.com/studio#command-tools
    echo 2. Download "Command line tools only" for Windows
    echo 3. Extract to: %ANDROID_HOME%\cmdline-tools\latest\
    echo 4. Run this script again
    pause
    exit /b 1
)

echo 📦 Setting up Android SDK components...
call "%ANDROID_HOME%\cmdline-tools\latest\bin\sdkmanager.bat" --licenses
call "%ANDROID_HOME%\cmdline-tools\latest\bin\sdkmanager.bat" "platform-tools" "platforms;android-33" "build-tools;33.0.0"

echo 🔨 Building Traxxion09 tunnel pro APK...
call gradlew.bat assembleDebug

if exist "app\build\outputs\apk\debug\app-debug.apk" (
    echo.
    echo 🎉 SUCCESS! APK built successfully!
    echo 📱 APK Location: app\build\outputs\apk\debug\app-debug.apk
    echo.
    echo 📥 Next Steps:
    echo 1. Copy the APK to your Android device
    echo 2. Enable 'Unknown Sources' in Android Settings
    echo 3. Install the APK
    echo 4. Enjoy Traxxion09 tunnel pro!
) else (
    echo ❌ Build failed. Check the output above for errors.
)

pause