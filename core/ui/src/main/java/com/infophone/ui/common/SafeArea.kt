package com.infophone.ui.common
import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.infophone.ui.theme.White
import androidx.compose.foundation.layout.Box
import com.infophone.ui.theme.Primary
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.asPaddingValues
import com.infophone.ui.theme.Indigo900


@Composable
fun SafeArea(
    modifier: Modifier = Modifier,
    fullPageModifier: Modifier? = null,
    backgroundColor: Color = White,
    systemBarColor: Color = Primary,


    // Pass your custom AppBar or null
    appBar: @Composable (() -> Unit)? = null,

    // Optional additional Scaffold slots
    bottomBar: @Composable (() -> Unit)? = null,
    floatingActionButton: @Composable () -> Unit = {},
    horizontalPadding: Dp = 16.dp,
    verticalPadding: Dp = 12.dp,

    // Main screen UI
    body: @Composable ColumnScope.() -> Unit
) {
    Box() {
        Scaffold(
            modifier = modifier
                .fillMaxSize()
                .background(color = Indigo900),
            topBar = { appBar?.invoke() },
            bottomBar = { bottomBar?.invoke() },
            floatingActionButton = { floatingActionButton?.invoke() }
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
                    .padding(innerPadding)
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    ),
                content = body
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GenericPagePreview() {
    SafeArea(
        appBar = {
            Text("Preview AppBar", modifier = Modifier.padding(16.dp))
        }
    ) {
        Text("Hello World")
        Spacer(Modifier.height(16.dp))
    }
}
