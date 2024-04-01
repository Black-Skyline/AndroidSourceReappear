import org.gradle.kotlin.dsl.dependencies
import scope.PluginScope

/**
 * @ClassName :   BaseAndroidPlugin
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/3/23
 * @Version:      1.0
 * @Description : TODO
 */
internal class BaseAndroidPlugin : BasePlugin() {
  override fun PluginScope.configure() {
    // 检查逻辑
    AndroidProjectChecker.configBefore(this.project)

    // 导入测试相关的依赖
    dependTestBase()

    // 导入基础Android依赖
    dependAndroidLowBase()
    dependAndroidView()
    dependAndroidKtx()
    dependLifecycleKtx()

    // 自动依赖当前模块下的所有子模块
    dependencies {
      // 罗列 根目录的 gradle 中包含的所有子模块
      val includeProjectNames = rootProject.subprojects.map { it.name }

      projectDir.listFiles()!!.filter {
        // 1.是文件夹
        // 2.以 lib_ 或者 api_ 开头
        // 3.根 gradle 导入了的模块
        it.isDirectory
                && "(lib_.+)|(api_.+)".toRegex().matches(it.name)
                && includeProjectNames.contains(it.name)
      }.forEach {
        "implementation"(project(":${name}:${it.name}"))
      }
    }

    // 配置完成后的项目检查
    AndroidProjectChecker.configAfter(this.project)
  }
}