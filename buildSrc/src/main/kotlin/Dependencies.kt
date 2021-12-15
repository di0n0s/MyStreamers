const val kotlinVersion = "1.6.0"


object Build {
    object Versions {
        const val buildToolsVersion = "7.0.4"
        const val googleServicesVersion = "4.2.0"
        const val googleHilt = "2.28-alpha"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.googleHilt}"

}

object Plugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinKapt = "kotlin-kapt"
    const val hilt = "dagger.hilt.android.plugin"

}

object AndroidSdk {
    const val min = 21
    const val compile = 31
    const val target = compile
    const val buildToolsVersion = "30.0.2"
}

object Libraries {
    private object Versions {
        //AndroidX
        const val appCompat = "1.4.0"
        const val constraintLayout = "2.1.2"

        //AndroidX ktx
        const val coreKtx = "1.7.0"
        const val fragmentKtx = "1.4.0"

        //Material
        const val material = "1.6.0-alpha01"

        //Navigation
        const val navigation = "2.3.5"

        //Lifecycle
        const val lifecycle = "2.4.0"

        //AndroidX Hilt
        const val androidXHilt = "1.0.0-alpha01"

        //Coroutines
        const val coroutines = "1.5.2"

        //JUnit
        const val jUnit = "4.13.2"

        //Robolectric
        const val robolectric = "1.4.0"

        //Mockito
        const val mockito = "4.1.0"
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
    const val hilt = "com.google.dagger:hilt-android:${Build.Versions.googleHilt}"
    const val hiltAndroidCompiler =
        "com.google.dagger:hilt-android-compiler:${Build.Versions.googleHilt}"

    // Navigation
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationTesting = "androidx.navigation:navigation-testing:${Versions.navigation}"

    //ViewModel
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    //ViewModel with Hilt
    const val hiltLifecycleViewModel =
        "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.androidXHilt}"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.androidXHilt}"

    //Coroutines
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"

    //JUnit
    const val jUnit = "junit:junit:${Versions.jUnit}"

    //Robolectric
    const val robolectric = "androidx.test:core:${Versions.robolectric}"

    //Mockito
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"

}