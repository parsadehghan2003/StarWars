plugins {
    id("com.parsadehghan.gradle.jvm.library")

}
dependencies{
    "implementation"(libs.gson)
    "implementation"(project(":base"))
    "implementation"(project(":people:domain"))
    "implementation"(project(":core"))
    "implementation"(libs.coroutines)
    "implementation"(libs.hilt.core)

}