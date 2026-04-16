package com.infophone.group.presentation.screen


import CustomAppBar
import DocsSection
import LinksSection
import MediaSection
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infophone.group.R
import com.infophone.ui.common.SafeArea
import com.infophone.ui.theme.Gray100
import com.infophone.ui.theme.Gray600
import com.infophone.ui.theme.Indigo900
import com.infophone.ui.theme.White



@Composable
fun SharedMediaScreen(
    onBack:()-> Unit
) {

    var selectedTab by remember { mutableStateOf(0) }
    val background = Gray100

    SafeArea(


        appBar = {
            CustomAppBar(
                onBack = {onBack()},
                onClick = {},
                bntColor = White,
                bntText = R.string.select,
                darkIcons = false

            )
        },

        horizontalPadding = 20.dp,
        verticalPadding = 0.dp,
        backgroundColor = background
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(Modifier.height(height = 4.dp))

            SharedMediaTabs(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it },

            )

            Spacer(Modifier.height(height = 20.dp))

            when (selectedTab) {
                0 -> MediaSection()
                1 -> LinksSection()
                2 -> DocsSection()
            }
        }
    }
}


@Composable
fun SharedMediaTabs(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabs = listOf("Media", "Links", "Docs")

    TabRow(
        selectedTabIndex = selectedTab,
        containerColor = Gray100,
        divider = {},
        indicator = { positions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .tabIndicatorOffset(positions[selectedTab])
                    .padding(horizontal = 24.dp),
                color = Indigo900,
                height = 3.dp
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->

            Tab(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        text = tab,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = if (selectedTab == index)
                            Indigo900
                        else
                            Gray600
                    )
                }
            )
        }
    }
}

