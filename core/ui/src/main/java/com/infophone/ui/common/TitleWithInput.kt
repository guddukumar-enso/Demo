package com.infophone.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.ui.theme.Gray100
import com.infophone.ui.theme.Indigo900

@Composable
fun TitleWithInput(title:Int, onClick: () -> Unit, hintText: String, modifier: Modifier? = null,  textValue: String,     onValueChange: (String) -> Unit,
){



    Column(
        modifier = modifier?:Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {

        Text(text = stringResource(title),
            color = Indigo900,
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(Modifier.height(16.dp))


        CustomInputField(
            value = textValue,
            onValueChange = onValueChange,
            placeholder = hintText,
            height = 46.dp,
            backgroundColor = Gray100,
            borderWidth = 0.dp,
            borderColor = Gray100,
        )

    }
}