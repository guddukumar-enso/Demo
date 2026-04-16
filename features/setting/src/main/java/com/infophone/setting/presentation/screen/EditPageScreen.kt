package com.infophone.setting.presentation.screen


import CustomAppBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.setting.R
import com.infophone.ui.common.CustomInputField
import com.infophone.ui.theme.Gray600
import com.infophone.ui.theme.Lavender50


@Composable
fun EditPageView() {
    var name by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            CustomAppBar(
                onBack = {},
                title = R.string.name,
                bntText = R.string.save,
                onClick = {},
                darkIcons = false


            )
        }
    ) { innerPadding ->   // <-- you MUST use this

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Lavender50)
                .padding(innerPadding)    // <-- FIXED
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Spacer(Modifier.height(16.dp))

            CustomInputField(
                value = name,
                onValueChange = { name = it },
                placeholder = "Purvi",
                )

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.urgent_note),
                fontWeight = FontWeight.W400,
                fontSize = 10.sp,
                color = Gray600,
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun EditPageViewPreview(){
    EditPageView()
}