package com.infophone.chat.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.ui.R
import com.infophone.ui.theme.Gray100
import com.infophone.ui.theme.Gray600
import com.infophone.ui.theme.White

@Composable
fun MessageEmpty(
    modifier: Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Gray100),
//        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(14.dp))
            Box(
                modifier = Modifier
                    .background(White, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            ) {
                Text(
                    text = "Today",
                    style = MaterialTheme.typography.labelSmall,
                    color = Gray600
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .background(color = Color(0xFF56758A), shape = RoundedCornerShape(10.dp))
                    .padding(horizontal = 16.dp, vertical = 30.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append(stringResource(com.infophone.chat.R.string.messages_and_calls_are_end_to_end_encrypted))
                        append(stringResource(com.infophone.chat.R.string.only_people_in_this_chat_can_read_listen_or_share_them))
                        withStyle(
                            style = SpanStyle(fontWeight = FontWeight.SemiBold)
                        ) {
                            append(stringResource(com.infophone.chat.R.string.click_to_learn_more))
                        }
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFF2F2FF),
                    textAlign = TextAlign.Center
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.SemiBold)
                    ) {
                        append(stringResource(com.infophone.chat.R.string.start_a_conversation))
                    }
                    append(stringResource(com.infophone.chat.R.string.send_a_message_to_get_started))
                },
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.outfit_light)),
                    fontWeight = FontWeight.Light,
                    lineHeight = 24.sp
                ),
                color = Gray600,
                textAlign = TextAlign.Center
            )

            /*ChatBubble(
                message = "Hello, bro! Can you help me?",
                time = "2:56 PM",
                modifier = Modifier
                    .padding(12.dp)
                    .widthIn(max = 280.dp)
            )*/

        }
    }
}