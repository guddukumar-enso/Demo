package com.infophone.database.domain.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

internal class UserRealm : RealmObject {
    @PrimaryKey
    var userId: String = ""

    // Phone details
    var countryCode: String = ""
    var countryIso: String = ""
    var phoneNumber: String = ""
    var dialNumber: String = ""

    // Profile info
    var userName: String = ""
    var userAbout: String = ""
    var userProfileImg: String = ""
    var userEmail: String = ""

    // Timestamps (epoch millis)
    var lastSeen: Long = 0L
    var createdAt: Long = 0L
}

