package com.thusee.footballevent.ui.theme.themecolors

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.thusee.footballevent.ui.theme.Black
import com.thusee.footballevent.ui.theme.DarkGray
import com.thusee.footballevent.ui.theme.LighterDarkGray
import com.thusee.footballevent.ui.theme.OffWhite
import com.thusee.footballevent.ui.theme.RedPrimary
import com.thusee.footballevent.ui.theme.RedSecondary
import com.thusee.footballevent.ui.theme.White

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

class FootballThemeColors(type: Type) : ThemeColors(type = type) {
    val material: ColorScheme = if (type == Type.LIGHT) LightColorScheme else DarkColorScheme

    val defaultBackground: Color by color(
        light = AppColors.White,
        dark = AppColors.Black,
    )
    val cardBackground: Color by color(
        dark = AppColors.DarkGray,
        light = AppColors.Grey200,
    )
    val highlightBackground: Color by color(
        light = AppColors.Red800,
        dark = AppColors.Red800,
    )
    val highlightTextColor: Color by color(
        light = AppColors.White,
        dark = AppColors.White,
    )

    val defaultTextColor: Color by color(
        light = AppColors.Black,
        dark = AppColors.White,
    )

}