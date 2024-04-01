import checker.ModuleNameSpaceChecker
import org.gradle.api.Project
import rule.ICheckRule

/**
 * @FileName :    AndroidProjectChecker
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/3/24
 * @Version:      1.0
 * @Description : TODO
 */
object AndroidProjectChecker {
  // TODO 联合规则检查 集合
  private val jointRuleCheck: Array<ICheckRule> = arrayOf(ModuleNameSpaceChecker)


  /**
   * 配置插件前触发
   */
  fun configBefore(project: Project, isObeyAll: Boolean = true) {
    try {
      jointRuleCheck.forEach {
        if (isObeyAll || it.isNecessary) it.onConfigBefore(project)
      }
    } catch (e: RuntimeException) {
      println("项目检查工具发现问题：${e.message}")
      throw RuntimeException(e.message +  hint)
    }
  }


  /**
   * 配置插件后触发
   */
  fun configAfter(project: Project, isObeyAll: Boolean = true) {
    try {
      jointRuleCheck.forEach {
        if (isObeyAll || it.isNecessary) it.onConfigAfter(project)
      }
    } catch (e: RuntimeException) {
      println("项目检查工具发现问题：${e.message}")
      throw RuntimeException(e.message + hint)
    }
  }

  // gradle 默然字符集会导致中文乱码，所以这里单独写了个英文提示
  private val hint = """
    
    =====================================================================================================
    =  NOTE: If you have garbled Chinese characters, such as: 一二三                                     =
    =  place click "Help - Edit Custom VM Options" and then add "-Dfile.encoding=UTF-8" and restart AS  =
    =====================================================================================================
    
  """.trimIndent()
}