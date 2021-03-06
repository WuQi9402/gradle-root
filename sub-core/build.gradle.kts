val commonscodec: String by System.getProperties()
val mybatisplus: String by System.getProperties()
val javassist: String by System.getProperties()
val caffeine: String by System.getProperties()
val protobuf: String by System.getProperties()
val byteman: String by System.getProperties()
val netty: String by System.getProperties()
val jna: String by System.getProperties()

plugins {
  `maven-publish`
}

java {
  withSourcesJar()
}

publishing {
  publications {
    create<MavenPublication>("maven") {
      from(components["java"])
    }
  }
}

dependencies {
  compileOnly("org.springframework.boot:spring-boot-starter-amqp")
  compileOnly("org.springframework.boot:spring-boot-starter-web")
  compileOnly("org.springframework.boot:spring-boot-starter-data-redis") {
    exclude(group = "io.netty")
    exclude(group = "io.lettuce")
  }
  compileOnly("javax.servlet:javax.servlet-api")
  compileOnly("io.netty:netty-all:$netty")
  compileOnly("com.google.protobuf:protobuf-java-util:$protobuf")

  compileOnly("com.baomidou:mybatis-plus-core:$mybatisplus")

  testImplementation("com.github.ben-manes.caffeine:caffeine:$caffeine") { exclude(group = "org.checkerframework") }
  testImplementation("commons-codec:commons-codec:$commonscodec")
  testImplementation("org.jboss.byteman:byteman:$byteman")
  testImplementation("net.java.dev.jna:jna:$jna")
  testImplementation("org.javassist:javassist:$javassist")
}
