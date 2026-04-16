package com.infophone.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    onClick: () -> Unit,
    textResId: Int,
    modifier: Modifier = Modifier,
    colors: ButtonColors,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(10.dp),
    contentPadding: Dp = 12.dp,
    textStyle: TextStyle = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 16.sp
    ),
    leadingIcon: (@Composable (() -> Unit))? = null,
    trailingIcon: (@Composable (() -> Unit))? = null,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    isLoading: Boolean = false,
    contentDescription: String? = null
) {
    Button(
        onClick = { if (!isLoading) onClick() },
        modifier = modifier.sizeIn(minHeight = 48.dp),
        colors = colors,
        shape = shape,
        enabled = enabled && !isLoading,
        contentPadding = PaddingValues(horizontal = contentPadding, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = horizontalArrangement,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (leadingIcon != null) {
                leadingIcon()
            }

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(18.dp),
                    strokeWidth = 2.dp,
                    color = Color.Green
                )
            } else {
                Text(
                    text = stringResource(textResId),
                    style = textStyle,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f),
                    maxLines = 1
                )
            }

            if (trailingIcon != null) {
                trailingIcon()
            }
        }
    }
}


