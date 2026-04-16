package com.infophone.navigation

import com.infophone.common.ExplorerResult
import com.infophone.common.ExplorerType

import com.infophone.navigation.domain.PrivacyType


sealed class NavKey {
    data object Splash : NavKey()
    data object Onboard : NavKey()
    data object Auth : NavKey()
    data class Profile(val countryCode: String, val phoneNumber: String) : NavKey()
    data object Chat : NavKey()
    data object Call : NavKey()

    data object Contact : NavKey()

    data object Group : NavKey()

    data object SelectGroup : NavKey()

    data object CreateGroup : NavKey()

    data object GroupInfo : NavKey()

    data object EditGroup : NavKey()

    data object MediaShare : NavKey()

    //Setting and customization
    data object Setting : NavKey()
    data object Message : NavKey()
    data object ContactInfo : NavKey()
    data class Calling(val type: String) : NavKey()
    data object Camera : NavKey()
    data class Explorer(val type: ExplorerType) : NavKey()
    data object LinkedDevices : NavKey()

    data object QRScan : NavKey()

    data object SettingProfile : NavKey()
    data object SettingChat : NavKey()
    data object SettingAccount : NavKey()
    data object SettingPrivacy : NavKey()
    data object SettingNotification : NavKey()
    data class PrivacySection(
        val privacyType: PrivacyType,
    ) : NavKey()

    data object  ChangeNumber : NavKey()

    data object  DeleteAccount : NavKey()

    data object  UserNotification : NavKey()


}