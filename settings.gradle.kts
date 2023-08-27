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
    }
}

rootProject.name = "GoCart"

include(":core")
include(":ui-compose")
include(":ui-resources")
include(":app")
include(":feature:onboarding")
include(":feature:authentication")
