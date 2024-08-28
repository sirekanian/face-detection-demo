plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.sirekanyan.version-checker")
}

android {
    namespace = "org.sirekanyan.facedetection"
    compileSdk = 34
    defaultConfig {
        applicationId = "org.sirekanyan.facedetection"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        allWarningsAsErrors = true
    }
    lint {
        warningsAsErrors = true
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // kotlinx
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-guava:1.8.1")

    // androidx
    implementation("androidx.activity:activity-ktx:1.9.1")
    implementation("androidx.appcompat:appcompat:1.7.0")

    // camerax
    implementation("androidx.camera:camera-camera2:1.3.4")
    implementation("androidx.camera:camera-view:1.3.4")
    implementation("androidx.camera:camera-mlkit-vision:1.4.0-rc01")

    // mlkit (standalone) // todo: create separate build type for devices without play services
    // implementation("com.google.mlkit:face-detection:16.1.7")

    // mlkit (depends on play services)
    implementation("com.google.android.gms:play-services-mlkit-face-detection:17.1.0")
}
