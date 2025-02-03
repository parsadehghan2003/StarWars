package com.parsadehghan.gradle.plugins

import com.parsadehghan.gradle.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.support.delegates.DependencyHandlerDelegate
import java.util.Optional
import kotlin.apply

class FeaturePlugin : Plugin<Project> {
  override fun apply(target: Project) {
      with(target) {
          pluginManager.apply {
              apply("com.parsadehghan.gradle.android.library")
              apply("com.parsadehghan.gradle.android.hilt")
          }

          dependencies {
              add("implementation", project(":core"))
              add("implementation", project(":resources"))
              add("implementation", project(":navigator"))
              add("implementation", project(":design-system"))

              add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
              add("implementation", libs.findLibrary("androidx.lifecycle.viewmodel.compose.runtime").get())
              add("implementation", libs.findLibrary("androidx.lifecycle.viewmodel.compose").get())
          }
      }
  }
}
