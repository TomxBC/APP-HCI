package com.example.turnsmart_hci.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    background = Color(0xFF312F38),
    surface = Color(0xFF1C1B1F),

    //bars
    primary = darkBottomAppBar,
    secondary = darkToolbar,

    //text and icons
    onPrimary = lightText,
    onSecondary = lightText,

    //selected item
    onTertiary = dark_purple,

)

private val LightColorScheme = lightColorScheme(
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),

    //bars
    primary = lightBottomAppBar,
    secondary = lightToolbar,

    //text and icons
    onPrimary = darkText,
    onSecondary = darkText,

    //selected item
    onTertiary = pale_purple
)

@Composable
fun TurnSmartTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

object TurnSmartTheme {
    val colors: ColorScheme
        @Composable
        get() = if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme

}