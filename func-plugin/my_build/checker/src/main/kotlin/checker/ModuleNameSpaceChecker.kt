package checker

import org.gradle.api.Project
import rule.ICheckRule
import rule.RuleFlag
import java.io.File

/**
 * @FileName :    ModuleNameSpaceChecker
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/3/24
 * @Version:      1.0
 * @Description : TODO
 */
object ModuleNameSpaceChecker : ICheckRule {
  override val isNecessary: Boolean
    get() = true
  override val flag: RuleFlag
    get() = RuleFlag.NAME_SPACE

  // TODO 这里面是用于兼容特殊模块的，请自行配置
  private val specialModuleNameSpaceMap: Map<String, String> = mapOf(
    "app" to "com.study.androidsourcereappear",
    "Handler" to "com.study.androidsourcereappear.handler"
  )

  override fun onConfigBefore(project: Project) {
    val namespace = getCorrectNamespace(project)
    val file = project.projectDir
      .resolve("src")
      .resolve("main")
      .resolve("java")
      .resolve(namespace.replace(".", File.separator))
    if (!file.exists()) {
      val rule = """
        
        模块包名命名规范：
        1、一级模块
          一级模块以 com.study.androidsourcereappear.xxx 包名命名。如：Handler 为 com.study.androidsourcereappear.handler
        2、二级模块
          一级模块以 com.study.androidsourcereappear.[module|lib|api].xxx 包名命名。如：api_handler 为 com.study.androidsourcereappear.api.handler
          
        你当前 ${project.name} 模块的包名应该改为：$namespace
        
      """.trimIndent()
      throw RuntimeException("${project.name} 模块包名错误！" + rule)
    }
  }

  override fun onConfigAfter(project: Project) {
    println("${project.name} has been configured")
  }

  /**
   * 获取某个模块正确的 namespace
   */
  fun getCorrectNamespace(project: Project): String {
    // 分三种情况
    /// case 1：被登记了的特殊模块
    val specialNamespace = specialModuleNameSpaceMap[project.name]
    if (specialNamespace != null) {
      return specialNamespace
    }
    return if (project.projectDir.parentFile == project.rootDir) {
      /// case 2：是一级模块,该模块比较特殊
      // 一级模块以 com.study.androidsourcereappear.xxx 命名
      "com.study.androidsourcereappear.${project.name.substringAfter("_")}"
    } else {
      /// case 3：是二级模块
      // 二级模块以 com.study.androidsourcereappear.[module|lib|api].xxx 命名
      "com.study.androidsourcereappear.${project.name.replace("_", ".")}"
    }
  }
}