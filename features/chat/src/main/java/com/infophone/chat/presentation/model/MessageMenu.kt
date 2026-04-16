package com.infophone.chat.presentation.model

import androidx.compose.ui.graphics.Color
import com.infophone.chat.util.MessageMenuType

data class MessageMenu(
    val type: MessageMenuType,
    val name: String,
    val icon: Int, // Your R.drawable.id
    val color: Color
)