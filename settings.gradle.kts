pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "Challenge Chapter 7"
include(":app")
//include(":domain")
//include(":di")
include(":data")
//include(":presentation")
include(":domain")
include(":presentation")
include(":di")
