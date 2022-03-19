package com.smarttoolfactory.colorpicker.ui.brush

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.smarttoolfactory.colorpicker.ui.gradientColorScaleHSL
import com.smarttoolfactory.colorpicker.ui.gradientColorScaleHSV


/*
    HSV Gradients
 */
/**
 * Gradient for Slider for selecting Hue from HSV
 */
fun sliderHueHSVGradient(
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
/**
 * Gradient for Slider for selecting Hue from HSL
 */
fun sliderHueHSLGradient(
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

fun sliderLightnessGradient3Stops(
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

/*
    RGB Gradients
 */
fun sliderRedGradient(
    alpha: Float = 1f,
    start: Offset = Offset.Zero,
    end: Offset = Offset.Infinite
): Brush {
    return Brush.linearGradient(
        colors = listOf(
            Color.hsl(hue = 0f, saturation = 1f, lightness = 0f, alpha = alpha),
            Color.hsl(hue = 0f, saturation = 1f, lightness = .5f, alpha = alpha)
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
            Color.hsl(hue = 120f, saturation = 1f, lightness = 0f, alpha = alpha),
            Color.hsl(hue = 120f, saturation = 1f, lightness = .5f, alpha = alpha)
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
            Color.hsl(hue = 240f, saturation = 1f, lightness = 0f, alpha = alpha),
            Color.hsl(hue = 240f, saturation = 1f, lightness = .5f, alpha = alpha)
        ),
        start = start,
        end = end
    )
}
