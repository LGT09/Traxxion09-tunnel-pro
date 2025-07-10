# 🚀 Visual Studio Code Setup - Traxxion 09 tunnel VPN

## 📱 Build & Install Your Android VPN App with VS Code

This guide shows you how to set up Visual Studio Code to build and install **Traxxion 09 tunnel VPN** with full IDE support.

## 📋 Prerequisites

### Step 1: Install Required Software

**1. Visual Studio Code**
- Download from: https://code.visualstudio.com/
- Install the latest stable version

**2. Java Development Kit (JDK)**
- **JDK 8 or higher** (JDK 11 recommended)
- Download from: https://adoptium.net/

**3. Android SDK**
- **Option A**: Install Android Studio (includes SDK)
  - Download: https://developer.android.com/studio
- **Option B**: Command Line Tools only
  - Download: https://developer.android.com/studio/command-line

## 🔧 VS Code Setup

### Step 2: Install Required Extensions

**Automatic Installation** (Recommended):
1. Open the project in VS Code
2. VS Code will prompt to install recommended extensions
3. Click "Install" when prompted

**Manual Installation**:
```
- Extension Pack for Java (vscode-java-pack)
- Language Support for Java (redhat.java)
- Gradle for Java (vscjava.vscode-gradle)
- Kotlin Language (mathiasfrohlich.kotlin)
- JSON Support (ms-vscode.vscode-json)
```

### Step 3: Configure Android SDK

**Create `local.properties` file**:
```bash
# In VS Code terminal (Ctrl+` or Cmd+`)
echo "sdk.dir=/path/to/your/android/sdk" > local.properties
```

**Example SDK paths**:
- **Windows**: `sdk.dir=C:\\Users\\YourName\\AppData\\Local\\Android\\Sdk`
- **macOS**: `sdk.dir=/Users/YourName/Library/Android/sdk`
- **Linux**: `sdk.dir=/home/yourname/Android/Sdk`

## 🚀 Building & Installing with VS Code

### Method 1: Using Command Palette (Recommended)

**1. Open Command Palette**: `Ctrl+Shift+P` (Windows/Linux) or `Cmd+Shift+P` (macOS)

**2. Available Build Commands**:
```
> Tasks: Run Task
  🧹 Clean Project
  🔨 Build Debug APK
  📦 Build Release APK
  📱 Install Debug APK
  🚀 Build and Install
  🧪 Run Tests
```

**3. Quick Build & Install**:
- Press `Ctrl+Shift+P`
- Type: "Tasks: Run Task"
- Select: "🚀 Build and Install"

### Method 2: Using Keyboard Shortcuts

**Default Build**: `Ctrl+Shift+B` (Windows/Linux) or `Cmd+Shift+B` (macOS)
- This runs "🔨 Build Debug APK" by default

### Method 3: Using Integrated Terminal

**Open Terminal**: `Ctrl+\`` (backtick) or `Cmd+\``

```bash
# Clean project
./gradlew clean

# Build debug APK
./gradlew assembleDebug

# Build and install on connected device
./gradlew clean assembleDebug installDebug
```

## 📱 Available VS Code Tasks

| Task | Command | Description |
|------|---------|-------------|
| 🧹 Clean Project | `./gradlew clean` | Cleans build artifacts |
| 🔨 Build Debug APK | `./gradlew assembleDebug` | Builds debug APK |
| 📦 Build Release APK | `./gradlew assembleRelease` | Builds release APK |
| 📱 Install Debug APK | `./gradlew installDebug` | Installs on device |
| 🚀 Build and Install | `./gradlew clean assembleDebug installDebug` | Complete workflow |
| 🧪 Run Tests | `./gradlew test` | Runs unit tests |
| 📋 List Gradle Tasks | `./gradlew tasks` | Shows all tasks |
| 🔍 Check Dependencies | `./gradlew dependencies` | Shows dependencies |
| 📱 Open APK Location | Opens APK output folder | Quick access to APKs |

## 🎯 Quick Start Guide

### 1. Open Project in VS Code
```bash
# Navigate to project directory
cd /path/to/traxxion09-tunnel-vpn

# Open in VS Code
code .

# Or open the workspace file
code Traxxion-09-tunnel-VPN.code-workspace
```

### 2. Install Extensions
- VS Code will prompt to install recommended extensions
- Click "Install" to install the Java and Android development extensions

