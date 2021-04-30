const val kotlinVersion = "1.4.32"


object Build {
    object Versions {
        const val buildToolsVersion = "4.1.3"
        const val googleServicesVersion = "4.2.0"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"

}

object Pluggins {
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"

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
    const val ktxCore = "androidx.core:core-ktx:${Versions.coreKtx}"

    //Material
    const val material = "com.google.android.material:material:${Versions.material}"
}