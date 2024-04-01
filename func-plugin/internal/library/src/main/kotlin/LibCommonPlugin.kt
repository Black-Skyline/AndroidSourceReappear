import org.gradle.kotlin.dsl.apply
import scope.PluginScope

/**
 * @ClassName :   LibCommonPlugin
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/3/28
 * @Version:      1.0
 * @Description : TODO
 */
class LibCommonPlugin : BasePlugin() {
  override fun PluginScope.configure() {

    apply(plugin = "internal.base.library")

  }
}