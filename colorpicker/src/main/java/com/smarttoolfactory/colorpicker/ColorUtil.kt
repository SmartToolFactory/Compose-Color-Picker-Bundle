package com.smarttoolfactory.colorpicker

import androidx.core.graphics.ColorUtils
import java.util.*

/*
    HSV-HSL Conversions
 */
/**
 * Convert HSV components(hue-saturation-value) to HSL
 * (hue-saturation-lightness) components.
 *
 * ```
 * hsv[0] is Hue [0 .. 360)
 * hsv[1] is Saturation [0...1]
 * hsv[2] is Value [0...1]
 * ```
 *
 * ```
 * hsl[0] is Hue [0 .. 360)
 * hsl[1] is Saturation [0...1]
 * hsl[2] is Lightness [0...1]
 * ```
 *
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

    return floatArrayOf(hue, saturationHSL.coerceIn(0f, 1f), lightness.coerceIn(0f, 1f))
}

/**
 * Convert HSV components(hue-saturation-value) to HSL
 * (hue-saturation-lightness) components.
 *
 * ```
 * hsv[0] is Hue [0 .. 360)
 * hsv[1] is Saturation [0...1]
 * hsv[2] is Value [0...1]
 * ```
 *
 * ```
 * hsl[0] is Hue [0 .. 360)
 * hsl[1] is Saturation [0...1]
 * hsl[2] is Lightness [0...1]
 * ```
 *
 */
fun hsvToHSL(hsvIn: FloatArray): FloatArray {
    return hsvToHSL(hsvIn[0], hsvIn[1], hsvIn[2])
}

/**
 * Convert HSL components(hue-saturation-lightness) to HSV
 * (hue-saturation-value) components.
 *
 * ```
 * hsv[0] is Hue [0 .. 360)
 * hsv[1] is Saturation [0...1]
 * hsv[2] is Value [0...1]
 * ```
 *
 * ```
 * hsl[0] is Hue [0 .. 360)
 * hsl[1] is Saturation [0...1]
 * hsl[2] is Lightness [0...1]
 * ```
 *
 */
fun hslToHSV(hue: Float, saturation: Float, lightness: Float): FloatArray {
    val value = lightness + saturation * lightness.coerceAtMost(1 - lightness)
    val saturationHSV = if (value == 0f) 0f else 2 * (1 - lightness / value)
    return floatArrayOf(hue, saturationHSV.coerceIn(0f, 1f), value.coerceIn(0f, 1f))
}

/**
 * Convert HSL components(hue-saturation-lightness) to HSV
 * (hue-saturation-value) components.
 *
 * ```
 * hsv[0] is Hue [0 .. 360)
 * hsv[1] is Saturation [0...1]
 * hsv[2] is Value [0...1]
 * ```
 *
 * ```
 * hsl[0] is Hue [0 .. 360)
 * hsl[1] is Saturation [0...1]
 * hsl[2] is Lightness [0...1]
 * ```
 *
 */
fun hslToHSV(hslIn: FloatArray): FloatArray {
    return hslToHSV(hslIn[0], hslIn[1], hslIn[2])
}

/*
    HSL-COLOR Conversions
 */

/**
 * Convert HSL (hue-saturation-lightness) components to a RGB color.
 * ```
 * hsl[0] is Hue [0 .. 360)
 * hsl[1] is Saturation [0...1]
 * hsl[2] is Lightness [0...1]
 * ```
 */
fun hslToColor(hslIn: FloatArray): Int {
    return hslToColor(hslIn[0], hslIn[1], hslIn[2])
}

/**
 * Convert HSL (hue-saturation-lightness) components to a RGB color.
 * ```
 * hsl[0] is Hue [0 .. 360)
 * hsl[1] is Saturation [0...1]
 * hsl[2] is Lightness [0...1]
 * ```
 */
fun hslToColor(hue: Float, saturation: Float, lightness: Float): Int {
    return ColorUtils.HSLToColor(floatArrayOf(hue, saturation, lightness))
}

/**
 * Convert RGB color to HSL (hue-saturation-lightness) components.
 * ```
 * hsl[0] is Hue [0 .. 360)
 * hsl[1] is Saturation [0...1]
 * hsl[2] is Lightness [0...1]
 * ```
 */
fun colorToHsl(color: Int): FloatArray {
    val outHsl = floatArrayOf(0f, 0f, 0f)
    ColorUtils.colorToHSL(color, outHsl)
    return outHsl
}

/*
    HSL-RGB Conversions
 */

/**
 * Convert HSL (hue-saturation-lightness) components to a RGB red, green blue array.
 * ```
 * hsl[0] is Hue [0 .. 360)
 * hsl[1] is Saturation [0...1]
 * hsl[2] is Lightness [0...1]
 * ```
 *
 * ```
 * rgb[0] is Red [0 .. 255]
 * rgb[1] is Green [0...255]
 * rgb[2] is Blue [0...255]
 * ```
 */
fun hslToRGBArray(hslIn: FloatArray): IntArray {
    return colorToRGBArray(hslToColor(hslIn))
}

/**
 * Convert HSL (hue-saturation-lightness) components to a RGB red, green blue array.
 * ```
 * hsl[0] is Hue [0 .. 360)
 * hsl[1] is Saturation [0...1]
 * hsl[2] is Lightness [0...1]
 * ```
 *
 * ```
 * rgb[0] is Red [0 .. 255]
 * rgb[1] is Green [0...255]
 * rgb[2] is Blue [0...255]
 * ```
 */
fun hslToRGBArray(hue: Float, saturation: Float, lightness: Float): IntArray {
    return colorToRGBArray(hslToColor(hue, saturation, lightness))
}

/**
 * Convert HSL (hue-saturation-lightness) components to a RGB red, green blue array.
 * ```
 * hsl[0] is Hue [0 .. 360)
 * hsl[1] is Saturation [0...1]
 * hsl[2] is Lightness [0...1]
 * ```
 *
 * ```
 * rgb[0] is Red [0 .. 255]
 * rgb[1] is Green [0...255]
 * rgb[2] is Blue [0...255]
 * ```
 */
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

/**
 * Converts **RGB** colors in range of [0f-1f] to [0-255]
 */
fun Float.toColorString() = "${(this * 255).toInt()}"

/**
 * Converts **HSV** or **HSL** colors that are in range of [0f-1f] to [0-100] int range to
 * display in a String
 */
fun Float.toPercent() = (this * 100).toInt()

