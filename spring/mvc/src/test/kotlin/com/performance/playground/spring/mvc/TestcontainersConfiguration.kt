package com.performance.playground.spring.mvc

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.RabbitMQContainer
import org.testcontainers.kafka.KafkaContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    @Bean
    @ServiceConnection
    fun kafkaContainer(): KafkaContainer {
        return KafkaContainer(DockerImageName.parse("apache/kafka-native:latest"))
    }

    @Bean
    @ServiceConnection
    fun mongoDbContainer(): MongoDBContainer {
        return MongoDBContainer(DockerImageName.parse("mongo:latest"))
    }

    @Bean
    @ServiceConnection
    fun postgresContainer(): PostgreSQLContainer<*> {
        return PostgreSQLContainer(DockerImageName.parse("postgres:latest"))
    }

    @Bean
    @ServiceConnection
    fun rabbitContainer(): RabbitMQContainer {
        return RabbitMQContainer(DockerImageName.parse("rabbitmq:latest"))
    }

    @Bean
    @ServiceConnection(name = "redis")
    fun redisContainer(): GenericContainer<*> {
        return GenericContainer(DockerImageName.parse("redis:latest")).withExposedPorts(6379)
    }

    @Bean
    @ServiceConnection(name = "openzipkin/zipkin")
    fun zipkinContainer(): GenericContainer<*> {
        return GenericContainer(DockerImageName.parse("openzipkin/zipkin:latest")).withExposedPorts(9411)
    }

}
