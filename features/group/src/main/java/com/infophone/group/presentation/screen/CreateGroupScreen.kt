package com.infophone.group.presentation.screen


import CustomAppBar
import CustomCard
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.infophone.contact.presentation.ContactProfileImage
import com.infophone.contact.presentation.ContactsViewModel
import com.infophone.group.R
import com.infophone.group.presentation.viewModal.SelectUserViewModal
import com.infophone.ui.common.ActionRow
import com.infophone.ui.common.CustomInputField
import com.infophone.ui.common.SafeArea
import com.infophone.ui.common.UserProfileImage
import com.infophone.ui.theme.Black80
import com.infophone.ui.theme.Gray100
import com.infophone.ui.theme.GrayDisabled
import com.infophone.ui.theme.Indigo900
import com.infophone.ui.theme.Lavender50
import com.infophone.ui.theme.White


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGroupScreen(
    modifier: Modifier,
    onBack: () -> Unit,
    onClick: () -> Unit,
    viewModel: SelectUserViewModal = hiltViewModel(),
    ) {


    var textValue by remember { mutableStateOf("") }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var disappearingBottomSheet by remember { mutableStateOf(false) }
    var permissionBottomSheet by remember { mutableStateOf(false) }


    SafeArea(
        appBar = {
            CustomAppBar(
                onBack = {onBack()},
                onClick = {onClick()},
                title = R.string.add_members,
                userName = "${viewModel.selectedContacts.size}/${viewModel.contacts.size}",
                bntColor = White,
                bntText = R.string.create,
                darkIcons = false

            )
        },
        horizontalPadding = 16.dp,
        verticalPadding = 12.dp,
        backgroundColor = Gray100
    ) {

        Spacer(Modifier.height(height = 10.dp))

        //User profile image
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            UserProfileImage(
                size = 80.dp,
                imageRes = R.drawable.user_img,
                backgroundColor = Color.LightGray,
                borderColor = Color.Transparent,
                borderWidth = 0.dp
            )

            Image(
                painter = painterResource(R.drawable.ic_camera),//camera
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 56.dp, start = 56.dp)
                    .size(30.dp),


                )
        }

        Spacer(Modifier.height(height = 10.dp))

        //Group name field
        CustomInputField(
            value = textValue,
            onValueChange = { textValue = it },
            placeholder = "Enter Group name",
            height = 46.dp,
//            backgroundColor = Gray100,
            borderWidth = 0.dp,
            borderColor = Gray100,
            textStyle = MaterialTheme.typography.titleSmall.copy(color = Indigo900)
        )

        Spacer(Modifier.height(height = 10.dp))

        //setting card
        CustomCard(
            verticalPadding = 0.dp,
            horizontalPadding = 0.dp
        ) {

           Column() {
               ActionRow(
                   title = R.string.disappearing_messages,
                   subTitle = R.string.off,
                   icon = Icons.Filled.ArrowForward,
                   onClick = { disappearingBottomSheet = true  })
               HorizontalDivider(
                   thickness = 1.dp,
                   color = Lavender50
               )
               ActionRow(
                   title = R.string.group_permissions,
                   icon = Icons.Filled.ArrowForward,
                   onClick = { permissionBottomSheet = true  })
           }


        }



        Spacer(Modifier.height(height = 10.dp))

        //Selected user Card
        if (viewModel.selectedContacts.isNotEmpty()){
                CustomCard(
                ) {
                    Column() {
                        Text("MEMBERS: ${viewModel.selectedContacts.size} OF ${viewModel.contacts.size}", fontSize = 14.sp, fontWeight = FontWeight.W400, modifier = Modifier.padding(vertical = 5.dp))
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

        Spacer(Modifier.height(height = 10.dp))


    }


    //Disappearing Bottom Sheet
    if (disappearingBottomSheet) {
        DisappearingMessage(
            sheetState = sheetState,
            onDismiss = { disappearingBottomSheet = false },

        )
    }

    //Permission Bottom Sheet
    if (permissionBottomSheet) {
        GroupPermissionBottomSheet(
            sheetState = sheetState,
            onDismiss = { permissionBottomSheet = false },

        )
    }
}
