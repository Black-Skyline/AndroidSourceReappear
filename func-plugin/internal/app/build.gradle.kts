plugins {
  `kotlin-dsl`
}

dependencies {
  implementation(project(":internal:base"))
  implementation(project(":my_build:versions"))
}

gradlePlugin {
  plugins {
    create("app") {
      id = "app"
      implementationClass ="AppPlugin"
    }
  }
}