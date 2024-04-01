import org.jetbrains.kotlin.fir.declarations.builder.buildScript

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.android.library") version "8.2.0" apply false
}
tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}

buildscript {
    repositories {
        google()
        mavenCentral() // 优先 MavenCentral，一是：github CI 下不了 aliyun 依赖；二是：开 VPN 访问 aliyun 反而变慢了
        maven("https://jitpack.io")
//        jcenter() // 部分依赖需要
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/repository/google") }
    }
    dependencies {
        // 版本号在 根目录/gradle/libs.versions.toml 中
        classpath(libs.android.gradlePlugin)
        classpath(libs.kotlin.gradlePlugin)
        classpath(libs.ksp.gradlePlugin)
    }
}
