package com.infophone.chat.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.emoji2.emojipicker.EmojiPickerView

@Composable
fun EmojiPicker(
    onEmojiSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    AndroidView(
        factory = { context ->
            EmojiPickerView(context).apply {
                // Set the number of columns (usually 7-9 is standard)
                emojiGridColumns = 8
                // Callback when an emoji is clicked
                setOnEmojiPickedListener { item ->
                    onEmojiSelected(item.emoji)
                }
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp) // Standard keyboard height
    )
}