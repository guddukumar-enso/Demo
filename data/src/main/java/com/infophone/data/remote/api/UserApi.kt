package com.infophone.data.remote.api

import com.infophone.data.remote.dto.UserDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import javax.inject.Inject

// Remote Data Source - uses the Ktor HttpClient [5]
class UserApi @Inject constructor(
    private val httpClient: HttpClient
) {
    suspend fun fetchUser(userId: String): UserDto {
        return httpClient.get {
            url("https://api.example.com/users/$userId")
            // Ktor features (serialization, headers, etc.) are handled here
        }.body() // Deserializes JSON into UserDto using ContentNegotiation
    }
}