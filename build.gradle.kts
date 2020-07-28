buildscript {
    val kotlin_version by extra("1.3.72")
    repositories {
        google()
        jcenter()
    }
    dependencies {
        val kotlin_version = "1.3.72"
        classpath("com.android.tools.build:gradle:3.6.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}
