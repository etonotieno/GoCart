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
include(":feature:address")
include(":feature:favorites")
include(":feature:offers")
include(":feature:payments")
include(":feature:orders")
include(":feature:services")
include(":feature:settings")
include(":feature:homefeed")
