
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.group.R
import com.infophone.ui.theme.Black80
import com.infophone.ui.theme.BlueIndigo
import com.infophone.ui.theme.Indigo900
import com.infophone.ui.theme.White

@Composable
fun LinksSection(){

    LazyColumn() {

        item {
            FileLinkCard(
                leftBoxContent = {
                    Icon(painter = painterResource(R.drawable.ic_location), contentDescription = null, tint = White)
                },
                rightContent = {
                    Column(
                        modifier = Modifier.fillMaxWidth().height(60.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Dalamal Towers", fontSize = 14.sp, fontWeight = FontWeight.W300,
                            color = Black80
                        )
                        Spacer(Modifier.height(8.dp))
                        Text("https://share.google/pXZ8Ibc4QmYuKxJ8v", fontSize = 14.sp, fontWeight = FontWeight.W300,  color = BlueIndigo)

                    }
                }
            )
        }

        item {
            Spacer(Modifier.height(16.dp))
            FileLinkCard(
                leftBoxContent = {
                    Icon(painter = painterResource(R.drawable.ic_attachment), contentDescription = null, tint = White)
                },
                rightContent = {
                    Column(
                        modifier = Modifier.fillMaxWidth().height(60.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("meet.google.com", fontSize = 14.sp, fontWeight = FontWeight.W300,
                            color = Black80)
                        Spacer(Modifier.height(8.dp))
                        Text("https://share.google/pXZ8Ibc4QmYuKxJ8v", fontSize = 14.sp, fontWeight = FontWeight.W300,  color = BlueIndigo)
                    }
                }
            )
        }

        item {
            Spacer(Modifier.height(16.dp))
            FileLinkCard(
                leftBoxContent = {
                    Icon(painter = painterResource(R.drawable.ic_attachment), contentDescription = null, tint = White)
                },
                rightContent = {
                    Column(
                        modifier = Modifier.fillMaxWidth().height(60.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("teams meeting", fontSize = 14.sp, fontWeight = FontWeight.W300,
                            color = Black80)
                        Spacer(Modifier.height(8.dp))
                        Text("https://share.google/pXZ8Ibc4QmYuKxJ8v", fontSize = 14.sp, fontWeight = FontWeight.W300,  color = BlueIndigo)
                    }
                }
            )
        }

        item {
            Spacer(Modifier.height(16.dp))
            FileLinkCard(
                leftBoxContent = {
                    Icon(painter = painterResource(R.drawable.ic_pdf), contentDescription = null, tint = White)
                },
                rightContent = {
                    Column(
                        modifier = Modifier.fillMaxWidth().height(60.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Documentation", fontSize = 14.sp, fontWeight = FontWeight.W300,
                            color = Black80)
                        Spacer(Modifier.height(8.dp))
                        Text("bit.ly/doc", fontSize = 14.sp, fontWeight = FontWeight.W300,  color = BlueIndigo)
                    }
                }
            )
        }

    }
}