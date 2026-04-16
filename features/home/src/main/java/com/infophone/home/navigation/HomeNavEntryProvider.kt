package com.infophone.home.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.Modifier
import com.infophone.home.presentation.setting.SettingScreen
import com.infophone.home.presentation.call.CallScreen
import com.infophone.home.presentation.chat.ChatScreen
import com.infophone.navigation.NavEntry
import com.infophone.navigation.NavEntryProvider
import com.infophone.navigation.NavKey
import com.infophone.navigation.TopLevelBackStack
import javax.inject.Inject

class HomeNavEntryProvider @Inject constructor(
    private val backStack: TopLevelBackStack<NavKey>,
    private val context: Context
): NavEntryProvider {
    override fun getEntry(navKey: NavKey, modifier: Modifier): NavEntry<NavKey>? {
        return when (navKey) {
            // Bottom Nav Bar Screens
            is NavKey.Chat -> NavEntry(navKey) {
                ChatScreen(
                    modifier = modifier,
                    onContacts = {
                        backStack.add(NavKey.Group)
                    },
                    onMore = {
                        Toast.makeText(context, "More clicked", Toast.LENGTH_SHORT).show()
                    },
                    onMessage = {
                        backStack.add(NavKey.Message)
//                        backStack.switchTopLevel(NavKey.Message)
                    }
                )
            }
            is NavKey.Call -> NavEntry(navKey) {
                CallScreen(
                    modifier = modifier,
                    onContacts = {
                        Toast.makeText(context, "New contact clicked", Toast.LENGTH_SHORT).show()
                    },
                    onMore = {
                        Toast.makeText(context, "More clicked", Toast.LENGTH_SHORT).show()
                    }
                )
            }
            is NavKey.Setting -> NavEntry(navKey) {

                SettingScreen(
                    modifier = modifier,
                    onContacts = {
                        Toast.makeText(context, "New contact clicked", Toast.LENGTH_SHORT).show()
                    },
                    onMore = {
                        Toast.makeText(context, "More clicked", Toast.LENGTH_SHORT).show()
                    },
                   onClick = {key ->
                       backStack.add(key)

                   },
                    onProfile = {
                        backStack.add(NavKey.SettingProfile)

                    }
                )
            }
            else -> null // This provider doesn't handle other keys (Chat, Call, Setting, Contact)
        }
    }
}
