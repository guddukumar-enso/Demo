package com.infophone.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class NavEntry<T : NavKey>(
    val key: T,
    val content: @Composable () -> Unit
)

@Composable
fun NavDisplay(
    backStack: List<NavKey>,
    onBack: () -> Unit,
    entryProvider: (NavKey) -> NavEntry<NavKey>
) {
    BackHandler(enabled = backStack.size > 1) {
        onBack()
    }

//    val currentEntry = entryProvider(backStack.last())
//    currentEntry.content()

    // We wrap in a Box to allow Z-axis stacking
    Box(modifier = Modifier.fillMaxSize()) {
        val lastIndex = backStack.lastIndex

        // Logic: If the top screen is an Explorer/Camera,
        // we must also render the screen underneath it.
        val showPreviousUnderneath = backStack.size > 1 &&
                (backStack.last() is NavKey.Explorer || backStack.last() is NavKey.Camera)

        if (showPreviousUnderneath) {
            val previousKey = backStack[lastIndex - 1]
            val previousEntry = entryProvider(previousKey)
            previousEntry.content()
        }

        // Always render the current (top) entry
        val currentKey = backStack.last()
        val currentEntry = entryProvider(currentKey)

        currentEntry.content()
    }
}