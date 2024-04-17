import org.gradle.kotlin.dsl.apply
import scope.PluginScope

/**
 * @ClassName :   ModuleMiddlewareManagerPlugin
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/3/27
 * @Version:      1.0
 * @Description : TODO
 */
class ModuleMiddlewareManagerPlugin: BasePlugin() {
  override fun PluginScope.configure() {
    val projectName: String = project.name
    val pluginId = when {
      projectName == "app" -> "app"
      projectName == "Handler" -> "internal.module.middleware"
      projectName.startsWith("module_") -> "internal.module.middleware"
      projectName.startsWith("lib_") -> "internal.library.common"
      projectName.startsWith("api_") -> "internal.library.common"  // 后续请实现实现api类模块的脚本依赖管理插件
      else -> throw Exception("出现未知类型模块: name = $projectName   dir = $projectDir\n请为该模块声明对应的依赖插件！")
    }

    apply(plugin = pluginId)

  }
}