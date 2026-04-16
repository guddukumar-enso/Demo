package com.infophone.home.presentation.chat

import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.infophone.home.common.AppSearchBar
import com.infophone.home.common.ChatListItem
import com.infophone.home.common.TopAppBar
import com.infophone.ui.R
import com.infophone.ui.theme.Gray600

@Composable
fun ChatScreen(
    modifier: Modifier,
    onContacts: () -> Unit,
    onMore: () -> Unit,
    onMessage: () -> Unit,
    viewModel: ChatViewModel = hiltViewModel()
) {
//    val chatState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopAppBar("chat", "Chats", onContacts, onMore)
//        Spacer(modifier = Modifier.height(16.dp))
        AppSearchBar()
        Spacer(modifier = Modifier.height(8.dp))

        LaunchedEffect(Unit) {
            // Collect the events emitted by the ViewModel
            viewModel.itemClickedEvent.collect { index ->
//                Toast.makeText(context, "Clicked Item Index: $index", Toast.LENGTH_SHORT).show()
                onMessage()
            }
        }

        LazyColumn {
            items(10) { item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.onItemClicked(item)
                        }
                ) {
                    ChatListItem("", 5, false)
                }
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