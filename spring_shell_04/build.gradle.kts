plugins {
    id("java")
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
    implementation("org.springframework.shell:spring-shell-starter:2.1.5")
    implementation("org.apache.commons:commons-csv:1.9.0")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
