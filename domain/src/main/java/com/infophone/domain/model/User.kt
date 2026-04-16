package com.infophone.domain.model

// Domain Entity - Immutable and framework-agnostic [1]
data class User(
    val id: String,
    val name: String,
    val email: String
)