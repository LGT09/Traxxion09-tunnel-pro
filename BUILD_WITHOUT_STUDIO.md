# 🔨 Build Traxxion09 tunnel pro WITHOUT Android Studio

## 🚀 Quick Methods (Choose One)

### **Method 1: One-Click Script (Linux/Mac/WSL)**
```bash
chmod +x build-without-studio.sh && ./build-without-studio.sh
```

### **Method 2: Windows Batch File**
```cmd
build-windows.bat
```

### **Method 3: GitHub Actions (Online)**
1. Push code to GitHub
2. Go to Actions tab
3. Click "Build Traxxion09 tunnel pro APK"
4. Download the APK from artifacts

### **Method 4: Docker (Any Platform)**
```bash
# Build container and APK
docker build -t traxxion09-builder .
docker run --rm -v $(pwd)/output:/output traxxion09-builder

# APK will be in ./output/traxxion09-tunnel-pro.apk
```

### **Method 5: GitHub Codespaces**
```bash
chmod +x build_apk.sh && ./build_apk.sh
```

---

## 📋 Manual Setup (Step by Step)

### **Requirements**
- ☑️ Java JDK 17+
- ☑️ Android SDK Command Line Tools
- ☑️ Internet connection (for downloads)

### **Step 1: Install Java JDK**

**Ubuntu/Debian:**
```bash
sudo apt-get update
sudo apt-get install -y openjdk-17-jdk
```

**macOS:**
```bash
brew install openjdk@17
```

**Windows:**
- Download from: https://adoptium.net/
- Install and add to PATH

### **Step 2: Download Android SDK**

**Linux/Mac:**
```bash
export ANDROID_HOME="$HOME/android-sdk"
mkdir -p $ANDROID_HOME
cd /tmp

# Download command line tools
wget https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip
unzip commandlinetools-linux-9477386_latest.zip -d $ANDROID_HOME

# Organize structure
mv $ANDROID_HOME/cmdline-tools $ANDROID_HOME/latest
mkdir -p $ANDROID_HOME/cmdline-tools
mv $ANDROID_HOME/latest $ANDROID_HOME/cmdline-tools/
```

**Windows:**
1. Download from: https://developer.android.com/studio#command-tools
2. Extract to: `%USERPROFILE%\android-sdk\cmdline-tools\latest\`

### **Step 3: Set Environment Variables**

**Linux/Mac:**
```bash
export ANDROID_HOME="$HOME/android-sdk"
export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools

# Make permanent
echo 'export ANDROID_HOME="$HOME/android-sdk"' >> ~/.bashrc
echo 'export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools' >> ~/.bashrc
```

**Windows:**
```cmd
set ANDROID_HOME=%USERPROFILE%\android-sdk
set PATH=%PATH%;%ANDROID_HOME%\cmdline-tools\latest\bin;%ANDROID_HOME%\platform-tools
```

### **Step 4: Install SDK Components**
```bash
# Accept licenses
sdkmanager --licenses

# Install required components
sdkmanager "platform-tools" "platforms;android-33" "build-tools;33.0.0"
```

### **Step 5: Build the APK**
```bash
# Navigate to project directory
cd traxxion09-tunnel-pro

# Make gradlew executable (Linux/Mac)
chmod +x gradlew

# Build debug APK
./gradlew assembleDebug         # Linux/Mac
# OR
gradlew.bat assembleDebug       # Windows
```

### **Step 6: Find Your APK**
```
📁 Location: app/build/outputs/apk/debug/app-debug.apk
📏 Size: ~15-25 MB
📱 Ready to install on Android!
```

---

## 🌐 Online Build Services (No Setup Required)

### **1. GitHub Actions (Recommended)**
- ✅ Free for public repositories
- ✅ Automatic builds on code changes
- ✅ APK downloads from releases

### **2. GitLab CI/CD**
- ✅ Free tier available
- ✅ Similar to GitHub Actions

### **3. CircleCI**
- ✅ Free tier available
- ✅ Fast builds

### **4. GitHub Codespaces**
- ✅ Complete development environment
- ✅ Pre-configured Android setup

---

## 🐳 Docker Build (Isolated Environment)

Create and run the Docker container:
```bash
# Build the Docker image
docker build -t traxxion09-builder .

# Run and extract APK
mkdir -p output
docker run --rm -v $(pwd)/output:/output traxxion09-builder

# Your APK is now in ./output/traxxion09-tunnel-pro.apk
```

**Benefits:**
- ✅ No local Android SDK installation
- ✅ Consistent build environment
- ✅ Works on any platform with Docker

---

## 🛠️ Alternative Build Tools

### **1. Bazel (Google's Build Tool)**
```bash
# Install Bazel
# Build with Bazel (requires additional setup)
bazel build //app:traxxion09
```

### **2. Buck (Facebook's Build Tool)**
```bash
# Install Buck
# Build with Buck (requires additional setup)
buck build //app:traxxion09
```

### **3. Gradle Wrapper (Simplest)**
```bash
# Just use the included wrapper
./gradlew assembleDebug
```

---

## 🔧 Troubleshooting

### **Problem: "Java not found"**
**Solution:**
```bash
# Check Java installation
java -version

# Install Java if missing
sudo apt-get install openjdk-17-jdk   # Ubuntu
brew install openjdk@17              # macOS
```

### **Problem: "Android SDK not found"**
**Solution:**
```bash
# Check ANDROID_HOME
echo $ANDROID_HOME

# Set if missing
export ANDROID_HOME="$HOME/android-sdk"
```

### **Problem: "Build failed"**
**Solution:**
```bash
# Clean and rebuild
./gradlew clean
./gradlew assembleDebug

# Check for detailed errors
./gradlew assembleDebug --stacktrace
```

### **Problem: "Permission denied"**
**Solution:**
```bash
# Make gradlew executable
chmod +x gradlew

# Or use gradle directly
gradle assembleDebug
```

---

## 📱 Installing the APK

1. **Transfer APK** to your Android device
2. **Enable Unknown Sources**:
   - Settings → Security → Unknown Sources
   - Or Settings → Apps → Special Access → Install Unknown Apps
3. **Tap the APK** file to install
4. **Launch** Traxxion09 tunnel pro!

---

## 🎯 What You Get

Your built APK includes:
- ✅ **App Name**: Traxxion09 tunnel pro
- ✅ **Creator**: Vincent Ganiza LilGagaTraxx09
- ✅ **Contact**: 263780078177, 263716857999
- ✅ **10-Second Splash**: Professional loading
- ✅ **8 Zimbabwe Servers**: Econet & NetOne
- ✅ **Offline Mode**: Works without internet
- ✅ **Import/Export**: Configuration management

## 🆘 Need Help?

**Contact the Developer:**
- **Name**: Vincent Ganiza LilGagaTraxx09
- **Phone**: 263780078177 or 263716857999
- **App**: Traxxion09 tunnel pro