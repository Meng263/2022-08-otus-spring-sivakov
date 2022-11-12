plugins {
    id("java")
//    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"
}

group = "ru.otus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.apache.commons:commons-csv:1.9.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

//tasks.jar {
//    manifest.attributes["Main-Class"] = "ru.otus.Application"
//}
