package com.parsadehghan.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.support.delegates.DependencyHandlerDelegate
import kotlin.apply

class DomainPlugin : Plugin<Project> {
  override fun apply(target: Project) {
      with(target) {
          pluginManager.apply {
              apply("com.parsadehghan.gradle.android.library")
              apply("com.parsadehghan.gradle.android.hilt")
          }

          dependencies {
              add("implementation", project(":core"))
          }
      }
  }
}
