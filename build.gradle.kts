// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // Apply plugins but do not apply them immediately (apply false)
    alias(libs.plugins.android.application) apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false

}
buildscript {
    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.51.1")
    }
}

