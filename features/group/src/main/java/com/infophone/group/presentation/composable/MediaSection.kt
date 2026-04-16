
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.infophone.group.R


@Composable
fun MediaSection() {

    val mediaList = listOf(
        R.drawable.ic_media_1,
        R.drawable.ic_media_2,
        R.drawable.ic_media_3,
        R.drawable.ic_media_3,
        R.drawable.ic_media_5,
        R.drawable.ic_media_6,
        R.drawable.ic_media_7,
        R.drawable.ic_media_8,
        R.drawable.ic_media_9,
        R.drawable.ic_media_10,
        R.drawable.ic_media_11,
        R.drawable.ic_media_12,
    ) // example

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {

        items(mediaList) { imageRes ->
            MediaGridItem(imageRes)
        }
    }
}

@Composable
fun MediaGridItem(imageRes: Int) {
    Image(
        painter = painterResource(imageRes),
        contentDescription = null,
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}
