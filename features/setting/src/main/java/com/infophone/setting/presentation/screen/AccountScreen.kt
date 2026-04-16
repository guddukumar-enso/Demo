package com.infophone.setting.presentation.screen

import CustomAppBar
import CustomCard
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.infophone.navigation.NavKey
import com.infophone.setting.R
import com.infophone.ui.common.ActionRow
import com.infophone.ui.common.SafeArea
import com.infophone.ui.theme.BackGroundColor
import com.infophone.ui.theme.Gray100
import com.infophone.ui.theme.Lavender50

@Composable
fun AccountScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onClick: (NavKey) -> Unit
){

    SafeArea(
        backgroundColor = Gray100,
        horizontalPadding = 16.dp,
        verticalPadding = 20.dp,
        appBar = {
            CustomAppBar(
                onBack = {onBackClick()},
                title = R.string.account,
                onClick = {},
                darkIcons = false
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(vertical = 10.dp)
        ) {
            item {
             CustomCard(
                 verticalPadding = 0.dp,
                 horizontalPadding = 0.dp
             ) {
                 Column(){
                     ActionRow(title = R.string.security_notifications)
                     HorizontalDivider(thickness = 1.dp, color = Lavender50)
                     ActionRow(title = R.string.two_step_verification)
                     HorizontalDivider(thickness = 1.dp, color = Lavender50)
                     ActionRow(title = R.string.email_address)
                     HorizontalDivider(thickness = 1.dp, color = Lavender50)
                     ActionRow(title = R.string.change_phone_number, onClick = {onClick(NavKey.ChangeNumber)})

                 }
             }
            }

            item {
                Spacer(Modifier.height(10.dp))
                CustomCard(
                    verticalPadding = 0.dp,
                    horizontalPadding = 0.dp
                ) {
                    ActionRow(title = R.string.delete_my_account, onClick = {onClick(NavKey.DeleteAccount)})
                }
            }
        }
    }
}