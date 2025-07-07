FROM openjdk:17-jdk-slim

# Install required packages
RUN apt-get update && apt-get install -y \
    wget \
    unzip \
    && rm -rf /var/lib/apt/lists/*

# Set up Android SDK
ENV ANDROID_HOME=/opt/android-sdk
ENV PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools

# Create Android SDK directory
RUN mkdir -p $ANDROID_HOME

# Download and install Android SDK command line tools
RUN wget https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip -O /tmp/commandlinetools.zip \
    && unzip -q /tmp/commandlinetools.zip -d $ANDROID_HOME \
    && mv $ANDROID_HOME/cmdline-tools $ANDROID_HOME/latest \
    && mkdir -p $ANDROID_HOME/cmdline-tools \
    && mv $ANDROID_HOME/latest $ANDROID_HOME/cmdline-tools/ \
    && rm /tmp/commandlinetools.zip

# Accept licenses and install SDK components
RUN yes | $ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager --licenses
RUN $ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager "platform-tools" "platforms;android-33" "build-tools;33.0.0"

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Make gradlew executable
RUN chmod +x gradlew

# Build the APK
RUN ./gradlew assembleDebug

# Create output directory
RUN mkdir -p /output

# Copy APK to output directory
RUN cp app/build/outputs/apk/debug/app-debug.apk /output/traxxion09-tunnel-pro.apk

# Command to copy APK out of container
CMD ["cp", "/output/traxxion09-tunnel-pro.apk", "/output/"]