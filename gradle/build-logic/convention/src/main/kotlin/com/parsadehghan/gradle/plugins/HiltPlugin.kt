package com.parsadehghan.gradle.plugins

import com.parsadehghan.gradle.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import java.util.Optional
import kotlin.text.get

class HiltPlugin : Plugin<Project> {
  override fun apply(target: Project) {
      with(target) {
          with(pluginManager) {
              apply("dagger.hilt.android.plugin")
              apply("kotlin-kapt")
          }

          dependencies {
              "implementation"(libs.findLibrary("hilt.android").get())
              "kapt"(libs.findLibrary("dagger.hilt.android.compiler").get())
          }
      }
  }
}
