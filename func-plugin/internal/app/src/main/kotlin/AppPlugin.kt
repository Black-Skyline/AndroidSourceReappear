import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import scope.PluginScope

/**
 * @ClassName :   AppPlugin
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/4/13
 * @Version:      1.0
 * @Description : TODO
 */
class AppPlugin : BasePlugin() {
  override fun PluginScope.configure() {

    apply(plugin = "internal.base.application")
    dependAllProject()

  }

  private fun dependAllProject() {

    with(project) {
      // 测试使用，设置 module_app 暂时不依赖的模块
      val excludeList = mutableListOf<String>(
      )

      // 根 gradle 中包含的所有子模块
      val includeProjects = rootProject.allprojects.map { it.name }

      dependencies {
        //引入所有的module和lib模块
        rootDir.listFiles()!!.filter {
          // 1.是文件夹
          // 2.不是module_app
          ///  (此项暂时取消) 3.以lib_或者module_开头
          // 4.去掉暂时排除的模块
          // 5.根 gradle 导入了的模块
          it.isDirectory
                  && it.name != "app"
//                  && "(lib_.+)|(module_.+)|(api_.+)".toRegex().matches(it.name)
                  && !it.name.contains("lib_common") // 目前 app 模块已经去掉了对 common 模块的依赖
                  && !it.name.contains("lib_debug") // 去除主动依赖 lib_debug 模块
                  && it.name !in excludeList
                  && includeProjects.contains(it.name)
        }.forEach {
          "implementation"(project(":${it.name}"))
        }
      }

    }
  }

}