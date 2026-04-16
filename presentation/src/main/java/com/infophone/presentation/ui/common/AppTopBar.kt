package com.infophone.presentation.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.infophone.presentation.R

@Composable
fun TopAppBar(
    key: String,
    title: String,
    onContacts: () -> Unit,
    onMore: () -> Unit
) {
    val isInvisible = key == "setting"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 4.dp)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.weight(1f)
        )

        IconButton(onClick = { if (!isInvisible) onContacts() },
            modifier = Modifier.alpha(if (isInvisible) 0f else 1f)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add_circle),
                contentDescription = "New Chat",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        IconButton(onClick = { if (!isInvisible) onMore() },
            modifier = Modifier.alpha(if (isInvisible) 0f else 1f)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_more_vertical),
                contentDescription = "More",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}