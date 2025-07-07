#!/bin/bash

echo "🚀 Setting up Android development environment..."

# Install Android SDK
export ANDROID_HOME=/opt/android-sdk
sudo mkdir -p $ANDROID_HOME
sudo chown -R codespace:codespace $ANDROID_HOME

# Download and install command line tools
cd /tmp
wget https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip
unzip commandlinetools-linux-9477386_latest.zip -d $ANDROID_HOME
mv $ANDROID_HOME/cmdline-tools $ANDROID_HOME/latest
mkdir -p $ANDROID_HOME/cmdline-tools
mv $ANDROID_HOME/latest $ANDROID_HOME/cmdline-tools/

# Add to PATH
echo 'export ANDROID_HOME=/opt/android-sdk' >> ~/.bashrc
echo 'export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools' >> ~/.bashrc
source ~/.bashrc

# Accept licenses and install SDK components
yes | $ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager --licenses
$ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager "platform-tools" "platforms;android-33" "build-tools;33.0.0"

# Make gradlew executable
chmod +x ./gradlew

echo "✅ Android development environment setup complete!"
echo "📱 You can now build your Traxxion09 tunnel pro app!"