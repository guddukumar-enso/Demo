package com.infophone.setting.presentation.screen


import CustomAppBar
import CustomCard
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.setting.R
import com.infophone.ui.common.CustomInputField
import com.infophone.ui.common.SafeArea
import com.infophone.ui.common.UserProfileImage
import com.infophone.ui.theme.BackGroundColor
import com.infophone.ui.theme.Gray100
import com.infophone.ui.theme.GrayDisabled
import com.infophone.ui.theme.Green80
import com.infophone.ui.theme.Indigo900
import com.infophone.ui.theme.White


@Composable
fun SettingProfileScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onClick: () -> Unit
){

    val userName = stringResource(R.string.user_name)
    val urgentNote = stringResource(R.string.urgent_note)
    val phoneExample = stringResource(R.string.phone_example)

    var name by remember { mutableStateOf(userName) }
    var about by remember { mutableStateOf(urgentNote) }
    var phone by remember { mutableStateOf(phoneExample) }


    SafeArea(
        backgroundColor = Gray100,
        appBar = {
            CustomAppBar(
                onBack = {onBackClick()},
                title = R.string.profile,
                onClick = {},
                bntText = R.string.save,
                bntColor = White,
                darkIcons = false

            )
        }
    ) {
        LazyColumn(){


            item {
                Column (
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    UserProfileImage(onClick = {}, imageRes = R.drawable.ic_user_image)
                    Spacer(Modifier.height(10.dp))
                    Text(text = stringResource(R.string.edit),
                        style = MaterialTheme.typography.titleMedium,
                        color = Indigo900)


                }
            }
            item {
                Spacer(Modifier.height(10.dp))

            }
            item {
                UserInput(title = R.string.name, value = name, placeholder = "Enter name", onchange = {})

            }
            item {
                Spacer(Modifier.height(10.dp))

            }
            item {
                UserInput(title = R.string.about, value = about, placeholder = "Enter about", onchange = {})

            }
            item {
                Spacer(Modifier.height(10.dp))

            }
            item {
                UserInput(title = R.string.phone, value = phone, placeholder = "Enter phone number", onchange = {})


            }
            item {
                Spacer(Modifier.height(10.dp))

            }
            item {
                Text(text = stringResource(R.string.links),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black,
                    modifier = Modifier.padding(horizontal = 8.dp))
                Spacer(Modifier.height(10.dp))
                CustomCard(

                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = stringResource(R.string.add_links),
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Light)
                            , color = Green80)
                        Icon(imageVector = Icons.Filled.ArrowForwardIos, contentDescription = null, tint = GrayDisabled)
                    }
                }

            }
        }
    }
}





@Composable
fun UserInput(title:Int, placeholder:String, onchange:(String) -> Unit, value:String){

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(title),
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 8.dp))
        Spacer(Modifier.height(10.dp))
        CustomInputField(value = value,
            onValueChange = onchange,
            borderColor = White,
            borderWidth = 0.dp,
            textStyle = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Light),
            placeholder = placeholder)
    }
}