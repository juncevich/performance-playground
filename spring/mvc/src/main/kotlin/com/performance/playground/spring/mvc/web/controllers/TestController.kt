package com.performance.playground.spring.mvc.web.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    @GetMapping("/sping/mvc/return-only-ok-code")
    fun getOnlyOkCode() {
        ResponseEntity.ok()
    }

    @GetMapping("/sping/mvc/return-ok-code-string")
    fun getOkCodeString() {
        ResponseEntity.ok("OK")
    }

}