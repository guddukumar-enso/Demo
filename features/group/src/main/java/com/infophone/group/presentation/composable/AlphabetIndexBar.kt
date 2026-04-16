
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AlphabetIndexBar(
    modifier: Modifier = Modifier,
    onLetterClick: (String) -> Unit = {}
) {
    val letters = ('A'..'Z').map { it.toString() } + "#"

    LazyColumn(
        modifier = modifier.fillMaxHeight().width(width = 10.dp),
        verticalArrangement = Arrangement.Center
    ) {

        items(letters) { letter ->
            Text(
                text = letter,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(horizontal = 0.dp, vertical = 4.dp)
                   /* .clickable { onLetterClick(letter) }*/,
                color = Color.DarkGray
            )
        }
    }
}
