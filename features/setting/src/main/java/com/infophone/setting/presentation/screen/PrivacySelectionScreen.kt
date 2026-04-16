package com.infophone.setting.presentation.screen


import CustomAppBar
import CustomCard
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.navigation.domain.PrivacyType
import com.infophone.setting.R
import com.infophone.ui.common.ActionRow
import com.infophone.ui.common.SafeArea
import com.infophone.ui.theme.BackGroundColor
import com.infophone.ui.theme.Black80
import com.infophone.ui.theme.Green80
import com.infophone.ui.theme.Lavender50

@Composable
fun PrivacySelectionScreen(
    modifier: Modifier,
    privacyType: PrivacyType,
    onBackClick: () -> Unit,
    onClick: () -> Unit
    ) {

    SafeArea(
        backgroundColor = BackGroundColor,
        appBar = {
            CustomAppBar(
                onBack = {onBackClick()},
                title = privacyType.appBarTitle,
                onClick = {},
                darkIcons = false

            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(vertical = 10.dp)
        ) {
            item {
                Text(
                    text = stringResource(privacyType.pageTitle),
                    fontWeight = FontWeight.W300,
                    fontSize = 14.sp,
                    color = Black80,

                    )
                Spacer(Modifier.height(10.dp))
                CustomCard(
                    verticalPadding = 0.dp,
                    horizontalPadding = 0.dp
                ) {
                    Column() {
                        ActionRow(title = R.string.everyone, isIcon = false)
                        HorizontalDivider(thickness = 1.dp, color = Lavender50)
                        ActionRow(title = R.string.my_contacts, isIcon = false)
                        HorizontalDivider(thickness = 1.dp, color = Lavender50)
                        ActionRow(title = R.string.my_contacts_except)
                        HorizontalDivider(thickness = 1.dp, color = Lavender50)
                        ActionRow(title = R.string.nobody, icon = Icons.Filled.Check, iconColor = Green80)

                    }
                }
            }

            if (privacyType.isLastOnlineSeen) {
               item {
                   WhoCanSeeOnline()
               }
            }
        }
    }
}


@Composable
fun WhoCanSeeOnline() {
    Column() {
        Spacer(Modifier.height(10.dp))
        Text(
            text = stringResource(R.string.who_can_see_online),
            fontWeight = FontWeight.W300,
            fontSize = 14.sp,
            color = Black80,

            )
        Spacer(Modifier.height(10.dp))
        CustomCard(
            verticalPadding = 0.dp,
            horizontalPadding = 0.dp
        ) {
           Column() {
               ActionRow(title = R.string.everyone, icon = Icons.Filled.Check, iconColor = Green80)
               HorizontalDivider(thickness = 1.dp, color = Lavender50)
               ActionRow(title = R.string.same_as_last_seen, isIcon = false)

           }
        }
    }
}