package com.infophone.chat.presentation.message


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DemoChatScreen() {
    // A bright Box to verify visibility
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF6200EE)), // Purple background
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "NATIVE UI LOADED",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "This is Jetpack Compose inside Flutter",
                color = Color.White.copy(alpha = 0.8f)
            )
        }
    }
}