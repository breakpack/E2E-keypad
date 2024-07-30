package org.example.presentation.controller

import org.example.presentation.service.KeypadService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/keypad")
class Controller(private val keypadService: KeypadService) {

    @GetMapping("/image")
    fun getKeypadImage(): String {
        return keypadService.getEncodedImage()
    }
}