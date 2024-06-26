import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * @FileName :    Rxjava
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/3/24
 * @Version:      1.0
 * @Description : TODO
 */
@Suppress("MemberVisibilityCanBePrivate", "ObjectPropertyName", "SpellCheckingInspection")
object Rxjava {
  // https://github.com/ReactiveX/RxJava
  const val rxjava3 = "io.reactivex.rxjava3:rxjava:3.1.6"
  // https://github.com/ReactiveX/RxAndroid
  const val rxjava3_android = "io.reactivex.rxjava3:rxandroid:3.0.2"
  // https://github.com/ReactiveX/RxKotlin
  const val rxjava3_kotlin = "io.reactivex.rxjava3:rxkotlin:3.0.1"
}

/**
 * 导入必要的Rxjava3依赖
 */
fun Project.dependRxjava() {
  dependencies {
    "implementation"(Rxjava.rxjava3)
    "implementation"(Rxjava.rxjava3_android)
    "implementation"(Rxjava.rxjava3_kotlin)
  }
}
/**
 * 以api关键字导入必要的Rxjava3依赖，可传递给父模块，有需要再用
 */
fun Project.dependRxjavaByApi() {
  dependencies {
    "api"(Rxjava.rxjava3)
    "api"(Rxjava.rxjava3_android)
    "api"(Rxjava.rxjava3_kotlin)
  }
}