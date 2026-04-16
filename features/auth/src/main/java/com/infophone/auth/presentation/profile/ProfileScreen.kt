package com.infophone.auth.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.infophone.auth.R
import com.infophone.auth.presentation.viewModel.AuthViewModel
import com.infophone.auth.presentation.viewModel.ProfileViewModel
import com.infophone.ui.common.AppBarTextLogo
import com.infophone.ui.common.CustomButton
import com.infophone.ui.common.SafeArea
import com.infophone.ui.common.TitleWithInput
import com.infophone.ui.common.UserProfileImage
import com.infophone.ui.theme.Gray100
import com.infophone.ui.theme.Indigo900
import com.infophone.ui.theme.Lavender50
import com.infophone.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier,
    countryCode: String,
    phoneNumber: String,
    onSaved: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
){

    var showExitSheet by remember { mutableStateOf(false) }

    var showImgSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )


    LaunchedEffect(showImgSheet) {
        if (!showImgSheet && sheetState.currentValue != SheetValue.Hidden) {
            sheetState.hide()
        }
    }


    var userName by remember { mutableStateOf("") }
    var userAbout by remember { mutableStateOf("") }

    SafeArea(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Gray100)
            .systemBarsPadding(),

        horizontalPadding = 0.dp,
        verticalPadding = 0.dp,
        bottomBar = {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Gray100)
                    .padding(bottom = 12.dp)
            ){
                CustomButton(
                    onClick = {

                        viewModel.saveProfile(
                           name = userName,
                           about =  userAbout,
                            profileImg = "Image"
                        )
                        onSaved()

                    },
                    textResId = R.string.next,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 10.dp, bottom = 10.dp)
                        .height(47.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Indigo900,
                        contentColor = Color.White,
                    )
                )
            }
        },

        ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Gray100),
        ) {

            //Back Array and logo
            AppBarTextLogo(
                onBack = {
                    showExitSheet = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(290.dp)
                    .background(color = Lavender50)
                    .padding(top = 30.dp, start = 16.dp, end = 16.dp),
            )

            //Profile Card
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 90.dp, start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = Color.White),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = White
                )

            ){
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally

                ){

                    //Personal profile title and description
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height = 85.dp)
                            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                            .background(color = Indigo900)
                            .padding(start = 16.dp),
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(text = stringResource(R.string.profile_personal_info_title),
                            color = Lavender50,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W500
                        )

                        Spacer(Modifier.height(8.dp))
                        Text(text = stringResource(R.string.profile_personal_info_subtitle),
                            color = Lavender50,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W300
                        )
                    }

//                    Spacer(Modifier.height(10.dp))

                    //User profile image
                    Box(
                        modifier = Modifier
                            .size(100.dp).clip(shape = CircleShape)
                            .clickable { showImgSheet = true },
                        contentAlignment = Alignment.Center
                    ){

                        UserProfileImage(
                            imageRes = R.drawable.ic_user,
                            size = 80.dp,
                            backgroundColor = Color.LightGray,
                            borderColor = Color.Transparent,
                            borderWidth = 0.dp
                        )

                        Image(
                            painter = painterResource(R.drawable.camera),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(top = 50.dp, start = 50.dp)
                                .size(30.dp),


                            )
                    }

                    Spacer(Modifier.height(8.dp))
                    //Phone number
                    Text(text = "+91 9876543210",
                        color = Indigo900,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W500,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )



                    TitleWithInput(onClick = { }, title = R.string.name, hintText = "enter name", modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                        textValue = userName,
                        onValueChange = {
                            userName = it
                        }
                    )

                    //About field
                    TitleWithInput(onClick = { }, title = R.string.about, hintText = "enter about", modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                        textValue = userAbout,
                        onValueChange = {
                            userAbout = it
                        }
                        )

                }

            }
        }
    }

    if (showExitSheet) {
        ExitAccountBottomSheet(
            onDismiss = { showExitSheet = false },
            onExit = {
                showExitSheet = false
            }
        )
    }

    if(showImgSheet){
        SelectProfileImageSheet(
            sheetState = sheetState,
            onDismiss = {
                showImgSheet = false
            },
        )
    }

}


@Preview(showBackground = true)
@Composable
fun BasicProfileScreenPreview(){
    ProfileScreen(modifier = Modifier, countryCode = "", phoneNumber = "", onSaved = {})
}