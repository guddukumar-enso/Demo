package com.infophone.setting.presentation.screen


import CustomAppBar
import CustomCard
import CustomSwitch
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.setting.R
import com.infophone.ui.common.ActionRow
import com.infophone.ui.common.SafeArea
import com.infophone.ui.theme.BackGroundColor
import com.infophone.ui.theme.Black80
import com.infophone.ui.theme.Gray600
import com.infophone.ui.theme.GrayDisabled
import com.infophone.ui.theme.Lavender50
import com.infophone.ui.theme.RedError


@Composable
fun SettingNotificationScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onClick: () -> Unit
){
    val scrollState = rememberScrollState()


    SafeArea(
        backgroundColor = BackGroundColor,
        appBar = {
            CustomAppBar(
                onBack = {onBackClick()},
                title = R.string.notifications,
                onClick = {},
                darkIcons = false

            )
        }
    ) {

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                ThreeGroupCard(leading = R.string.message_notification, title1 = R.string.show_notifications, title2 = R.string.sound, title3 = R.string.reaction_notifications)
                Spacer(Modifier.height(10.dp))
                ThreeGroupCard(leading = R.string.group_notification, title1 = R.string.show_notifications, title2 = R.string.sound, title3 = R.string.reaction_notifications)
                Spacer(Modifier.height(10.dp))
                ThreeGroupCard(leading = R.string.status_notification, title1 = R.string.show_notifications, title2 = R.string.sound, title3 = R.string.reaction_notifications)

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
                            text = stringResource(R.string.reminders),
                            fontWeight = FontWeight.W300,
                            fontSize = 14.sp,
                            color = Black80,

                            )
                        Spacer(Modifier.height(height = 4.dp))
                        Text(
                            text = stringResource(R.string.reminder_info),
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
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = stringResource(R.string.in_app_notifications),
                            fontWeight = FontWeight.W300,
                            fontSize = 14.sp,
                            color = Black80,

                            )
                        Spacer(Modifier.height(height = 4.dp))
                        Text(
                            text = stringResource(R.string.banner_sound_vibrate),
                            fontWeight = FontWeight.W300,
                            fontSize = 14.sp,
                            color = Gray600,

                            )

                        Spacer(Modifier.height(height = 4.dp))



                    }

                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null
                    )



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
                        text = stringResource(R.string.show_preview),
                        fontWeight = FontWeight.W300,
                        fontSize = 14.sp,
                        color = Black80,

                        )
                    Spacer(Modifier.height(height = 4.dp))
                    Text(
                        text = stringResource(R.string.preview_message_info),
                        fontWeight = FontWeight.W300,
                        fontSize = 14.sp,
                        color = Gray600,

                        )

                    Spacer(Modifier.height(height = 4.dp))



                }

                CustomSwitch()



            }
        }

       item{
           Spacer(Modifier.height(16.dp))
           CustomCard(
               verticalPadding = 10.dp,
               horizontalPadding = 10.dp
           ) {

               Column(
                   modifier = Modifier.weight(1f)
               ) {
                   Text(
                       text = stringResource(R.string.reset_notification_settings),
                       fontWeight = FontWeight.W300,
                       fontSize = 14.sp,
                       color = RedError,

                       )
                   Spacer(Modifier.height(height = 4.dp))
                   Text(
                       text = stringResource(R.string.reset_notification_info),
                       fontWeight = FontWeight.W300,
                       fontSize = 14.sp,
                       color = Gray600,

                       )

                   Spacer(Modifier.height(height = 4.dp))



               }

           }
       }
        }
    }
}




@Composable
fun RowSwitch(title: Int){
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(title),
            fontWeight = FontWeight.W300,
            fontSize = 14.sp,
            color = Color.Black,

        )
        CustomSwitch()
    }
}


@Composable
fun ThreeGroupCard(leading:Int, title1:Int, title2:Int, title3:Int){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text= stringResource(leading), color = Color.Black, fontWeight = FontWeight.W400, fontSize = 14.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(16.dp))

        CustomCard(
            verticalPadding = 0.dp,
            horizontalPadding = 0.dp
        ) {

            Column() {
                RowSwitch(title = title1)
                HorizontalDivider(thickness = 1.dp, color = Lavender50)
                ActionRow(title = title2, subTitle = R.string.note)
                HorizontalDivider(thickness = 1.dp, color = Lavender50)
                RowSwitch(title = title3)

            }




        }

    }
}