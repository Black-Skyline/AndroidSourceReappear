import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * @FileName :    Paging
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/3/24
 * @Version:      1.0
 * @Description : TODO
 */

@Suppress("MemberVisibilityCanBePrivate", "ObjectPropertyName", "SpellCheckingInspection")
object Paging {
  // https://developer.android.com/jetpack/androidx/releases/paging
  const val paging_version = "3.2.0"

  const val paging_runtime = "androidx.paging:paging-runtime:$paging_version"
  const val paging_rxjava3 = "androidx.paging:paging-rxjava3:$paging_version"

  // alternatively - without Android dependencies for tests
  const val paging_testing = "androidx.paging:paging-common:$paging_version"
}

/**
 * 导入必要的Paging依赖
 */
fun Project.dependPaging() {
  dependencies {
    "implementation"(Paging.paging_runtime)
    "implementation"(Paging.paging_rxjava3)
  }
}