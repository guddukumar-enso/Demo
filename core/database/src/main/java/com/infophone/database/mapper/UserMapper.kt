package com.infophone.database.mapper

import com.infophone.database.domain.model.UserRealm
import com.infophone.database.entity.UserEntity

internal fun UserRealm.toEntity(): UserEntity =
    UserEntity(
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

internal fun UserEntity.toRealm(): UserRealm =
    UserRealm().apply {
        userId = this@toRealm.userId
        countryCode = this@toRealm.countryCode
        countryIso = this@toRealm.countryIso
        phoneNumber = this@toRealm.phoneNumber
        dialNumber = this@toRealm.dialNumber
        userName = this@toRealm.userName
        userAbout = this@toRealm.userAbout
        userProfileImg = this@toRealm.userProfileImg
        userEmail = this@toRealm.userEmail
        lastSeen = this@toRealm.lastSeen
        createdAt = this@toRealm.createdAt
    }


