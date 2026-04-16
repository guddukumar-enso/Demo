package com.infophone.navigation.domain

import com.infophone.navigation.R


sealed class PrivacyType(
    val pageTitle: Int,
    val appBarTitle: Int,
    val isLastOnlineSeen: Boolean = false
) {
    object ProfilePhoto : PrivacyType(
        pageTitle = R.string.who_can_see_profile_photo,
        appBarTitle = R.string.profile_photo,
        isLastOnlineSeen = false
    )

    object LastSeen : PrivacyType(
        pageTitle = R.string.who_can_see_last_seen,
        appBarTitle = R.string.last_seen_online,
        isLastOnlineSeen = true

    )
}

