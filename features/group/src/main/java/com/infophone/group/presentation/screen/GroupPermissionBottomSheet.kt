package com.infophone.group.presentation.screen


import CustomCard
import CustomSwitch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.group.R
import com.infophone.ui.common.CustomBottomSheet
import com.infophone.ui.theme.Indigo900
import com.infophone.ui.theme.Lavender50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupPermissionBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    onDismiss: () -> Unit = {}
) {

    CustomBottomSheet(
        sheetState = sheetState,
        onDismissRequest ={ onDismiss()}

    ) {

        Column(
            modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp)
        ) {

            //Exit title
            Text(
                text = stringResource(R.string.group_permissions),
                style = MaterialTheme.typography.titleMedium,
                color = Indigo900,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()

            )
            Spacer(Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.members_can),
                style = MaterialTheme.typography.titleMedium,
                color = Indigo900,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()

            )
            Spacer(Modifier.height(10.dp))
            MembarCanCard()

            Spacer(Modifier.height(10.dp))

            Text(
                text = "Turning off these settings means that only group admins can perform this action.",
                fontSize = 11.sp,
                fontWeight = FontWeight.W400,
                color = Indigo900,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()

            )

            Spacer(Modifier.height(10.dp))
            Text(
                text = "Admins can",
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                color = Indigo900,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()

            )

            Spacer(Modifier.height(10.dp))
            AdminCan()

            Spacer(Modifier.height(10.dp))
            Text(
                text = "When turned on, admins must approve anyone who wants to join this group. Learn more",
                fontSize = 11.sp,
                fontWeight = FontWeight.W400,
                color = Indigo900,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()

            )

            Spacer(Modifier.height(10.dp))



        }

    }
}




@Composable
fun MembarCanCard(){
    CustomCard(
        horizontalPadding = 10.dp,
        verticalPadding = 5.dp
    ) {
      Column(
          modifier = Modifier.fillMaxWidth()
      ) {
          PermissionSwitch(icon = com.infophone.ui.R.drawable.ic_edit, title = "Edit group settings")
          Text(
              text = "This includes the group name, icon, description, disappearing, disappearing message timer, advanced chat privacy, and the ability to pin, keep or unkeep messages.",
              fontSize = 11.sp,
              fontWeight = FontWeight.W400,
              color = Indigo900,
              textAlign = TextAlign.Start,
              modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)

          )
          Spacer(Modifier.height(10.dp))
          HorizontalDivider(thickness = 1.dp, color = Lavender50)
          Spacer(Modifier.height(10.dp))
          PermissionSwitch(icon = com.infophone.ui.R.drawable.ic_send_newmessage, title = "Send new messages")
          HorizontalDivider(thickness = 1.dp, color = Lavender50)
          PermissionSwitch(icon = R.drawable.ic_users, title = "Add other members")
          HorizontalDivider(thickness = 1.dp, color = Lavender50)
          PermissionSwitch(icon = R.drawable.ic_invite, title = "Invite via link or QR code")



      }
    }
}

@Composable
fun PermissionSwitch(icon:Int, title:String){
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(painter = painterResource(icon), contentDescription = null)
        Spacer(Modifier.width(width = 10.dp))
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.W400,
            color = Indigo900,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth().weight(1f)

        )
        CustomSwitch()
    }
}


@Composable
fun AdminCan(){
    CustomCard(
        horizontalPadding = 10.dp,
        verticalPadding = 15.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            PermissionSwitch(icon = com.infophone.ui.R.drawable.ic_new_user, title = "Approve new members")

        }
    }
}