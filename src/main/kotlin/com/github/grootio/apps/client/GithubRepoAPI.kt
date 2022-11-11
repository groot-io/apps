package com.github.grootio.apps.client

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Component
class GithubRepoAPI(val mapper: ObjectMapper, @Value("\${platform.github.accessKey}") val accessKey: String) {

    private val logger: Logger = LoggerFactory.getLogger(GithubRepoAPI::class.java)

    fun createRepo(name: String, org: String): CreateRepoResponseBody {

        val body = mapper.writeValueAsString(
            CreateRepoRequestBody(org, makeRepoName(name, org))
        )

        logger.info("[class:GithubAPI][method:createRepo] request body: $body")

        val request = makeHttpRequest(org, body)

        // client
        val newHttpClient = HttpClient.newHttpClient()

        // send
        val response = newHttpClient.send(request, HttpResponse.BodyHandlers.ofString())

        logger.info("[class:GithubAPI][method:createRepo] response body: ${response.body()}")

        return mapper.readValue(response.body(), CreateRepoResponseBody::class.java)
    }

    private fun makeHttpRequest(org: String, body: String?): HttpRequest? {
        return HttpRequest.newBuilder(makeURI(org))
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .header("Authorization", "Bearer $accessKey")
            .build()
    }

    private fun makeRepoName(name: String, org: String): String = "${org}_${name}"

    private fun makeURI(org: String): URI = URI.create("https://api.github.com/orgs/$org/repos")
}

