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
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.0")
}
