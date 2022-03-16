package com.smarttoolfactory.colorpicker.ui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/*
    Vertical Gradients for picking Saturation and Lightness/Value content
 */
fun transparentToBlackVerticalGradient(): Brush {
    return Brush.verticalGradient(listOf(Color.Transparent, Color.Black))
}

fun transparentToWhiteVerticalGradient(): Brush {
    return Brush.verticalGradient(listOf(Color.Transparent, Color.White))
}

fun transparentToGrayVerticalGradient(): Brush {
    return Brush.verticalGradient(listOf(Color.Transparent, Gray))
}


fun whiteToTransparentToBlackVerticalGradient(): Brush {
    return Brush.verticalGradient(
        0.0f to Color.White,
        0.5f to WhiteTransparent,
        0.5f to Color.Transparent,
        1f to Color.Black
    )
}

fun lightnessGradient(size: Float): Brush {
    return Brush.verticalGradient(
        colors = listOf(Color.White, Color.Black),
        startY = 0f,
        endY = size
    )
}

/*
    Horizontal Gradients for picking Saturation and Lightness/Value content
 */
fun whiteToSaturationForHSVGradient(hue: Float, size: Float): Brush {
    return Brush.linearGradient(
        colors = listOf(Color.White, Color.hsv(hue, 1f, 1f)),
        end = Offset(size, 0f)
    )
}

fun whiteToSaturationForHSLGradient(hue: Float, size: Float): Brush {
    return Brush.linearGradient(
        colors = listOf(Color.White, Color.hsl(hue, 1f, .5f)),
        end = Offset(size, 0f)
    )
}

/*
    Horizontal Slider Gradients
 */

/**
 * Gradient for Slider for selecting Hue from HSV
 */
fun sliderHueSelectionHSVGradient(
    saturation: Float = 1f,
    value: Float = 1f,
    alpha: Float = 1f,
    size: Float
): Brush {
    return Brush.linearGradient(
        colors = gradientColorScaleHSV(
            saturation = saturation,
            value = value,
            alpha = alpha,
        ),
        end = Offset(size, 0f)
    )
}

fun sliderColorScaleHSLGradient(
    saturation: Float = 1f,
    lightness: Float = .5f,
    alpha: Float = 1f,
    size: Float
): Brush {
    return Brush.linearGradient(
        colors = gradientColorScaleHSL(
            saturation = saturation,
            lightness = lightness,
            alpha = alpha,
        ),
        end = Offset(size, 0f)
    )
}

fun sliderSaturationHSVGradient(
    hue: Float,
    value: Float = 1f,
    alpha: Float = 1f,
    size: Float
): Brush {
    return Brush.linearGradient(
        colors = listOf(
            Color.hsv(hue = hue, saturation = 0f, value = value, alpha = alpha),
            Color.hsv(hue = hue, saturation = 1f, value = value, alpha = alpha)
        ),
        end = Offset(size, 0f)
    )
}

fun sliderSaturationHSLGradient(
    hue: Float,
    lightness: Float = .5f,
    alpha: Float = 1f,
    size: Float
): Brush {
    return Brush.linearGradient(
        colors = listOf(
            Color.hsl(hue = hue, saturation = 0f, lightness = lightness, alpha = alpha),
            Color.hsl(hue = hue, saturation = 1f, lightness = lightness, alpha = alpha),
        ),
        end = Offset(size, 0f)
    )
}


fun sliderValueGradient(hue: Float, alpha: Float = 1f, size: Float): Brush {
    return Brush.linearGradient(
        colors = listOf(
            Color.hsv(hue = hue, saturation = 1f, value = 0f, alpha = alpha),
            Color.hsv(hue = hue, saturation = 1f, value = 1f, alpha = alpha)
        ),
        end = Offset(size, 0f)
    )
}


fun lightnessSliderGradient(hue: Float, alpha: Float = 1f, size: Float): Brush {
    return Brush.linearGradient(
        colors = listOf(
            Color.hsl(hue = hue, saturation = 1f, lightness = 0f, alpha = alpha),
            Color.hsl(hue = hue, saturation = 1f, lightness = .5f, alpha = alpha),
            Color.hsl(hue = hue, saturation = 1f, lightness = 1f, alpha = alpha)
        ),
        end = Offset(size, 0f)
    )
}


fun sliderRedGradient(alpha: Float = 1f, size: Float): Brush {
    return Brush.linearGradient(
        colors = listOf(

        ),
        end = Offset(size, 0f)
    )
}

fun sliderGreenGradient(alpha: Float = 1f, size: Float): Brush {
    return Brush.linearGradient(
        colors = listOf(

        ),
        end = Offset(size, 0f)
    )
}

fun sliderBlueGradient(alpha: Float = 1f, size: Float): Brush {
    return Brush.linearGradient(
        colors = listOf(

        ),
        end = Offset(size, 0f)
    )
}