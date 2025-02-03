plugins {
    id("com.parsadehghan.gradle.jvm.library")

}
dependencies{
    "implementation"(libs.gson)
    "implementation"(project(":base"))
    "implementation"(project(":core"))
    "implementation"(libs.coroutines)

}