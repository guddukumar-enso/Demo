package com.infophone.group.navigation


import android.content.Context
import androidx.compose.ui.Modifier
import com.infophone.group.presentation.screen.CreateGroupScreen
import com.infophone.group.presentation.screen.EditGroupScreen
import com.infophone.group.presentation.screen.GroupInfoScreen
import com.infophone.group.presentation.screen.SharedMediaScreen
import com.infophone.group.presentation.screen.AddMemberGroupScreen
import com.infophone.group.presentation.screen.NewGroupScreen
import com.infophone.navigation.NavEntry
import com.infophone.navigation.NavEntryProvider
import com.infophone.navigation.NavKey
import com.infophone.navigation.TopLevelBackStack

class GroupNavEntryProvider(
    private val backStack: TopLevelBackStack<NavKey>,
    private val context: Context
): NavEntryProvider {
    override fun getEntry(navKey: NavKey, modifier: Modifier): NavEntry<NavKey>? {
        return when (navKey) {
            is NavKey.Group -> NavEntry(navKey) {
                NewGroupScreen(
                    modifier = modifier,
                    onBack = {
                        backStack.removeLast()
                    },
                    onGroupClick = { navKey ->
                      backStack.add(navKey)

                    }
                )
            }

            is NavKey.SelectGroup -> NavEntry(navKey) {
                AddMemberGroupScreen(
                    modifier = modifier,
                    onBack = {
                        backStack.removeLast()
                    },
                    onSelect = {},
                    onCreate = {
                        backStack.add(NavKey.CreateGroup)

                    }
                )
            }


            is NavKey.CreateGroup -> NavEntry(navKey) {
                CreateGroupScreen(
                    modifier = modifier,
                    onBack = {
                        backStack.removeLast()
                    },
                    onClick = {
                        backStack.add(NavKey.GroupInfo)

                    }
                )
            }

            is NavKey.GroupInfo -> NavEntry(navKey){
                GroupInfoScreen(
                    modifier = modifier,
                    onBack = {
                        backStack.removeLast()
                    },
                    onClick = {
                        backStack.add(NavKey.EditGroup)

                    },
                    onTap = {key ->
                        backStack.add(key)

                    }
                )
            }

            is NavKey.EditGroup -> NavEntry(navKey){
                EditGroupScreen(
                    modifier = modifier,
                    onBack = {
                        backStack.removeLast()
                    },
                    onClick = {

                    }
                )
            }

            is NavKey.MediaShare -> NavEntry(navKey){
                SharedMediaScreen(
                    onBack = {
                        backStack.removeLast()

                    }
                )
            }

            else -> null
        }
    }
}
