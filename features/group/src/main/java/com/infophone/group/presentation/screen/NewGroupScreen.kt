package com.infophone.group.presentation.screen


import CustomAppBar
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import com.infophone.contact.presentation.ContactScreen
import com.infophone.group.R
import com.infophone.group.presentation.composable.UserSearchBar
import com.infophone.navigation.NavKey
import com.infophone.ui.common.SafeArea
import com.infophone.ui.theme.Black80
import com.infophone.ui.theme.Gray100
import demoUsers
import groupUsers


@SuppressLint("UnrememberedMutableState")
@Composable
fun NewGroupScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onGroupClick: (NavKey) -> Unit,
) {
    var search by remember { mutableStateOf("") }

    val users = demoUsers
    val groupedUsers = groupUsers(users)

    SafeArea(
        fullPageModifier = Modifier.fillMaxSize().background(color = Gray100),
        horizontalPadding = 0.dp,
        verticalPadding = 0.dp,
        backgroundColor = Gray100,
        appBar = {
            CustomAppBar(
                onBack = {onBack()},
                onClick = {},
                title = R.string.new_group,
                darkIcons = false

            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            UserSearchBar(
                query = search,
                onChange = { search = it}
            )


                    // You can add your ContactScreen here if you want
                    ContactScreen(
                        modifier = modifier,
                        header = {
                            item {
                                ActionRow(R.drawable.ic_users, "New Group") {onGroupClick(NavKey.SelectGroup)}

                            }

                            item {
                                ActionRow(R.drawable.ic_user_circle_add, "New Contact") {}
                            }

                            item {
                                ActionRow(R.drawable.ic_magic_wand, "Chat with AI assist") {}
                            }
                        },
                        onContactClick = {  }
                    )
                }



    }
}

@Composable
fun ActionRow(
    icon: Int,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}


@Composable
fun TitleText(
    title: Int,
    icon:Int,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null
        )

        Spacer(Modifier.width(10.dp))

        Text(
            text = stringResource(title),
            fontSize = 16.sp,
            fontWeight = FontWeight.W400,
            color = Black80
        )
    }
}


