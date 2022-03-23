package com.smarttoolfactory.colorpicker

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.ColorUtils
import java.util.*

/*
    HSV-HSL Conversions
 */
/**
 * Convert [HSV](https://en.wikipedia.org/wiki/HSL_and_HSV)
 * components(hue-saturation-value) to HSL
 * (hue-saturation-lightness) components.
 *
 * ```
 * hsv[0] is Hue [0 .. 360)
 * hsv[1] is Saturation [0...1]
 * hsv[2] is Value [0...1]
 * ```
 *
 * ```
 * Hue [0 .. 360)
 * Saturation is [0...1]
 * Value is [0...1]
 * ```
 * @return float array that contains hue, saturation and lightness
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
 * Convert [HSV](https://en.wikipedia.org/wiki/HSL_and_HSV)
 * components(hue-saturation-value) to HSL
 * (hue-saturation-lightness) components and apply them to [hslOut].
 *
 * ```
 * hsv[0] is Hue [0 .. 360)
 * hsv[1] is Saturation [0...1]
 * hsv[2] is Value [0...1]
 * ```
 *
 * ```
 * Hue [0 .. 360)
 * Saturation is [0...1]
 * Value is [0...1]
 * ```
 * @param hslOut array contains hue, saturation and lightness properties
 *
 */
