plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(28)
    buildToolsVersion("28")

    defaultConfig {
        applicationId = "com.wyq_github_pen_do"
        minSdkVersion(23)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        ndk {
            // 设置支持的SO库架构
            abiFilters("armeabi")
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    dataBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(path = ":common"))


    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test:core:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    androidTestImplementation("org.mockito:mockito-core:3.3.3")
}
