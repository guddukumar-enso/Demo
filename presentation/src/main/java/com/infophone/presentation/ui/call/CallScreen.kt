package com.infophone.presentation.ui.call

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.infophone.presentation.R
import com.infophone.presentation.ui.common.AppSearchBar
import com.infophone.presentation.ui.common.CallListItem
import com.infophone.presentation.ui.common.ChatListItem
import com.infophone.presentation.ui.common.EmptyState
import com.infophone.presentation.ui.common.TopAppBar

@Composable
fun CallScreen(
    modifier: Modifier,
    onContacts: () -> Unit,
    onMore: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopAppBar("call", "Calls", onContacts, onMore)
        Spacer(modifier = Modifier.height(16.dp))
        AppSearchBar()
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(10) { item ->
                CallListItem(
                    "",
                    "Incoming",
                    "audio",
                    "Today, 16:25"
                )
            }
        }
        /*EmptyState(
            "Tap the ",
            " to place InfoPhone call",
            R.drawable.add_circle,
            "Your recent calls will appear here once you start making them.",
            "Invite a Friend to InfoPhone",
            "Make Your First Call"
        )*/
    }
}