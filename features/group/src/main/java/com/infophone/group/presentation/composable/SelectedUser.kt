
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.group.R
import com.infophone.ui.common.UserProfileImage
import com.infophone.ui.theme.Black80


@Composable
fun SelectedUser(name:Int){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
    ) {

       Box(
        modifier = Modifier.size( 50.dp),
        contentAlignment = Alignment.TopStart
       ){
           UserProfileImage(
               imageRes = R.drawable.user_img,
               size = 48.dp,
               backgroundColor = Color.LightGray,
               borderColor = Color.Transparent,
               borderWidth = 0.dp
           )

           Box(
               modifier = Modifier.padding(start = 22.dp).size(25.dp)
                   .clip(RoundedCornerShape(15.dp))
                   .background(color = Color.Black),
               contentAlignment = Alignment.Center
           ){
               Icon(imageVector = Icons.Filled.Close, contentDescription = null, tint = Color.White, modifier = Modifier.size(15.dp))
           }

       }

        Spacer(Modifier.height(4.dp))

        Text(
            text = stringResource(name),
            color = Black80,
            fontSize = 10.sp,
            fontWeight = FontWeight.W400)

    }
}