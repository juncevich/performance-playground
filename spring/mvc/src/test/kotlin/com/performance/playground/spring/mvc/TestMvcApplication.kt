package com.performance.playground.spring.mvc

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
    fromApplication<MvcApplication>().with(TestcontainersConfiguration::class).run(*args)
}
