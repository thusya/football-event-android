package com.thusee.footballevent.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.thusee.footballevent.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
titleLarge = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
),
labelSmall = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
)
*/
)
val Typography.WonByTextStyle: TextStyle
    @Composable
    get() = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight(700),
        color = Color(0xFF5EB95E),
        fontFamily = poppins
    )

val poppins: FontFamily = FontFamily(
    Font(R.font.poppins_black, weight = FontWeight.W900, style = FontStyle.Normal),
    Font(R.font.poppins_black_italic, weight = FontWeight.W900, style = FontStyle.Italic),
    Font(R.font.poppins_bold, weight = FontWeight.W700, style = FontStyle.Normal),
    Font(R.font.poppins_bold_italic, weight = FontWeight.W700, style = FontStyle.Italic),
    Font(R.font.poppins_extrabold, weight = FontWeight.W800, style = FontStyle.Normal),
    Font(R.font.poppins_extrabold_italic, weight = FontWeight.W800, style = FontStyle.Italic),
    Font(R.font.poppins_extralight, weight = FontWeight.W200, style = FontStyle.Normal),
    Font(R.font.poppins_extralight_italic, weight = FontWeight.W200, style = FontStyle.Italic),
    Font(R.font.poppins_italic, weight = FontWeight.W400, style = FontStyle.Italic),
    Font(R.font.poppins_light, weight = FontWeight.W300, style = FontStyle.Normal),
    Font(R.font.poppins_light_italic, weight = FontWeight.W300, style = FontStyle.Italic),
    Font(R.font.poppins_medium, weight = FontWeight.W500, style = FontStyle.Normal),
    Font(R.font.poppins_medium_italic, weight = FontWeight.W500, style = FontStyle.Italic),
    Font(R.font.poppins_regular, weight = FontWeight.W400, style = FontStyle.Normal),
    Font(R.font.poppins_semibold, weight = FontWeight.W600, style = FontStyle.Normal),
    Font(R.font.poppins_semibold_italic, weight = FontWeight.W600, style = FontStyle.Italic),
    Font(R.font.poppins_thin, weight = FontWeight.W100, style = FontStyle.Normal),
    Font(R.font.poppins_thin_italic, weight = FontWeight.W100, style = FontStyle.Italic),
)