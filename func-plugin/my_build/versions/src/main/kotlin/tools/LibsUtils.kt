package tools

import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.artifacts.VersionConstraint
import org.gradle.kotlin.dsl.getByType
import org.gradle.plugin.use.PluginDependency

/**
 * @FileName :    LibsUtils
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/3/24
 * @Version:      1.0
 * @Description : 获取 libs.versions.toml 下的内容并解析出来
 */

/**
 * 得到 libs.versions.toml 下的版本号
 */
fun Project.libsVersion(alias: String): VersionConstraint {
  return extensions.getByType(VersionCatalogsExtension::class)
    .named("libs").findVersion(alias).get()
}

/**
 * 得到 libs.versions.toml 下的依赖
 */
fun Project.libsLibrary(alias: String): ExternalModuleDependencyBundle {
  return extensions.getByType(VersionCatalogsExtension::class)
    .named("libs").findBundle(alias).get().get()
}

/**
 * 得到 libs.versions.toml 下的插件
 */
fun Project.libsPlugin(alias: String): PluginDependency {
  return extensions.getByType(VersionCatalogsExtension::class)
    .named("libs").findPlugin(alias).get().get()
}


/**
 * 得到 libs.versions.toml 下的 Bundle
 */
fun Project.libsBundle(alias: String): ExternalModuleDependencyBundle {
  return extensions.getByType(VersionCatalogsExtension::class)
    .named("libs").findBundle(alias).get().get()
}