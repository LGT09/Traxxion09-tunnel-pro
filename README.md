# Traxxion09 Tunnel PRO VPN

This is a **skeleton** Android project ready to be opened and built with the [AIDE](https://play.google.com/store/apps/details?id=com.aide.ui) mobile IDE. It includes minimal code for a 10-second splash screen and a placeholder main screen. You can extend it with full VPN functionality, diagnostics, and extra tools.

## How to import in AIDE

1. Copy the entire `Traxxion09 Tunnel PRO` project folder to your device storage.
2. Open **AIDE** → *Open Project* and browse to the folder.
3. Wait for AIDE to sync Gradle. If prompted, choose **Gradle build**.
4. Tap the **Run** icon to build and install the APK on your device.

## Project Structure

```
/ (root)
 ├── settings.gradle
 ├── build.gradle       (project-level)
 ├── gradle.properties
 ├── app/
     ├── build.gradle   (module)
     ├── src/main/
         ├── AndroidManifest.xml
         ├── java/com/traxxion09/tunnelpro/...
         └── res/...
```

### Next Steps

* Implement actual VPN logic using `VpnService` or third-party libraries.
* Add diagnostic activities (IP checker, SIM info, etc.).
* Package payload `.txt` files inside the `assets/` folder.
* Expand UI with dark/light/theme switcher.
* Sign the release build via **Build → Generate Signed APK** in AIDE.

---

© 2025 Vincent Ganiza (Lil Gaga Traxx09)
"Gaga is the King"