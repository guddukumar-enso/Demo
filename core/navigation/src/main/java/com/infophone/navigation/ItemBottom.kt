package com.infophone.navigation

import androidx.compose.ui.graphics.painter.Painter

data class ItemBottom(
    val route: NavKey,
    val iconSelected: Painter,
    val iconUnselected: Painter,
    val title: String
)