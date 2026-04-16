package com.infophone.chat.presentation.message

import android.net.Uri
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.infophone.chat.common.FullScreenImageBox
import com.infophone.chat.common.MessageBubble
import com.infophone.chat.common.MessageComposer
import com.infophone.chat.common.MessageEmpty
import com.infophone.chat.common.MessageMenuFocus
import com.infophone.chat.common.MessageTopBar
import com.infophone.chat.presentation.model.Message
import com.infophone.chat.presentation.model.SelectedMessageState
import com.infophone.chat.util.AudioWaveform
import com.infophone.chat.util.shareMixedContent
import com.infophone.chat.util.shareText
import com.infophone.common.CameraResult
import com.infophone.common.ExplorerResult
import com.infophone.common.ExplorerType
import com.infophone.navigation.NavKey
import com.infophone.navigation.TopLevelBackStack
import com.infophone.ui.R
import com.infophone.ui.theme.Black80
import com.infophone.ui.theme.Gray100
import com.infophone.ui.theme.Gray600
import com.infophone.ui.theme.Green80
import com.infophone.ui.theme.Indigo900
import com.infophone.ui.theme.White
import kotlinx.coroutines.delay
import java.io.File

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MessageScreen(
    modifier: Modifier,
    onBack: () -> Unit,
    onContactInfo: () -> Unit,
    onContactForward: () -> Unit,
    onVideoCall: (String) -> Unit,
    onAudioCall: (String) -> Unit,
    onCamera: () -> Unit,
    onAttachment: (ExplorerType) -> Unit,
    backStack: TopLevelBackStack<NavKey>,
    viewModel: MessageViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var isReplyOn by remember { mutableStateOf(false) }
    var isReplyOnMessage by remember { mutableStateOf<Message?>(null) }

    var selectedMessageState by remember { mutableStateOf<SelectedMessageState?>(null) }
    var isSelectMode by remember { mutableStateOf(false) }
    val selectedMessages = remember { mutableStateListOf<Int>() }
    var isRecordingMode by remember { mutableStateOf(false) }

    val messages by viewModel.messages.collectAsStateWithLifecycle()

    // State to track which image is being zoomed
    var zoomedImageUri by remember { mutableStateOf<Uri?>(null) }

    // 1. Collect the results map from the backstack
    val results by backStack.results.collectAsStateWithLifecycle()

    // 2. React to the specific key
    LaunchedEffect(results) {
        val explorerResult = results["EXPLORER_RESULT"] as? ExplorerResult
        if (explorerResult != null) {
            val data = explorerResult.data

            when (explorerResult.type) {
                ExplorerType.GALLERY -> {
                    val uris = (data as? List<*>)?.filterIsInstance<Uri>() ?: emptyList()
                    if (uris.isNotEmpty()) viewModel.sendImageMessage(uris, "")
                }
                ExplorerType.AUDIO -> {
//                    (data as? Uri)?.let { viewModel.sendAudioMessage(it) }
                }
                ExplorerType.DOCUMENT -> {
                    (data as? Uri)?.let { viewModel.sendDocumentMessage(it) }
                }
                ExplorerType.CONTACT -> {
//                    (data as? Uri)?.let { viewModel.sendContactMessage(it) }
                }
                ExplorerType.LOCATION -> {
                    // Handle location object if you implemented a picker
                }
                ExplorerType.CAMERA -> {
                    // Handled separately or via CameraResult
                }
            }
            backStack.clearResult("EXPLORER_RESULT")
        }

        val mediaResult = results["CAMERA_MEDIA_KEY"] as? CameraResult
        if (mediaResult != null) {
            // 3. Process the data in the ViewModel
            viewModel.sendImageMessage(
                uris = mediaResult.uris,
                message = mediaResult.text
            )
            // 4. IMPORTANT: Clear the result so it doesn't re-trigger
            // if the user rotates the phone or navigates away/back.
            backStack.clearResult("CAMERA_MEDIA_KEY")
        }
    }

    // Root is a Box to allow full-screen overlays
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                // Apply blur only when a message is selected
                .blur(if (selectedMessageState != null) 15.dp else 0.dp)
        ) {
            MessageTopBar(
                onBack,
                onContactInfo,
                onVideoCall,
                onAudioCall,
                true
            )

            if (messages.isEmpty()) {
                MessageEmpty(modifier = Modifier.weight(1f))
            } else {
                MessageBody(
                    modifier = Modifier.weight(1f),
                    messages,
                    selectedMessageState = selectedMessageState,
                    onLongPress = { newState ->
                        selectedMessageState = newState
                    },
                    onReply = { message ->
//                    viewModel.reply(message)
                        isReplyOn = true
                        isReplyOnMessage = message
                        Log.d("MessageScreen", "Reply to: ${message.text}")
                    },
                    isSelectMode = isSelectMode,
                    selectedMessages = selectedMessages,
                    onToggleSelection = { id ->
                        // TOGGLE LOGIC:
                        if (selectedMessages.contains(id)) {
                            selectedMessages.remove(id)
                            // Optional: exit select mode if last item removed
                            if (selectedMessages.isEmpty()) isSelectMode = false
                        } else {
                            selectedMessages.add(id)
                        }
                    },
                    onZoomedImage = {
                        zoomedImageUri = it
                    }
                )
            }

            if (isSelectMode) {
                // BOTTOM FORWARD COMPOSER
                ForwardComposer(
                    selectedMessages.count(),
                    onContactForward,
                    onShareIntent = {
                        // 1. Filter the selected message objects
                        val selectedObjects = messages.filter { it.id in selectedMessages }

                        // 2. Flatten the nested lists: List<List<Uri>> becomes List<Uri>
                        val uris = selectedObjects.flatMap { it.imageUris }

                        // 3. Combine texts from all selected messages
                        val combinedText = selectedObjects
                            .filter { it.text.isNotEmpty() }
                            .joinToString("\n") { it.text }

                        // 4. Execution Logic
                        if (uris.isEmpty()) {
                            // No images selected, share text only
                            shareText(context, combinedText)
                        } else {
                            // Images (and possibly text) selected, use mixed content sharer
                            shareMixedContent(
                                context,
                                uris, // Now a flat list of URIs
                                combinedText
                            )
                        }
                    }
                )
            } else if (isRecordingMode) {
                RecordingComposer(
                    viewModel,
                    onRecordingDelete = { isRecordingMode = false },
                    onRecordingSend = { isRecordingMode = false }
                )
            } else {
                // BOTTOM REPLY BOX
                ReplyComposer(
                    message = if (isReplyOn) isReplyOnMessage else null,
                    onReplyClose = {
                        isReplyOn = false
                        isReplyOnMessage = null
                    }
                )

                // BOTTOM MESSAGE COMPOSER
                MessageComposer(
                    viewModel,
                    onCamera = {
                        onCamera()
                    },
                    onRecording = {
                        isRecordingMode = true
                    },
                    replyingToMessage = if (isReplyOn) isReplyOnMessage else null,
                    onMessageSent = {
                        isReplyOn = false
                        isReplyOnMessage = null
                    },
                    onAttachment = { type ->
                        if (type == ExplorerType.CAMERA) {
                            onCamera()
                        } else {
                            onAttachment(type)
                        }
                    }
                )
            }
        }

        // THE FOCUS OVERLAY
        if (selectedMessageState != null) {
            MessageMenuFocus(
                state = selectedMessageState!!,
                onDismiss = { selectedMessageState = null },
                onAction = {
                    when (it) {
                        "Reply" -> {
                            isReplyOn = true
                            isReplyOnMessage = selectedMessageState!!.message
                        }

                        "Copy" -> {}
                        "Forward" -> {
                            isSelectMode = true
                            selectedMessages.clear()
                            selectedMessages.add(selectedMessageState!!.message.id)
//                            selectedMessageState = null // Close the focus UI
                        }

                        "Starred" -> {}
                        "Delete" -> {}
                        else -> {}
                    }
                }
            )
        }

        BackHandler(enabled = isSelectMode) {
            isSelectMode = false
            selectedMessages.clear()
        }

        // The Full-screen Zoom Overlay
        zoomedImageUri?.let { uri ->
            FullScreenImageBox(
                uri = uri,
                onDismiss = { zoomedImageUri = null }
            )
        }
    }
}

