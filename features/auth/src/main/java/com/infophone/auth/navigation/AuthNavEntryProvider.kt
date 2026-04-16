package com.infophone.auth.navigation

import androidx.compose.ui.Modifier
import com.infophone.auth.presentation.login.AuthScreen
import com.infophone.auth.presentation.onboard.OnboardScreen
import com.infophone.auth.presentation.profile.ProfileScreen
import com.infophone.auth.presentation.splash.SplashScreen
import com.infophone.navigation.NavEntry
import com.infophone.navigation.NavEntryProvider
import com.infophone.navigation.NavKey
import com.infophone.navigation.TopLevelBackStack

class AuthNavEntryProvider(
    private val backStack: TopLevelBackStack<NavKey>
): NavEntryProvider {
    override fun getEntry(navKey: NavKey, modifier: Modifier): NavEntry<NavKey>? {
        return when (navKey) {
            is NavKey.Splash -> NavEntry(navKey) {
                // Dependency: splashViewModel passed down from AppScreen/MainActivity
                SplashScreen(
                    modifier = modifier,
                    onComplete = {navKey ->
                        backStack.removeLast()
                        backStack.add(navKey)
                    }
                )
            }
            is NavKey.Onboard -> NavEntry(navKey) {
                // Dependency: onboardViewModel passed down
                OnboardScreen(
                    modifier = modifier,
                    onFinish = {
                        backStack.removeLast()
                        backStack.add(NavKey.Auth) // Navigate to top-level Auth
                    }
                )
            }
            is NavKey.Auth -> NavEntry(navKey) {
                AuthScreen(
                    modifier = modifier,
                    onPhoneNumberEntered = { countryCode, phoneNumber ->
                        backStack.add(NavKey.Profile(countryCode, phoneNumber))
                    }
                )
            }
            is NavKey.Profile -> NavEntry(navKey) {
            // Profile screen belongs logically here as it follows Auth
                ProfileScreen(
                    modifier = modifier,
                    countryCode = navKey.countryCode,
                    phoneNumber = navKey.phoneNumber,
                    onSaved = {
//                        backStack.add(NavKey.Chat)
                        backStack.replaceStack(NavKey.Chat) // This sets the stack to [Chat]
                        backStack.switchTopLevel(NavKey.Chat) // Ensure topLevelKey is Chat
                    }
                )
            }
            else -> null // This provider doesn't handle other keys (Chat, Call, Setting, Contact)
        }
    }
}
