package com.infophone.chat.presentation.model

import androidx.compose.ui.graphics.Color
import com.infophone.common.ExplorerResult
import com.infophone.common.ExplorerType

data class Attachment(
    val type: ExplorerType,
    val name: String,
    val icon: Int, // Your R.drawable.id
    val color: Color
)