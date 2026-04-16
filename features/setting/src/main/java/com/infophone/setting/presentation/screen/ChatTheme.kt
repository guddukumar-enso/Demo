package com.infophone.setting.presentation.screen


import CustomAppBar
import CustomCard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.setting.R
import com.infophone.ui.theme.Lavender50

@Composable
fun ChatThemeView(){
    var selectedOption by remember { mutableStateOf("Dark") }

    Scaffold(
        topBar = {
            CustomAppBar(
                onBack = {},
                title = R.string.appearance,
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
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(16.dp))
            CustomCard(
                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
            ) {
                Text(
                    text = "Dark",
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)

                )

                RadioButton(
                    selected = (selectedOption == "Dark"),
                    onClick = { selectedOption = "Dark" }
                )
            }
            HorizontalDivider(thickness = 1.dp, color = Lavender50)
            CustomCard(
                shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
            ) {
                Text(
                    text = "Light",
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)

                )

                RadioButton(
                    selected = (selectedOption == "Light"),
                    onClick = { selectedOption = "Light" }
                )
            }


        }
    }
}


