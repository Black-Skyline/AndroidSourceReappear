package config

import org.gradle.api.Project

/**
 * @FileName :   BuildConfig
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/3/22
 * @Version:      1.0
 * @Description : TODO
 */
object BuildConfig {
  const val isRelease = false // false意味着可以单模块调试,true表示打包成app
  const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  private const val releaseApplicationId = "com.study.androidsourcereappear"
  private const val debugApplicationId = "com.study.androidsourcereappear.debug"

  const val minSdk = 24
  const val targetSdk = 33
  const val compileSdk = targetSdk

  const val versionCode = 1
  const val versionName = "1.0.0"

  val releaseAbiFilters = listOf("arm64-v8a")
  val debugAbiFilters = listOf("arm64-v8a","x86_64")

  // 排除的资源文件的列表
  val resourcesExclude = listOf(
    "LICENSE.txt",
    "META-INF/DEPENDENCIES",
    "META-INF/ASL2.0",
    "META-INF/NOTICE",
    "META-INF/LICENSE",
    "META-INF/LICENSE.txt",
    "META-INF/services/javax.annotation.processing.Processor",
    "META-INF/MANIFEST.MF",
    "META-INF/NOTICE.txt",
    "META-INF/rxjava.properties",
    "**/schemas/**", // 用于取消数据库的导出文件
  )

  // 排除的jni文件的列表
  val jniExclude = emptyList<String>()

  /**
   * 获取当前所在模块的包名
   */
  fun getApplicationId(project: Project): String {
    return when (project.name) {
      "app" -> {
        if(project.gradle.startParameter.taskNames.any { it.contains("Release") }) {
          releaseApplicationId
        } else {
          debugApplicationId
        }
      }
      else -> "com.study.androidsourcereappear.${project.name}"
    }
  }
}