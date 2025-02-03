plugins {
    id("com.parsadehghan.gradle.android.application")
}

android {
    namespace = "com.parsadehghan.StarWars"

    defaultConfig {
        applicationId = "com.parsadehghan.StarWars"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":navigator"))
    implementation(project(":people:ui"))
    implementation(project(":design-system"))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.viewmodel.compose.runtime)
    implementation(libs.androidx.hilt.navigation.compose)
}