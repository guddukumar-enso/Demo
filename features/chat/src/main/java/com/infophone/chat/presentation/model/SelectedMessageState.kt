package com.infophone.chat.presentation.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize

data class SelectedMessageState(
    val message: Message,
    val offset: Offset, // Position on screen
    val size: IntSize   // Size of the bubble
)
