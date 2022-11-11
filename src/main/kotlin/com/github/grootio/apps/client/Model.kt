package com.github.grootio.apps.client

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

data class CreateRepoRequestBody(
    val org: String,
    val name: String,
    val private: Boolean = true,
    val visibility: String = "private"
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CreateRepoResponseBody(
    @JsonProperty(value = "html_url")
    val htmlUrl: String
)