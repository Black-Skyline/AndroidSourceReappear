plugins {
  `kotlin-dsl`
}

dependencies {
  implementation(project(":internal:base"))
  implementation(project(":internal:library"))
  implementation(project(":internal:module"))

  api(project(":my_build:versions"))
  implementation(project(":my_build:checker"))

}

gradlePlugin {
  plugins {
    create("internal.integration.auto-adaption") {
      id = "internal.integration.auto-adaption"
      implementationClass = "AutoAdaptionManagerPlugin"
    }

    create("internal.integration.module-debug") {
      id = "internal.integration.module-debug"
      implementationClass = "ModuleDebugManagerPlugin"
    }

    create("internal.integration.module-middleware") {
      id = "internal.integration.module-middleware"
      implementationClass = "ModuleMiddlewareManagerPlugin"
    }

    create("internal.integration.lib-manager") {
      id = "internal.integration.lib-manager"
      implementationClass = "LibManagerPlugin"
    }
  }
}