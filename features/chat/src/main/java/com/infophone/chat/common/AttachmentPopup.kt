package com.infophone.chat.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.infophone.chat.presentation.model.Attachment
import com.infophone.common.ExplorerResult
import com.infophone.common.ExplorerType
import com.infophone.ui.R
import com.infophone.ui.theme.Black80

@Composable
fun AttachmentPopup(
    onDismiss: () -> Unit,
    onOptionClick: (ExplorerType) -> Unit
) {
    val options = listOf(
        Attachment(ExplorerType.DOCUMENT, "Document",R.drawable.ic_document, Color(0xFFFF981F)),
        Attachment(ExplorerType.CAMERA, "Camera", R.drawable.ic_camera, Color(0xFF009689)),
        Attachment(ExplorerType.GALLERY, "Gallery", R.drawable.ic_image, Color(0xFF9D28AC)),
        Attachment(ExplorerType.AUDIO, "Audio", R.drawable.ic_headphones, Color(0xFF4AAF57)),
        Attachment(ExplorerType.LOCATION, "Location", R.drawable.ic_location, Color(0xFFFF5726)),
        Attachment(ExplorerType.CONTACT, "Contact", R.drawable.ic_profile, Color(0xFF1A96F0))
    )

    // Popup appears anchored to where it's called
    Popup(
        alignment = Alignment.BottomCenter,
        onDismissRequest = onDismiss,
        properties = PopupProperties(focusable = true)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 70.dp), // Space above composer
//                .popupShadow(),
            shape = RoundedCornerShape(34.dp),
            elevation = CardDefaults.cardElevation(0.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
//            border = CardDefaults.outlinedCardBorder(true).copy((0.1).dp)
        ) {
            // Grid Layout for Options
            Column(modifier = Modifier.padding(16.dp)) {
                // We split the list into two rows of three
                options.chunked(3).forEach { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        rowItems.forEach { option ->
                            AttachmentItem(option) { onOptionClick(option.type) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AttachmentItem(option: Attachment, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(62.dp)
                .background(option.color, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = option.icon),
                contentDescription = option.name,
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = option.name,
            style = MaterialTheme.typography.bodyMedium,
            color = Black80
        )
    }
}