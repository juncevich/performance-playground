package com.performance.playground.spring.mvc

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(TestcontainersConfiguration::class)
@SpringBootTest
class MvcApplicationTests {

    @Test
    fun contextLoads() {
    }

}
