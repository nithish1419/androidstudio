buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Add this line for Google services
        classpath("com.google.gms:google-services:4.3.15") // Update to the latest version
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
