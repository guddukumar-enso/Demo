package com.infophone.chat.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.chat.presentation.model.MessageMenu
import com.infophone.chat.presentation.model.SelectedMessageState
import com.infophone.chat.util.MessageMenuType
import com.infophone.ui.R
import com.infophone.ui.theme.Black80
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MessageMenuFocus(
    state: SelectedMessageState,
    onDismiss: () -> Unit,
    onAction: (String) -> Unit
) {
    val density = LocalDensity.current
    var isVisible by remember { mutableStateOf(false) }

    // Trigger animation when Composable enters composition
    LaunchedEffect(Unit) { isVisible = true }

    // Dim Background Animation
    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 0.4f else 0f,
        animationSpec = tween(durationMillis = 200),
        label = "alpha"
    )

    // Full screen clickable area to dismiss
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = alpha))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                isVisible = false
                onDismiss()
            }
    ) {
        // 1. Get Screen Dimensions
        val screenWidth = maxWidth
        val screenHeight = maxHeight

        // 2. Convert Bubble position/size from Pixels to DP
        val bubbleX = with(density) { state.offset.x.toDp() }
        val bubbleY = with(density) { state.offset.y.toDp() }
        val bubbleWidth = with(density) { state.size.width.toDp() }
        val bubbleHeight = with(density) { state.size.height.toDp() }

        // 3. Define Menu Dimensions (Constants)
        val menuWidth = 260.dp
        val emojiRowHeight = 44.dp
        val menuHeight = 260.dp
        val totalHeight = menuHeight + emojiRowHeight + 8.dp
        val spacing = 8.dp

        // 4. Calculate Vertical Position (Top vs Bottom)
        // If there is enough space above the bubble, put it on top.
        val showAbove = bubbleY > totalHeight + spacing
        val finalY = if (showAbove) {
            bubbleY - totalHeight - spacing
        } else {
            bubbleY + bubbleHeight + spacing
        }

        // Horizontal position
        val finalX = if (state.message.isSender) {
            // Align END of popup to END of bubble
            (bubbleX + bubbleWidth - menuWidth)
        } else {
            // Align START of popup to START of bubble
            bubbleX
        }.coerceIn(8.dp, screenWidth - menuWidth - 8.dp)

        // The "Focused" Bubble Animation
        val bubbleScale by animateFloatAsState(
            targetValue = if (isVisible) 1.03f else 1f,
            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
            label = "bubbleScale"
        )

        // 6. Render
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // 1. Focused Bubble with slight pop
            Box(
                modifier = Modifier
                    .offset(x = bubbleX, y = bubbleY)
                    .graphicsLayer {
                        scaleX = bubbleScale
                        scaleY = bubbleScale
                    }
            ) {
                MessageBubble(message = state.message, isReaction = false, onReply = {}, onImageClick = {})
            }

            // Popup
            Column(
                modifier = Modifier
                    .offset(finalX, if (!showAbove) finalY else finalY + 16.dp)
                    .width(menuWidth),
                horizontalAlignment = if (state.message.isSender)
                    Alignment.End else Alignment.Start
            ) {
                if (!showAbove) {
                    EmojiReactionRow(
                        onEmojiSelected = {
                            onAction(it)
                            onDismiss()
                        }
                    )
                    Spacer(Modifier.height(8.dp))
                    MessageActionMenu(
                        onMenuSelect = {
                            onAction(it)
                            onDismiss()
                        }
                    )
                } else {
                    MessageActionMenu(
                        onMenuSelect = {
                            onAction(it)
                            onDismiss()
                        }
                    )
                    Spacer(Modifier.height(8.dp))
                    EmojiReactionRow(
                        onEmojiSelected = {
                            onAction(it)
                            onDismiss()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MessageActionMenu(
    onMenuSelect: (String) -> Unit
) {

    val options = listOf(
        MessageMenu(MessageMenuType.REPLY, "Reply", R.drawable.ic_reply, Black80),
        MessageMenu(MessageMenuType.COPY, "Copy", R.drawable.ic_copy, Black80),
        MessageMenu(MessageMenuType.FORWARD, "Forward", R.drawable.ic_reply, Black80),
        MessageMenu(MessageMenuType.STARRED, "Starred", R.drawable.ic_starred, Black80),
        MessageMenu(MessageMenuType.DELETE, "Delete", R.drawable.ic_trash, Color(0xFFB52224))
    )

    Card(
        modifier = Modifier
            .width(180.dp),
        shape = RoundedCornerShape(28.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        // Use Column instead of LazyColumn for static short menus (better performance)
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            options.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onMenuSelect(item.name) } // Handle click
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically // Center icon and text
                ) {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.name,
                        tint = item.color, // Use the color from the object
                        modifier = Modifier
                            .size(20.dp)
                            // Rotate only the Forward icon
                            .rotate(if (item.name == "Forward") 180f else 0f)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.bodyMedium,
                        color = item.color
                    )
                }
            }
        }
    }
}

@Composable
fun EmojiReactionRow(onEmojiSelected: (String) -> Unit) {
    val emojis = listOf("👍", "❤️", "😂", "😮", "😢", "😡")

    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            emojis.forEachIndexed { index, emoji ->
                // Staggered Slide up animation for each emoji
                val animatedAlpha = remember { Animatable(0f) }
                val animatedOffsetY = remember { Animatable(20f) }

                LaunchedEffect(Unit) {
                    delay(index * 30L) // Stagger timing
                    launch { animatedAlpha.animateTo(1f, tween(200)) }
                    launch { animatedOffsetY.animateTo(0f, spring(Spring.DampingRatioLowBouncy)) }
                }

                Text(
                    text = emoji,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .graphicsLayer {
                            alpha = animatedAlpha.value
                            translationY = animatedOffsetY.value
                        }
                        .clickable { onEmojiSelected(emoji) }
                )
            }
        }
    }
}