@Composable
fun MessageBody(
    modifier: Modifier,
    messages: List<Message>,
    selectedMessageState: SelectedMessageState?,
    onLongPress: (SelectedMessageState) -> Unit,
    onReply: (Message) -> Unit,
    isSelectMode: Boolean,
    selectedMessages: SnapshotStateList<Int>,
    onToggleSelection: (Int) -> Unit,
    onZoomedImage: (Uri) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Auto-scroll to bottom when new message arrives
        val listState = rememberLazyListState()
        // When reverseLayout is true, item 0 is the BOTTOM of the list.
        // We scroll to 0 to show the latest message.
        LaunchedEffect(messages.size) {
            if (messages.isNotEmpty()) {
                listState.animateScrollToItem(0)
            }
        }

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            // CHANGE THIS: Arrangement.Bottom keeps items at the bottom when the list is short
            verticalArrangement = Arrangement.spacedBy(13.dp, Alignment.Bottom),
            reverseLayout = true
        ) {
            items(messages, key = { it.id }) { message ->
                val isFocused = selectedMessageState?.message?.id == message.id
                val isSelected = selectedMessages.contains(message.id)
                val interactionSource = remember { MutableInteractionSource() }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .combinedClickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = {
                                if (isSelectMode) onToggleSelection(message.id)
                            },
                            onLongClick = { /* Trigger select mode here if not active */ }
                        )
                        .padding(vertical = 4.dp),
//                    horizontalArrangement = if (message.isSender) Arrangement.End else Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 1. SWINGING SELECTION BUTTON
                    AnimatedVisibility(
                        visible = isSelectMode,
                        enter = expandHorizontally() + fadeIn(),
                        exit = shrinkHorizontally() + fadeOut()
                    ) {
                        SelectionCircle(
                            isSelected = isSelected,
                            modifier = Modifier.padding(start = 0.dp, end = 16.dp)
                        )
                    }
                    // 2. THE ACTUAL BUBBLE
                    // We keep your existing logic for Start/End alignment
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = if (message.isSender) Alignment.CenterEnd else Alignment.CenterStart
                    ) {
                        MessageBubble(
                            message = message,
                            // Optional: Hide original bubble when focused so overlay takes over
                            modifier = Modifier
                                .alpha(if (isFocused) 0f else 1f),
                            /*.graphicsLayer {
                                alpha = if (isSelectMode && !isSelected) 0.7f else 1.0f
                            }*/
                            onLongClick = { offset, size ->
                                Log.d("MessageBubble", "Long click detected")
                                onLongPress(SelectedMessageState(message, offset, size))
                            },
                            isReaction = false,
                            onReply = onReply,
                            isSelectMode = isSelectMode,
                            onToggleSelection = { onToggleSelection(message.id) },
                            onImageClick = { uri ->
                                onZoomedImage(uri)
                            }
                        )
                    }
                }
            }
        }

        var showChip by remember { mutableStateOf(false) }
        LaunchedEffect(listState.isScrollInProgress) {
            if (listState.isScrollInProgress) {
                showChip = true
            } else {
                delay(1000) // Stay visible for 1 second after stop
                showChip = false
            }
        }

        AnimatedVisibility(
            visible = showChip,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            DateChip("Today")
        }
    }
}

