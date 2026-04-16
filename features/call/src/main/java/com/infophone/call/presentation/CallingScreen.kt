package com.infophone.call.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CallingScreen(
    modifier: Modifier,
    onBack: () -> Unit
) {
    Box(
        modifier = modifier
        .fillMaxSize()
        .background(Color.White)
    )
}