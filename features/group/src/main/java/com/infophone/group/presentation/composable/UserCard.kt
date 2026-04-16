
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.contact.presentation.ContactProfileImage
import com.infophone.group.R
import com.infophone.ui.common.UserProfileImage
import com.infophone.ui.theme.Black80
import com.infophone.ui.theme.Gray600


@Composable
fun UserCard(
    userName:String,
    userImage:Int? = null,
    message: String,
    isRadioButton: Boolean,
    shape: RoundedCornerShape = RoundedCornerShape(10.dp),

    ){

       var selectedOption by remember { mutableStateOf("yes") }

    CustomCard(
        Modifier.height(65.dp),
        shape = shape


    ) {

        Row(
            modifier = Modifier.fillMaxWidth().height(height = 65.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(userImage == null){
                ContactProfileImage(
                    name = userName,
                    photoUri = null
                )
            }else{
                UserProfileImage(
                    imageRes = R.drawable.user_img,
                    size = 50.dp,
                    backgroundColor = Color.LightGray,
                    borderColor = Color.Transparent,
                    borderWidth = 0.dp
                )
            }

            Spacer(Modifier.width(10.dp))
            Column(
                modifier = Modifier.size(height = 44.dp, width = 234.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = userName/*stringResource(userName)*/, fontSize = 14.sp, fontWeight = FontWeight.W400, color = Black80)
                Spacer(Modifier.height(6.dp))
                Text(text =  message/*stringResource(message)*/, fontSize = 12.sp, fontWeight = FontWeight.W400, color = Black80)

            }

            if(isRadioButton){
                Spacer(Modifier.width(10.dp))
                RadioButton(
                    selected = (selectedOption == "No"),
                    onClick = { selectedOption = "No" }
                )
            }

        }


    }

}



@Preview(showBackground = true)
@Composable
fun UserCardPreview(){
    UserCard(userName = "R.string.user", userImage = 0, message = "R.string.your_self_message", isRadioButton = false)
}


@Composable
fun GroupTitle(title:String){
    Text(text = title /*stringResource(title)*/, color = Black80, fontWeight = FontWeight.W600, fontSize = 16.sp)
}