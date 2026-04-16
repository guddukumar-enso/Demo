

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
import com.infophone.ui.theme.GrayDisabled
import com.infophone.ui.theme.White

@Composable
fun DocsSection(){
    LazyColumn() {

        item {
            FileLinkCard(
                leftBoxContent = {
                    Icon(painter = painterResource(R.drawable.ic_pdf), contentDescription = null, tint = White)
                },
                rightContent = {
                    Column(
                        modifier = Modifier.fillMaxWidth().height(60.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Pritesh Jain.png", fontSize = 14.sp, fontWeight = FontWeight.W300,
                            color = Black80
                        )
                        Spacer(Modifier.height(8.dp))
                        Text("12.1 MB", fontSize = 14.sp, fontWeight = FontWeight.W300,  color = GrayDisabled)
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
                        Text("Mumbai to Surat.pdf", fontSize = 14.sp, fontWeight = FontWeight.W300,
                            color = Black80)
                        Spacer(Modifier.height(8.dp))
                        Text("3 pages - 432 KB ", fontSize = 14.sp, fontWeight = FontWeight.W300,  color = GrayDisabled)
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
                        Text("Acct Statement_XXX1234_5678987.pdf", fontSize = 14.sp, fontWeight = FontWeight.W300,
                            color = Black80)
                        Spacer(Modifier.height(8.dp))
                        Text("11 pages - 45 KB", fontSize = 14.sp, fontWeight = FontWeight.W300,  color = GrayDisabled)
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
                        Text("Purvi Jain.jpeg", fontSize = 14.sp, fontWeight = FontWeight.W300,
                            color = Black80)
                        Spacer(Modifier.height(8.dp))
                        Text("12.1 MB", fontSize = 14.sp, fontWeight = FontWeight.W300,  color = GrayDisabled)
                    }
                }
            )
        }

    }
}