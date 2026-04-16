package com.infophone.auth.presentation.onboard.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp



@Composable
fun OnBoardingImage(img: Int) {
        Image(
            painter = painterResource(id = img),
            contentDescription = null,
            modifier = Modifier.height(height = 344.dp).width(width = 361.dp),
            contentScale = ContentScale.Fit   // optional → makes image fill nicely
        )
    }



