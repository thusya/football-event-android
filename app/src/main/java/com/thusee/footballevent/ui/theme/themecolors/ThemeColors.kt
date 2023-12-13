package com.thusee.footballevent.ui.theme.themecolors

import androidx.compose.ui.graphics.Color

abstract class ThemeColors(private val type: Type) {
    fun color(light: Color, dark: Color): Lazy<Color> = lazy {
        when (type) {
            Type.DARK -> dark
            else -> light
        }
    }

    enum class Type {
        LIGHT, DARK
    }
}