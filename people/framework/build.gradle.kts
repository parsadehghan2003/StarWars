plugins {
    id("com.parsadehghan.gradle.android.library.framework")
}

android {
    namespace = "com.parsadehghabn.starwars.people.framework"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(project(":base"))
    implementation(project(":gateway"))
    implementation(project(":core"))
    implementation(project(":people:domain"))
    implementation(project(":people:data-source"))

    testImplementation(libs.junit)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockito.core)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.core.testing)
}
