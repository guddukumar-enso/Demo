package com.infophone.auth.presentation.login

import LoginContent
import OtpContent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.infophone.auth.R
import com.infophone.auth.domain.model.AuthPage
import com.infophone.auth.domain.usecase.AuthUseCases
import com.infophone.auth.presentation.viewModel.AuthViewModel
import com.infophone.ui.common.SafeArea
import com.infophone.ui.theme.Black80
import com.infophone.ui.theme.White
import kotlin.math.log


@Composable
fun AuthScreen(
    modifier: Modifier,
    onPhoneNumberEntered: (String, String) -> Unit,
    viewModel: AuthViewModel = hiltViewModel()){


    val loginItemsList = viewModel.loginItemsList

    val pagerState = rememberPagerState(pageCount = { loginItemsList.size })
    val scope = rememberCoroutineScope()


    SafeArea(
        modifier = Modifier.fillMaxWidth().background(color = White).systemBarsPadding(),
        appBar = {AppBarLogo() },
        bottomBar = { BottomTermAndCondition() },
        horizontalPadding = 25.dp,

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false

            ){ page ->


                when (loginItemsList[page]) {
                    AuthPage.LoginPage -> LoginContent(
                        viewModel = viewModel,
                        pagerState = pagerState,
                        scope = scope
                    )
                    AuthPage.OtpPage -> OtpContent(
                        viewModel = viewModel,
                        pagerState = pagerState,
                        scope = scope,
                        onPhoneNumberEntered = onPhoneNumberEntered
                    )


                }

            }

        }

    }

}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    AuthScreen(
        modifier = Modifier,
        onPhoneNumberEntered = { countryCode, phoneNumber->
            Log.d("TAG", "Code: $countryCode  Number: $phoneNumber")
        }
    )
}


@Composable
fun AppBarLogo(){
    Row (
        modifier = Modifier.fillMaxWidth().padding(horizontal = 25.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.Start,
    ){
        Image(painter = painterResource(R.drawable.logo_text), contentScale = ContentScale.Fit, contentDescription = null,
            modifier = Modifier.size(height = 30.dp, width = 170.dp))
    }
}


//Bottom term condition
@Composable
fun BottomTermAndCondition(){

    Box(
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
        contentAlignment = Alignment.Center
    ){

        Text(
            text = buildAnnotatedString {
                append(text = stringResource(R.string.login_terms_intro))

                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                    append(text = stringResource(R.string.login_terms_conditions))
                }

                append(text = stringResource(R.string.and))

                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                    append(text = stringResource(R.string.login_privacy_policy))
                }
            },
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall,
            color = Black80
        )

    }

}


