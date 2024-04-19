@file:Suppress("UnstableApiUsage")
pluginManagement {
  includeBuild(".")
  repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
    maven("https://maven.aliyun.com/repository/public")
    maven("https://maven.aliyun.com/repository/google")
    maven("https://jitpack.io")
  }
}

dependencyResolutionManagement {
  repositories {
    google()
    mavenCentral() // 优先 MavenCentral，一是：github CI 下不了 aliyun 依赖；二是：开 VPN 访问 aliyun 反而变慢了
    maven("https://maven.aliyun.com/repository/public")
    maven("https://maven.aliyun.com/repository/google")
    maven("https://jitpack.io")
  }

  // 开启 versionCatalogs 功能
  versionCatalogs {
    // 名称固定写成 libs
    create("libs") {
      // 这个 libs.versions.toml 名字也必须固定，不能改成其他的
      from(files("../gradle/libs.versions.toml"))
    }
  }

}

rootProject.name = "func-plugin"

// 基础的 内部 插件
include(":internal")
include(":internal:app")
include(":internal:base")
include(":internal:library")
include(":internal:module")
include(":internal:integration")

// 用于 构建 的插件
include(":my_build")
include(":my_build:versions")
include(":my_build:checker")