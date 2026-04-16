package com.infophone.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TopLevelBackStack<T : NavKey>(private val initialStartKey: T) {
    // Private mutable variable to track the *effective* start key/root of the application.
    var effectiveStartKey: T = initialStartKey

    // The main map now uses the effectiveStartKey for its initial state.
    private val topLevelBackStack: HashMap<T, SnapshotStateList<T>> = hashMapOf(
        effectiveStartKey to mutableStateListOf(effectiveStartKey)
    )

    var topLevelKey by mutableStateOf(initialStartKey)
        private set

    val backStack = mutableStateListOf<T>(initialStartKey)

    var currentKey by mutableStateOf(initialStartKey)
        private set

    private val _results = MutableStateFlow<Map<String, Any?>>(emptyMap())
    val results = _results.asStateFlow()

    fun setResult(key: String, value: Any?) {
        _results.update { it + (key to value) }
    }

    fun clearResult(key: String) {
        _results.update { it + (key to null) }
    }

    fun updateBackStack() {
        backStack.clear()
        val currentStack = topLevelBackStack[topLevelKey] ?: mutableStateListOf(topLevelKey).also {
            topLevelBackStack[topLevelKey] = it
        }

        // We use effectiveStartKey in the comparison logic now
        if (topLevelKey == effectiveStartKey) {
            backStack.addAll(currentStack)
        } else {
            // Include the effective start stack only if needed
            val startStack = topLevelBackStack[effectiveStartKey] ?: emptyList()
            if (startStack.isNotEmpty() && effectiveStartKey != topLevelKey) {
                backStack.addAll(startStack)
            }
            backStack.addAll(currentStack)
        }
        // The last element of the backStack is always the current visible key.
        currentKey = backStack.lastOrNull() ?: effectiveStartKey
    }

    fun switchTopLevel(key: T) {
        // 1. Check if the current state is the initial Splash screen.
        if (topLevelKey == effectiveStartKey && effectiveStartKey == NavKey.Splash) {
            // 2. Perform the Re-rooting:
            // a. Clear the old splash state from the map
            topLevelBackStack.remove(effectiveStartKey)
            // b. Set the new effective root/start key (e.g., NavKey.Chat)
            effectiveStartKey = key
            // The backStack for the new key (Chat) will be initialized below.
        }
        if (topLevelBackStack[key] == null) {
            topLevelBackStack[key] = mutableStateListOf(key)
        }
        topLevelKey = key
        updateBackStack()
    }

    fun add(key: T) {
        topLevelBackStack[topLevelKey]?.add(key)
        updateBackStack()
    }

    fun removeLast() {
        val currentStack = topLevelBackStack[topLevelKey] ?: return
        if (currentStack.size > 1) {
            currentStack.removeLastOrNull()
        } else if (topLevelKey != effectiveStartKey) {
            topLevelKey = effectiveStartKey
        }
        updateBackStack()
    }

    fun replaceStack(vararg key: T) {
        topLevelBackStack[topLevelKey] = mutableStateListOf(*key)
        updateBackStack()
    }

}