import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks.withType<PublishToMavenRepository> { enabled = false }

tasks.withType<BootJar> {
  enabled = true
  archiveFileName.set("web-boot.jar")
}

tasks.withType<Test> {
  enabled = false
  jvmArgs = listOf("-ea", "-Djava.library.path=/usr/local/opt/tomcat-native/lib")
}

val commonspool2: String by System.getProperties()
val mybatisplus: String by System.getProperties()
val retrofit: String by System.getProperties()
// val okhttp3: String by System.getProperties()
val mysql: String by System.getProperties()
val p6spy: String by System.getProperties()
val netty: String by System.getProperties()
val shiro: String by System.getProperties()
val jedis: String by System.getProperties()

dependencies {
  implementation(project(":sub-core"))
  implementation(project(":sub-model"))

  api("io.netty:netty-all:$netty")
  api("io.vertx:vertx-web") { exclude(group = "io.netty") }
  api("io.vertx:vertx-web-client") { exclude(group = "io.netty") }
  api("io.vertx:vertx-lang-kotlin") { exclude(group = "io.netty") }
  api("io.vertx:vertx-lang-kotlin-coroutines") { exclude(group = "io.netty") }

  implementation("com.baomidou:mybatis-plus-boot-starter:$mybatisplus")
  implementation("org.apache.commons:commons-pool2:$commonspool2")
  implementation("com.squareup.retrofit2:retrofit:$retrofit")
  // implementation("com.squareup.okhttp3:okhttp:$okhttp3")
  implementation("mysql:mysql-connector-java:$mysql")
  implementation("io.netty:netty-all:$netty")
  implementation("p6spy:p6spy:$p6spy")
  implementation("org.apache.shiro:shiro-spring-boot-web-starter:$shiro")
  implementation("redis.clients:jedis:$jedis")

  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.boot:spring-boot-starter-amqp")
  implementation("org.springframework.boot:spring-boot-starter-cache")
  implementation("org.springframework.boot:spring-boot-starter-aop")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-websocket")
  implementation("org.springframework.boot:spring-boot-starter-data-redis") {
    exclude(group = "io.netty")
    exclude(group = "io.lettuce")
  }
  // implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

  runtimeOnly("org.springframework.boot:spring-boot-devtools")

  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(group = "org.junit.vintage")
    exclude(module = "android-json")
    exclude(group = "junit")
  }
  testImplementation("org.springframework.security:spring-security-test")
}
