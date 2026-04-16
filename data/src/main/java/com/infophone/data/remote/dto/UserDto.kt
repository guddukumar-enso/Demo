package com.infophone.data.remote.dto

import kotlinx.serialization.Serializable

// DTO - Matches the external API/Ktor response structure
@Serializable
data class UserDto(
    val id: String,
    val first_name: String, // Note the snake_case mismatch with Domain Entity
    val last_name: String,
    val user_email: String
)