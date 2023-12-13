package com.thusee.footballevent.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.thusee.footballevent.ui.theme.themecolors.FootballThemeColors
import com.thusee.footballevent.ui.theme.themecolors.ThemeColors

private val DarkColorScheme = darkColorScheme(
    primary = RedPrimary,
    secondary = RedSecondary,
    tertiary = DarkGray,
    background = Black,
    surface = LighterDarkGray,
)

private val LightColorScheme = lightColorScheme(
    primary = RedPrimary,
    secondary = RedSecondary,
    tertiary = OffWhite,
    background = White,
    surface = OffWhite,
)

/* Other default colors to override
background = Color(0xFFFFFBFE),
surface = Color(0xFFFFFBFE),
onPrimary = Color.White,
onSecondary = Color.White,
onTertiary = Color.White,
onBackground = Color(0xFF1C1B1F),
onSurface = Color(0xFF1C1B1F),
*/

private val LocalColors = staticCompositionLocalOf { FootballThemeColors(ThemeColors.Type.LIGHT) }

@Composable
fun FootballEventTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
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
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    val colors = if (darkTheme) {
        FootballThemeColors(ThemeColors.Type.DARK)
    } else {
        FootballThemeColors(ThemeColors.Type.LIGHT)
    }

    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
        LocalColors provides colors
    ) {
        MaterialTheme(
            colorScheme = colors.material,
            typography = Typography,
            content = content
        )
    }
}

object AppTheme {
    val colors: FootballThemeColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

}