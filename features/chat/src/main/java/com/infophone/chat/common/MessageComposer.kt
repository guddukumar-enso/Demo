package com.infophone.chat.common

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.chat.presentation.message.MessageViewModel
import com.infophone.chat.presentation.model.Message
import com.infophone.common.ExplorerResult
import com.infophone.common.ExplorerType
import com.infophone.ui.R
import com.infophone.ui.theme.Gray100
import com.infophone.ui.theme.Gray600
import com.infophone.ui.theme.Indigo900
import com.infophone.ui.theme.White

@Composable
fun MessageComposer(
    viewModel: MessageViewModel,
    onCamera: () -> Unit,
    onRecording: () -> Unit,
    replyingToMessage: Message?,
    onMessageSent: () -> Unit,
    onAttachment: (ExplorerType) -> Unit,
) {
    var showAttachmentMenu by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
    val isInputEmpty = textFieldValue.text.isBlank()
    var isEmojiVisible by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val softwareKeyboardController = LocalSoftwareKeyboardController.current

    // Helper function to insert emoji at the current cursor position
    val onEmojiSelected: (String) -> Unit = { emoji ->
        val currentText = textFieldValue.text
        val selection = textFieldValue.selection

        // Calculate the new text by inserting emoji at the cursor
        val newText = StringBuilder(currentText)
            .replace(selection.start, selection.end, emoji)
            .toString()

        // 2. Update the value and move cursor to the end of the new emoji
        textFieldValue = TextFieldValue(
            text = newText,
            selection = TextRange(selection.start + emoji.length)
        )
    }

    Row(
        modifier = Modifier
            .background(Gray100)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .navigationBarsPadding() // Clear the system "pill"
            .imePadding(),            // IMPORTANT: Slide up when keyboard appears,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .background(White, shape = RoundedCornerShape(10.dp))
//                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(10.dp))
                .weight(1f)
                .heightIn(min = 52.dp)
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier
                    .width(32.dp),
                onClick = {
                    isEmojiVisible = !isEmojiVisible
                    if (isEmojiVisible) {
                        softwareKeyboardController?.hide() // Hide keyboard when showing emojis
                    } else {
                        focusRequester.requestFocus()
                        softwareKeyboardController?.show()
                    }
                }
            ) {
                Icon(painter = painterResource(R.drawable.ic_smile), contentDescription = "Toggle Emoji", tint = Gray600)
            }
            BasicTextField(
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Transparent)
                    .focusRequester(focusRequester),
                maxLines = 5,
                textStyle = TextStyle(
                    color = Color.DarkGray,
                    fontSize = 14.sp
                ),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        if (isInputEmpty) Text(text = "Type a message...", color = Color.Gray.copy(alpha = 0.6f), fontSize = 14.sp)
                        innerTextField()
                    }
                }
            )
            IconButton(
                modifier = Modifier
                    .width(32.dp),
                onClick = { showAttachmentMenu = !showAttachmentMenu }
            ) {
                Icon(painter = painterResource(R.drawable.ic_plus), contentDescription = "Attachment", tint = Gray600)
            }
            IconButton(
                modifier = Modifier
                    .width(32.dp),
                onClick = { onCamera() }
            ) {
                Icon(painter = painterResource(R.drawable.ic_camera), contentDescription = "Camera", tint = Gray600)
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        FloatingActionButton(
            onClick = {
                if (!isInputEmpty) {
                    viewModel.sendTextMessage(textFieldValue.text, replyTo = replyingToMessage)
                    textFieldValue = TextFieldValue("")
                    onMessageSent()
                } else {
                    onRecording()
                }
            },
            shape = RoundedCornerShape(50),
            containerColor = Indigo900,
            elevation = FloatingActionButtonDefaults.elevation(0.dp),
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                painter = painterResource(if (!isInputEmpty) R.drawable.ic_arrow_right else R.drawable.ic_voice),
                contentDescription = "Voice Message",
                tint = Color.White,
//                modifier = Modifier.size(24.dp)
            )
        }
    }

    // --- Emoji Box ---
    if (isEmojiVisible) {
        EmojiPicker(
            onEmojiSelected = onEmojiSelected
        )
    }

    BackHandler(enabled = isEmojiVisible) {
        isEmojiVisible = false
    }

    if (showAttachmentMenu) {
        AttachmentPopup(
            onDismiss = { showAttachmentMenu = false },
            onOptionClick = { type ->
                showAttachmentMenu = false
                onAttachment(type)
            }
        )
    }
}