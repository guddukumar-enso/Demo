package com.infophone.presentation.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.infophone.presentation.ui.common.TopAppBar

@Composable
fun SettingScreen(
    modifier: Modifier,
    onContacts: () -> Unit,
    onMore: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopAppBar("setting", "Settings", onContacts, onMore)
    }
}