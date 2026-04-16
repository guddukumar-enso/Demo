
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.infophone.ui.theme.Green80
import com.infophone.ui.theme.GreenSuccess
import com.infophone.ui.theme.Lavender50
import com.infophone.ui.theme.RedError


@Composable
fun SettingChatScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onClick: () -> Unit
){
    SafeArea(
        backgroundColor = BackGroundColor,
        appBar = {
            CustomAppBar(
                onBack = {onBackClick()},
                title = R.string.chats,
                darkIcons = false

            )
        }
    ) {

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

           item {
               CustomCard(
                   horizontalPadding = 0.dp
               ) {

                   Column(
                       modifier = Modifier.fillMaxWidth(),
                       verticalArrangement = Arrangement.Center
                   ) {
                       ActionRow(title =R.string.default_chat_theme)
                       HorizontalDivider(thickness = 1.dp, color = Lavender50)

                       Row(
                           modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal = 10.dp)

                       ) {
                           Column(
                               modifier = Modifier.weight(1f)
                           ) {
                               Text(
                                   text = "Save to photos",
                                   fontWeight = FontWeight.W300,
                                   fontSize = 14.sp,
                                   color = Black80,

                                   )
                               Spacer(Modifier.height(height = 4.dp))
                               Text(
                                   text = "Automatically save photos and \nvideos you receive to Photos",
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

           item {
               Spacer(Modifier.height(10.dp))
              CustomCard(
                  horizontalPadding = 0.dp
              ) {
                  Column() {
                      ActionRow(title =R.string.chat_backup )

                    HorizontalDivider(thickness = 1.dp, color = Lavender50)
                      ActionRow(title =R.string.export_chat, subTitle = R.string.off )

                  }

              }
              Spacer(Modifier.height(10.dp))

          }

           item {
               CustomCard(verticalPadding = 10.dp) {
                   Column(
                       modifier = Modifier.weight(1f)
                   ) {
                       Text(
                           text = "Keep chats archived",
                           fontWeight = FontWeight.W300,
                           fontSize = 14.sp,
                           color = Black80,

                           )
                       Spacer(Modifier.height(height = 4.dp))
                       Text(
                           text = "Archived chats will remain archived when \nyou receive a new message.",
                           fontWeight = FontWeight.W300,
                           fontSize = 14.sp,
                           color = Gray600,

                           )

                       Spacer(Modifier.height(height = 4.dp))



                   }

                   CustomSwitch()

               }
               Spacer(Modifier.height(10.dp))

           }

           item {
               CustomCard(
                horizontalPadding = 0.dp
               ) {
                   Column(
                       modifier = Modifier.fillMaxWidth(),
                       verticalArrangement = Arrangement.Center
                   ) {
                       TitleText(title = R.string.archive_all_chats, color = Green80)
                       HorizontalDivider(thickness = 1.dp, color = Lavender50)
                       TitleText(title = R.string.clear_all_chats, color = RedError)
                       HorizontalDivider(thickness = 1.dp, color = Lavender50)
                       TitleText(title = R.string.delete_all_chats, color = RedError)

                   }
               }
            }

        }
    }
}


@Composable
fun TitleText(title:Int, color: Color){
    Text(
        text = stringResource(title),
        fontWeight = FontWeight.W300,
        fontSize = 14.sp,
        color = color,
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal = 10.dp)

        )
}