package com.infophone.auth.presentation.onboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.infophone.ui.theme.Gray80
import com.infophone.ui.theme.GrayDisabled
import com.infophone.ui.theme.Indigo900


@Composable
fun DotsIndicator(totalDots: Int, selectedIndex: Int) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.width(width = 40.dp)
    ) {

        repeat(totalDots) { index ->

            Box(
                modifier = Modifier
                    .height(height = 7.dp)
                    .width(if (index == selectedIndex) 18.dp else 7.dp)
                    .clip(CircleShape)
                    .background(if (index == selectedIndex) Indigo900 else GrayDisabled)

            )
        }
    }
}
