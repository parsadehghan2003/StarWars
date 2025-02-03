import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  `kotlin-dsl`
}

group = "com.parsadehghan.build"

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
  compilerOptions {
    jvmTarget = JvmTarget.JVM_17
  }
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)
  compileOnly(libs.ksp.gradlePlugin)

  compileOnly(libs.compose.gradlePlugin)
}

tasks {
  validatePlugins {
    enableStricterValidation = true
    failOnWarning = true
  }
}

gradlePlugin {
  plugins {
    register("jvmLibrary") {
      id = "com.parsadehghan.gradle.jvm.library"
      implementationClass = "com.parsadehghan.gradle.plugins.LibraryJvmPlugin"
    }
    register("androidLibrary") {
      id = "com.parsadehghan.gradle.android.library"
      implementationClass = "com.parsadehghan.gradle.plugins.LibraryPlugin"
    }
    register("androidApplicationCompose") {
      id = "com.parsadehghan.gradle.android.application.compose"
      implementationClass = "com.parsadehghan.gradle.plugins.ApplicationComposePlugin"
    }
    register("androidApplication") {
      id = "com.parsadehghan.gradle.android.application"
      implementationClass = "com.parsadehghan.gradle.plugins.ApplicationPlugin"
    }
    register("androidFeature") {
      id = "com.parsadehghan.gradle.android.feature"
      implementationClass = "com.parsadehghan.gradle.plugins.FeaturePlugin"
    }
    register("androidHilt") {
      id = "com.parsadehghan.gradle.android.hilt"
      implementationClass = "com.parsadehghan.gradle.plugins.HiltPlugin"
    }
    register("androidLibraryCompose") {
      id = "com.parsadehghan.gradle.android.library.compose"
      implementationClass = "com.parsadehghan.gradle.plugins.LibraryComposePlugin"
    }
    register("androidFramework") {
      id = "com.parsadehghan.gradle.android.library.framework"
      implementationClass = "com.parsadehghan.gradle.plugins.FrameworkPlugin"
    }
  }
}
