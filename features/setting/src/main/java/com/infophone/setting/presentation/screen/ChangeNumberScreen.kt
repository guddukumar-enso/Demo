package com.infophone.setting.presentation.screen

import CustomAppBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.setting.R
import com.infophone.ui.common.SafeArea
import com.infophone.ui.theme.BackGroundColor
import com.infophone.ui.theme.Gray100
import com.infophone.ui.theme.Gray600
import com.infophone.ui.theme.Lavender50


@Composable
fun ChangeNumberScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onClick: () -> Unit
) {
    SafeArea(
        backgroundColor = Gray100,
        appBar = {
            CustomAppBar(
                onBack = {onBackClick()},
                title = R.string.change_phone_number,
                bntText = R.string.next,
                onClick = {},
                darkIcons = false

            )
        }
    ) {    // <-- you MUST use this

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(10.dp))

            Image(
                painter = painterResource(R.drawable.ic_sim_card),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Spacer(Modifier.height(10.dp))

            Text(
                text = stringResource(R.string.change_phone_info),
                style = MaterialTheme.typography.titleSmall.copy(fontSize = 10.sp),
                color = Gray600,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 12.dp)
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = stringResource(R.string.confirm_phone_info),
                style = MaterialTheme.typography.titleSmall.copy(fontSize = 10.sp),
                textAlign = TextAlign.Center,
                color = Gray600,
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}
