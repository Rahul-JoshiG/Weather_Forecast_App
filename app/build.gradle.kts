import org.gradle.kotlin.dsl.libs
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
    implementation (libs.hilt.android)
    annotationProcessor (libs.hilt.android.compiler)
    annotationProcessor (libs.hilt.compiler)

    // Retrofit for HTTP requests
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.google.gson)

    // Lifecycle components
    implementation(libs.lifecycle.viewmodel.ktx)

    //size
    implementation (libs.sdp.android)
    implementation (libs.ssp.android)

    //glide for image
    implementation (libs.glide)
    annotationProcessor (libs.compiler)

    //room database
    implementation (libs.room.runtime)
    annotationProcessor (libs.room.compiler)

    //navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    //lotti animation
    implementation (libs.lottie)
}