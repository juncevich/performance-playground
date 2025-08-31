plugins {
    id("java")
    id("io.gatling.gradle") version "3.14.3.7"
    kotlin("jvm") version "2.2.10"
    kotlin("plugin.allopen") version "2.2.10"
}

group = "com.performance.playground"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

gatling {
    gatlingVersion = "3.13.4"
    jvmArgs = listOf(
        "-server",
        "-Xms512M",
        "-Xmx512M",
        "--add-opens=java.base/java.lang=ALL-UNNAMED",
        "--add-opens=java.base/java.util=ALL-UNNAMED"
    )
    systemProperties = mapOf("file.encoding" to "UTF-8")
}