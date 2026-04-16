package com.infophone.ui.common

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.Window
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Renaming the argument for clarity: This controls whether the ICONS should be LIGHT or DARK
fun changeSystemBarColor(
    window: Window,
    view: View,
    statusBarColor: Color?,
    navigationBarColor: Color?,
    useDarkIcons: Boolean // NEW: True means use DARK icons (for a light status bar)
) {
    statusBarColor?.let {
        // Direct assignment, required for programmatic change despite deprecation
        window.statusBarColor = it.toArgb()
    }
    navigationBarColor?.let {
        window.navigationBarColor = it.toArgb()
    }

    // The official way to set icon contrast:
    // isAppearanceLightStatusBars = true -> use DARK icons (for light bars)
    // isAppearanceLightStatusBars = false -> use LIGHT icons (for dark bars)
    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = useDarkIcons
}

@Composable
fun SystemBarColorChanger(
    statusBarColor: Color?,
    navigationBarColor: Color?,
    useDarkIcons: Boolean // NEW: Pass the desired icon color contrast
) {
    val window = (LocalContext.current as Activity).window
    val view = LocalView.current

    SideEffect {
        changeSystemBarColor(
            window,
            view,
            statusBarColor,
            navigationBarColor,
            useDarkIcons
        )
    }
}