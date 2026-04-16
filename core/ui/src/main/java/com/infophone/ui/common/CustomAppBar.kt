
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.ui.R
import com.infophone.ui.common.StatusBarTheme
import com.infophone.ui.theme.GrayDisabled
import com.infophone.ui.theme.Indigo900
import com.infophone.ui.theme.Primary
import com.infophone.ui.theme.White


@Composable
fun CustomAppBar(
    onBack: () -> Unit,
    onClick: () -> Unit = {},
    title: Int? = null,
    titleColor: Color = White,
    bgColor: Color = Primary,
    bntColor: Color = GrayDisabled,
    bntText: Int? = null,
    userName: String? = null,
    darkIcons: Boolean = true
) {
    StatusBarTheme(darkIcons, color = Indigo900)

    Box(
        modifier = Modifier.fillMaxWidth().background(color = bgColor).systemBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Back Icon with contentDescription for accessibility
            IconButton(onClick = { onBack() }) {
                Icon(painter = painterResource(R.drawable.ic_arrow_left), contentDescription = "Back", tint = Color.White)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                if (title != null) {
                    Text(
                        text = stringResource(id = title),
                        color = titleColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                if (userName != null) {
                    Text(
                        text = userName,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W300,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            if (bntText != null) {
                Text(
                    text = stringResource(id = bntText),
                    color = bntColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable { onClick() }
                        .padding(horizontal = 8.dp)
                )
            } else {
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}




