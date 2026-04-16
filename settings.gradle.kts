pluginManagement {
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

rootProject.name = "InfoPhone"
include(":app")
include(":core:common")
include(":core:database")
include(":core:navigation")
include(":core:network")
include(":core:ui")
include(":features:auth")
include(":features:call")
include(":features:chat")
include(":features:contact")
include(":features:group")
include(":features:home")
include(":features:media")
include(":features:setting")
include(":features:workmode")
include(":core:xmpp")
include(":core:xmpp")
