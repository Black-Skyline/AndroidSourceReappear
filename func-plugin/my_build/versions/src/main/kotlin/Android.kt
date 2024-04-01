import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * @FileName :    Android.kt
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/3/22
 * @Version:      1.0
 * @Description : TODO
 */
@Suppress("MemberVisibilityCanBePrivate", "SpellCheckingInspection")
object Android {
  // 基础库 这个版本号跟 targetSdk 相关
  const val appcompat = "androidx.appcompat:appcompat:1.6.1"

  // 官方控件库
  const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.1.4"
  const val recyclerview = "androidx.recyclerview:recyclerview:1.3.0"
  const val cardview = "androidx.cardview:cardview:1.0.0"
  const val viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"
  const val material = "com.google.android.material:material:1.8.0"
  const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
  const val flexbox = "com.google.android.flexbox:flexbox:3.0.0"

  // 官方扩展库
  // https://developer.android.google.cn/kotlin/ktx?hl=zh_cn#core
  const val core_ktx = "androidx.core:core-ktx:1.10.1"

  // https://developer.android.google.cn/kotlin/ktx/extensions-list#dependency_4
  const val collection_ktx = "androidx.collection:collection-ktx:1.2.0"

  // https://developer.android.google.cn/kotlin/ktx/extensions-list#androidxfragmentapp
  const val fragment_ktx = "androidx.fragment:fragment-ktx:1.5.7"

  // https://developer.android.google.cn/kotlin/ktx/extensions-list#androidxactivity
  const val activity_ktx = "androidx.activity:activity-ktx:1.7.2"
}

/**
 * 导入常用的view控件与Ui布局依赖
 */
fun Project.dependAndroidView() {
  dependencies {
    "implementation"(Android.constraintlayout)
    "implementation"(Android.recyclerview)
    "implementation"(Android.cardview)
    "implementation"(Android.viewpager2)
    "implementation"(Android.material)
    "implementation"(Android.swiperefreshlayout)
    "implementation"(Android.flexbox)
  }
}

/**
 * 关于kts的官方拓展库
 */
fun Project.dependAndroidKtx() {
  dependencies {
    "implementation"(Android.core_ktx)
    "implementation"(Android.collection_ktx)
    "implementation"(Android.fragment_ktx)
    "implementation"(Android.activity_ktx)
  }
}

/**
 * 给lib模块准备的，这些基本都是所有Android工程都需要
 */
fun Project.dependAndroidCommonBase() {
  dependencies {
    "api"(Android.appcompat)
    "api"(Android.core_ktx)
    "api"(Android.constraintlayout)
    "api"(Android.material)
    // 为了保证ARouter使用的灵活性,使用一个单独的kt函数完成对ARouter的依赖
//    "api"(ARouter.arouter_api)
//    "kapt"(ARouter.arouter_compiler)
  }
}
/**
 * 给module模块准备的，这些基本都是所有Android工程都需要
 *
 * 注意：当该module模块依赖了 使用了[dependAndroidCommonBase]函数的lib模块，就没必要使用该函数
 */
fun Project.dependAndroidSpareCommonBase() {
  dependencies {
    "implementation"(Android.appcompat)
    "implementation"(Android.core_ktx)
    "implementation"(Android.constraintlayout)
    "implementation"(Android.material)
  }
}

/**
 * Android最基础的appcompat依赖，作为dependAndroidCommonBase()的低位替补
 */
fun Project.dependAndroidLowBase() {
  dependencies {
    "implementation"(Android.appcompat)
  }
}