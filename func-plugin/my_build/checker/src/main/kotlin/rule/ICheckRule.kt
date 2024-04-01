package rule

import org.gradle.api.Project

/**
 * @FileName :    ICheckRule
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/3/24
 * @Version:      1.0
 * @Description : TODO
 */
interface ICheckRule {
  abstract val isNecessary: Boolean
  abstract val flag: RuleFlag

  /**
   * 配置插件前触发 的检查
   */
  @kotlin.jvm.Throws(RuntimeException::class)
  fun onConfigBefore(project: Project)

  /**
   * 配置插件后触发 的检查
   */
  @kotlin.jvm.Throws(RuntimeException::class)
  fun onConfigAfter(project: Project)
}