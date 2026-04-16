package com.infophone.setting.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.Modifier
import com.infophone.navigation.NavEntry
import com.infophone.navigation.NavEntryProvider
import com.infophone.navigation.NavKey
import com.infophone.navigation.TopLevelBackStack
import com.infophone.setting.presentation.screen.AccountScreen
import com.infophone.setting.presentation.screen.ChangeNumberScreen
import com.infophone.setting.presentation.screen.DeleteAccountScreen
import com.infophone.setting.presentation.screen.LinkedDevicesScreen
import com.infophone.setting.presentation.screen.PrivacyScreen
import com.infophone.setting.presentation.screen.PrivacySelectionScreen
import com.infophone.setting.presentation.screen.QrScanScreen
import com.infophone.setting.presentation.screen.SettingChatScreen
import com.infophone.setting.presentation.screen.SettingNotificationScreen
import com.infophone.setting.presentation.screen.SettingProfileScreen
import com.infophone.setting.presentation.screen.UserNotificationScreen

class SettingNavEntryProvider(
    private val backStack: TopLevelBackStack<NavKey>): NavEntryProvider {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun getEntry(
        navKey: NavKey,
        modifier: Modifier
    ): NavEntry<NavKey>? {
        return when (navKey) {
            is NavKey.LinkedDevices -> NavEntry(navKey) {
                LinkedDevicesScreen(
                    modifier = modifier,
                    onBackClick = {
                        backStack.removeLast()
                    },
                    onLinkDeviceClick = {
                        backStack.add(NavKey.QRScan)
                    }
                )
            }

            is NavKey.QRScan -> NavEntry(navKey){
                QrScanScreen(
                    modifier = modifier,
                    onBackClick = {
                        backStack.removeLast()
                    })
            }

            is NavKey.SettingProfile -> NavEntry(navKey){
                SettingProfileScreen(
                    modifier = Modifier,
                    onBackClick = {
                        backStack.removeLast()
                    },
                    onClick = { }
                )
            }

            is NavKey.SettingChat -> NavEntry(navKey){
                SettingChatScreen(
                    onBackClick = {
                        backStack.removeLast()
                    },
                    modifier = Modifier,
                    onClick = {
                    }
                )
            }

            is NavKey.SettingAccount -> NavEntry(navKey){
                AccountScreen(
                    modifier = Modifier,
                    onBackClick = {
                        backStack.removeLast()
                    },
                    onClick = {
                        backStack.add(it)
                    }
                )
            }

            is NavKey.SettingPrivacy -> NavEntry(navKey){
                PrivacyScreen(
                    onBackClick = {
                        backStack.removeLast()
                    },
                    modifier = Modifier,
                    onClick = {key ->
                        backStack.add(key)
                    }
                )
            }

            is NavKey.SettingNotification -> NavEntry(navKey){
                SettingNotificationScreen(
                    onBackClick = {
                        backStack.removeLast()
                    },
                    modifier = Modifier,
                    onClick = {     }
                )
            }
            is NavKey.PrivacySection -> NavEntry(navKey){
                PrivacySelectionScreen(
                    modifier = Modifier,
                    privacyType = navKey.privacyType,
                    onBackClick = { backStack.removeLast() },
                    onClick = {}

                )
            }

            is NavKey.ChangeNumber -> NavEntry(navKey){
                ChangeNumberScreen(
                    modifier = Modifier,
                    onBackClick = { backStack.removeLast() },
                    onClick = {}

                )
            }
            is NavKey.DeleteAccount -> NavEntry(navKey){
                DeleteAccountScreen(
                    modifier = Modifier,
                    onBackClick = { backStack.removeLast() },
                    onClick = {}

                )
            }
            is NavKey.UserNotification -> NavEntry(navKey){
                UserNotificationScreen(
                    modifier = Modifier,
                    onBackClick = { backStack.removeLast() },
                    onClick = {}

                )
            }

            else -> null
        }
        }
}