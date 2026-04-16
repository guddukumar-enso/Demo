package com.infophone.auth.presentation.onboard.ui
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.ui.theme.Indigo900


@Composable
fun  OnBoardingTitleDis(title1:Int, title2: Int, title3: Int, title4: Int, description: Int){

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top

    ) {

        Text(text = buildAnnotatedString {
            withStyle(

                style = SpanStyle(
                    color = Color.Black
                )
            ){
                append(text = stringResource(title1))
            }
            withStyle(
                style = SpanStyle(
                    color = Indigo900,
                )
            ){
                append(text= stringResource(title2))
            }
            withStyle(
                style = SpanStyle(
                    color = Color.Black,
                )
            ){
                append(text= stringResource(title3))
            }

            withStyle(
                style = SpanStyle(
                    color = Color.Green,
                )
            ){
                append(text= stringResource(title4))
            }
        },
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            lineHeight = 55.sp,

            fontSize = 47.sp)


        Spacer(modifier = Modifier.height(16.dp))

        Text(text = stringResource(description),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.W400,
            fontSize = 20.sp)


    }

}

