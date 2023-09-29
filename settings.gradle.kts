pluginManagement {
    includeBuild("plugins")
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

include(":core:datastore")
include(":core:designsystem")
include(":core:model")
include(":core:network")
include(":core:resources")
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
include(":feature:product:categories")
include(":feature:product:category")
include(":feature:product:details")
include(":feature:cart")
