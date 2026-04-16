package com.infophone.data.remote.api

import com.infophone.data.remote.dto.PostDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class PostApi @Inject constructor(
    private val httpClient: HttpClient
) {
    suspend fun fetchPosts(): List<PostDto> {
        return httpClient.get("/posts").body()
    }
}