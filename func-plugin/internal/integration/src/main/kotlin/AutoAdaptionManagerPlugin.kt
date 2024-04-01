import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import scope.PluginScope

/**
 * @ClassName :   AutoAdaptionManagerPlugin
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/3/28
 * @Version:      1.0
 * @Description : 该类作用未定，暂时搁置
 */
class AutoAdaptionManagerPlugin: BasePlugin() {
  override fun PluginScope.configure() {

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
    } && !name.startsWith("api_") && !name.startsWith("lib_") // api 模块、lib 模块不开启
  }

  // 允许执行单模块调试
  private fun Project.doDebugModule() {
    apply(plugin = "internal.integration.module-debug")
  }

  // 不允许执行单模块调试
  private fun Project.cancelDebugModule() {
    println("${project.name} 的单模块调试被取消！")
    apply(plugin = "internal.integration.module-middleware")
  }
}