package com.infophone.contact.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.add
import androidx.compose.ui.Modifier
import com.infophone.contact.presentation.ContactScreen
import com.infophone.navigation.NavEntry
import com.infophone.navigation.NavEntryProvider
import com.infophone.navigation.NavKey
import com.infophone.navigation.TopLevelBackStack
import javax.inject.Inject

class ContactNavEntryProvider @Inject constructor(
    private val backStack: TopLevelBackStack<NavKey>,
    private val context: Context
): NavEntryProvider  {
    override fun getEntry(navKey: NavKey, modifier: Modifier): NavEntry<NavKey>? {
        return when(navKey){
            is NavKey.Contact -> NavEntry(navKey) {
                ContactScreen(
                    modifier = modifier,
                    onContactClick = {
                        backStack.add(NavKey.Group)

                        Toast.makeText(context, "On contact clicked", Toast.LENGTH_SHORT).show()
                    }
                )
            }

            else -> null
        }
    }
}