repositories {
    google()
    mavenCentral()
}

plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(gradleApi())
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}