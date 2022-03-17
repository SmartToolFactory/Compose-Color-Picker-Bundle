package com.smarttoolfactory.colorpicker.ui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


/*
    Vertical Brushes for picking Saturation and Lightness/Value content
 */
fun transparentToBlackVerticalGradient(
    startY: Float = 0.0f,
    endY: Float = Float.POSITIVE_INFINITY
): Brush {
    return Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.Black),
        startY = startY,
        endY = endY
    )
}

fun transparentToWhiteVerticalGradient(
    startY: Float = 0.0f,
    endY: Float = Float.POSITIVE_INFINITY
): Brush {
    return Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.White),
        startY = startY,
        endY = endY
    )
}

fun transparentToGrayVerticalGradient(
    startY: Float = 0.0f,
    endY: Float = Float.POSITIVE_INFINITY
): Brush {
    return Brush.verticalGradient(
        colors = listOf(Color.Transparent, Gray),
        startY = startY,
        endY = endY
    )
}


fun whiteToTransparentToBlackVerticalGradient(
    startY: Float = 0.0f,
    endY: Float = Float.POSITIVE_INFINITY
): Brush {
    return Brush.verticalGradient(
        0.0f to Color.White,
        0.5f to WhiteTransparent,
        0.5f to Color.Transparent,
        1f to Color.Black,
        startY = startY,
        endY = endY
    )
}

fun whiteToBlackGradient(
    startY: Float = 0.0f,
    endY: Float = Float.POSITIVE_INFINITY
): Brush {
    return Brush.verticalGradient(
        colors = listOf(Color.White, Color.Black),
        startY = startY,
        endY = endY
    )
}

/*
    Horizontal Gradients for picking Saturation and Lightness/Value content
 */
fun whiteToSaturationForHSVGradient(
    hue: Float,
    start: Offset = Offset.Zero,
    end: Offset = Offset.Infinite
): Brush {
    return Brush.linearGradient(
        colors = listOf(Color.White, Color.hsv(hue, 1f, 1f)),
        start = start,
        end = end
    )
}

fun whiteToSaturationForHSLGradient(
    hue: Float,
    start: Offset = Offset.Zero,
    end: Offset = Offset.Infinite
): Brush {
    return Brush.linearGradient(
        colors = listOf(Color.White, Color.hsl(hue, 1f, .5f)),
        start = start,
        end = end
    )
}

/*
    Horizontal Slider Gradients
 */

/*
    HSV Gradients
 */
/**
 * Gradient for Slider for selecting Hue from HSV
 */
fun sliderHueSelectionHSVGradient(
    saturation: Float = 1f,
    value: Float = 1f,
    alpha: Float = 1f,
    start: Offset = Offset.Zero,
    end: Offset = Offset.Infinite
): Brush {
    return Brush.linearGradient(
        colors = gradientColorScaleHSV(
            saturation = saturation,
            value = value,
            alpha = alpha,
        ),
        start = start,
        end = end
    )
}

fun sliderSaturationHSVGradient(
    hue: Float,
    value: Float = 1f,
    alpha: Float = 1f,
    start: Offset = Offset.Zero,
    end: Offset = Offset.Infinite
): Brush {
    return Brush.linearGradient(
        colors = listOf(
            Color.hsv(hue = hue, saturation = 0f, value = value, alpha = alpha),
            Color.hsv(hue = hue, saturation = 1f, value = value, alpha = alpha)
        ),
        start = start,
        end = end
    )
}

fun sliderValueGradient(
    hue: Float,
    alpha: Float = 1f,
    start: Offset = Offset.Zero,
    end: Offset = Offset.Infinite
): Brush {
    return Brush.linearGradient(
        colors = listOf(
            Color.hsv(hue = hue, saturation = 1f, value = 0f, alpha = alpha),
            Color.hsv(hue = hue, saturation = 1f, value = 1f, alpha = alpha)
        ),
        start = start,
        end = end
    )
}

/*
    HSL Gradients
 */
fun sliderHueSelectionHSLGradient(
    saturation: Float = 1f,
    lightness: Float = .5f,
    alpha: Float = 1f,
    start: Offset = Offset.Zero,
    end: Offset = Offset.Infinite
): Brush {
    return Brush.linearGradient(
        colors = gradientColorScaleHSL(
            saturation = saturation,
            lightness = lightness,
            alpha = alpha,
        ),
        start = start,
        end = end
    )
}


