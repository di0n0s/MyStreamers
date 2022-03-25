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

    //Retrofit
    implementation(Libraries.retrofit)
    implementation(Libraries.okhttp3)
    implementation(Libraries.loginInterceptor)

    //Gson
    implementation(Libraries.gson)
    implementation(Libraries.gsonConverter)

    //Hilt
    implementation(Libraries.hilt)
    kapt(Libraries.hiltAndroidCompiler)

    //JUnit
    testImplementation(Libraries.jUnit)

    //Mockito
    testImplementation(Libraries.mockito)
}