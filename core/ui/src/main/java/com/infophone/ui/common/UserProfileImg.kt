package com.infophone.ui.common
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.infophone.ui.R

@Composable
fun UserProfileImage(
    imageUrl: String? = null,                 // https Image
    imageRes: Int? = null,                    // drawable
    icon: (@Composable () -> Unit)? = null,   // vector icon
    initials: String? = null,                 // AB, JP, RK
    size: Dp = 60.dp,
    shape: Shape = CircleShape,
    backgroundColor: Color = Color.LightGray.copy(alpha = 0.3f),
    borderColor: Color = Color.White,
    borderWidth: Dp = 1.dp,
    placeholder: Int = R.drawable.user_profile,  // fallback
    errorImg: Int = R.drawable.user_profile,   // when failed
    onClick: (() -> Unit)? = null
) {
    val modifier = Modifier
        .size(size)
        .clip(shape)
        .border(borderWidth, borderColor, shape)
        .background(backgroundColor)
        .let {
            if (onClick != null) it.clickable { onClick() } else it
        }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {

        when {
            // Remote Image (Coil)
            !imageUrl.isNullOrEmpty() -> {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .error(errorImg)
                        .placeholder(placeholder)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Local Drawable
            imageRes != null -> {
                Image(
                    painter = painterResource(imageRes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize().padding(all = 20.dp),
                    contentScale = ContentScale.Crop
                )
            }

            // Vector Icon
            icon != null -> {
                icon()
            }

            // Initials Text
            !initials.isNullOrEmpty() -> {
                Text(
                    text = initials.take(2).uppercase(),
                    style = TextStyle(
                        fontSize = (size.value / 2.8).sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )
                )
            }

            // Final Fallback (Placeholder)
            else -> {
                Image(
                    painter = painterResource(placeholder),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun UserProfileImageLocalPreview() {
    UserProfileImage(
        imageRes = R.drawable.user_img,
        size = 80.dp,
        backgroundColor = Color.LightGray,
        borderColor = Color.Transparent,
        borderWidth = 0.dp
    )
}

