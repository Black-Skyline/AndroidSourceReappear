import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * @FileName :    WorkManger
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/3/24
 * @Version:      1.0
 * @Description : TODO
 */
@Suppress("MemberVisibilityCanBePrivate")
object WorkManger {
  // https://developer.android.google.cn/kotlin/ktx?hl=zh_cn#workmanager
  const val work_runtime_ktx = "androidx.work:work-runtime-ktx:2.8.1"
}

/**
 * 导入WorkManger依赖
 */
fun Project.dependWorkManger() {
  dependencies {
    "implementation"(WorkManger.work_runtime_ktx)
  }
}