package com.smarttoolfactory.colorpicker.util

import androidx.compose.ui.graphics.Color

/*
    HEX-ColorInt Conversion
 */
fun hexToColorInt(colorString: String): Int {
    val completeColorString = if (colorString.first() == '#') colorString else "#$colorString"
    return android.graphics.Color.parseColor(completeColorString)
}

/*
    HEX-RGB Conversion
 */
fun hexToRGB(colorString: String): IntArray {
    val colorInt = hexToColorInt(colorString)
    return colorIntToRGBArray(colorInt)
}

fun hexToRGB(colorString: String, rgbIn: IntArray) {
    val colorInt = hexToColorInt(colorString)
    colorIntToRGBArray(colorInt, rgbIn)
}

fun hexToARGB(colorString: String): IntArray {
    val colorInt = hexToColorInt(colorString)
    return colorIntToARGBArray(colorInt)
}

fun hexToARGB(colorString: String, argbIn: IntArray) {
    val colorInt = hexToColorInt(colorString)
    colorIntToARGBArray(colorInt, argbIn)
}


fun hexToColor(colorString: String): Color {
    return Color(hexToColorInt(colorString))
}


// Regex for checking if this string is a 6 char hex or 8 char hex
val hexWithAlphaRegex = "^#?([0-9a-fA-F]{6}|[0-9a-fA-F]{8})\$".toRegex()
val hexRegex = "^#?([0-9a-fA-F]{6})\$".toRegex()
// Check only on char if it's in range of 0-9, a-f, A-F
val hexRegexSingleChar = "[a-fA-F0-9]".toRegex()