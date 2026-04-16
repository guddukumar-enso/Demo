package com.infophone.auth.presentation.splash

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.infophone.auth.R
import com.infophone.auth.presentation.viewModel.SplashViewModel
import com.infophone.navigation.NavKey
import com.infophone.ui.common.SafeArea
import com.infophone.ui.common.StatusBarTheme
import com.infophone.ui.theme.Black80
import com.infophone.ui.theme.White

@Composable
fun SplashScreen(
    modifier: Modifier,
    onComplete: (NavKey) -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
){
    val isLoading by viewModel.isLoading.collectAsState()
    val user by viewModel.user.collectAsState()


    if (!isLoading) {
        Log.d("SplashScreen", "User: $user")
        when{

            user != null -> onComplete(NavKey.Onboard)
            else -> onComplete(NavKey.Onboard)
        }

    }



    SafeArea(
        modifier = Modifier.fillMaxWidth().background(color = White).systemBarsPadding(),
        bottomBar = { BottomText(bottomText = R.string.app_copyright) }) {
        SplashScreenComposable()
    }
}


@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    SplashScreen(modifier = Modifier, onComplete = {})
}


@Composable
fun SplashScreenComposable()
{
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(R.drawable.logo_app_name), contentDescription = null)
        Spacer(modifier = Modifier.height(28.dp))
        Text(text = stringResource(R.string.app_tagline),
            color = Black80,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BottomText(bottomText:Int){

    Box(
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
        contentAlignment = Alignment.Center){
        Text(text = stringResource(bottomText),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall,
            color = Black80,
        )
    }
}