plugins {
    id("com.parsadehghan.gradle.jvm.library")

}
dependencies{
    "implementation"(libs.gson)
    "implementation"(project(":base"))
    "implementation"(project(":core"))
    "implementation"(project(":people:data-source"))
    "implementation"(libs.coroutines)
    "implementation"(libs.hilt.core)

}