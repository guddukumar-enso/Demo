package com.infophone.auth.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.auth.R
import com.infophone.ui.common.CustomBottomSheet
import com.infophone.ui.theme.Indigo900

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessAccountBottomSheet(){

    CustomBottomSheet() {

        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {

            //Success Icon
            Image(
                painterResource(R.drawable.success),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().size(41.dp)
            )
            Spacer(Modifier.height(16.dp))

            //Success title
            Text(
                text = stringResource(R.string.profile_success_message),
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                color = Indigo900,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()

            )
            Spacer(Modifier.height(16.dp))

            //Success Description
            Text(
                text = stringResource(R.string.profile_success_subtitle),
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Center,
                color = Indigo900,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(24.dp))

            //Success Prograss
            Image(
                painterResource(R.drawable.progress),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().size(41.dp)
            )

        }

    }
}


@Preview(showBackground = true)
@Composable
fun SuccessAccountBottomSheetPreview(){
    SuccessAccountBottomSheet()
}