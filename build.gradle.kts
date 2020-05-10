buildscript {
  repositories {
    google()
    gradlePluginPortal()
    jcenter()
    kotlin()

    //android()
    //gradlePluginPortal()
    //kotlin()
    //mavenCentral()
    //tensorflow()
    //trove4j()
  }

  dependencies {
    classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.0-alpha06")
    classpath("com.android.tools.build:gradle:4.1.0-alpha09")
    classpath("com.github.ben-manes:gradle-versions-plugin:0.27.0")
    classpath("com.google.android.gms:oss-licenses-plugin:0.10.2")
    classpath("com.google.gms:google-services:4.3.3")
    classpath("eu.appcom.gradle:android-versioning:1.0.2")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4-M1")
  }
}

subprojects {
  repositories {
    google()
    gradlePluginPortal()
    jcenter()
    jitpack()
    kotlin()

    //android()
    //jitpack()
    //kotlin()
    //mavenCentral()
    //tensorflow()
    //trove4j()
  }
}
