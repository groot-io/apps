package com.github.grootio.apps.usecase

import com.github.grootio.apps.core.domain.App
import com.github.grootio.apps.core.domain.GithubService
import com.github.grootio.apps.data.AppRepository
import org.springframework.stereotype.Component

@Component
class CreateAppUseCase(private val appRepository: AppRepository, private val githubService: GithubService) {

    fun execute(input: CreateAppInput): CreateAppOutput {

        val app = App(input.name, input.language, input.technology)

        val newApp = appRepository.save(app)

        val url = githubService.createRepo(newApp.name)

        return CreateAppOutput(newApp.id, newApp.name, newApp.language, newApp.technology, url)
    }
}

data class CreateAppInput(val name: String, val language: String, val technology: String)
data class CreateAppOutput(
    val id: String,
    val name: String,
    val language: String,
    val technology: String,
    val url: String
)