plugins {
    id("app")
}

useViewBinding()

dependencies {
  if (!config.BuildConfig.isRelease) {
    // 依赖 LeakCanary，检查内存泄漏 https://github.com/square/leakcanary
    implementation("com.squareup.leakcanary:leakcanary-android:2.10")
    }
}