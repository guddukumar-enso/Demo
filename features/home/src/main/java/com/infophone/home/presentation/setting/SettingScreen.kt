package com.infophone.home.presentation.setting

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Devices
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.PersonAddAlt
import androidx.compose.material.icons.outlined.Storage
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.home.common.TopAppBar
import com.infophone.navigation.NavKey
import com.infophone.ui.theme.Indigo900
import com.infophone.ui.theme.White
import com.infophone.ui.R
import com.infophone.ui.theme.Black80
import com.infophone.ui.theme.Gray100



@Composable
fun SettingScreen(
    modifier: Modifier,
    onContacts: () -> Unit,
    onClick: (NavKey)  -> Unit,
    onProfile: ()  -> Unit,
    onMore: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Gray100)
    ) {
        // Top purple app bar
        TopAppBar("setting", "Settings", onContacts, onMore)

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            ProfileHeader(onProfile)

            Spacer(modifier = Modifier.height(10.dp))

            SettingsGroup {
                SettingsItemRow(
                    icon = com.infophone.setting.R.drawable.ic_star,
                    title = "Marked as important",
                )
                SettingsDivider()
                SettingsItemRow(
                    icon = com.infophone.setting.R.drawable.ic_devices,
                    title = "Linked devices",
                    onClick = { onClick(NavKey.LinkedDevices) }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            SettingsGroup {
                SettingsItemRow(
                    icon = com.infophone.setting.R.drawable.ic_user_rectangle,
                    title = "Account",
                    onClick = {onClick(NavKey.SettingAccount)}
                )
                SettingsDivider()
                SettingsItemRow(
                    icon = com.infophone.setting.R.drawable.ic_lock, // using as generic icon for privacy
                    title = "Privacy",
                    onClick = {onClick(NavKey.SettingPrivacy)}

                )
                SettingsDivider()
                SettingsItemRow(
                    icon = com.infophone.setting.R.drawable.ic_chat_circle_dots,
                    title = "Chats",
                    onClick = { onClick(NavKey.SettingChat) }

                )
                SettingsDivider()
                SettingsItemRow(
                    icon = com.infophone.setting.R.drawable.ic_bell,
                    title = "Notifications",
                    onClick = { onClick(NavKey.SettingNotification) }

                )
                SettingsDivider()
                SettingsItemRow(
                    icon = com.infophone.setting.R.drawable.ic_cloud,
                    title = "Storage and data",
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            SettingsGroup {
                SettingsItemRow(
                    icon = com.infophone.setting.R.drawable.ic_info,
                    title = "FAQ",
                )
                SettingsDivider()
                SettingsItemRow(
                    icon = com.infophone.setting.R.drawable.ic_address_book,
                    title = "Invite a friend",
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Bottom nav (reuse your existing composable)
            /*BottomNavBar(
                selected = Screen.Settings,
                onSelect = onTabSelected
            )*/
        }
    }
}

/**
 * Top profile row: avatar + name + subtitle
 */
@Composable
private fun ProfileHeader(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Replace with real image if you have one; using placeholder
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape).clickable{onClick()}
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFBDB2FF)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "P",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = "Purvi Jain",
                style = MaterialTheme.typography.titleMedium,
                color = Indigo900
            )
            Spacer(Modifier.height(height = 5.dp))
            Text(
                text = "If anything urgent, please sort it yourself.",
               style = MaterialTheme.typography.bodySmall,
                color = Indigo900
            )
        }
    }
}

/**
 * Card-like container group for setting items
 */
@Composable
private fun SettingsGroup(
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White),
        content = content
    )
}

/**
 * Single settings row (icon + title + chevron)
 */
@Composable
private fun SettingsItemRow(
    icon: Int,
    title: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = Color(0xFF4A4A4A),
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = Black80,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, // add simple chevron drawable, or use Icons.Default.ChevronRight if you prefer
            contentDescription = null,
            tint = Color(0xFFB0B0B0),
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
private fun SettingsDivider() {
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp),
        thickness = 1.dp,
        color = Color(0xFFE6E6E6)
    )
}
