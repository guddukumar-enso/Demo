
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.infophone.ui.theme.White

@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    height: Dp? = null,
    horizontalPadding: Dp = 10.dp,
    verticalPadding: Dp = 0.dp,
    bgColor: Color = White,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    shape: RoundedCornerShape= RoundedCornerShape(10.dp),
    onClick: (() -> Unit)? = null,
    content: @Composable RowScope.() -> Unit,

    ) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .then(if (height != null) Modifier.height(height) else Modifier)
            .clickable(enabled = onClick != null) { onClick?.invoke() },
        elevation = CardDefaults.cardElevation(0.dp),

        colors = CardDefaults.cardColors(containerColor = bgColor),
        shape = shape,

    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .then(if (height != null) Modifier.height(height) else Modifier)
                .padding(horizontal = horizontalPadding, vertical = verticalPadding),
            verticalAlignment = verticalAlignment,
            horizontalArrangement = horizontalArrangement,
            content = content
        )
    }
}

