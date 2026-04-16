package com.infophone.setting.presentation.screen

import CustomAppBar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.ui.common.SafeArea
import com.infophone.ui.theme.BackGroundColor
import com.infophone.setting.R


@Composable
fun LinkedDevicesScreen(
    modifier: Modifier,
    onBackClick: () -> Unit,
    onLinkDeviceClick: () -> Unit
) {

    SafeArea(
        backgroundColor = BackGroundColor,
        appBar = {
            CustomAppBar(
                onBack = {onBackClick()},
                title = R.string.linked_devices,
                darkIcons = false
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            // Link a device button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .background(
                        color = Color(0xFF31295C),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable { onLinkDeviceClick },
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Link a device",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Linked devices",
                fontSize = 14.sp,
                color = Color(0xFF333333)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Device list
            DeviceRow(title = "MacOS", lastActive = "Last active today at 02:37 PM") {}
            Divider(color = Color(0xFFE6E6E6), thickness = 1.dp)
            DeviceRow(title = "MacOS", lastActive = "Last active today at 02:37 PM") {}

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Tap a device to log out.",
                fontSize = 13.sp,
                color = Color(0xFF6A6A6A)
            )

            Spacer(modifier = Modifier.weight(1f))

            /*BottomNavBar(
                selected = selectedTab,
                onSelect = onTabSelected
            )*/
        }
    }

}

@Composable
fun DeviceRow(title: String, lastActive: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 14.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = title, fontSize = 16.sp, color = Color.Black)
            Text(
                text = lastActive,
                fontSize = 12.sp,
                color = Color(0xFF888888)
            )
        }

        Icon(
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = "Go",
            tint = Color(0xFFB0B0B0)
        )
    }
}
