
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.infophone.ui.theme.Indigo900


@Composable
fun ResendOTP(onClick:()-> Unit, text:Int){

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ){

        Text(text= stringResource(text),
            fontSize = 14.sp,
            color = Indigo900,
            fontWeight = FontWeight.W500
            )
    }

}