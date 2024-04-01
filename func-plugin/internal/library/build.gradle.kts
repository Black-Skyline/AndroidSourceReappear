plugins {
  `kotlin-dsl`
}

dependencies {
  implementation(project(":internal:base"))
}

gradlePlugin {
  plugins {
    create("internal.library.common") {
      id = "internal.library.common" 
      implementationClass = "LibCommonPlugin"
    }
  }
}