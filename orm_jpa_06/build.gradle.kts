plugins {
    id("java")
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"
}

springBoot {
    mainClass.set("ru.otus.jdbc.Application")
}

group = "ru.otus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    val springVersion = "2.7.6"
    implementation("org.springframework.boot:spring-boot-starter:$springVersion")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:$springVersion")
    implementation("org.springframework.shell:spring-shell-starter:2.1.3")
    implementation("com.h2database:h2:2.1.212")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springVersion")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
