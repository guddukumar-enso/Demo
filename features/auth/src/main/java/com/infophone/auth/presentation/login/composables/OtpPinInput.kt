
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.infophone.auth.presentation.viewModel.AuthViewModel
import com.infophone.ui.theme.Gray100
import com.infophone.ui.theme.RedError


@Composable
fun OtpInput(otpLength: Int = 6, viewModel: AuthViewModel) {
    val otp = viewModel.otp
    val otpError = viewModel.otpError



    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    // spacing between boxes
    val boxSpacing = 8.dp

    // calculate total spacing width
    val totalSpacing = boxSpacing * (otpLength - 1)

    // final width for each box
    val boxSize = (screenWidth - totalSpacing - 50.dp) / otpLength   // 32.dp = horizontal padding



    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {

        BasicTextField(
            value = otp,
            onValueChange = { newValue ->
                if (newValue.length <= otpLength && newValue.all { it.isDigit() }) {
                    viewModel.onOtpChanged(newValue)

                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            decorationBox = { innerTextField ->
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    repeat(otpLength) { index ->
                        val char = if (index < otp.length) otp[index].toString() else ""
                        Text(
                            text = char,
                            modifier = Modifier
                                .size(boxSize)
                                .background(color = Gray100)
                                .border(
                                    width = 1.dp,
                                    shape = RoundedCornerShape(10.dp),
                                    color = if (index == otp.length) Color.Blue else Color.Gray
                                )
                                .wrapContentSize(Alignment.Center)
                        )
                    }
                }
            }
        )

        if (otpError != null) {
            Text(
                text = otpError ?: "",
                color = RedError,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }

}



