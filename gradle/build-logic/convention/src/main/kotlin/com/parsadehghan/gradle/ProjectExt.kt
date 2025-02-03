package com.parsadehghan.gradle

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs
  get(): VersionCatalog = extensions
    .getByType<VersionCatalogsExtension>()
    .named("libs")

fun ApplicationExtension.configureVersion() {
  defaultConfig.versionCode = 1
  defaultConfig.versionName = "1.0.0"
}