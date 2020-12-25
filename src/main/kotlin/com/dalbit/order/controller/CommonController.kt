package com.dalbit.order.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class CommonController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    fun healthCheck() = "OK"
}