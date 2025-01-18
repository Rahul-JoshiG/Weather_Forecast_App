import java.util.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.android.application)
    id ("dagger.hilt.android.plugin")

}

android {
    namespace = "com.rahuljoshi.weatherforecastapp"
    compileSdk = 35

    // Load properties from local.properties
    val file = rootProject.file("local.properties")
    val properties = Properties().apply {
        if (file.exists()) {
            load(FileInputStream(file))
        }
    }

    defaultConfig {
        applicationId = "com.rahuljoshi.weatherforecastapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        // Load API key from properties
        buildConfigField("String", "API_KEY", properties.getProperty("API_KEY")) // Add default value for safety

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            // Customize debug settings if necessary
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        buildConfig = true
        dataBinding = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //dagger hilt
    implementation ("com.google.dagger:hilt-android:2.51.1")
    annotationProcessor ("com.google.dagger:hilt-android-compiler:2.51.1")
    annotationProcessor ("androidx.hilt:hilt-compiler:1.2.0")

    // Retrofit for HTTP requests
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation("com.google.code.gson:gson:2.11.0")

    // Lifecycle components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")

    // Optional Coroutine support if you're using it in your Java code
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")

    //size
    implementation ("com.intuit.sdp:sdp-android:1.1.1")
    implementation ("com.intuit.ssp:ssp-android:1.1.1")

    //glide for image
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.14.2")

    //room database
    implementation ("com.google.code.gson:gson:2.11.0")
    implementation ("androidx.room:room-runtime:2.6.1")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")
}