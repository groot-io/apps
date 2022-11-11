package com.github.grootio.apps.controller

import com.github.grootio.apps.usecase.CreateAppInput
import com.github.grootio.apps.usecase.CreateAppOutput
import com.github.grootio.apps.usecase.CreateAppUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/applications")
class AppController(private val createAppUseCase: CreateAppUseCase) {

    @PostMapping
    fun post(@RequestBody createAppInput: CreateAppInput): ResponseEntity<CreateAppOutput> {
        val output = createAppUseCase.execute(createAppInput)
        return ResponseEntity.ok(output)
    }
}