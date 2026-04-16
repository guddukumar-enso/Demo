package com.infophone.auth.domain.model

fun User.merge(new: User): User {
    return User(
        userId = new.userId ?: this.userId,
        countryCode = new.countryCode ?: this.countryCode,
        countryIso = new.countryIso ?: this.countryIso,
        phoneNumber = new.phoneNumber ?: this.phoneNumber,
        dialNumber = new.dialNumber ?: this.dialNumber,
        userName = new.userName ?: this.userName,
        userAbout = new.userAbout ?: this.userAbout,
        userProfileImg = new.userProfileImg ?: this.userProfileImg,
        userEmail = new.userEmail ?: this.userEmail,
        lastSeen = new.lastSeen ?: this.lastSeen,
        createdAt = new.createdAt ?: this.createdAt
    )
}
