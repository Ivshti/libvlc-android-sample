LibVLC on Android demo
======================

This is a sample application demonstrating the use of libVLC on Android.

This repository contains only sample code, and the libVLC on Android sdk (the LibVLC AAR) must be built and placed into the root project folder.

Getting started
---------------
Requirements:

* An up-to-date Linux distribution.
  * This is because VLC (and VLC for Android) uses autotools and make in order to be portable on so many diverse platforms.
  * [VirtualBox](http://www.virtualbox.org/) is always an option if Linux is not available natively on the computer.
* Android NDK r10e
* Android SDK
* Android Studio
* [Other software](https://wiki.videolan.org/AndroidCompile#Requirements) listed in the compile guide.


1. First build VLC for Android, by following [AndroidCompile](https://wiki.videolan.org/AndroidCompile).
2. Copy ```libvlc/build/outputs/aar/libvlc-3.0.0.aar``` to the same directory as this README and **rename** it to ```libvlc.aar```.
3. Either build in Android Studio, or from the command line with Gradle: ```./gradlew assembleDebug```

The APK build output can be found under ```build/outputs/apk/```.

See ```build.gradle``` for build-related details.

Debugging
---------

Having problems making some media play?

Use [**adb logcat**](http://developer.android.com/tools/help/logcat.html) to read the debug logs. This is analogous to Tools → Messages (verbosity 2) in desktop VLC.

In addition, try different configurations of hardware acceleration, chroma, and deblocking. Android devices vary greatly in their capabilities.
