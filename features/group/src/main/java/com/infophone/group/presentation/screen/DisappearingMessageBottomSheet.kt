package com.infophone.group.presentation.screen


import CustomCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.group.R
import com.infophone.ui.common.CustomBottomSheet
import com.infophone.ui.theme.Indigo900
import com.infophone.ui.theme.Lavender50


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisappearingMessage(
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
                text = stringResource(R.string.disappearing_messages),
                style = MaterialTheme.typography.titleMedium,
                color = Indigo900,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()

            )
            Spacer(Modifier.height(20.dp))


            CustomCard() {

                    Text(
                        text = stringResource(R.string.disappearing_messages_info),
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Light),
                        color = Indigo900,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)

                    )

            }
            Spacer(Modifier.height(10.dp))

            CustomCard(
                horizontalPadding = 10.dp,
                verticalPadding = 5.dp,
                horizontalArrangement = Arrangement.Start
            ) {

              Column(
                  modifier = Modifier.fillMaxWidth()
              ) {
                  MenuItem("24 Hours")
                  MenuItem("7 Days")
                  MenuItem("90 Days")
                  MenuItem("Off", false)
              }

            }



        }

    }
}





@Composable
fun MenuItem(text: String, isDivider: Boolean = true) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = Indigo900,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp)
        )
        if(isDivider)
        HorizontalDivider(thickness = 1.dp, color = Lavender50)
    }
}
