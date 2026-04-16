package com.infophone.group.presentation.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.infophone.ui.common.CustomInputField
import com.infophone.ui.theme.LightGrayE5
import com.infophone.ui.theme.White

@Composable
fun UserSearchBar(
    query: String,
    onChange: (String) -> Unit
) {
    CustomInputField(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
        value = query,

        onValueChange = onChange,
        placeholder = "Search name or number",
        prefixIcon =  {
            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
            Spacer(Modifier.width(width = 10.dp))
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        height = 48.dp,
        backgroundColor = White,
        borderWidth = 1.5.dp,
        borderColor = LightGrayE5,

        cornerRadius = 45.dp,

        )
}