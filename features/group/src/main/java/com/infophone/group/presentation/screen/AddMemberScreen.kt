package com.infophone.group.presentation.screen

import CustomAppBar
import CustomCard
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.infophone.contact.domain.model.Contact
import com.infophone.contact.presentation.ContactProfileImage
import com.infophone.contact.presentation.ContactScreen
import com.infophone.contact.presentation.ContactsViewModel
import com.infophone.group.R
import com.infophone.group.presentation.composable.UserSearchBar
import com.infophone.group.presentation.viewModal.SelectUserViewModal
import com.infophone.ui.common.SafeArea
import com.infophone.ui.theme.Black80
import com.infophone.ui.theme.Gray100
import com.infophone.ui.theme.Green80
import com.infophone.ui.theme.Indigo900
import com.infophone.ui.theme.White



@SuppressLint("UnrememberedMutableState")
@Composable
fun AddMemberGroupScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onSelect:()-> Unit,
    onCreate: () -> Unit,
    viewModel: SelectUserViewModal = hiltViewModel(),

    ) {


    SafeArea(
        fullPageModifier = Modifier.fillMaxSize().background(color = Gray100),
        horizontalPadding = 0.dp,
        verticalPadding = 0.dp,
        backgroundColor = Gray100,
        appBar = {
            CustomAppBar(
                onBack = {onBack()},
                onClick = {
                    onCreate()
                },
                title = R.string.add_members,
                userName = "${viewModel.selectedContacts.size}/${viewModel.contacts.size}",
                bntText = R.string.next,
                bntColor = White,
                darkIcons = false


            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            UserSearchBar(
                query = viewModel.search,
               onChange = { viewModel.onSearchChange(it) }
            )

            // You can add your ContactScreen here if you want
            ContactScreen(
                modifier = modifier,
                onContactClick = { contract ->
                    viewModel.toggleContact(contract)
                },
                selectUser = {contact, contacts ->

                    viewModel.contacts.clear()
                    viewModel.contacts.addAll(contacts)

                    RoundCheckbox(

                        checked = viewModel.isContactSelected(contact),
                        onCheckedChange = {
                            viewModel.toggleContact(contact)
                        }
                    )

                },

                header = {

                        if (viewModel.selectedContacts.isNotEmpty()){
                            item {
                                CustomCard(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 16.dp)
                                ) {
                                    LazyRow(
                                        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                                    ) {
                                        items(
                                            items = viewModel.selectedContacts,
                                            key = { it.id }
                                        ) { contact ->


                                            Column(
                                                modifier = Modifier.width(width = 80.dp),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                Box(){

                                                    ContactProfileImage(
                                                        name = contact.name,
                                                        photoUri = contact.photoUri
                                                    )
                                                    Box(
                                                        modifier = Modifier.padding(start = 30.dp).size(25.dp).clip(RoundedCornerShape(15.dp)).background(color = Indigo900)
                                                            .clickable {
                                                                viewModel.toggleContact(contact = contact)
                                                        },
                                                        contentAlignment = Alignment.Center
                                                    ){
                                                        Icon(imageVector = Icons.Filled.Close, contentDescription = null, tint = White, modifier = Modifier.size(16.dp))
                                                    }
                                                }


                                                Text(text = contact.name, fontSize = 10.sp, color = Black80, fontWeight = FontWeight.W400,
                                                    maxLines = 1,
                                                    textAlign = TextAlign.Center,
                                                    overflow = TextOverflow.Ellipsis
                                                )
                                            }

                                        }
                                    }
                                }
                            }
                        }


                }

            )
        }


    }

}



@Composable
fun RoundCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    checkedColor: Color = Green80,
    uncheckedColor: Color = Black80
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(if (checked) checkedColor else Color.Transparent)
            .border(
                width = 1.dp,
                color = uncheckedColor,
                shape = CircleShape
            )
            .clickable { onCheckedChange(!checked) },
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(imageVector = Icons.Filled.Check, contentDescription = "check", tint = White, modifier = Modifier.padding(4.dp))
        }
    }
}


