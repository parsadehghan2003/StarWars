plugins {
    id("com.parsadehghan.gradle.android.library.framework")
}

android {
    namespace = "com.parsadehghan.starwars.gateway"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.gson)
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(project(":base"))
    implementation(project(":core"))
    implementation(libs.logging.interceptor)
}