@Composable
fun DateChip(date: String) {
    if (date.isEmpty()) return
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Surface(
            color = Gray600.copy(alpha = 0.1f),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = date,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                style = MaterialTheme.typography.labelSmall,
                color = Gray600
            )
        }
    }
}

@Composable
fun ReplyComposer(
    message: Message?,
    onReplyClose: () -> Unit
) {
    AnimatedVisibility(
        visible = message != null,
        enter = slideInVertically(
            initialOffsetY = { it / 2 }, // subtle slide
            animationSpec = tween(
                durationMillis = 280,
                easing = FastOutSlowInEasing
            )
        ) + fadeIn(
            animationSpec = tween(200)
        ) + scaleIn(
            initialScale = 0.98f,
            animationSpec = tween(280)
        ),
        exit = slideOutVertically(
            targetOffsetY = { it / 2 },
            animationSpec = tween(
                durationMillis = 220,
                easing = FastOutSlowInEasing
            )
        ) + fadeOut(
            animationSpec = tween(150)
        ) + scaleOut(
            targetScale = 0.98f,
            animationSpec = tween(220)
        )
    ) {
        message?.let {
            ReplyComposerContent(
                message = it,
                onReplyClose = onReplyClose
            )
        }
    }
}

@Composable
private fun ReplyComposerContent(
    message: Message,
    onReplyClose: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gray100)
            .padding(horizontal = 16.dp)
            .padding(top = 12.dp, bottom = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Indigo900, RoundedCornerShape(10.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (message.imageUris.isNotEmpty()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(message.imageUris.first())
                        .crossfade(true)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(8.dp)),
//                .border(2.dp, Color.Gray, CircleShape)
                )
                Spacer(modifier = Modifier.width(10.dp))
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Aarav Mehta",
                    color = White,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))

                val displayPreviewText = message.text.ifEmpty {
                    if (message.imageUris.isNotEmpty()) "Photo" else ""
                }
                Text(
                    text = displayPreviewText,
                    color = White,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Light
                )
            }
            IconButton(
                modifier = Modifier
                    .size(20.dp)
                    .background(White.copy(alpha = 0.5f), CircleShape)
                    .padding(4.dp),
                onClick = { onReplyClose() }
            ) {
                Icon(Icons.Default.Clear, contentDescription = "Close", tint = White)
            }
        }
    }
}

