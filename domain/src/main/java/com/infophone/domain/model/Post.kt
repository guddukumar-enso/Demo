package com.infophone.domain.model

// Domain Entity - Immutable and framework-agnostic [1]
data class Post(
    val id: Int,
    val body: String,
    val title: String,
    val userId: Int
)