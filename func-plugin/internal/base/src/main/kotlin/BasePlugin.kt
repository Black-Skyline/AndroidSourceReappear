import org.gradle.api.Plugin
import org.gradle.api.Project
import scope.PluginScope

/**
 * @ClassName :   BasePlugin
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/3/23
 * @Version:      1.0
 * @Description : 基础的Plugin抽象类，不具有具体业务逻辑，作为业务插件的顶层超类，提供插件配置和管理的大致流程
 */
abstract class BasePlugin: Plugin<Project> {
  lateinit var project: Project
  final override fun apply(target: Project) {
    this.project = target
    PluginScope(project).configure()
    PluginScope(project).control()
  }
  abstract fun PluginScope.configure()
  open fun PluginScope.control(){}
}