fun sliderSaturationHSLGradient(
    hue: Float,
    lightness: Float = .5f,
    alpha: Float = 1f,
    start: Offset = Offset.Zero,
    end: Offset = Offset.Infinite
): Brush {
    return Brush.linearGradient(
        colors = listOf(
            Color.hsl(hue = hue, saturation = 0f, lightness = lightness, alpha = alpha),
            Color.hsl(hue = hue, saturation = 1f, lightness = lightness, alpha = alpha),
        ),
        start = start,
        end = end
    )
}

fun sliderLightnessGradient(
    hue: Float,
    alpha: Float = 1f,
    start: Offset = Offset.Zero,
    end: Offset = Offset.Infinite
): Brush {
    return Brush.linearGradient(
        colors = listOf(
            Color.hsl(hue = hue, saturation = 1f, lightness = 0f, alpha = alpha),
            Color.hsl(hue = hue, saturation = 1f, lightness = 1f, alpha = alpha)
        ),
        start = start,
        end = end
    )
}

fun sliderLightnessGradient2(
    hue: Float, alpha: Float = 1f,
    start: Offset = Offset.Zero,
    end: Offset = Offset.Infinite
): Brush {
    return Brush.linearGradient(
        colors = listOf(
            Color.hsl(hue = hue, saturation = 1f, lightness = 0f, alpha = alpha),
            Color.hsl(hue = hue, saturation = 1f, lightness = .5f, alpha = alpha),
            Color.hsl(hue = hue, saturation = 1f, lightness = 1f, alpha = alpha)
        ),
        start = start,
        end = end
    )
}


fun sliderRedGradient(
    alpha: Float = 1f,
    start: Offset = Offset.Zero,
    end: Offset = Offset.Infinite
): Brush {
    return Brush.linearGradient(
        colors = listOf(

        ),
        start = start,
        end = end
    )
}

fun sliderGreenGradient(
    alpha: Float = 1f,
    start: Offset = Offset.Zero,
    end: Offset = Offset.Infinite
): Brush {
    return Brush.linearGradient(
        colors = listOf(

        ),
        start = start,
        end = end
    )
}

fun sliderBlueGradient(
    alpha: Float = 1f,
    start: Offset = Offset.Zero,
    end: Offset = Offset.Infinite
): Brush {
    return Brush.linearGradient(
        colors = listOf(

        ),
        start = start,
        end = end
    )
}

/**
 *
 * Get start and end offsets that are limited between [0f, Float.POSITIVE_INFINITY] in x and
 * y axes wrapped in [GradientOffset].
 * Infinity is converted to Composable width on x axis, height on y axis in shader.
 *
 * Default angle for [Brush.linearGradient] is 135 degrees, [Brush.verticalGradient] also is
 * [Brush.linearGradient] with 90 degrees.
 *
 * ```
 *  0 degrees
 *  start = Offset(0f,0f),
 *  end = Offset(Float.POSITIVE_INFINITY,0f)
 *
 * 45 degrees
 * start = Offset(0f, Float.POSITIVE_INFINITY),
 * end = Offset(Float.POSITIVE_INFINITY, 0f)
 *
 * 90 degrees
 * start = Offset(0f, Float.POSITIVE_INFINITY),
 * end = Offset(0f,0f)
 *
 * 135 degrees
 * start = Offset(0f,0f),
 * end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
 *
 * ```
 */
fun GradientOffset(angle: GradientAngle): GradientOffset {
    return when (angle) {
        GradientAngle.Degree45 -> GradientOffset(
            start = Offset(0f, Float.POSITIVE_INFINITY),
            end = Offset(Float.POSITIVE_INFINITY, 0f)
        )
        GradientAngle.Degree90 -> GradientOffset(
            start = Offset(0f, Float.POSITIVE_INFINITY),
            end = Offset(0f, 0f)
        )
        GradientAngle.Degree135 -> GradientOffset(
            start = Offset(0f, 0f),
            end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
        )
        else -> GradientOffset(
            start = Offset(0f, 0f),
            end = Offset(Float.POSITIVE_INFINITY, 0f)
        )
    }
}

data class GradientOffset(val start: Offset, val end: Offset)

enum class GradientAngle {
    Degree0, Degree45, Degree90, Degree135
}