import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import scope.PluginScope

/**
 * @ClassName :   ModuleDebugManagerPlugin
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/3/27
 * @Version:      1.0
 * @Description : 单模块调试应当引入的插件的实现类
 */
class ModuleDebugManagerPlugin: BasePlugin() {
  override fun PluginScope.configure() {
    if(isAllowDebugModule()) {
      doDebugModule()
    } else {
      cancelDebugModule()
    }
  }
  /**
   * 检查当前项目包是否允许单模块调试
   */
  private fun Project.isAllowDebugModule(): Boolean {
    return !project.gradle.startParameter.taskNames.any {
      // 注意：这里面的是取反，即满足下面条件的不执行单模块调试
      it.contains("assembleRelease")
              || it.contains("assembleDebug") && !it.contains(project.name)
              || it == "publishModuleCachePublicationToMavenRepository" // 本地缓存任务
              || it == "cacheToLocalMaven"
              || it == "channelRelease"
              || it == "channelDebug"
    } && !name.startsWith("api_") && !name.startsWith("lib_") && name != "app"// api 模块、lib 模块与 app模块 不开启
  }

  /**
   * 对当前项目包执行单模块调试
   */
  private fun Project.doDebugModule() {
    apply(plugin = "internal.module.single-debug")
  }

  /**
   * 取消对当前项目包的单模块调试
   */
  private fun Project.cancelDebugModule() {
    println("${project.name} 的单模块调试被取消！")
    apply(plugin = "internal.integration.module-middleware")
  }
}