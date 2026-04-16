package com.infophone.app.ui

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.infophone.auth.navigation.AuthNavEntryProvider
import com.infophone.auth.presentation.viewModel.SplashViewModel
import com.infophone.call.navigation.CallNavEntryProvider
import com.infophone.chat.navigation.ChatNavEntryProvider
import com.infophone.contact.data.ContactsRepositoryImpl
import com.infophone.contact.domain.GetContactsUseCase
import com.infophone.contact.navigation.ContactNavEntryProvider
import com.infophone.group.navigation.GroupNavEntryProvider
import com.infophone.home.navigation.HomeNavEntryProvider
import com.infophone.media.navigation.MediaNavEntryProvider
import com.infophone.navigation.ItemBottom
import com.infophone.navigation.NavKey
import com.infophone.navigation.TopLevelBackStack
import com.infophone.setting.navigation.SettingNavEntryProvider
import com.infophone.ui.R
import com.infophone.ui.common.SystemBarColorChanger
import com.infophone.ui.common.changeSystemBarColor
import com.infophone.ui.theme.Black80
import com.infophone.ui.theme.Green80
import com.infophone.ui.theme.InfoPhoneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Must be called before super.onCreate()
        //val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        // Keep splash screen visible until ViewModel signals ready
        /*splashScreen.setKeepOnScreenCondition {
            splashViewModel.isLoading.value
        }*/

        enableEdgeToEdge()
        setContent {
            // Observe the loading state in a Composable way
            // Use collectAsState if splashViewModel.isLoading is a StateFlow
            //val isLoading by splashViewModel.isLoading.collectAsState()
            val topLevelBackStack = remember { TopLevelBackStack<NavKey>(NavKey.Splash) }
            // Determine start key based on initial state (e.g., if user is logged in)
//            val initialKey: NavKey = NavKey.Splash // if (splashViewModel.isLoggedIn.value) NavKey.Chat else NavKey.Splash
//            val topLevelBackStack = remember { TopLevelBackStack(initialKey) }

            // 1. Create Onboard Provider (needs the backStack instance for navigation)
            val authProvider = remember { AuthNavEntryProvider(topLevelBackStack) }
            val homeProvider = remember { HomeNavEntryProvider(topLevelBackStack, this) }
            val contactProvider = remember { ContactNavEntryProvider(topLevelBackStack, this) }
            val chatProvider = remember { ChatNavEntryProvider(topLevelBackStack, this) }
            val callProvider = remember { CallNavEntryProvider(topLevelBackStack, this) }
            val mediaProvider = remember { MediaNavEntryProvider(topLevelBackStack, this) }
            val groupProvider = remember { GroupNavEntryProvider(topLevelBackStack, this) }
            val settingProvider = remember { SettingNavEntryProvider(topLevelBackStack) }


            val bottomNavItems = listOf(
                ItemBottom(
                    NavKey.Chat,
                    iconSelected = painterResource(id = R.drawable.ic_chat_selected),
                    iconUnselected = painterResource(id = R.drawable.ic_chat_unselected),
                    title = "Chats (12)"
                ),
                ItemBottom(NavKey.Call,
                    iconSelected = painterResource(id = R.drawable.ic_call_selected),
                    iconUnselected = painterResource(id = R.drawable.ic_call_unselected),
                    title = "Calls (0)"
                ),
                ItemBottom(
                    NavKey.Setting,
                    iconSelected = painterResource(id = R.drawable.ic_setting_selected),
                    iconUnselected = painterResource(id = R.drawable.ic_setting_unselected),
                    title = "Settings"
                )
            )

            val showBottomBar = bottomNavItems.any {
                it.route == topLevelBackStack.currentKey
            }
            Log.d("MainActivity", "Key: ${topLevelBackStack.currentKey}")

            InfoPhoneTheme {
                Scaffold(
                    contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
                    bottomBar = {
                        if (showBottomBar) {
                            NavigationBar(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                containerColor = Color.White,
                                tonalElevation = 0.dp // Modern flat look
                            ) {
                                bottomNavItems.forEach { item ->
                                    val selected = topLevelBackStack.topLevelKey == item.route
                                    NavigationBarItem(
                                        selected = selected,
                                        onClick = {
                                            topLevelBackStack.switchTopLevel(item.route)
                                        },
                                        icon = {
                                            Icon(
                                                painter = if (selected) item.iconSelected else item.iconUnselected,
                                                contentDescription = item.title,
                                                tint = if (selected) Green80 else Black80
                                            )
                                        },
                                        label = {
                                            Text(
                                                text = item.title,
                                                style = MaterialTheme.typography.titleSmall,
                                                color = if (selected) Green80 else Black80
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize(),
//                    contentWindowInsets = WindowInsets.safeDrawing
                ) { innerPadding ->
                    //if (!isLoading) {
                        AppScreen(
                            authNavEntryProvider = authProvider,
                            homeNavEntryProvider = homeProvider,
                            contactNavEntryProvider = contactProvider,
                            chatNavEntryProvider = chatProvider,
                            callNavEntryProvider = callProvider,
                            mediaNavEntryProvider = mediaProvider,
                            groupNavEntryProvider = groupProvider,
                            settingNavEntryProvider = settingProvider,
                            modifier = Modifier.padding(innerPadding),
                            backStack = topLevelBackStack
                        )
                    //}
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InfoPhoneTheme {
        Greeting("Android")
    }
}