### 3. Configure SDK
```bash
# Set your Android SDK path
echo "sdk.dir=/your/android/sdk/path" > local.properties
```

### 4. Build Your VPN App
- Press `Ctrl+Shift+B` to build
- Or use Command Palette: `Ctrl+Shift+P` → "Tasks: Run Task" → "🚀 Build and Install"

## 🔧 Project Features in VS Code

### ✅ **Intelligent Code Completion**
- Java autocomplete for VPN classes
- Android API suggestions
- Import organization

### ✅ **Debugging Support**
- Set breakpoints in Java code
- Debug VPN service logic
- Inspect variable values

### ✅ **Integrated Terminal**
- Run Gradle commands
- ADB device management
- Git integration

### ✅ **Error Detection**
- Real-time syntax checking
- Build error highlighting
- Android manifest validation

### ✅ **Project Explorer**
- Organized file tree
- Quick navigation
- File nesting for cleaner view

## 📍 APK Output Locations

After successful build:
```
📁 app/build/outputs/apk/
├── 📁 debug/
│   └── 📱 app-debug.apk
└── 📁 release/
    └── 📱 app-release-unsigned.apk
```

**Quick Access**: Use the "📱 Open APK Location" task to open the APK folder directly.

## 🛠️ Troubleshooting

### ❌ "Java extension not working"
**Solution**:
1. Install "Extension Pack for Java"
2. Restart VS Code
3. Open Command Palette → "Java: Reload Projects"

### ❌ "Gradle import failed"
**Solution**:
```bash
# In VS Code terminal
./gradlew clean
```
Then: Command Palette → "Java: Reload Projects"

### ❌ "SDK location not found"
**Solution**:
```bash
echo "sdk.dir=/path/to/your/android/sdk" > local.properties
```

### ❌ "Build failed with errors"
**Solution**:
1. Check the "Problems" panel (View → Problems)
2. Fix any red-underlined errors in code
3. Run "🧹 Clean Project" task

### ❌ "Device not found for installation"
**Solution**:
1. Enable USB debugging on Android device
2. Connect via USB
3. Run: `adb devices` in terminal to verify connection

## 🎨 VS Code Workspace Features

### **File Nesting**
- Gradle files are grouped together
- Build artifacts are hidden
- Clean project structure

### **Code Formatting**
- Automatic Java formatting on save
- Import organization
- Consistent code style

### **Search & Navigation**
- Fast file search: `Ctrl+P`
- Symbol search: `Ctrl+Shift+O`
- Global search: `Ctrl+Shift+F`

## 🚀 Advanced Features

### **Debugging Android App**
1. Build and install debug APK
2. Set breakpoints in Java code
3. Attach debugger to running process

### **Git Integration**
- Source control panel
- Commit changes
- Branch management

### **Extensions Marketplace**
- Android development tools
- Gradle enhanced support
- XML formatting tools

## 📱 Your VPN App Features

✅ **App Name**: Traxxion 09 tunnel VPN  
✅ **Creator**: Vincent Ganiza LilGagaTraxx09  
✅ **Contact**: 263780078177, 263716857999  
✅ **10-second splash screen** with loading animation  
✅ **Zimbabwe servers**: Econet & NetOne (8 locations)  
✅ **Offline mode** capability  
✅ **Import/Export** configuration  
✅ **Professional VPN interface**  

## 🎯 Quick Commands Summary

| Action | Shortcut | Command |
|--------|----------|---------|
| Build | `Ctrl+Shift+B` | Default build task |
| Terminal | `Ctrl+\`` | Open integrated terminal |
| Command Palette | `Ctrl+Shift+P` | Access all commands |
| File Explorer | `Ctrl+Shift+E` | Open file explorer |
| Search | `Ctrl+Shift+F` | Global search |
| Problems | `Ctrl+Shift+M` | View build errors |

## 🎉 Success!

You've successfully set up Visual Studio Code for Android development! Your **Traxxion 09 tunnel VPN** app is now ready to be built and installed with full IDE support.

**Created by**: Vincent Ganiza LilGagaTraxx09  
**Phone**: 263780078177, 263716857999

---

**💡 Pro Tip**: Use the workspace file `Traxxion-09-tunnel-VPN.code-workspace` to quickly open the project with all settings pre-configured!