package com.infophone.media.navigation

import android.content.Context
import androidx.compose.ui.Modifier
import com.infophone.common.CameraResult
import com.infophone.media.presentation.camera.CameraScreen
import com.infophone.media.presentation.explorer.ExplorerScreen
import com.infophone.navigation.NavEntry
import com.infophone.navigation.NavEntryProvider
import com.infophone.navigation.NavKey
import com.infophone.navigation.TopLevelBackStack
import javax.inject.Inject

class MediaNavEntryProvider @Inject constructor(
    private val backStack: TopLevelBackStack<NavKey>,
    private val context: Context
): NavEntryProvider {
    override fun getEntry(navKey: NavKey, modifier: Modifier): NavEntry<NavKey>? {
        return when (navKey) {
            // Bottom Nav Bar Screens
            is NavKey.Camera -> NavEntry(navKey) {
                CameraScreen(
                    modifier = modifier,
                    onClose = {
                        backStack.removeLast()
                    },
                    onFinalSend = { uris, message ->
                        // 1. Pack the result
                        val result = CameraResult(uris, message)
                        // 2. Post to backstack mailbox
                        backStack.setResult("CAMERA_MEDIA_KEY", result)
                        // 3. Close the screen
                        backStack.removeLast()
                    }
                )
            }
            is NavKey.Explorer -> NavEntry(navKey) {
                ExplorerScreen(
                    modifier = modifier,
                    type = navKey.type,
                    backStack = backStack
                )
            }
            else -> null // This provider doesn't handle other keys (Chat, Call, Setting, Contact)
        }
    }
}