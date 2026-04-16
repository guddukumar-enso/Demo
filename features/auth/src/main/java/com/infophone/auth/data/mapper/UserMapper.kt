package com.infophone.auth.data.mapper


import com.infophone.auth.domain.model.User
import com.infophone.database.entity.UserEntity

fun UserEntity.toDomain(): User = User(
    userId = userId,
    countryCode = countryCode,
    countryIso = countryIso,
    phoneNumber = phoneNumber,
    dialNumber = dialNumber,
    userName = userName,
    userAbout = userAbout,
    userProfileImg = userProfileImg,
    userEmail = userEmail,
    lastSeen = lastSeen,
    createdAt = createdAt
)

fun User.toEntity(): UserEntity = UserEntity(
    userId = userId ?: "",
    countryCode = countryCode ?: "",
    countryIso = countryIso ?: "",
    phoneNumber = phoneNumber ?: "",
    dialNumber = dialNumber ?: "",
    userName = userName ?: "",
    userAbout = userAbout ?: "",
    userProfileImg = userProfileImg ?: "",
    userEmail = userEmail ?: "",
    lastSeen = lastSeen ?: 0L,
    createdAt = createdAt ?: 0L
)





