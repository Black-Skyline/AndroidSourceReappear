import com.android.build.api.dsl.ApplicationBuildFeatures
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryBuildFeatures
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

/**
 * @FileName :    ViewDataBinding
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/4/14
 * @Version:      1.0
 * @Description : TODO
 */
object ViewDataBinding {

  // databinding的依赖版本
  const val version = "8.1.0"

  // https://mvnrepository.com/artifact/androidx.databinding/databinding-runtime
  val databinding_runtime = "androidx.databinding:databinding-runtime:$version"
  // https://mvnrepository.com/artifact/androidx.databinding/databinding-ktx
  val databinding_ktx = "androidx.databinding:databinding-ktx:$version"
}

/**
 * 引入 DataBinding依赖 (可选是否使用)
 * @param isOnlyDepend 是否只依赖而不开启 DataBinding，默认开启 DataBinding
 */
fun Project.useDataBinding(isOnlyDepend: Boolean = false) {
  if (!isOnlyDepend) {
    // kapt 按需引入
    apply(plugin = "org.jetbrains.kotlin.kapt")
    extensions.configure(CommonExtension::class.java) {
      buildFeatures {
        when (this) {
          is LibraryBuildFeatures -> dataBinding = true // com.android.library 插件的配置
          is ApplicationBuildFeatures -> dataBinding = true // com.android.application 插件的配置
        }
      }
    }
  }
  dependencies {
    "implementation"(ViewDataBinding.databinding_runtime)
    "implementation"(ViewDataBinding.databinding_ktx)
  }
}

fun Project.useViewBinding()  {
  extensions.configure(CommonExtension::class.java) {
    buildFeatures {
      when (this) {
        is LibraryBuildFeatures -> viewBinding = true // com.android.library 插件的配置
        is ApplicationBuildFeatures -> viewBinding = true // com.android.application 插件的配置
      }
    }
  }
}