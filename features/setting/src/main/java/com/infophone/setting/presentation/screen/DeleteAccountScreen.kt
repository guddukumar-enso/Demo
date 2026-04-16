package com.infophone.setting.presentation.screen

import BulletPointItem
import CustomAppBar
import CustomCard
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.setting.R
import com.infophone.ui.common.CustomButton
import com.infophone.ui.common.CustomInputField
import com.infophone.ui.common.SafeArea
import com.infophone.ui.theme.BackGroundColor
import com.infophone.ui.theme.Gray100
import com.infophone.ui.theme.Gray600
import com.infophone.ui.theme.Gray80
import com.infophone.ui.theme.Green80
import com.infophone.ui.theme.Lavender50
import com.infophone.ui.theme.RedError
import com.infophone.ui.theme.White


@Composable
fun DeleteAccountScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onClick: () -> Unit
) {

    var phone by remember { mutableStateOf("") }


    SafeArea(
        backgroundColor = Gray100,
        verticalPadding = 20.dp,
        appBar = {
            CustomAppBar(
                onBack = {onBackClick()},
                title = R.string.delete_my_account,
                onClick = {},
                darkIcons = false


            )
        }
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {

            item {
                SectionTitle(text = R.string.delete_account_info, modifier = Modifier.padding(horizontal = 5.dp))
                Spacer(Modifier.height(5.dp))
                BulletPointItem(text = R.string.delete_account_item1)
                BulletPointItem(text = R.string.delete_account_item2)
                BulletPointItem(text = R.string.delete_account_item3)
                Spacer(Modifier.height(10.dp))
                SectionTitle(text = R.string.enter_phone_number, color = Color.Black, fontSize = 14.sp, modifier = Modifier.padding(start = 10.dp))
                Spacer(Modifier.height(10.dp))
            }



          item {
              CustomCard(
                  verticalPadding = 0.dp,
                  horizontalPadding = 0.dp,
              ) {
                  Column() {
                      Row(
                          modifier = Modifier.fillMaxWidth().clickable{}.padding(horizontal = 10.dp, vertical = 12.dp),
                          verticalAlignment = Alignment.CenterVertically
                      ){
                          Text(
                              text = "Country",
                              style = MaterialTheme.typography.titleMedium,

                          )
                          Spacer(Modifier.width(20.dp))
                          Text(
                              text = "India",
                              color = Gray80,
                              style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Light),
                              modifier = Modifier.weight(1f)

                          )

                          Box(
                              modifier = Modifier.size(24.dp),
                              contentAlignment = Alignment.Center
                          ){
                              Icon(imageVector = Icons.Filled.ArrowForwardIos, contentDescription = null, tint = Gray600,
                                  modifier = Modifier.padding(4.dp))

                          }

                      }
                      HorizontalDivider(thickness = 1.dp, color = Lavender50)
                      Row(
                          modifier = Modifier.padding(horizontal = 10.dp, vertical = 12.dp),
                          verticalAlignment = Alignment.CenterVertically


                      ) {
                          Text(
                              text = "Phone",
                              style = MaterialTheme.typography.titleMedium
                          )

                          CustomInputField(
                              value = phone,
                              onValueChange = { phone = it },
                              placeholder = "Your phone number",
                              backgroundColor = Color.White,
                              height = 24.dp,
                              prefixIcon = {
                                  Text(
                                      text = "         +91 ",
                                      fontSize = 14.sp,
                                      fontWeight = FontWeight.W300
                                  )
                              },
                              borderWidth = 0.dp,
                              borderColor = Transparent
                          )
                      }
                  }

              }

          }

           item {
               Spacer(Modifier.height(10.dp))

               CustomButton(
                   onClick = {},
                   textResId = R.string.delete_my_account,
                   textStyle = MaterialTheme.typography.titleMedium,
                   contentPadding = 10.dp,
                   modifier = Modifier.fillMaxWidth(),
                   colors = ButtonDefaults.buttonColors(
                       containerColor = White,
                       contentColor = RedError,
                   )
               )
           }

           item {
               Spacer(Modifier.height(10.dp))
               CustomButton(
                   onClick = {},
                   textResId = R.string.change_phone_number,
                   textStyle = MaterialTheme.typography.titleMedium,
                   contentPadding = 10.dp,
                   modifier = Modifier.fillMaxWidth(),
                   colors = ButtonDefaults.buttonColors(
                       containerColor = White,
                       contentColor = Green80,
                   )
               )
           }




        }
    }
}



@Composable
fun SectionTitle(
    text: Int,
    color: Color = Color.Red,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.W400,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(text),
        color = color,
        style = MaterialTheme.typography.titleSmall.copy(fontSize = fontSize, fontWeight = fontWeight),
        modifier = modifier
    )
}

