plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(28)
    buildToolsVersion("28.0.3")

    defaultConfig {
        applicationId = "com.wyq_github_pen_do"
        minSdkVersion(23)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    packagingOptions {
        exclude("META-INF/LICENSE*")
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(path = ":common"))


    androidTestImplementation("junit:junit:4.13")
    androidTestImplementation("androidx.test:rules:1.2.0")
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test:core:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.2.0")
    androidTestImplementation("org.mockito:mockito-core:3.3.3")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.4")


    androidTestImplementation("org.robolectric:robolectric:4.4")
}
