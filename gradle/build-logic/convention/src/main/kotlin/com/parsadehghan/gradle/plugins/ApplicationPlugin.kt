package com.parsadehghan.gradle.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.parsadehghan.gradle.Versions.TARGET_SDK
import com.parsadehghan.gradle.configureKotlinAndroid
import com.parsadehghan.gradle.configureVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class ApplicationPlugin : Plugin<Project> {
  override fun apply(target: Project) {
      with(target) {
          with(pluginManager) {
              apply("com.android.application")
              apply("org.jetbrains.kotlin.android")

              apply("com.parsadehghan.gradle.android.application.compose")
              apply("com.parsadehghan.gradle.android.hilt")

          }

          extensions.configure<ApplicationExtension> {
              configureKotlinAndroid()

              defaultConfig {
                  targetSdk = TARGET_SDK
              }
              configureVersion()


              buildTypes {
                  getByName("debug") {
                      signingConfig = signingConfigs.getByName("debug")
                  }
                  release {
                      signingConfig = signingConfigs.findByName("release")
                      isShrinkResources = false
                      isMinifyEnabled = false
                      proguardFiles("proguard-rules.pro")
                  }
              }
          }

          dependencies {
              add("implementation", project(":resources"))
              add("implementation", project(":base"))
              add("implementation", project(":design-system"))
          }

      }
  }
}


