plugins {
  id("com.parsadehghan.gradle.android.feature")
  id("com.parsadehghan.gradle.android.library.compose")
}

android {
  namespace = "com.parsadehghan.starwars.people.ui"
}

dependencies {


  implementation(project(":people:data-source"))
  implementation(project(":people:framework"))
  implementation(project(":people:domain"))
  implementation(project(":base"))
  implementation(project(":people:usecase"))

  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.material3)
}
