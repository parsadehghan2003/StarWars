plugins {
  id("com.parsadehghan.gradle.android.library")
  id("com.parsadehghan.gradle.android.library.compose")
}

android {
  namespace = "com.parsadehghan.starwars.core.designsystem"
}

dependencies {

  implementation(project(":base"))

  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.material3)
  implementation(libs.androidx.lifecycle.viewmodel.compose.runtime)
  implementation(libs.androidx.hilt.navigation.compose)
}
