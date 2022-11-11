package com.github.grootio.apps.core.domain

import com.github.grootio.apps.client.GithubRepoAPI
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class GithubService(
    @Value("\${platform.github.org}") private val org: String,
    val githubRepoAPI: GithubRepoAPI,
) {

    fun createRepo(name: String): String {
        val createRepoResponseBody = githubRepoAPI.createRepo(name, org)
        return createRepoResponseBody.htmlUrl
    }
}