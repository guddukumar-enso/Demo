package com.infophone.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.infophone.navigation.NavDisplay
import com.infophone.navigation.NavEntry
import com.infophone.navigation.NavEntryProvider
import com.infophone.navigation.NavKey
import com.infophone.navigation.TopLevelBackStack

@Composable
fun AppScreen(
    authNavEntryProvider: NavEntryProvider,
    homeNavEntryProvider: NavEntryProvider,
    contactNavEntryProvider: NavEntryProvider,
    chatNavEntryProvider: NavEntryProvider,
    callNavEntryProvider: NavEntryProvider,
    mediaNavEntryProvider: NavEntryProvider,
    groupNavEntryProvider: NavEntryProvider,
    settingNavEntryProvider: NavEntryProvider,
    modifier: Modifier = Modifier,
    backStack: TopLevelBackStack<NavKey>
) {
    val context = LocalContext.current
    // Combine all providers into a single function
    val entryProvider: (NavKey) -> NavEntry<NavKey> = remember {
        { key ->
            // Check providers in order (e.g., Auth flow first, then Home flow)
            authNavEntryProvider.getEntry(key, modifier)
                ?: homeNavEntryProvider.getEntry(key, modifier)
                ?: contactNavEntryProvider.getEntry(key, modifier)
                ?: chatNavEntryProvider.getEntry(key, modifier)
                ?: callNavEntryProvider.getEntry(key, modifier)
                ?: mediaNavEntryProvider.getEntry(key, modifier)
                ?: groupNavEntryProvider.getEntry(key, modifier)
                ?: settingNavEntryProvider.getEntry(key, modifier)
                ?: throw IllegalArgumentException("Unknown NavKey: $key")
        }
    }

    NavDisplay(
        backStack = backStack.backStack,
        onBack = { backStack.removeLast() },
        entryProvider = entryProvider
    )
}