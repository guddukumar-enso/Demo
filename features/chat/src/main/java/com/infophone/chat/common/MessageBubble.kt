package com.infophone.chat.common

import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Headset
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.infophone.chat.presentation.model.Message
import com.infophone.chat.util.AudioWaveform
import com.infophone.chat.util.MessageType
import com.infophone.ui.R
import com.infophone.ui.theme.Gray100
import com.infophone.ui.theme.Gray600
import com.infophone.ui.theme.Indigo900
import com.infophone.ui.theme.White
import kotlinx.coroutines.launch

@Composable
fun MessageBubble(
    modifier: Modifier = Modifier,
    message: Message,
    onLongClick: ((Offset, IntSize) -> Unit)? = null,
    isReaction: Boolean,
    onReply: (Message) -> Unit,
    isSelectMode: Boolean = false,
    onToggleSelection: () -> Unit = {},
    onImageClick: (Uri) -> Unit
) {
    var coordinates: LayoutCoordinates? = null
    // Create an interaction source to track the clicks
    val interactionSource = remember { MutableInteractionSource() }

    // Swipe State
    val offsetX = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val haptic = LocalHapticFeedback.current

    val replyThreshold = 120f
    val maxPull = 250f

    val bubbleColor = if (message.isSender)
        Color(0xFFF8F8FF) // Sender
    else
        Color(0xFFEBEBEB) // Receiver (light gray)

    val bubbleShape = if (message.isSender)
        RoundedCornerShape(
            topStart = 28.dp,
            topEnd = 28.dp,
            bottomStart = 28.dp,
            bottomEnd = 8.dp
        )
    else
        RoundedCornerShape(
            topStart = 28.dp,
            topEnd = 28.dp,
            bottomEnd = 28.dp,
            bottomStart = 8.dp
        )

    Box(
        modifier = modifier // Use the modifier passed from parent
            .graphicsLayer {
                translationX = offsetX.value
            }
            .pointerInput(message.isSender) {
                // If you want ONLY receivers to be swipable, check !message.isSender
                if (message.isSender) return@pointerInput

                detectHorizontalDragGestures(
                    onHorizontalDrag = { change, dragAmount ->
                        change.consume()
                        scope.launch {
                            val totalDrag =
                                (offsetX.value + dragAmount * 0.5f).coerceIn(0f, maxPull)
                            offsetX.snapTo(totalDrag)
                        }
                    },
                    onDragEnd = {
                        if (offsetX.value >= replyThreshold) {
                            Log.d("MessageBubble", "Forward reply detected")
                            onReply(message)
                        }
                        scope.launch {
                            offsetX.animateTo(
                                0f,
                                spring(Spring.DampingRatioMediumBouncy, Spring.StiffnessLow)
                            )
                        }
                    }
                )
            }
    ) {
        Column(
            modifier = modifier
//            .width(IntrinsicSize.Min)
                .onGloballyPositioned { coordinates = it }
                .combinedClickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        if (isSelectMode) {
                            onToggleSelection() // Toggle if in selection mode
                        } else {
                            /* Handle normal bubble click (e.g., open image) */
                        }
                    },
                    onLongClick = {
                        coordinates?.let {
                            val position = it.positionInWindow()
                            val size = it.size
                            onLongClick?.invoke(position, size)
                        }
                    }
                )
                .widthIn(max = 280.dp) // Maximum constraint
                .background(bubbleColor, bubbleShape)
                .padding(start = 16.dp, top = 14.dp, end = 16.dp, bottom = 14.dp),
            horizontalAlignment = Alignment.End // This pushes the time Row to the end
        ) {
            when (message.type) {
                MessageType.TEXT -> { }
                MessageType.IMAGE -> AttachImage(uris = message.imageUris, onImageClick = {})
                MessageType.RECORDING -> AttachRecording(message)
                MessageType.AUDIO -> AttachAudio(
                    duration = message.duration ?: "0:00",
                    fileSize = message.fileSize ?: ""
                )

                MessageType.VIDEO -> AttachVideo(
                    uri = message.imageUris.firstOrNull(),
                    duration = message.duration ?: "0:00"
                )

                MessageType.DOCUMENT -> AttachDocument(
                    fileName = message.fileName ?: "Document",
                    size = message.fileSize ?: ""
                )

                MessageType.LOCATION -> AttachLocation(placeName = message.text)
                MessageType.CONTACT -> AttachContact(name = message.text)
            }

            // Render Image if it exists
            /*if (message.imageUris.isNotEmpty()) {
                AttachImage(uris = message.imageUris, onImageClick)
            }

            if (message.audioFile != null && message.amplitudes.isNotEmpty()) {
                AttachVoice(message)
            }*/

            message.repliedTo?.let { originalMessage ->
                AttachReply(originalMessage)
                Spacer(modifier = Modifier.height(8.dp))
            }

            if (message.text.isNotEmpty()) {
                AttachText(message)
            }

            Spacer(modifier = Modifier.height(4.dp))

            // No fillMaxWidth() here! This keeps the bubble "compressed"
            AttachDeliveryStatus(message)
        }

        if (isReaction) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .offset(x = 11.dp, y = 8.dp) // push slightly outside if you want
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(Color.White),      // thin white border ring
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "\uD83D\uDC4D",
                    fontSize = 11.sp
                )
            }
        }
    }
}

