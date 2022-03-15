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

    //Hilt
    implementation(Libraries.hilt)
    kapt(Libraries.hiltAndroidCompiler)

    //ViewModel
    implementation(Libraries.viewModel)

    //Hilt with ViewModel
    implementation(Libraries.hiltLifecycleViewModel)
    kapt(Libraries.hiltCompiler)

    //Coroutines
    implementation(Libraries.coroutinesAndroid)
    testImplementation(Libraries.coroutinesTest)

    //JUnit
    testImplementation(Libraries.jUnit)

    //Mockito
    testImplementation(Libraries.mockito)
}