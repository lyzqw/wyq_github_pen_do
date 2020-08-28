plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(28)
    buildToolsVersion("28.0.3")

    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    val kotlin_version = "1.3.72"
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version")

    //携程
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.8")
    //appcompat
    api("androidx.appcompat:appcompat:1.1.0")
    api("androidx.core:core-ktx:1.3.1")
    api("androidx.constraintlayout:constraintlayout:1.1.3")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    //api("androidx.lifecycle:lifecycle-extensions:2.2.0")
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.2.0")
    api("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    api("com.tencent.bugly:crashreport:3.2.1")
    api("com.tencent.bugly:nativecrashreport:3.7.1")
    api("androidx.recyclerview:recyclerview:1.1.0")
    api("androidx.paging:paging-runtime:3.0.0-alpha05")

    //room
    api("androidx.room:room-runtime:2.3.0-alpha02")
    kapt("androidx.room:room-compiler:2.3.0-alpha02")
    //koin
    api("org.koin:koin-android:2.1.6")
    api("org.koin:koin-androidx-scope:2.1.6")
    api("org.koin:koin-androidx-viewmodel:2.1.6")
    //log
    api("com.jakewharton.timber:timber:4.7.1")
    //tools
    api("com.blankj:utilcodex:1.29.0")
    debugApi("com.squareup.leakcanary:leakcanary-android:2.4")
    //键盘
    api("cn.dreamtobe.kpswitch:library:1.6.1")

    //glide
    api("com.github.bumptech.glide:glide:4.11.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.11.0")
    api("com.github.bumptech.glide:okhttp3-integration:4.9.0")


    testImplementation("junit:junit:4.13")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}
