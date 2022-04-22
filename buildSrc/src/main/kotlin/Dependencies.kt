const val kotlinVersion = "1.6.10"


object Build {
    object Versions {
        const val buildToolsVersion = "7.1.2"
        const val googleServicesVersion = "4.2.0"
        const val googleHilt = "2.41"
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
    const val buildToolsVersion = "30.0.3"
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

        //Glide
        const val glide = "4.13.0"

        //Retrofit
        const val retrofit = "2.9.0"

        //Okhttp3
        const val okhttp3 = "4.9.3"

        //Gson
        const val gson = "2.9.0"

        //Paging
        const val paging = "3.1.1"

        //Navigation
        const val navigation = "2.3.5"

        //Lifecycle
        const val lifecycle = "2.4.0"

        //Coroutines
        const val coroutines = "1.5.2"

        //JUnit
        const val jUnit = "4.13.2"

        //Robolectric
        const val robolectric = "4.7.3"

        //Mockito
        const val mockito = "4.1.0"

        //Fragment testing
        const val fragmentTesting = "1.4.0"

        //AndroidXTest
        const val androidXTest = "1.4.0"

        //Espresso
        const val espresso = "3.1.0"

        //JUnit Android
        const val jUnitAndroid = "1.1.1"

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

    //Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    //Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"

    //Okhttp3
    const val okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
    const val loginInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"

    //Gson
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.gson}"

    //Paging
    const val paging = "androidx.paging:paging-runtime:${Versions.paging}"

    //Hilt
    const val hilt = "com.google.dagger:hilt-android:${Build.Versions.googleHilt}"
    const val hiltAndroidCompiler =
        "com.google.dagger:hilt-android-compiler:${Build.Versions.googleHilt}"
    const val hiltAndroidTesting =
        "com.google.dagger:hilt-android-testing:${Build.Versions.googleHilt}"

    // Navigation
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationTesting = "androidx.navigation:navigation-testing:${Versions.navigation}"
    const val navigationCommonKtx =
        "androidx.navigation:navigation-common-ktx:${Versions.navigation}"

    //ViewModel
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    //Coroutines
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"

    //JUnit
    const val jUnit = "junit:junit:${Versions.jUnit}"

    //Mockito
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"

    //Robolectric
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val testCoreKtx = "androidx.test:core-ktx:${Versions.androidXTest}"


    //UI Testing
    const val testRunner = "androidx.test:runner:${Versions.androidXTest}"
    const val testRules = "androidx.test:rules:${Versions.androidXTest}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val webViewEspresso = "androidx.test.espresso:espresso-web:${Versions.espresso}"
    const val jUnitAndroid = "androidx.test.ext:junit:${Versions.jUnitAndroid}"
    const val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragmentTesting}"

}