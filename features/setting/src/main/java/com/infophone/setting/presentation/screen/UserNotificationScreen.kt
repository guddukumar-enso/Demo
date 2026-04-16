package com.infophone.setting.presentation.screen


import CustomAppBar
import CustomCard
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.navigation.NavKey
import com.infophone.setting.R
import com.infophone.ui.common.ActionRow
import com.infophone.ui.common.CustomBottomSheet
import com.infophone.ui.common.SafeArea
import com.infophone.ui.theme.BackGroundColor
import com.infophone.ui.theme.Lavender50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserNotificationScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onClick: (NavKey) -> Unit
){

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    var showSheet by remember { mutableStateOf(false) }

    LaunchedEffect(showSheet) {
        if (!showSheet && sheetState.currentValue != SheetValue.Hidden) {
            sheetState.hide()
        }
    }

    SafeArea(
        backgroundColor = BackGroundColor,
        appBar = {
            CustomAppBar(
                onBack = {
                    showSheet = false
                    onBackClick()},
                title = R.string.notification,
                userName = "${stringResource(R.string.user_name)}",
                onClick = {},
                darkIcons = false

            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(vertical = 10.dp)
        ) {
            item {
                Text(text = stringResource(R.string.message_notification), fontSize = 14.sp, fontWeight = FontWeight.W300, color = Color.Black)
                Spacer(Modifier.height(10.dp))
                CustomCard(
                    verticalPadding = 0.dp,
                    horizontalPadding = 0.dp
                ) {
                    Column(){
                        ActionRow(title = R.string.mute_notifications, subTitle = R.string.none, onClick = { showSheet = true})
                        HorizontalDivider(thickness = 1.dp, color = Lavender50)
                        ActionRow(title = R.string.alert_tone, subTitle = R.string.chord, onClick = {})

                    }
                }
            }

        }
    }

    if(showSheet){
        MuteDurationBottomSheet(
            sheetState = sheetState,
            onDismiss = {
                showSheet = false
            },
        )
    }
}