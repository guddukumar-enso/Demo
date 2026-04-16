package com.infophone.setting.presentation.screen


import CustomAppBar
import CustomCard
import CustomSwitch
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.navigation.NavKey
import com.infophone.navigation.domain.PrivacyType
import com.infophone.setting.R
import com.infophone.ui.common.ActionRow
import com.infophone.ui.common.SafeArea
import com.infophone.ui.theme.BackGroundColor
import com.infophone.ui.theme.Black80
import com.infophone.ui.theme.Gray600
import com.infophone.ui.theme.Lavender50

@Composable
fun PrivacyScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onClick:(NavKey)-> Unit
){

    SafeArea(
        backgroundColor = BackGroundColor,
        appBar = {
            CustomAppBar(
                onBack = {onBackClick()},
                title = R.string.privacy,
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
                        ActionRow(title = R.string.profile_photo,onClick = {onClick(NavKey.PrivacySection(
                            PrivacyType.ProfilePhoto))})
                        HorizontalDivider(thickness = 1.dp, color = Lavender50)
                        ActionRow(title = R.string.last_seen_online, onClick = {onClick(NavKey.PrivacySection(PrivacyType.LastSeen))})
                        HorizontalDivider(thickness = 1.dp, color = Lavender50)
                        ActionRow(title = R.string.status, onClick = {})
                        HorizontalDivider(thickness = 1.dp, color = Lavender50)
                        ActionRow(title = R.string.links)

                    }
                }
            }

            item {
                Spacer(Modifier.height(10.dp))
                Text(text = stringResource(R.string.disappearing_messages), fontSize = 14.sp, fontWeight = FontWeight.W400, color = Black80)

                Spacer(Modifier.height(10.dp))
                CustomCard(
                    verticalPadding = 0.dp,
                    horizontalPadding = 0.dp
                ) {
                    ActionRow(title = R.string.disappearing_messages, subTitle = R.string.off)
                }
            }

            item {
                Spacer(Modifier.height(10.dp))
                CustomCard(
                    verticalPadding = 10.dp,
                    horizontalPadding = 10.dp
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = stringResource(R.string.read_receipts),
                            fontWeight = FontWeight.W300,
                            fontSize = 14.sp,
                            color = Black80,

                            )
                        Spacer(Modifier.height(height = 4.dp))
                        Text(
                            text = stringResource(R.string.read_receipts_info),
                            fontWeight = FontWeight.W300,
                            fontSize = 14.sp,
                            color = Gray600,

                            )

                        Spacer(Modifier.height(height = 4.dp))



                    }

                    CustomSwitch()



                }
            }

            item {
                Spacer(Modifier.height(10.dp))
                CustomCard(
                    verticalPadding = 10.dp,
                    horizontalPadding = 10.dp
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = stringResource(R.string.app_lock),
                            fontWeight = FontWeight.W300,
                            fontSize = 14.sp,
                            color = Black80,

                            )
                        Spacer(Modifier.height(height = 4.dp))
                        Text(
                            text = stringResource(R.string.app_lock_info),
                            fontWeight = FontWeight.W300,
                            fontSize = 14.sp,
                            color = Gray600,

                            )

                        Spacer(Modifier.height(height = 4.dp))



                    }

                    CustomSwitch()



                }
            }
        }
    }
}