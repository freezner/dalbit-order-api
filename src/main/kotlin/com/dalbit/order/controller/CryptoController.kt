package com.dalbit.order.controller

import com.dalbit.order.service.CryptoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/crypto")
@CrossOrigin(origins = ["https://order.moonlgalu.com", "https://order-stage.moonlgalu.com", "http://localhost:3000"])
class CryptoController(
    val cryptoService: CryptoService
) {
    @GetMapping("/encrypt/{input}")
    fun encrypt(
        @PathVariable("input") input: String
    ) = cryptoService.encrypt(input)

    @GetMapping("/decrypt/{input}")
    fun decrypt(
        @PathVariable("input") input: String
    ) = cryptoService.decrypt(input)
}