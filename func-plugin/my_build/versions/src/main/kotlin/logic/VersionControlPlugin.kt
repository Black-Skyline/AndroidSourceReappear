package logic

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @ClassName :   VersionControlPlugin
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/3/23
 * @Version:      1.0
 * @Description : TODO
 */
class VersionControlPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    println("插件使用成功")
  }
}