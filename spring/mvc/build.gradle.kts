import com.google.protobuf.gradle.id

plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.5.5"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.google.protobuf") version "0.9.4"
    kotlin("plugin.jpa") version "1.9.25"
}

group = "com.performance.playground.spring"
version = "0.0.1-SNAPSHOT"
description = "mvc"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

extra["springGrpcVersion"] = "0.10.0"
extra["springModulithVersion"] = "1.4.1"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
//    implementation("org.springframework.boot:spring-boot-starter-amqp")
//    implementation("org.springframework.boot:spring-boot-starter-cache")
//    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
//    implementation("org.springframework.boot:spring-boot-starter-data-redis")
//    implementation("org.springframework.boot:spring-boot-starter-jdbc")
//    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//    implementation("io.grpc:grpc-services")
//    implementation("io.micrometer:micrometer-tracing-bridge-brave")
//    implementation("io.zipkin.reporter2:zipkin-reporter-brave")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
//    implementation("org.liquibase:liquibase-core")
//    implementation("org.springframework.grpc:spring-grpc-server-web-spring-boot-starter")
//    implementation("org.springframework.kafka:spring-kafka")
//    implementation("org.springframework.modulith:spring-modulith-events-api")
//    implementation("org.springframework.modulith:spring-modulith-starter-core")
//    implementation("org.springframework.modulith:spring-modulith-starter-jdbc")
//    implementation("org.springframework.modulith:spring-modulith-starter-jpa")
//    implementation("org.springframework.modulith:spring-modulith-starter-mongodb")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
//    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
//    runtimeOnly("com.h2database:h2")
//    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
//    runtimeOnly("org.postgresql:postgresql")
//    runtimeOnly("org.springframework.modulith:spring-modulith-actuator")
//    runtimeOnly("org.springframework.modulith:spring-modulith-events-amqp")
//    runtimeOnly("org.springframework.modulith:spring-modulith-observability")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
//    testImplementation("org.springframework.amqp:spring-rabbit-test")
//    testImplementation("org.springframework.grpc:spring-grpc-test")
//    testImplementation("org.springframework.kafka:spring-kafka-test")
//    testImplementation("org.springframework.modulith:spring-modulith-starter-test")
    testImplementation("org.testcontainers:junit-jupiter")
//    testImplementation("org.testcontainers:kafka")
//    testImplementation("org.testcontainers:mongodb")
//    testImplementation("org.testcontainers:postgresql")
//    testImplementation("org.testcontainers:rabbitmq")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.modulith:spring-modulith-bom:${property("springModulithVersion")}")
        mavenBom("org.springframework.grpc:spring-grpc-dependencies:${property("springGrpcVersion")}")
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc") {
                    option("@generated=omit")
                }
            }
        }
    }
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
