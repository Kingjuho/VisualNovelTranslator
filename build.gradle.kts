plugins {
    id("java")
    id("application")

    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "com.juho"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.core:jackson-databind")
}

tasks.test {
    useJUnitPlatform()
}