package com.infophone.database.entity

data class UserEntity(
    val userId: String,

    // Phone info
    val countryCode: String,     // +91
    val countryIso: String,      // IN
    val phoneNumber: String,     // 9876543210
    val dialNumber: String,      // +919876543210

    // Profile info
    val userName: String,
    val userAbout: String,
    val userProfileImg: String,
    val userEmail: String,

    // Timestamps (epoch millis)
    val lastSeen: Long,
    val createdAt: Long
)

