
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.infophone.ui.theme.Indigo900

@Composable
fun FileLinkCard(
    modifier: Modifier = Modifier,
    height: Dp = 60.dp,
    leftBoxContent: @Composable BoxScope.() -> Unit,
    rightContent: @Composable RowScope.() -> Unit
) {
    CustomCard(
        height = height,
        modifier = modifier,
        horizontalPadding = 0.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {

            Box(
                modifier = Modifier
                    .size(height)
                    .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                    .background(color = Indigo900),
                contentAlignment = Alignment.Center,
                content = leftBoxContent
            )


            // 🔵 RIGHT SIDE CONTENT (Title, subtitle, icons…)
            Row(
                modifier = Modifier.weight(1f).padding( horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                content = rightContent
            )
        }
    }
}



