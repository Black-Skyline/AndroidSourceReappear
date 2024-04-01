@file:Suppress("UnstableApiUsage")

include(":Handler")

pluginManagement {
  includeBuild("func-plugin")
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
    maven("https://jitpack.io")
  }
}
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    maven("https://maven.aliyun.com/repository/public")
    maven("https://maven.aliyun.com/repository/google")
  }
}

rootProject.name = "AndroidSourceReappear"
include(":app")
include(":Handler")