@Composable
fun SelectionCircle(
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val backgroundColor by animateColorAsState(
        if (isSelected) Green80 else Color.Transparent, label = ""
    )
    val borderColor = if (isSelected) Green80 else Color.LightGray

    Box(
        modifier = modifier
            .size(22.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .border(2.dp, borderColor, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun ForwardComposer(messageCount: Int, onContactForward: () -> Unit, onShareIntent: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onContactForward() }) {
                Icon(
                    painter = painterResource(R.drawable.ic_forward),
                    contentDescription = "Forward", tint = Indigo900
                )
            }

            Text(
                modifier = Modifier
                    .weight(1f),
                text = "$messageCount Selected",
                style = MaterialTheme.typography.bodyMedium,
                color = Indigo900,
                textAlign = TextAlign.Center
            )

            IconButton(onClick = { onShareIntent() }) {
                Icon(
                    painter = painterResource(R.drawable.ic_export),
                    contentDescription = "Send", tint = Indigo900
                )
            }
        }
    }
}

@Composable
fun RecordingComposer(
    viewModel: MessageViewModel,
    onRecordingDelete: () -> Unit,
    onRecordingSend: () -> Unit
) {
    val context = LocalContext.current
    val pcmFile = remember { File(context.cacheDir, "temp_recording.pcm") }

    LaunchedEffect(Unit) {
        viewModel.startRecording(pcmFile)
    }

    // 3. CLEANUP: Ensure recorder stops if user leaves the screen unexpectedly
    DisposableEffect(Unit) {
        onDispose { viewModel.deleteRecording(pcmFile) }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gray100)
            .navigationBarsPadding()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp, bottom = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "%02d:%02d".format(viewModel.duration / 60, viewModel.duration % 60),
                    style = MaterialTheme.typography.titleSmall,
                    color = Black80
                )
                Spacer(modifier = Modifier.width(12.dp))
                // Now uses the optimized "scrolling" canvas
                AudioWaveform(
                    amplitudes = viewModel.amplitudes,
                    progress = 0f,
                    modifier = Modifier
                        .weight(1f)
                        .height(34.dp),
                    activeColor = Indigo900,
                    inactiveColor = Gray600
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // DELETE BUTTON
                IconButton(onClick = {
                    viewModel.deleteRecording(pcmFile)
                    onRecordingDelete()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_trash_2),
                        contentDescription = "Delete", tint = Color(0xFFB52224)
                    )
                }

                // PLAY/PAUSE TOGGLE
                IconButton(onClick = { viewModel.togglePause() }) {
                    Icon(
                        if (viewModel.isPaused) Icons.Default.PlayArrow else Icons.Default.Pause,
                        contentDescription = "Play Pause", tint = Color.Black.copy(alpha = 0.8f)
                    )
                }

                // SEND BUTTON (Finalize and Convert)
                FilledIconButton(
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = Indigo900
                    ),
                    onClick = {
                        viewModel.stopAndSend(pcmFile, context.filesDir) { file, amps ->
                            onRecordingSend()
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_right),
                        contentDescription = "Send", tint = White
                    )
                }
            }
        }
    }
}