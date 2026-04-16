package com.infophone.media.presentation.explorer

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.infophone.common.ExplorerResult
import com.infophone.common.ExplorerType
import com.infophone.navigation.NavKey
import com.infophone.navigation.TopLevelBackStack

@Composable
fun ExplorerScreen(
    modifier: Modifier,
    type: ExplorerType,
    backStack: TopLevelBackStack<NavKey>
) {
    // Helper to close the overlay
    val closeOverlay = { backStack.removeLast() }

    // 1. Unified Result Logic
    val handleResult: (Any) -> Unit = { data ->
        // Wrap the data with the type so the receiver knows what to do
        val result = ExplorerResult(type = type, data = data)
        backStack.setResult("EXPLORER_RESULT", result)
        closeOverlay() // Close instantly on success
    }

    // 1. Document Picker
    val documentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) handleResult(uri) else closeOverlay()
    }

    // 2. Audio Picker
    val audioLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) handleResult(uri) else closeOverlay()
    }

    // 3. Contact Picker
    val contactLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickContact()
    ) { uri ->
        if (uri != null) handleResult(uri) else closeOverlay()
    }

    // 4. Gallery Picker (Modern Photo Picker)
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(maxItems = 10) // We can change it
    ) { uris ->
        if (uris.isNotEmpty()) handleResult(uris) else closeOverlay()
    }

    // 3. Trigger automatically based on type when screen opens
    LaunchedEffect(Unit) {
        when(type) {
            ExplorerType.DOCUMENT -> documentLauncher.launch("application/pdf")
            ExplorerType.CAMERA -> {}
            ExplorerType.GALLERY -> photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            ExplorerType.AUDIO -> audioLauncher.launch("audio/*")
            ExplorerType.LOCATION -> {}
            ExplorerType.CONTACT -> contactLauncher.launch(null)
        }
    }

    // The "Invisible" Dialog
    Dialog(
        onDismissRequest = { backStack.removeLast() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        // We use a Box with a very slight dim or completely transparent background
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.01f)) // Almost invisible but catches touch
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { closeOverlay() }
        )
    }
}