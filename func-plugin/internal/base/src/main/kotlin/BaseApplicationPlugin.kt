import checker.ModuleNameSpaceChecker
import config.BuildConfig
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import scope.PluginScope
import tools.libsVersion

/**
 * @ClassName :   BaseApplicationPlugin
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/3/23
 * @Version:      1.0
 * @Description : TODO
 */
internal class BaseApplicationPlugin: BasePlugin() {
  override fun PluginScope.configure() {
    // 此处先引入一些必要的插件
    apply(plugin = "com.android.application")
    apply(plugin = "org.jetbrains.kotlin.android")

    apply(plugin = "internal.base.android")

    androidApp {
      namespace = ModuleNameSpaceChecker.getCorrectNamespace(project)

      compileSdk = BuildConfig.compileSdk
      defaultConfig {
        minSdk = BuildConfig.minSdk
        testInstrumentationRunner = BuildConfig.testInstrumentationRunner

      }

      buildTypes {
        release {
          isMinifyEnabled = true
          proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            rootDir.resolve("func-plugin")
              .resolve("internal")
              .resolve("proguard-rules.pro")
          )

          ndk {
            abiFilters += BuildConfig.releaseAbiFilters
          }
        }

        debug {
          isMinifyEnabled = false
          proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            rootDir.resolve("func-plugin")
              .resolve("internal")
              .resolve("proguard-rules.pro")
          )

          ndk {
            abiFilters += BuildConfig.debugAbiFilters
          }
        }
      }

      compileOptions {
        val javaVersion = libsVersion("javaTarget").requiredVersion
        sourceCompatibility = JavaVersion.toVersion(javaVersion)
        targetCompatibility = JavaVersion.toVersion(javaVersion)
      }

      // kotlinOptions 闭包
      // 这里的 extensions 拿的是 android 闭包中的 extensions，不能拿 Project.extensions
      extensions.configure<KotlinJvmOptions> {
        jvmTarget = libsVersion("kotlinJvmTarget").requiredVersion
      }

      lint {
        // 编译遇到错误不退出
        abortOnError = false
      }

      // 命名规范设置，因为多模块相同资源名在打包时会合并，所以必须强制开启
      resourcePrefix = project.name.substringAfter("_")

      defaultConfig {
        applicationId = BuildConfig.getApplicationId(project)
        versionCode = BuildConfig.versionCode
        versionName = BuildConfig.versionName
        targetSdk = BuildConfig.targetSdk
      }

      buildFeatures {
        buildConfig = true
        // dataBinding 按需开启，请使用 useDataBinding() 方法
      }

      buildTypes {
        release {
          isShrinkResources = true
        }
      }

      packaging {
        jniLibs.excludes += BuildConfig.jniExclude
        resources.excludes += BuildConfig.resourcesExclude
      }
    }

    // kotlin 闭包
    extensions.configure<KotlinAndroidProjectExtension> {
      jvmToolchain(libsVersion("kotlinJvmTarget").requiredVersion.toInt())
    }

  }
}