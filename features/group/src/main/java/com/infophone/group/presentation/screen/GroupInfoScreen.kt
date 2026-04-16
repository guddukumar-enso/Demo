package com.infophone.group.presentation.screen


import CustomAppBar
import CustomCard
import CustomSwitch
import UserCard
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.infophone.contact.presentation.ContactsViewModel
import com.infophone.group.R
import com.infophone.group.presentation.viewModal.SelectUserViewModal
import com.infophone.navigation.NavKey
import com.infophone.ui.common.ActionRow
import com.infophone.ui.common.CustomInputField
import com.infophone.ui.common.SafeArea
import com.infophone.ui.common.UserProfileImage
import com.infophone.ui.theme.Gray100
import com.infophone.ui.theme.Gray600
import com.infophone.ui.theme.GrayDisabled
import com.infophone.ui.theme.Indigo900
import com.infophone.ui.theme.Lavender50
import com.infophone.ui.theme.White


@Composable
fun GroupInfoScreen(
    modifier: Modifier,
    onBack: () -> Unit,
    onClick: () -> Unit,
    onTap:(NavKey)-> Unit,
    viewModel: SelectUserViewModal = hiltViewModel(),


    ){

    var textValue by remember { mutableStateOf("") }


    SafeArea(
        appBar = { CustomAppBar(
            onBack = {onBack()},
            onClick = {
                onClick()
            },
            title = R.string.group_info,
            bntColor = White,
            bntText = R.string.edit,
            darkIcons = false

        )},
        horizontalPadding = 24.dp,
        verticalPadding = 0.dp,
        backgroundColor = Gray100
    ){

        LazyColumn() {

            //User profile image
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    contentAlignment = Alignment.Center
                ){

                    UserProfileImage(
                        imageRes = R.drawable.user_img,
                        size = 80.dp,
                        backgroundColor = Color.LightGray,
                        borderColor = Color.Transparent,
                        borderWidth = 0.dp
                    )

                }
            }

            item {
                Spacer(Modifier.height(height = 16.dp))
                Text(text = "Enso Webworks", fontSize = 20.sp, fontWeight = FontWeight.W500, color = Indigo900,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center)
                Spacer(Modifier.height(height = 4.dp))
                Text(text = "2 members", fontSize = 14.sp, fontWeight = FontWeight.W400, color = Indigo900,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center)
            }

            //Search here
            item {
                Spacer(Modifier.height(height = 20.dp))
                CustomInputField(
                    value = textValue,
                    onValueChange = { textValue = it },
                    prefixIcon = {
                        Icon(imageVector = Icons.Filled.Search, tint = Gray600, contentDescription = null)
                    },
                    cornerRadius = 70.dp,
                    placeholder = "Search here...",
                    height = 46.dp,
                    backgroundColor = Gray100,
                    borderWidth = 1.dp,
                    borderColor = GrayDisabled,
                )
            }

            //Audio, video, invite and add taps
            item {
                Spacer(Modifier.height(height = 10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FeatureCard(
                            title = "Audio",
                            painter = painterResource(R.drawable.ic_call),
                            modifier = Modifier.weight(1f)
                        )

                        FeatureCard(
                            title = "Add",
                            painter = painterResource(R.drawable.ic_user_add),
                            modifier = Modifier.weight(1f)
                        )

                        FeatureCard(
                            title = "Video",
                            painter = painterResource(R.drawable.ic_video),
                            modifier = Modifier.weight(1f)
                        )

                        FeatureCard(
                            title = "Invite",
                            painter = painterResource(R.drawable.ic_invite),
                            modifier = Modifier.weight(1f)
                        )
                    }

                }


                Spacer(Modifier.height(height = 10.dp))
                Text(text = stringResource(R.string.add_description), fontSize = 14.sp, fontWeight = FontWeight.W400, color = Indigo900,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    textAlign = TextAlign.Start)
                Spacer(Modifier.height(height = 10.dp))
            }

            //FirstTh Group card
            item {
                CustomCard(
                    verticalPadding = 0.dp,
                    horizontalPadding = 0.dp
                ){

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ActionRow(title = R.string.labels, subTitle = R.string.none)
                    HorizontalDivider(Modifier
                        .fillMaxWidth()
                        .padding(0.dp), thickness = 1.dp, color = Lavender50)
                    ActionRow(title = R.string.media_links_docs, subTitle = R.string.none, onClick = {onTap(
                        NavKey.MediaShare)})
                    HorizontalDivider(Modifier
                        .fillMaxWidth()
                        .padding(0.dp), thickness = 1.dp, color = Lavender50)
                    ActionRow(title = R.string.marked_as_important, subTitle = R.string.none)

                }
                }
                Spacer(Modifier.height(10.dp))

            }

            //SecondTh Group Card
            item {
                CustomCard(
                    verticalPadding = 0.dp,
                    horizontalPadding = 0.dp
                ){

                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ActionRow(title = R.string.notifications, subTitle = R.string.all)
                        HorizontalDivider(Modifier
                            .fillMaxWidth()
                            .padding(0.dp), thickness = 1.dp, color = Lavender50)
                        ActionRow(title = R.string.save_to_photos, subTitle = R.string.default_text)

                    }

                }
                Spacer(Modifier.height(10.dp))


            }

            //ThirdTh Group card
            item {
                CustomCard(
                    verticalPadding = 0.dp,
                    horizontalPadding = 0.dp

                ){

                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ActionRow(title = R.string.disappearing_messages, subTitle = R.string.off)
                        HorizontalDivider(
                            Modifier
                                .fillMaxWidth()
                                .padding(0.dp),
                            thickness = 1.dp,
                            color = Lavender50
                        )
                        ActionRow(title = R.string.group_permissions, subTitle = null)
                        HorizontalDivider(
                            Modifier
                                .fillMaxWidth()
                                .padding(0.dp),
                            thickness = 1.dp,
                            color = Lavender50
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp, horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column() {
                                Text(
                                    text = stringResource(R.string.lock_chat),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.W300,
                                    color = Color.Black
                                )
                                Spacer(Modifier.height(height = 4.dp))
                                Text(
                                    text = stringResource(R.string.lock_chat_description),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.W300,
                                    color = Color.Black
                                )
                            }

                            //group_info_lock_hide_desc
                            CustomSwitch()
                        }
                        HorizontalDivider(
                            Modifier
                                .fillMaxWidth()
                                .padding(0.dp),
                            thickness = 1.dp,
                            color = Lavender50
                        )
                        ActionRow(title = R.string.advanced_chat_privacy, subTitle = R.string.off)
                        HorizontalDivider(
                            Modifier
                                .fillMaxWidth()
                                .padding(0.dp),
                            thickness = 1.dp,
                            color = Lavender50
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp, horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column() {
                                Text(
                                    text = stringResource(R.string.encryption),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.W300,
                                    color = Color.Black
                                )
                                Text(
                                    text = stringResource(R.string.encryption_description),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.W300,
                                    color = Color.Black
                                )

                            }
                            Row(
                                modifier = Modifier.height(40.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowForwardIos,
                                    contentDescription = null,
                                    tint = GrayDisabled
                                )
                            }
                        }
                    }

                }

            }

            //fourTh Group card
            item {
                Spacer(Modifier.height(height = 16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "MEMBERS: ${viewModel.selectedContacts.size} OF ${viewModel.contacts.size}", fontSize = 14.sp, fontWeight = FontWeight.W300, color = Gray600,
                        modifier = Modifier.padding(start = 10.dp))

                    Icon(imageVector = Icons.Filled.Search, contentDescription = null, tint = Gray600)

                }

                Spacer(Modifier.height(height = 10.dp))
                CustomCard(
                  verticalPadding = 0.dp,
                  horizontalPadding = 0.dp
                ){
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ){
                        TitleText(
                            title = R.string.add_members,
                            icon = R.drawable.ic_users,
                            onClick = {})

                    }
                    HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp, color = Lavender50)

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TitleText(
                            title = R.string.invite_via_link_qr,
                            icon = R.drawable.ic_user_circle_add_fill,
                            onClick = {})
                    }
                    HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp, color = Lavender50)

                    viewModel.selectedContacts.forEachIndexed {index, contact ->
                        UserCard(userName = contact.name, message = "${contact.phone}", isRadioButton = false, shape = RoundedCornerShape(0.dp))

                        if(index < viewModel.selectedContacts.size){
                            HorizontalDivider(
                                Modifier.fillMaxWidth(),
                                thickness = 1.dp,
                                color = Lavender50
                            )
                        }

                    }

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = stringResource(R.string.see_past_members), fontSize = 14.sp, fontWeight = FontWeight.W300, color = Color.Black)
                        Icon(imageVector = Icons.Filled.ArrowForwardIos, contentDescription = null, tint = GrayDisabled)

                    }
                }
                }


            }

            //FiveTh Group card
            item {
                Spacer(Modifier.height(height = 10.dp))
                CustomCard(
                   verticalPadding = 0.dp,
                    horizontalPadding = 0.dp
                ){

                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(R.string.add_to_favourites), fontSize = 14.sp, fontWeight = FontWeight.W300,
                            color = Color.Black,
                            modifier = Modifier.padding(10.dp)
                        )

                        HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp, color = Lavender50)
                        Text(text = stringResource(R.string.export_chat), fontSize = 14.sp,
                            modifier = Modifier.padding(  10.dp),
                            fontWeight = FontWeight.W300, color = Color.Black)

                        HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp, color = Lavender50)
                        Text(text = stringResource(R.string.clear_chat), fontSize = 14.sp,
                            modifier = Modifier.padding( 10.dp),
                            fontWeight = FontWeight.W300, color = Color.Red)

                    }

                }

                Spacer(Modifier.height(height = 16.dp))

            }

            //SixTh Group card
            item {
                //Second Group Card
                CustomCard(

                    verticalPadding = 0.dp,
                    horizontalPadding = 0.dp
                ){

                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(R.string.exit_group), fontSize = 14.sp,
                            modifier = Modifier.padding(10.dp),
                            fontWeight = FontWeight.W300, color = Color.Red)

                        HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp, color = Lavender50)
                        Text(text = stringResource(R.string.report_group),
                            modifier = Modifier.padding(10.dp),

                            fontSize = 14.sp, fontWeight = FontWeight.W300, color = Color.Red)


                    }

                }


                Text(text = stringResource(R.string.created_by_and_on), fontSize = 14.sp, fontWeight = FontWeight.W300, color = Gray600,
                    modifier = Modifier.padding(  10.dp))


            }


        }



    }
}




@Composable
fun FeatureCard(
    title: String,
    painter: Painter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    CustomCard(
        modifier = modifier
            .aspectRatio(1f)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Black)

            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = title,
                fontSize = 14.sp,
                maxLines = 1,
                fontWeight = FontWeight.W300,
                color = Color.Black
            )
        }
    }
}

