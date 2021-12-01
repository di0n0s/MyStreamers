const val kotlinVersion = "1.6.0"


object Build {
    object Versions {
        const val buildToolsVersion = "7.0.3"
        const val googleServicesVersion = "4.2.0"
        const val hiltVersion = "2.28-alpha"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"

}

object Plugins {
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinKapt = "kotlin-kapt"
    const val hilt = "dagger.hilt.android.plugin"

}

object AndroidSdk {
    const val min = 21
    const val compile = 30
    const val target = compile
    const val buildToolsVersion = "30.0.0"
}

object Libraries {
    private object Versions {
        //AndroidX
        const val appCompat = "1.2.0"
        const val constraintLayout = "2.0.4"

        //AndroidX ktx
        const val coreKtx = "1.3.2"
        const val fragmentKtx = "1.3.3"

        //Material
        const val material = "1.3.0"
    }

    //Kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"

    //AndroidX
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"

    //AndroidX Ktx
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

    //Material
    const val material = "com.google.android.material:material:${Versions.material}"

    //Hilt
    const val hilt = "com.google.dagger:hilt-android:${Build.Versions.hiltVersion}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Build.Versions.hiltVersion}"
}