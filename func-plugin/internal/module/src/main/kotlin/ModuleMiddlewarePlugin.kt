import org.gradle.kotlin.dsl.apply
import scope.PluginScope

/**
 * @ClassName :   ModuleMiddlewarePlugin
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/3/28
 * @Version:      1.0
 * @Description : TODO
 */
class ModuleMiddlewarePlugin : BasePlugin() {
  override fun PluginScope.configure() {
    if (plugins.hasPlugin("com.android.application")) {
      /// 中间件模块 不该引入application 插件
      throw RuntimeException("取消单模块调试才能使用多模块插件！")
    }

    apply(plugin = "internal.base.library")
  }
}