package com.smarttoolfactory.colorpicker

import androidx.core.graphics.ColorUtils
import java.util.*


/*
    HSV-HSL Conversions
 */
/**
 * Convert HSV (hue-saturation-value) components to HSL (hue-saturation-lightness)
 */
fun hsvToHSL(hue: Float, saturation: Float, value: Float): FloatArray {
    val lightness = (2 - saturation) * value / 2
    var saturationHSL = saturation

    if (lightness != 0f) {
        saturationHSL = when {
            lightness == 1f -> {
                0f
            }
            lightness < 0.5f -> {
                saturationHSL * value / (lightness * 2)
            }
            else -> {
                saturationHSL * value / (2 - lightness * 2)
            }
        }
    }

    return floatArrayOf(hue, saturationHSL, lightness)
}


/**
 * Convert HSV (hue-saturation-value) components to HSL (hue-saturation-lightness)
 */
fun hsvToHSL(hsvIn: FloatArray): FloatArray {
    return hsvToHSL(hsvIn[0], hsvIn[1], hsvIn[2])
}

fun hslToHSV(hue: Float, saturation: Float, lightness: Float): FloatArray {
    val value = lightness + saturation * lightness.coerceAtMost(1 - lightness)
    val saturationHSV = if (value == 0f) 0f else 2 * (1 - lightness / value)
    return floatArrayOf(hue, saturationHSV, value)
}

fun hslToHSV(hslIn: FloatArray): FloatArray {
    return hslToHSV(hslIn[0], hslIn[1], hslIn[2])
}

/*
    HSL-COLOR Conversions
 */
fun hslToColor(hslIn: FloatArray): Int {
    return hslToColor(hslIn[0], hslIn[1], hslIn[2])
}

fun hslToColor(hue: Float, saturation: Float, lightness: Float): Int {
    return ColorUtils.HSLToColor(floatArrayOf(hue, saturation, lightness))
}

fun colorToHsl(color: Int): FloatArray {
    val outHsl = floatArrayOf(0f, 0f, 0f)
    ColorUtils.colorToHSL(color, outHsl)
    return outHsl
}

/*
    HSL-RGB Conversions
 */
fun hslToRGBArray(hslIn: FloatArray): IntArray {
    return colorToRGBArray(hslToColor(hslIn))
}

fun hslToRGBArray(hue: Float, saturation: Float, lightness: Float): IntArray {
    return colorToRGBArray(hslToColor(hue, saturation, lightness))
}

fun rgbToHSL(red: Int, green: Int, blue: Int): FloatArray {
    val outHsl = floatArrayOf(0f, 0f, 0f)
    ColorUtils.RGBToHSL(red, green, blue, outHsl)
    return outHsl
}

/*
    HSV-COLOR Conversions
 */

fun hsvToColor(hue: Float, saturation: Float, value: Float): Int {
    return hsvToColor(floatArrayOf(hue, saturation, value))
}

fun hsvToColor(hsvArray: FloatArray): Int {
    return android.graphics.Color.HSVToColor(hsvArray)
}

fun colorToHSV(color: Int): FloatArray {
    val hsvOut = floatArrayOf(0f, 0f, 0f)
    android.graphics.Color.colorToHSV(color, hsvOut)
    return hsvOut
}

/*
    HSV-RGB
 */
fun hsvToRGB(hue: Float, saturation: Float, lightness: Float): IntArray {
    return colorToRGBArray(hsvToColor(hue, saturation, lightness))
}

fun hsvToRGB(hsvIn: FloatArray): IntArray {
    return colorToRGBArray(hsvToColor(hsvIn))
}

fun rgbToHsv(red: Int, green: Int, blue: Int): FloatArray {
    val hsvOut = floatArrayOf(0f, 0f, 0f)
    android.graphics.Color.RGBToHSV(red, green, blue, hsvOut)
    return hsvOut
}

/*
    COLOR-RGB Conversions
 */
fun colorToRGBArray(color: Int): IntArray {
    val red = android.graphics.Color.red(color)
    val green = android.graphics.Color.green(color)
    val blue = android.graphics.Color.blue(color)
    return intArrayOf(red, green, blue)
}

fun rgbToColor(red: Int, green: Int, blue: Int): Int {
    return android.graphics.Color.rgb(red, green, blue)
}


fun Int.toRgbString(): String =
    ("#" +
            red.toStringComponent() +
            green.toStringComponent() +
            blue.toStringComponent())
        .uppercase(Locale.getDefault())

fun Int.toArgbString(): String =
    ("#" +
            alpha.toStringComponent() +
            red.toStringComponent() +
            green.toStringComponent() +
            blue.toStringComponent()
            ).uppercase(Locale.getDefault())

private fun Int.toStringComponent(): String =
    this.toString(16).let { if (it.length == 1) "0${it}" else it }

inline val Int.alpha: Int
    get() = (this shr 24) and 0xFF

inline val Int.red: Int
    get() = (this shr 16) and 0xFF

inline val Int.green: Int
    get() = (this shr 8) and 0xFF

inline val Int.blue: Int
    get() = this and 0xFF

