package com.infophone.chat.navigation

import android.content.Context
import androidx.compose.ui.Modifier
import com.infophone.chat.presentation.message.MessageScreen
import com.infophone.navigation.NavEntry
import com.infophone.navigation.NavEntryProvider
import com.infophone.navigation.NavKey
import com.infophone.navigation.TopLevelBackStack
import javax.inject.Inject

class ChatNavEntryProvider @Inject constructor(
    private val backStack: TopLevelBackStack<NavKey>,
    private val context: Context
): NavEntryProvider {
    override fun getEntry(navKey: NavKey, modifier: Modifier): NavEntry<NavKey>? {
        return when (navKey) {
            // Bottom Nav Bar Screens
            is NavKey.Message -> NavEntry(navKey) {
                MessageScreen(
                    modifier = modifier,
                    onBack = {
                        backStack.removeLast()
                    },
                    onContactInfo = {
                        backStack.add(NavKey.ContactInfo)
                    },
                    onContactForward = {
                        backStack.add(NavKey.Contact)
                    },
                    onVideoCall = { type ->
                        backStack.add(NavKey.Calling(type))
                    },
                    onAudioCall = { type ->
                        backStack.add(NavKey.Calling(type))
                    },
                    onCamera = {
                        backStack.add(NavKey.Camera)
                    },
                    onAttachment = { type ->
                        backStack.add(NavKey.Explorer(type))
                    },
                    backStack
                )
            }
            else -> null // This provider doesn't handle other keys (Chat, Call, Setting, Contact)
        }
    }
}