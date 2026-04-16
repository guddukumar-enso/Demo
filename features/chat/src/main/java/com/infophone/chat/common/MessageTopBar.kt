package com.infophone.chat.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.infophone.ui.R
import com.infophone.ui.common.StatusBarTheme
import com.infophone.ui.theme.Indigo900

@Composable
fun MessageTopBar(
    onBack: () -> Unit,
    onContactInfo: () -> Unit,
    onVideoCall: (String) -> Unit,
    onAudioCall: (String) -> Unit,
    isOnline: Boolean
) {
    StatusBarTheme(false)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Indigo900)
            .padding(16.dp)
            .statusBarsPadding(), // Ensures padding below status bar
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back Button
        IconButton(onClick = { onBack() }) {
            Icon(painter = painterResource(R.drawable.ic_arrow_left), contentDescription = "Back", tint = Color.White)
        }

        // Profile Picture (Placeholder)
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("")
                    .crossfade(true)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
//                .border(2.dp, Color.Gray, CircleShape)
            )

            if (isOnline) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
//                        .offset(x = 2.dp, y = 2.dp) // push slightly outside if you want
                        .size(14.dp)
                        .clip(CircleShape)
                        .background(Color.White)        // thin white border ring
                ) {
                    Box(
                        modifier = Modifier
                            .padding(2.dp)              // border thickness
                            .fillMaxSize()
                            .clip(CircleShape)
                            .background(Color(0xFF17A31A)) // your Green80
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        // Name and Status
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Aarav Mehta",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Online",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        // Action Icons
        IconButton(onClick = { /*onVideoCall("video")*/ }) {
            Icon(painter = painterResource(R.drawable.ic_chat_video), contentDescription = "Video Call", tint = Color.White)
        }
        IconButton(onClick = { /*onAudioCall("audio")*/ }) {
            Icon(painter = painterResource(R.drawable.ic_chat_audio), contentDescription = "Voice Call", tint = Color.White)
        }
    }
}