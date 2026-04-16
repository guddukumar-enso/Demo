
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TitleAndDescription(title:String, description:String){
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Text(title,
            fontSize = 47.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            lineHeight = 55.sp
        )

        Spacer(Modifier.height(16.dp))

        Text(description,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.W400
        )

    }
}


