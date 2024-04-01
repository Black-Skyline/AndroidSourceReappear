plugins {
  `kotlin-dsl`
}
dependencies {
  implementation(project(":internal:base"))
}

gradlePlugin {
  plugins {
    create("internal.module.debug") {
      id = "internal.module.debug"
      implementationClass = "ModuleDebugPlugin"
    }

    create("internal.module.middleware") {
      id = "internal.module.middleware"
      implementationClass = "ModuleMiddlewarePlugin"
    }
  }
}

