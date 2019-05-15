plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "org.jetbrains.kotlin.mpp_app_android"
        minSdkVersion(23)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        val release by getting {
            isMinifyEnabled = false
        }
    }

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }
}

dependencies {
    implementation(project(":common"))

    implementation(kotlin("stdlib"))
    implementation("com.android.support:appcompat-v7:28.0.0")
    implementation("com.android.support.constraint:constraint-layout:1.1.3")

    implementation("com.eden:AndroidClog:2.0.7")
    implementation("com.eden:Clog4j:2.0.7")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.1.1")

    testImplementation("com.android.support.test:runner:1.0.2")
}
