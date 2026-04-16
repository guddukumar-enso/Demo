package com.infophone.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostDto(
    val id: Int,
    val body: String,
    val title: String,
    val userId: Int
)