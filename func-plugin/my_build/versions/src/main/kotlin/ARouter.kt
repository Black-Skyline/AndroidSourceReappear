import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

/**
 * @FileName :    ARouter
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/3/22
 * @Version:      1.0
 * @Description : TODO
 */
@Suppress("MemberVisibilityCanBePrivate", "SpellCheckingInspection")
object ARouter {
  // 原生ARouter https://github.com/alibaba/ARouter
  // 适配 AGP8: https://github.com/jadepeakpoet/ARouter
  const val arouter_version = "1.5.2"
  const val arouter_version_jadepeakpoet = "1.0.3"

  // 为了让ARouter至此 AGP8以上，请在 gradle.properties中 添加# android.enableJetifier=true 来重写二进制文件
  const val arouter_api = "com.alibaba:arouter-api:$arouter_version"
  const val arouter_compiler = "com.alibaba:arouter-compiler:$arouter_version"
//  const val arouter_api = "com.github.jadepeakpoet.ARouter:arouter-api:$arouter_version_jadepeakpoet"
//  const val arouter_compiler = "com.github.jadepeakpoet.ARouter:arouter-compiler:$arouter_version_jadepeakpoet"

}

/**
 * 使用 ARouter
 *
 * 单独给每个模块都添加而非集成在基础依赖函数里的原因:
 * - 为了按需引入 kapt(注解器处理)
 * - 部分 lib 模块只使用依赖，不包含注解(只使用路由，不负责制造路由)
 *
 * @param isNeedProcessAnnotation 是否需要处理注解，对于非实现模块是不需要处理注解的，比如 api 模块, 相当于只使用路由，不负责制造路由
 */
fun Project.useARouter(isNeedProcessAnnotation: Boolean = !name.startsWith("api_")) {
  if (isNeedProcessAnnotation) {
    // kapt 按需引入
    apply(plugin = "org.jetbrains.kotlin.kapt")
    extensions.configure<KaptExtension> {
      arguments {
        arg("AROUTER_MODULE_NAME", project.name)
      }
    }
    dependencies {
      "kapt"(ARouter.arouter_compiler)
    }
  }
  dependencies {
    "implementation"(ARouter.arouter_api)
  }
}