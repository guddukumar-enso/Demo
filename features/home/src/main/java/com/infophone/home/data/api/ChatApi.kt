package com.infophone.home.data.api

import com.infophone.home.data.dto.ChatDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import javax.inject.Inject

// Remote Data Source - uses the Ktor HttpClient [5]
class ChatApi @Inject constructor(
    private val httpClient: HttpClient
) {
    suspend fun fetchChat(): List<ChatDto> {
        return httpClient.get {
            url("https://api.example.com/users/")
            // Ktor features (serialization, headers, etc.) are handled here
        }.body() // Deserializes JSON into UserDto using ContentNegotiation
    }
}