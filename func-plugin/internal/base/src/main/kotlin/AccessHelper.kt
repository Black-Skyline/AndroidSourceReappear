import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.plugins.ExtensionContainer
import scope.PluginScope

/**
 * @FileName :    AccessHelper
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/3/24
 * @Version:      1.0
 * @Description : TODO
 */

fun PluginScope.androidLib(configure: LibraryExtension.() -> Unit) {
  extensions.configure("android", configure)
}

fun PluginScope.androidApp(configure: BaseAppModuleExtension.() -> Unit) {
  extensions.configure("android", configure)
}

/**
 * from 985892345 and False-Mask
 *
 * LibraryExtension 本身是没有实现 ExtensionAware，但是其最终的子类实现了 ExtensionAware
 * 这里这样写主要是为了作用域的问题，防止拿到 Project.extensions
 * ```
 * fun Project.test() {
 *   android {
 *     extensions
 *     // 这个本意上是使用 android 作用域中的 extensions，
 *     // 但是因为 LibraryExtension 并没有实现 ExtensionAware，导致没有 extensions 这个方法
 *     // 从而实际上是使用的 Project.extensions (所以给 LibraryExtension 添加扩展变量来避免这个问题)
 *   }
 * }
 *
 * fun android(configure: LibraryExtension.() -> Unit) {
 *   // ...
 * }
 * ```
 */
val LibraryExtension.extensions: ExtensionContainer
  get() = (this as ExtensionAware).extensions

// 原因同 LibraryExtension
val BaseAppModuleExtension.extensions: ExtensionContainer
  get() = (this as ExtensionAware).extensions