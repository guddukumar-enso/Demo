package com.infophone.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmptyState(
    textOneAppend: String,
    textTwoAppend: String,
    iconAppend: Int,
    textBase: String,
    textOneLink: String,
    textTwoLink: String
) {
    val iconId = "inlineIcon"
    val text = buildAnnotatedString {
        append(textOneAppend)
        appendInlineContent(iconId,"[icon]")
        append(textTwoAppend)
    }
    val inlineContent = mapOf(
        Pair(
            iconId,
            InlineTextContent(
                Placeholder(
                    width = 24.sp,
                    height = 24.sp,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                )
            ) {
                Icon(
                    painter = painterResource(id = iconAppend),
                    contentDescription = "add icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            color = Color.Gray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            inlineContent = inlineContent
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = textBase,
            color = Color.Gray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Invite Link
        Text(
            text = textOneLink,
            color = Color(0xFF3D3C70),
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier
                .clickable { /* Handle invite click */ }
                .padding(vertical = 12.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color(0xFFF5F5F5))
        )

        // Start Messaging Link
        Text(
            text = textTwoLink,
            color = Color(0xFF3D3C70),
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier
                .clickable { /* Handle start messaging click */ }
                .padding(vertical = 12.dp)
                .padding(top = 8.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color(0xFFF5F5F5))
        )
    }
}