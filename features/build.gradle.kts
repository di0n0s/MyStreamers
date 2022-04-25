plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.hilt)
}

android {
    compileSdk = AndroidSdk.compile

    defaultConfig {
        minSdk = AndroidSdk.min
        testInstrumentationRunner = "com.dionos.mystreamers.HiltTestRunner"
    }
    buildFeatures {
        viewBinding = true
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    //Visibility
    implementation(project(":core"))
    implementation(project(":user"))
    testImplementation(project(":test-utils"))

    //Kotlin
    implementation(Libraries.kotlinStdLib)

    //AndroidX ktx
    implementation(Libraries.coreKtx)
    implementation(Libraries.fragmentKtx) {
        exclude(module = "lifecycle-viewmodel-ktx")
        exclude(module = "lifecycle-viewmodel")
    }

    //AndroidX
    implementation(Libraries.appCompat) {
        exclude(module = "lifecycle-viewmodel")
    }
    implementation(Libraries.constraintLayout)

    //Android
    implementation(Libraries.material) {
        exclude(group = "androidx.transition", module = "transition")
    }

    //Glide
    implementation(Libraries.glide)
    annotationProcessor(Libraries.glideCompiler)

    //Paging
    implementation(Libraries.paging)

    //Hilt
    implementation(Libraries.hilt)
    kapt(Libraries.hiltAndroidCompiler)

    //ViewModel
    implementation(Libraries.viewModel)

    //Retrofit
    implementation(Libraries.retrofit)
    implementation(Libraries.okhttp3)
    implementation(Libraries.loginInterceptor)

    //Gson
    implementation(Libraries.gson)
    implementation(Libraries.gsonConverter)

    //Coroutines
    implementation(Libraries.coroutinesAndroid)
    testImplementation(Libraries.coroutinesTest)

    //JUnit
    testImplementation(Libraries.jUnit)
//    testImplementation("androidx.arch.core:core-testing:2.1.0")
//    testImplementation("app.cash.turbine:turbine:0.7.0")


    //Robolectric
    testImplementation(Libraries.robolectric)
    testImplementation(Libraries.testCoreKtx)

    //Mockito
    testImplementation(Libraries.mockito)
}