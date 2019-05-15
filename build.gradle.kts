buildscript {
    repositories {
        google()
        jcenter()
        maven(url = "https://kotlin.bintray.com/kotlinx")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.2.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.31")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.3.31")
    }
}
allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "https://jitpack.io")

    }
}
