
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.infophone.auth.R
import com.infophone.auth.presentation.viewModel.AuthViewModel
import com.infophone.ui.common.CustomButton
import com.infophone.ui.common.CustomInputField
import com.infophone.ui.theme.GrayDisabled
import com.infophone.ui.theme.Indigo900
import com.infophone.ui.theme.Lavender50
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun OtpContent(
    viewModel: AuthViewModel,
    pagerState: PagerState,
    scope: CoroutineScope,
    onPhoneNumberEntered: (String, String) -> Unit,
){

    val phone = viewModel.phone
    val otp = viewModel.otp
    var isClickAble = viewModel.otpError == null && !otp.isBlank()



    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //login or otp title and description
        TitleAndDescription(title = stringResource(R.string.otp_verification_title), description = stringResource(R.string.otp_verification_subtitle))

        //spacer
        Spacer(Modifier.height(34.dp))


        //Enter phone number section
        CustomInputField(
            value = phone,
            readOnly = true,
            onValueChange = { viewModel.onPhoneChanged(it) },
            placeholder = "Enter your mobile number",
            prefixIcon =  {
                CountryCodePicker()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            height = 48.dp,
            backgroundColor = Lavender50,
            borderWidth = 0.dp,
            borderColor = Lavender50,
            cornerRadius = 45.dp,

            )

        Spacer(Modifier.height(24.dp))

        //Enter otp section
        OtpInput(otpLength = 6, viewModel = viewModel)
        Spacer(Modifier.height(16.dp))

        //Resend otp
        ResendOTP(onClick = {}, text = R.string.otp_resend)
        Spacer(Modifier.height(24.dp))
        CustomButton(

            onClick = {
                if(isClickAble){
                    val nextPage = pagerState.currentPage + 1

                    if (nextPage < pagerState.pageCount) {
                        scope.launch {
                            pagerState.animateScrollToPage(nextPage)
                        }
                    }else{
                        viewModel.savePhoneInfo(
                            countryCode = "+91",
                            countryIso = "IN",
                            phoneNumber = phone,
                            dialNumber = "+91${phone}"
                        )
                        onPhoneNumberEntered("+91", "9876543210")

                    }
                }
            },
            textResId = R.string.verify,
            modifier = Modifier
                .fillMaxWidth()
                .height(47.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if(isClickAble) Indigo900 else GrayDisabled,
                contentColor = Color.White,
            )
        )


    }
}


@Preview(showBackground = true)
@Composable
fun OtpContentPreview(){
    val fakePagerState = rememberPagerState(pageCount = { 2 })
    val fakeScope = rememberCoroutineScope()
}