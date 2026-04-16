package com.infophone.setting.presentation.screen

import CustomCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.setting.R
import com.infophone.ui.common.ActionRow
import com.infophone.ui.common.CustomBottomSheet
import com.infophone.ui.theme.Indigo900
import com.infophone.ui.theme.Lavender50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MuteDurationBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    onDismiss: () -> Unit = {}
) {

    CustomBottomSheet(
        sheetState = sheetState,
        onDismissRequest ={ onDismiss()}

    ) {

        Spacer(Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.mute_durations),
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(10.dp))
        CustomCard(
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(R.string.mute_duration_info),
                fontSize = 12.sp, fontWeight = FontWeight.W300, color = Indigo900,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )

        }
        Spacer(Modifier.height(10.dp))
        CustomCard() {
            Column() {
                ActionRow(title = R.string.eight_hours)
                HorizontalDivider(thickness = 1.dp, color = Lavender50)
                ActionRow(title = R.string.one_week)
                HorizontalDivider(thickness = 1.dp, color = Lavender50)
                ActionRow(title = R.string.always)

            }



        }
        Spacer(Modifier.height(10.dp))


    }
}