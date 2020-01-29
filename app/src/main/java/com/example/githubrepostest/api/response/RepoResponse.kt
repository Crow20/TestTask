package com.example.githubrepostest.api.response

data class RepoResponse(
    val id: Int?,
    val name: String?,
    val owner: Owner?,
    val description: String?,
    val html_url: String?
)