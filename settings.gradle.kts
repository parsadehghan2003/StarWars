pluginManagement {
    includeBuild("gradle/build-logic")

    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "StarWars"
include(":app")
include(":base")
include(":resources")
include(":design-system")
include(":core")
include(":gateway")
include(":navigator")
include(":people")
include(":people:ui")
include(":people:domain")
include(":people:usecase")
include(":people:data-source")
include(":people:framework")
