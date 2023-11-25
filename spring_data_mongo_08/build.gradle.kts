plugins {
    id("java")
    id("org.springframework.boot") version "2.7.17"
    id("io.spring.dependency-management") version "1.1.4"
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
    val springVersion = "2.7.17"
    implementation("org.springframework.boot:spring-boot-starter:$springVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb:$springVersion")
    implementation("org.springframework.shell:spring-shell-starter:2.1.14")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springVersion")
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo:4.11.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
