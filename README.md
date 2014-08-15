LibVLC on Android demo
======================

This is a sample application demonstrating the use of libVLC on Android.

This repository contains only sample code, and the libVLC on Android sdk (*vlc-sdk.7z*) must be built and placed into the target application's source code directory.

Getting started
---------------
Requirements:

* An up-to-date Linux distribution.
  * This is because VLC (and VLC for Android) uses autotools and make in order to be portable to so many diverse platforms.
  * [VirtualBox](http://www.virtualbox.org/) is always an option if Linux is not available natively on the computer.
* Android NDK r9
* Android SDK
* [Any other pieces of software](https://wiki.videolan.org/AndroidCompile#Requirements) listed in the compile guide.

First build VLC for Android:

1. Follow [AndroidCompile](https://wiki.videolan.org/AndroidCompile) and build VLC for Android.
2. In the same build directory, run ```make .sdk```
3. Copy everything (libs and src) inside the **vlc-sdk/** directory to the application source directory (the one containing AndroidManifest.xml).

Now, just build your application, in Eclipse, ant, or any other tool of your choice.

Debugging
---------

Having problems making some media play?

Use [**adb logcat**](http://developer.android.com/tools/help/logcat.html) to read the debug logs. This is analogous to Tools → Messages (verbosity 2) in desktop VLC.

In addition, try different configurations of hardware acceleration, chroma, and deblocking. Android devices vary greatly in their capabilities.