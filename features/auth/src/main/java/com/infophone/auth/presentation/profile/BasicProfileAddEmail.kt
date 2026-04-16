package com.infophone.auth.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.infophone.ui.theme.Gray100
import com.infophone.ui.theme.Indigo900
import com.infophone.ui.theme.Lavender50
import com.infophone.ui.theme.White


@Composable
fun BasicProfileAddEmailScreen(
    onClick:()-> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
){

    var showExitSheet by remember { mutableStateOf(false) }

    var userEmail by remember { mutableStateOf("") }


    SafeArea(
        modifier = Modifier.fillMaxWidth().background(color = Gray100).systemBarsPadding(),
        horizontalPadding = 0.dp,
        verticalPadding = 0.dp,
        bottomBar = {

            Box(
                modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp).background(color = Gray100)
            ){
                CustomButton(


                    onClick = {

                        viewModel.saveProfile(email = userEmail)
                        showExitSheet = true

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
            modifier = Modifier.fillMaxSize().background(color = Gray100),
        ) {

            //Back array and logo
            AppBarTextLogo(
                onBack = {},
                modifier = Modifier.fillMaxWidth().height(290.dp).background(color = Lavender50).padding(top = 30.dp, start = 16.dp, end = 16.dp),
            )


            //Profile Card
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth().padding(top = 90.dp, start = 16.dp, end = 16.dp).
                clip(RoundedCornerShape(10.dp)).
                background(color = Color.White),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = White
                )

            ){
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start

                ){


                    //Title and description
                    Column(
                        modifier = Modifier.fillMaxWidth().height(height = 85.dp).clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                            .background(color = Indigo900).padding(start = 16.dp),
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(text = stringResource(R.string.profile_add_email_title),
                            color = Lavender50,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W500
                        )

                        Spacer(Modifier.height(8.dp))
                        Text(text = stringResource(R.string.profile_add_email_subtitle),
                            color = Lavender50,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W300
                        )
                    }


                    //Email section
                    TitleWithInput(onClick = { }, title = R.string.email, hintText = "purvi.jain@ensowebworks.com", modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 24.dp),
                        textValue = userEmail,
                        onValueChange = {userEmail = it})

                }

            }
        }
    }

    if (showExitSheet) {
        SuccessAccountBottomSheet()
    }

}


@Preview(showBackground = true)
@Composable
fun BasicProfileAddEmailScreenPreview(){
    BasicProfileAddEmailScreen(onClick = {})
}



