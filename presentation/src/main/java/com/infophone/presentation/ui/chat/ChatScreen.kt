package com.infophone.presentation.ui.chat

import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.presentation.R
import com.infophone.presentation.ui.common.AppSearchBar
import com.infophone.presentation.ui.common.ChatListItem
import com.infophone.presentation.ui.common.TopAppBar

@Composable
fun ChatScreen(
    modifier: Modifier,
    onContacts: () -> Unit,
    onMore: () -> Unit
) {
    /*Box(modifier = modifier.fillMaxSize()) {
        // Default content when search is not expanded
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar("chat", "Chats")
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Full-screen overlay when search expands
        SimpleSearchBar(
            TextFieldState(),
            {},
            emptyList(),
            modifier = Modifier
                .fillMaxSize()
                .height(38.dp)
        )
    }*/
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopAppBar("chat", "Chats", onContacts, onMore)
        Spacer(modifier = Modifier.height(16.dp))
        AppSearchBar()
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(10) { item ->
                ChatListItem("", 5, false)
            }
        }
        /*EmptyState(
            "Tap the ",
            " button to message a friend",
            R.drawable.add_circle,
            "You can message and call contacts who also have InfoPhone installed.",
            "Invite a Friend to InfoPhone",
            "Start Messaging"
        )*/
    }
}