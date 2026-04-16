package com.infophone.auth.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.auth.R
import com.infophone.ui.common.CustomBottomSheet
import com.infophone.ui.common.CustomButton
import com.infophone.ui.theme.Black80
import com.infophone.ui.theme.Indigo900
import com.infophone.ui.theme.RedError
import com.infophone.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExitAccountBottomSheet(
    onDismiss: () -> Unit,
    onExit: () -> Unit
){

    CustomBottomSheet() {

        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {

            //Exit Icon
            Image(
                painterResource(R.drawable.warning),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().size(41.dp)
            )
            Spacer(Modifier.height(16.dp))

            //Exit title
            Text(
                text = stringResource(R.string.profile_exit_setup_title),
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                color = Indigo900,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()

            )
            Spacer(Modifier.height(16.dp))

            //Exit Description
            Text(
                text = stringResource(R.string.profile_exit_setup_warning),
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Center,
                color = Indigo900,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(24.dp))

            //Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                CustomButton(
                    onClick = onDismiss,
                    textResId = R.string.profile_exit_setup_button,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RedError,
                        contentColor = White
                    ),
                    isLoading = false,
                    modifier = Modifier.width(width = 160.dp),
                    textStyle = TextStyle(
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp
                    )

                )

                CustomButton(
                    onClick = onDismiss,
                    textResId = R.string.profile_continue_setup_button,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = White,
                        contentColor = Black80
                    ),
                    isLoading = false,
                    modifier = Modifier.width(width = 160.dp).border(width = 1.dp, color = Black80, shape = RoundedCornerShape(10.dp)),
                    textStyle = TextStyle(
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp
                    )
                )
            }

        }

    }
}


@Preview(showBackground = true)
@Composable
fun ExitAccountBottomSheetPreview(){
    ExitAccountBottomSheet(
        onDismiss = {},
        onExit = {}
    )
}