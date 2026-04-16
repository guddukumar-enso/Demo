package com.infophone.navigation

import androidx.compose.ui.Modifier

interface NavEntryProvider {
    fun getEntry(navKey: NavKey, modifier: Modifier): NavEntry<NavKey>?
}