import org.gradle.configurationcache.extensions.capitalized

plugins {
  `kotlin-dsl`
}

dependencies {
  api(libs.android.gradlePlugin)
  api(libs.kotlin.gradlePlugin)
}
gradlePlugin {
  plugins.register("my_build.version-control") {
      id = "my_build.version-control"
      implementationClass = "logic.VersionControlPlugin"
    }
}