package com.infophone.ui.common
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import com.infophone.ui.theme.Black80
import com.infophone.ui.theme.Gray80


@Composable
fun CustomInputField(
    value: String,
    readOnly: Boolean = false,
    onValueChange:  (String) -> Unit,
    modifier: Modifier = Modifier,
    height: Dp = 46.dp,
    backgroundColor: Color = Color.White,
    borderColor: Color = Color.Gray,
    borderWidth: Dp = 1.dp,
    verticalPadding: Dp = 0.dp,
    cornerRadius: Dp = 8.dp,
    prefixIcon: @Composable (() -> Unit)? = null,
    suffixIcon: @Composable (() -> Unit)? = null,
    placeholder: String = "",
    textStyle: TextStyle = TextStyle(fontSize = 16.sp),
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    contentAlignment :Alignment = Alignment.CenterStart,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically


) {


    BasicTextField(
        value = value,
        readOnly= readOnly,
        onValueChange = { newValue -> onValueChange
            onValueChange(newValue)

        },
        singleLine = singleLine,
        textStyle = textStyle.copy(color = Color.Black),
        keyboardOptions = keyboardOptions,
        modifier = modifier
            .height(height)
            .background(color=backgroundColor, RoundedCornerShape(cornerRadius))
            .border(borderWidth, borderColor, RoundedCornerShape(cornerRadius))
            .padding(horizontal = 12.dp, verticalPadding),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = verticalAlignment,
                modifier = Modifier.fillMaxSize()
            ) {
                prefixIcon?.invoke()

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = contentAlignment
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = Gray80.copy(alpha = 0.6f),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400
                        )
                    }

                    innerTextField()
                }

                // Suffix Icon
                suffixIcon?.invoke()
            }
        }

    )

}




@Preview
@Composable
fun CustomInputPreView(){
    var phone by remember { mutableStateOf("") }

    CustomInputField(
        value = phone,
        onValueChange = { phone = it },
        placeholder = "Enter phone number",
        prefixIcon = {Icon(imageVector = Icons.Filled.Search, contentDescription = null)},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        height = 46.dp,
        backgroundColor = Color.White,
        borderWidth = 2.dp,
        borderColor = Color(0xFF4A90E2)
    )

}



