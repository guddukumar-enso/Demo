package com.infophone.onboard.navigation

import androidx.compose.ui.Modifier
import com.infophone.navigation.NavEntry
import com.infophone.navigation.NavEntryProvider
import com.infophone.navigation.NavKey
import com.infophone.navigation.TopLevelBackStack
import com.infophone.onboard.presentation.OnboardScreen
import com.infophone.onboard.presentation.SplashScreen

class OnboardNavEntryProvider(
    private val backStack: TopLevelBackStack<NavKey>
): NavEntryProvider {
    override fun getEntry(navKey: NavKey, modifier: Modifier): NavEntry<NavKey>? {
        return when (navKey) {
            is NavKey.Splash -> NavEntry(navKey) {
                // Dependency: splashViewModel passed down from AppScreen/MainActivity
                SplashScreen(
                    onComplete = {
                        backStack.removeLast()
                        backStack.add(NavKey.Onboard)
                    }
                )
            }
            is NavKey.Onboard -> NavEntry(navKey) {
                // Dependency: onboardViewModel passed down
                OnboardScreen(
                    onFinish = {
                        backStack.removeLast()
                        backStack.replaceStack(NavKey.Auth) // Navigate to top-level Auth
                    }
                )
            }
            else -> null // This provider doesn't handle other keys (Chat, Call, Setting, Contact)
        }
    }
}