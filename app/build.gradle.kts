plugins {
    id("com.android.application")
    kotlin("android")
//    id("com.google.protobuf")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.seventh.compose"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
        freeCompilerArgs = listOf(
            "-P",
            "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true"
        )
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-beta07"
        kotlinCompilerVersion = "1.4.32"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.5.0")
    implementation("androidx.activity:activity-compose:1.3.0-beta01")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("androidx.compose.ui:ui:1.0.0-beta08")
    implementation("androidx.compose.material:material:1.0.0-beta08")
    implementation("androidx.compose.ui:ui-tooling:1.0.0-beta08")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha02")

    implementation("com.google.accompanist:accompanist-pager:0.10.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("com.google.accompanist:accompanist-insets:0.10.0")
    implementation("com.google.accompanist:accompanist-coil:0.10.0")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

}