@Composable
fun AttachText(message: Message) {
    Text(
        text = message.text,
        modifier = Modifier
            .wrapContentWidth(),
        color = Color(0xFF1C1C1E),
        fontSize = 15.sp,
        lineHeight = 20.sp,
        textAlign = TextAlign.Start
    )
}

@Composable
fun AttachImage(uris: List<Uri>, onImageClick: (Uri) -> Unit) {
    val shape = RoundedCornerShape(12.dp)

    // Define layout based on count
    when (uris.size) {
        1 -> {
            AsyncImage(
                model = uris[0],
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(shape)
                    .clickable { onImageClick(uris[0]) }, // Trigger the zoom state,
                contentScale = ContentScale.Crop
            )
        }

        2 -> {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                uris.forEach { uri ->
                    AsyncImage(
                        model = uri,
                        contentDescription = null,
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .clip(shape)
                            .clickable { onImageClick(uri) },
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        else -> {
            // Flexible grid for 3 or more images
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                // Top row (up to 2 images)
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    uris.take(2).forEach { uri ->
                        AsyncImage(
                            model = uri,
                            contentDescription = null,
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .clip(shape)
                                .clickable { onImageClick(uri) },
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                // Bottom row if more than 2
                if (uris.size > 2) {
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        // Take the remaining images (limit to 2 for simplicity, or add a "+X" overlay)
                        uris.drop(2).take(2).forEachIndexed { index, uri ->
                            Box(modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)) {
                                AsyncImage(
                                    model = uri,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(shape)
                                        .clickable { onImageClick(uri) },
                                    contentScale = ContentScale.Crop
                                )
                                // Overlay for + count if more than 4 images total
                                if (index == 1 && uris.size > 4) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color.Black.copy(0.5f))
                                            .clip(shape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            "+${uris.size - 4}",
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
fun AttachReply(message: Message) {
    // We use an Intrinsic Height Row to make the vertical bar match the content height
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min) // Critical: makes the vertical bar match text height
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Black.copy(alpha = 0.05f)) // Subtle transparent overlay
    ) {
        // 1. The Accent Vertical Bar
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(4.dp)
                .background(Indigo900) // Or a dynamic color based on sender
        )

        Row(
            modifier = Modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = if (message.isSender) "You" else "Aarav Mehta",
                    color = Indigo900,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                val displayPreviewText = message.text.ifEmpty {
                    if (message.imageUris.isNotEmpty()) "Photo" else "Attachment"
                }

                Text(
                    text = displayPreviewText,
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // 2. Small Thumbnail if it's an image message
            if (message.imageUris.isNotEmpty()) {
                Spacer(modifier = Modifier.width(8.dp))
                AsyncImage(
                    model = message.imageUris.first(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(4.dp))
                )
            }
        }
    }
}

@Composable
fun AttachRecording(message: Message) {
    // 1. Audio State Management
    val mediaPlayer = remember { MediaPlayer() }
    var isPlaying by remember { mutableStateOf(false) }
    var currentProgress by remember { mutableFloatStateOf(0f) }

    // 2. Sample the amplitudes to a fixed size (e.g., 35 bars) for the UI
    val displayAmplitudes = remember(message.amplitudes) {
        message.amplitudes
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Play/Pause Button
        IconButton(
            onClick = {
                if (isPlaying) {
                    mediaPlayer.pause()
                    isPlaying = false
                } else {
                    // If audio finished, reset it
                    if (currentProgress >= 0.99f) {
                        currentProgress = 0f
                        mediaPlayer.reset()
                    }

                    if (!mediaPlayer.isPlaying && currentProgress == 0f) {
                        mediaPlayer.setDataSource(message.audioFile?.absolutePath)
                        mediaPlayer.prepare()
                    }

                    mediaPlayer.start()
                    isPlaying = true

                    mediaPlayer.setOnCompletionListener {
                        isPlaying = false
                        currentProgress = 1f
                    }
                }
            },
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = null,
                tint = if (message.isSender) Indigo900 else Indigo900
            )
        }

        // Waveform Canvas
        AudioWaveform(
            amplitudes = displayAmplitudes,
            progress = currentProgress,
            modifier = Modifier
                .weight(1f)
                .height(35.dp)
                .padding(horizontal = 8.dp),
            activeColor = Indigo900,
            inactiveColor = Gray600
        )
    }
}

@Composable
fun AttachAudio(
    title: String = "Recording_2024.mp3",
    duration: String = "2:30",
    fileSize: String = "1.2 MB"
) {
    var isPlaying by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF009688))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 1. Play/Pause Button
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(Color.White.copy(alpha = 0.2f), CircleShape)
                .clickable { isPlaying = !isPlaying },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = if (isPlaying) "Pause" else "Play",
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // 2. Audio Details and Progress
        Column(modifier = Modifier.weight(1f)) {
            // Audio Title
            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Seek Bar / Progress
            Slider(
                value = 0f,
                onValueChange = {},
                modifier = Modifier
                    .height(12.dp)
                    .fillMaxWidth(),
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTrackColor = Color.White,
                    inactiveTrackColor = Color.White.copy(alpha = 0.3f)
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Metadata Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = duration,
                    fontSize = 11.sp,
                    color = Color.White.copy(alpha = 0.7f)
                )
                Text(
                    text = fileSize,
                    fontSize = 11.sp,
                    color = Color.White.copy(alpha = 0.7f)
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        // 3. Audio Type Icon (Headphones or Music Note)
        Icon(
            imageVector = Icons.Default.Headset,
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.5f),
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.Top)
        )
    }
}

@Composable
fun AttachVideo(uri: Uri?, duration: String = "0:15") {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        AsyncImage(
            model = uri,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        )
        // Play Button Overlay
        Icon(
            imageVector = Icons.Default.PlayCircleFilled,
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.8f),
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.Center)
        )
        // Duration Tag
        Text(
            text = duration,
            color = Color.White,
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp)
                .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
                .padding(horizontal = 4.dp)
        )
    }
}

@Composable
fun AttachDocument(fileName: String = "Invoice_Dec.pdf", size: String = "450 KB") {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF0F0F0), RoundedCornerShape(8.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Description,
            contentDescription = null,
            tint = Color(0xFFE53935), // Red for PDF style
            modifier = Modifier.size(36.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = fileName,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(text = size, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Composable
fun AttachLocation(placeName: String = "Central Park, NY") {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
    ) {
        // Map Thumbnail Placeholder
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(Color(0xFFCDE4FF))) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(32.dp)
            )
        }
        Text(
            text = placeName,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun AttachContact(name: String = "John Doe") {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.LightGray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = null, tint = Color.White)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = name, style = MaterialTheme.typography.titleMedium)
        }
        HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
        Text(
            text = "View Contact",
            color = Color(0xFF075E54), // WhatsApp Teal
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { }
                .padding(vertical = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun AttachDeliveryStatus(message: Message) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = message.time,
            fontSize = 11.sp,
            color = Color.Gray
        )

        // Optional: Add WhatsApp-style double checks if isSender
        if (message.isSender) {
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_tick_double),
                contentDescription = null,
                modifier = Modifier.size(14.dp),
                tint = Color(0xFF17A31A)
            )
        }
    }
}
