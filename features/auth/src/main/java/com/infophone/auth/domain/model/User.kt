package com.infophone.auth.domain.model


data class User(
    val userId: String? = null,
    val countryCode: String? = null,
    val countryIso: String? = null,
    val phoneNumber: String? = null,
    val dialNumber: String? = null,
    val userName: String? = null,
    val userAbout: String? = null,
    val userProfileImg: String? = null,
    val userEmail: String? = null,
    val lastSeen: Long? = null,
    val createdAt: Long? = null
)
