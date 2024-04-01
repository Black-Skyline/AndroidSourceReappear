plugins {
  `kotlin-dsl`
}

dependencies {
  api(project(":my_build:versions"))
  api(project(":my_build:checker"))
  // 版本号在 根目录/gradle/libs.versions.toml 中
  api(libs.android.gradlePlugin)
  api(libs.kotlin.gradlePlugin)
  // api(libs.ksp.gradlePlugin)

}
gradlePlugin {
  plugins {
    create("internal.base.library") {
      id = "internal.base.library"
      implementationClass ="BaseLibraryPlugin"
    }
    create("internal.base.application") {
      id = "internal.base.application"
      implementationClass="BaseApplicationPlugin"
    }

    create("internal.base.android") {
      id = "internal.base.android"
      implementationClass="BaseAndroidPlugin"
    }
  }
}