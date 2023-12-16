package com.thusee.footballevent.ui.theme.themecolors

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.thusee.footballevent.ui.theme.Pink40
import com.thusee.footballevent.ui.theme.Pink80
import com.thusee.footballevent.ui.theme.Purple40
import com.thusee.footballevent.ui.theme.Purple80
import com.thusee.footballevent.ui.theme.PurpleGrey40
import com.thusee.footballevent.ui.theme.PurpleGrey80

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

class FootballThemeColors(type: Type) : ThemeColors(type = type) {
    val material: ColorScheme = if (type == Type.LIGHT) LightColorScheme else DarkColorScheme

    val orangeTextColor: Color by color(
        light = AppColors.Orange,
        dark = AppColors.Orange,
    )

    val greenTextColor: Color by color(
        light = AppColors.Green,
        dark = AppColors.Green,
    )

}