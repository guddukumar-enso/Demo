package com.infophone.group.presentation.screen


import CustomAppBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.group.R
import com.infophone.ui.common.CustomInputField
import com.infophone.ui.common.SafeArea
import com.infophone.ui.common.UserProfileImage
import com.infophone.ui.theme.Gray100
import com.infophone.ui.theme.Indigo900
import com.infophone.ui.theme.White



@Composable
fun EditGroupScreen(
    modifier: Modifier,
    onBack: () -> Unit,
    onClick: () -> Unit,
){

    var textValue by remember { mutableStateOf("Enso Webworks|") }
    var description by remember { mutableStateOf("") }


    SafeArea(
        appBar = {
            CustomAppBar(
                onBack = {onBack()},
                onClick = {
                    onClick()
                },
                title = R.string.edit,
                bntColor = White,
                bntText = R.string.save,
                darkIcons = false

            )
        },
        horizontalPadding = 24.dp,
        verticalPadding = 0.dp,
        backgroundColor = Gray100
    ) {

        LazyColumn() {
            item {
                Spacer(Modifier.height(height = 16.dp))
                //User profile image
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {

                    UserProfileImage(
                        size = 80.dp,
                        imageRes = R.drawable.user_img,
                        backgroundColor = Color.LightGray,
                        borderColor = Color.Transparent,
                        borderWidth = 0.dp
                    )

                }
            }

            item {
                Spacer(Modifier.height(height = 16.dp))
                Text(text = stringResource(R.string.edit), fontSize = 20.sp, fontWeight = FontWeight.W500, color = Indigo900,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center)
                Spacer(Modifier.height(height = 16.dp))

            }

            //Group name feild
            item {
                CustomInputField(
                    value = textValue,
                    onValueChange = { textValue = it },
                    cornerRadius = 10.dp,
                    placeholder = "Enter group name",
                    suffixIcon = {
                        Box(
                            modifier = Modifier.size(25.dp).clip(RoundedCornerShape(15.dp)).background(color = Indigo900),
                            contentAlignment = Alignment.Center
                        ){
                Icon(imageVector = Icons.Filled.Close, contentDescription = null, tint = White, modifier = Modifier.padding(5.dp))
                        }
                    },
                    height = 46.dp,
                    backgroundColor = White,
                    borderWidth = 0.dp,
                    borderColor = White,
                )
            }

            //Group description feild
            item {
                Spacer(Modifier.height(height = 16.dp))
                CustomInputField(
                    height = 130.dp,
                    value = description,
                    verticalPadding = 10.dp,
                    verticalAlignment = Alignment.Top,
                    contentAlignment = Alignment.TopStart,
                    onValueChange = { textValue = it },
                    cornerRadius = 10.dp,
                    placeholder = "Add group description",
                    backgroundColor = White,
                    borderWidth = 0.dp,
                    borderColor = White,
                )
            }

            item {
                Spacer(Modifier.height(height = 16.dp))
                Text(text = stringResource(R.string.add_group_description), fontSize = 12.sp, fontWeight = FontWeight.W300, color = Indigo900,
                    modifier = Modifier.fillMaxWidth().padding(start = 10.dp),
                    textAlign = TextAlign.Start)

            }
        }
    }
}

