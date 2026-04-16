package com.infophone.auth.presentation.profile

import CustomCard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.infophone.auth.R
import com.infophone.ui.common.ActionRow
import com.infophone.ui.common.CustomBottomSheet
import com.infophone.ui.theme.Gray100
import com.infophone.ui.theme.Gray600
import com.infophone.ui.theme.Gray80
import com.infophone.ui.theme.Lavender50
import com.infophone.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectProfileImageSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    onDismiss: () -> Unit = {}
){

    CustomBottomSheet(
        sheetState = sheetState,
        onDismissRequest ={ onDismiss()}
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier.size(width = 100.dp, height = 5.dp).clip(shape = RoundedCornerShape(20.dp)).background(color = Gray600)
            ){}

            Spacer(Modifier.height(height = 16.dp))

            CustomCard(
                horizontalPadding = 0.dp,
            ) {
                Column() {
                    ActionRow(title = R.string.camara)
                    HorizontalDivider(thickness = 1.dp, color = Lavender50)
                    ActionRow(title = R.string.gallery)
                    HorizontalDivider(thickness = 1.dp, color = Lavender50)
                    ActionRow(title = R.string.remove_profile)

                }



            }

        }

    }
}


