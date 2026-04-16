package com.infophone.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.infophone.ui.theme.Indigo900
import com.infophone.ui.theme.White

@Composable
fun StatusBarTheme(darkIcons: Boolean = true, color: Color = White) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = color,
            darkIcons = darkIcons
        )
    }
}