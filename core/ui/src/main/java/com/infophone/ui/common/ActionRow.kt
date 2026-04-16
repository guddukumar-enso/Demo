package com.infophone.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.ui.theme.Black80
import com.infophone.ui.theme.Gray600
import com.infophone.ui.theme.GrayDisabled

@Composable
fun ActionRow(
    title:Int,
    subTitle:Int? = null,
    onClick: () -> Unit = {},
    icon: ImageVector? = null,
    isIcon:Boolean = true,
    iconColor: Color = Gray600
){
    Row(
        modifier = Modifier.fillMaxWidth().clickable{onClick()}.padding( vertical = 12.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(title),
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Light), color = Black80
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(subTitle != null)
                Text(text = stringResource(subTitle),
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Light), color = Color.Black)
            Spacer(Modifier.width(width = 10.dp))
            if(isIcon)
                Box(
                    modifier = Modifier.size(24.dp),
                    contentAlignment = Alignment.Center
                ){
                    Icon(imageVector = icon?: Icons.Filled.ArrowForwardIos, contentDescription = null, tint = iconColor,
                        modifier = Modifier.padding(4.dp))

                }
        }
    }
}