fun hsvToHSL(hue: Float, saturation: Float, value: Float, hslOut: FloatArray) {
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

    hslOut[0] = hue
    hslOut[1] = saturationHSL.coerceIn(0f, 1f)
    hslOut[2] = lightness.coerceIn(0f, 1f)
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
 */
fun hsvToHSL(hsvIn: FloatArray): FloatArray {
    return hsvToHSL(hsvIn[0], hsvIn[1], hsvIn[2])
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
 */
fun hsvToHSL(hsvIn: FloatArray, hslOut: FloatArray) {
    hsvToHSL(hsvIn[0], hsvIn[1], hsvIn[2], hslOut)
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
 * Hue is [0 .. 360)
 * Saturation is [0...1]
 * Lightness is [0...1]
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
    HSV-ColorInt Conversions
 */

/**
 * Convert HSV (hue-saturation-value) components to a RGB color in [Integer] format.
 *
 *  * For instance, red =255, green =0, blue=0 is -65536
 * ```
 * Hue is [0 .. 360)
 * Saturation is [0...1]
 * Value is [0...1]
 * ```
 */
fun hsvToColorInt(hue: Float, saturation: Float, value: Float): Int {
    return hsvToColorInt(floatArrayOf(hue, saturation, value))
}

/**
 * Convert HSV (hue-saturation-value) components to a RGB color in [Integer] form.
 *
 *  * For instance, red =255, green =0, blue=0 is -65536
 * ```
 * Hue is [0 .. 360)
 * Saturation is [0...1]
 * Value is [0...1]
 * ```
 */
fun hsvToColorInt(hsvIn: FloatArray): Int {
    return android.graphics.Color.HSVToColor(hsvIn)
}

/**
 * Convert a RGB color in [Integer] form to HSV (hue-saturation-value) components.
 *  * For instance, red =255, green =0, blue=0 is -65536
 * ```
 * Hue is [0 .. 360)
 * Saturation is [0...1]
 * Value is [0...1]
 * ```
 */
fun colorIntToHSV(color: Int): FloatArray {
    val hsvOut = floatArrayOf(0f, 0f, 0f)
    android.graphics.Color.colorToHSV(color, hsvOut)
    return hsvOut
}

/**
 * Convert a RGB color in [Integer] form to HSV (hue-saturation-value) components.
 *  * For instance, red =255, green =0, blue=0 is -65536
 *
 * ```
 * Hue is [0 .. 360)
 * Saturation is [0...1]
 * Value is [0...1]
 * ```
 */
fun colorIntToHSV(color: Int, hsvIn: FloatArray) {
    android.graphics.Color.colorToHSV(color, hsvIn)
}

/*
    HSV-Color Conversions
 */

/**
 * Convert HSV (hue-saturation-value) components to Jetpack Compose [Color].
 * ```
 * Hue is [0 .. 360)
 * Saturation is [0...1]
 * Value is [0...1]
 * ```
 */
fun hsvToColor(hue: Float, saturation: Float, value: Float, alpha: Float): Color {
    return Color.hsv(hue, saturation, value, alpha)
}

/**
 * Convert Jetpack Compose [Color] to HSV (hue-saturation-value) components.
 * ```
 * Hue is [0 .. 360)
 * Saturation is [0...1]
 * Value is [0...1]
 * ```
 */
fun colorToHSV(color: Color, hslIn: FloatArray) {
    val red = color.red.toColorInt()
    val green = color.green.toColorInt()
    val blue = color.blue.toColorInt()
    rgbToHSV(red, green, blue, hslIn)
}

/**
 * Convert Jetpack Compose [Color] to HSV (hue-saturation-value) components.
 * ```
 * Hue is [0 .. 360)
 * Saturation is [0...1]
 * Value is [0...1]
 * ```
 */
fun colorToHSV(color: Color): FloatArray {
    val red = color.red.toColorInt()
    val green = color.green.toColorInt()
    val blue = color.blue.toColorInt()
    return rgbToHSV(red, green, blue)
}

/**
 * Convert Jetpack Compose [Color] to HSV (hue-saturation-value) components.
 * ```
 * hsl[0] is Hue [0 .. 360)
 * hsl[1] is Saturation [0...1]
 * hsl[2] is Lightness [0...1]
 * ```
 */
fun colorToHSL(color: Color, hslIn: FloatArray) {
    val red = color.red.toColorInt()
    val green = color.green.toColorInt()
    val blue = color.blue.toColorInt()
    rgbToHSL(red, green, blue, hslIn)
}

/**
 * Convert Jetpack Compose [Color] to HSV (hue-saturation-value) components.
 * ```
 * hsl[0] is Hue [0 .. 360)
 * hsl[1] is Saturation [0...1]
 * hsl[2] is Lightness [0...1]
 * ```
 */
fun colorToHSL(color: Color): FloatArray {
    val red = color.red.toColorInt()
    val green = color.green.toColorInt()
    val blue = color.blue.toColorInt()
    return rgbToHSL(red, green, blue)
}

/*
    HSV-RGB Conversions
 */

/**
 * Convert HSV (hue-saturation-value) components to a RGB red, green blue array.
 * ```
 * hsv[0] is Hue [0 .. 360)
 * hsv[1] is Saturation [0...1]
 * hsv[2] is Value [0...1]
 * ```
 *
 * ```
 * rgb[0] is Red [0 .. 255]
 * rgb[1] is Green [0...255]
 * rgb[2] is Blue [0...255]
 * ```
 */
fun hsvToRGB(hue: Float, saturation: Float, value: Float): IntArray {
    return colorIntToRGBArray(
        hsvToColorInt(hue, saturation, value)
    )
}

/**
 * Convert HSV (hue-saturation-value) components to a RGB red, green blue array.
 * ```
 * hsv[0] is Hue [0 .. 360)
 * hsv[1] is Saturation [0...1]
 * hsv[2] is Value [0...1]
 * ```
 *
 * ```
 * rgb[0] is Red [0 .. 255]
 * rgb[1] is Green [0...255]
 * rgb[2] is Blue [0...255]
 * ```
 */
fun hsvToRGB(hsvIn: FloatArray): IntArray {
    return colorIntToRGBArray(
        hsvToColorInt(hsvIn)
    )
}

/**
 * Convert HSV (hue-saturation-value) components to a RGB red, green blue array.
 * ```
 * hsv[0] is Hue [0 .. 360)
 * hsv[1] is Saturation [0...1]
 * hsv[2] is Value [0...1]
 * ```
 *
 * ```
 * rgb[0] is Red [0 .. 255]
 * rgb[1] is Green [0...255]
 * rgb[2] is Blue [0...255]
 * ```
 */
fun hsvToRGB(hue: Float, saturation: Float, value: Float, rgbIn: IntArray) {
    colorIntToRGBArray(
        color = hsvToColorInt(hue, saturation, value),
        rgbIn = rgbIn
    )
}

/**
 * Convert RGB red, green blue to HSV (hue-saturation-value) components.
 * ```
 * hsv[0] is Hue [0 .. 360)
 * hsv[1] is Saturation [0...1]
 * hsv[2] is Value [0...1]
 * ```
 *
 * ```
 * Red is [0 .. 255]
 * Green is [0...255]
 * Blue is [0...255]
 * ```
 */
fun rgbToHSV(red: Int, green: Int, blue: Int): FloatArray {
    val hsvOut = floatArrayOf(0f, 0f, 0f)
    android.graphics.Color.RGBToHSV(red, green, blue, hsvOut)
    return hsvOut
}

/**
 * Convert RGB red, green blue to HSV (hue-saturation-value) components.
 * ```
 * hsv[0] is Hue [0 .. 360)
 * hsv[1] is Saturation [0...1]
 * hsv[2] is Value [0...1]
 * ```
 *
 * ```
 * Red is [0 .. 255]
 * Green is [0...255]
 * Blue is [0...255]
 * ```
 */
fun rgbToHSV(red: Int, green: Int, blue: Int, hsvOut: FloatArray) {
    android.graphics.Color.RGBToHSV(red, green, blue, hsvOut)
}

/*
    HSL-ColorInt Conversions
 */

/**
 * Convert HSL (hue-saturation-lightness) components to a RGB color in [Integer] format.
 *
 * For instance, red =255, green =0, blue=0 is -65536
 *
 * ```
 * hsl[0] is Hue [0 .. 360)
 * hsl[1] is Saturation [0...1]
 * hsl[2] is Lightness [0...1]
 * ```
 */
fun hslToColorInt(hslIn: FloatArray): Int {
    return hslToColorInt(hslIn[0], hslIn[1], hslIn[2])
}

/**
 * Convert HSL (hue-saturation-lightness) components to a RGB color in [Integer] format.
 * ```
 * Hue is [0 .. 360)
 * Saturation is [0...1]
 * Lightness is [0...1]
 * ```
 */
fun hslToColorInt(hue: Float, saturation: Float, lightness: Float): Int {
    return ColorUtils.HSLToColor(floatArrayOf(hue, saturation, lightness))
}

/**
 * Convert RGB color [Integer] to HSL (hue-saturation-lightness) components.
 * ```
 * hsl[0] is Hue [0 .. 360)
 * hsl[1] is Saturation [0...1]
 * hsl[2] is Lightness [0...1]
 * ```
 */
fun colorIntToHSL(color: Int): FloatArray {
    val hslOut = floatArrayOf(0f, 0f, 0f)
    ColorUtils.colorToHSL(color, hslOut)
    return hslOut
}

/**
 * Convert RGB color [Integer] to HSL (hue-saturation-lightness) components.
 * ```
 * hsl[0] is Hue [0 .. 360)
 * hsl[1] is Saturation [0...1]
 * hsl[2] is Lightness [0...1]
 * ```
 */
fun colorIntToHSL(color: Int, hslIn: FloatArray) {
    ColorUtils.colorToHSL(color, hslIn)
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
    return colorIntToRGBArray(hslToColorInt(hslIn))
}

/**
 * Convert HSL (hue-saturation-lightness) components to a RGB red, green blue array.
 * ```
 * Hue is [0 .. 360)
 * Saturation is [0...1]
 * Lightness is [0...1]
 * ```
 *
 * ```
 * rgb[0] is Red [0 .. 255]
 * rgb[1] is Green [0...255]
 * rgb[2] is Blue [0...255]
 * ```
 */
fun hslToRGBArray(hue: Float, saturation: Float, lightness: Float): IntArray {
    return colorIntToRGBArray(hslToColorInt(hue, saturation, lightness))
}

/**
 * Convert HSL (hue-saturation-lightness) components to a RGB red, green blue array.
 * ```
 * Hue [0 .. 360)
 * Saturation is [0...1]
 * Lightness is [0...1]
 * ```
 *
 * ```
 * rgb[0] is Red [0 .. 255]
 * rgb[1] is Green [0...255]
 * rgb[2] is Blue [0...255]
 * ```
 */
fun hslToRGBArray(hue: Float, saturation: Float, lightness: Float, rgbIn: IntArray) {
    colorIntToRGBArray(hslToColorInt(hue, saturation, lightness), rgbIn)
}

/**
 * Convert RGB red, green blue to HSL (hue-saturation-lightness) components.
 * ```
 * hsl[0] is Hue [0 .. 360)
 * hsl[1] is Saturation [0...1]
 * hsl[2] is Lightness [0...1]
 * ```
 *
 * ```
 * Red is [0 .. 255]
 * Green is [0...255]
 * Blue is [0...255]
 * ```
 */
fun rgbToHSL(red: Int, green: Int, blue: Int): FloatArray {
    val outHsl = floatArrayOf(0f, 0f, 0f)
    ColorUtils.RGBToHSL(red, green, blue, outHsl)
    return outHsl
}

/**
 * Convert RGB red, green blue to HSL (hue-saturation-lightness) components.
 * ```
 * hsl[0] is Hue [0 .. 360)
 * hsl[1] is Saturation [0...1]
 * hsl[2] is Lightness [0...1]
 * ```
 *
 * ```
 * Red is [0 .. 255]
 * Green is [0...255]
 * Blue is [0...255]
 * ```
 */
fun rgbToHSL(red: Int, green: Int, blue: Int, hslIn: FloatArray) {
    ColorUtils.RGBToHSL(red, green, blue, hslIn)
}

/*
    ColorInt-RGB Conversions
 */
fun colorIntToRGBArray(color: Int): IntArray {
    val red = android.graphics.Color.red(color)
    val green = android.graphics.Color.green(color)
    val blue = android.graphics.Color.blue(color)
    return intArrayOf(red, green, blue)
}

fun colorIntToRGBArray(color: Int, rgbIn: IntArray) {
    val red = android.graphics.Color.red(color)
    val green = android.graphics.Color.green(color)
    val blue = android.graphics.Color.blue(color)

    rgbIn[0] = red
    rgbIn[1] = green
    rgbIn[2] = blue
}

fun rgbToColorInt(red: Int, green: Int, blue: Int): Int {
    return android.graphics.Color.rgb(red, green, blue)
}

/*
    RGB-HEX Conversions
 */
/**
 * Get hex representation of a rgb Color in [Integer] format
 */
fun Int.toRgbString(): String =
    ("#" +
            red.toStringComponent() +
            green.toStringComponent() +
            blue.toStringComponent())
        .uppercase(Locale.getDefault())

/**
 * Get hex representation of a argb Color in [Integer] format
 */
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
 * Convert red, green, blue components [0..255] range to Hex format String
 */
fun rgbToHex(red: Int, green: Int, blue: Int): String {
    return "#${Integer.toHexString(red)}" +
            Integer.toHexString(green) +
            Integer.toHexString(blue)
}

/**
 * Convert rgb array to Hex format String
 * ```
 * rgb[0] is Red [0 .. 255]
 * rgb[1] is Green [0...255]
 * rgb[2] is Blue [0...255]
 * ```
 */
fun rgbToHex(rgb: IntArray): String {
    return "#${Integer.toHexString(rgb[0])}" +
            Integer.toHexString(rgb[1]) +
            Integer.toHexString(rgb[2])
}

/**
 * Convert alpha, red, green, blue components in [0..255] range argb to Hex format String
 *
 * ```
 * Red is [0 .. 255]
 * Green is [0...255]
 * Blue is [0...255]
 * ```
 */
fun argbToHex(alpha: Int, red: Int, green: Int, blue: Int): String {
    return "#${Integer.toHexString(alpha)}" +
            Integer.toHexString(red) +
            Integer.toHexString(green) +
            Integer.toHexString(blue)
}

/**
 * Converts **RGB** colors in range of [0f-1f] to [0-255]
 */
fun Float.toColorInt() = (this * 255).toInt()

/**
 * Converts **RGB** colors in range of [0f-1f] to [0-255] to String
 */
fun Float.toColorString() = this.toColorInt().toString()

/**
 * Converts **HSV** or **HSL** colors that are in range of [0f-1f] to [0-100] int range to
 * display in a String
 */
fun Float.toPercent() = (this * 100).toInt()
