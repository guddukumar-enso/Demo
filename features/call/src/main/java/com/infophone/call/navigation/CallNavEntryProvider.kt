package com.infophone.call.navigation

import android.content.Context
import androidx.compose.ui.Modifier
import com.infophone.call.presentation.CallingScreen
import com.infophone.navigation.NavEntry
import com.infophone.navigation.NavEntryProvider
import com.infophone.navigation.NavKey
import com.infophone.navigation.TopLevelBackStack
import javax.inject.Inject

class CallNavEntryProvider @Inject constructor(
    private val backStack: TopLevelBackStack<NavKey>,
    private val context: Context
): NavEntryProvider {
    override fun getEntry(navKey: NavKey, modifier: Modifier): NavEntry<NavKey>? {
        return when (navKey) {
            // Bottom Nav Bar Screens
            is NavKey.Calling -> NavEntry(navKey) {
                CallingScreen(
                    modifier = modifier,
                    onBack = {
                        backStack.removeLast()
                    }
                )
            }
            else -> null // This provider doesn't handle other keys (Chat, Call, Setting, Contact)
        }
    }
}