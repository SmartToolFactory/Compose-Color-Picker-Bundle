package com.smarttoolfactory.colorpicker.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.ui.brush.*
import com.smarttoolfactory.slider.ColorBrush
import com.smarttoolfactory.slider.ColorfulSlider
import com.smarttoolfactory.slider.MaterialSliderDefaults

/*
    HSV Sliders
 */

/**
 * [CheckeredColorfulSlider] that displays [hue] change in
 * [HSV](https://en.wikipedia.org/wiki/HSL_and_HSV) color mode.
 */
@Composable
fun SliderHueHSV(
    modifier: Modifier = Modifier,
    hue: Float,
    saturation: Float,
    value: Float,
    onValueChange: (Float) -> Unit
) {
    val sliderHueSelectionHSLGradient = sliderHueHSVGradient(
        saturation = saturation,
        value = value,
    )

    CheckeredColorfulSlider(
        modifier = modifier,
        value = hue,
        valueRange = 0f..360f,
        onValueChange = onValueChange,
        brush = sliderHueSelectionHSLGradient
    )
}

/**
 * [CheckeredColorfulSlider] that display [saturation] change in
 * [HSV](https://en.wikipedia.org/wiki/HSL_and_HSV) color mode.
 */
@Composable
fun SliderSaturationHSV(
    modifier: Modifier = Modifier,
    hue: Float,
    saturation: Float,
    value: Float,
    onValueChange: (Float) -> Unit
) {
    val sliderHueSelectionHSLGradient = sliderSaturationHSVGradient(
        hue, value
    )

    CheckeredColorfulSlider(
        modifier = modifier,
        value = saturation,
        onValueChange = onValueChange,
        brush = sliderHueSelectionHSLGradient
    )
}

@Composable
fun SliderValueHSV(
    modifier: Modifier = Modifier,
    hue: Float,
    value: Float,
    onValueChange: (Float) -> Unit
) {

    val sliderValueGradient = sliderValueGradient(hue = hue)

    CheckeredColorfulSlider(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        brush = sliderValueGradient
    )
}

@Composable
fun SliderAlphaHSV(
    modifier: Modifier = Modifier,
    hue: Float,
    alpha: Float,
    onValueChange: (Float) -> Unit
) {
    val sliderAlphaHSLGradient = sliderAlphaHSVGradient(hue = hue)
    CheckeredColorfulSlider(
        modifier = modifier,
        value = alpha,
        onValueChange = onValueChange,
        brush = sliderAlphaHSLGradient,
        drawChecker = true
    )
}

/*
    HSL Sliders
 */

@Composable
fun SliderHueHSL(
    modifier: Modifier = Modifier,
    hue: Float,
    saturation: Float,
    lightness: Float,
    onValueChange: (Float) -> Unit
) {

    val sliderHueSelectionHSLGradient = sliderHueHSLGradient(
        saturation = saturation,
        lightness = lightness,
    )

    CheckeredColorfulSlider(
        modifier = modifier,
        value = hue,
        valueRange = 0f..360f,
        onValueChange = onValueChange,
        brush = sliderHueSelectionHSLGradient
    )
}

@Composable
fun SliderSaturationHSL(
    modifier: Modifier = Modifier,
    hue: Float,
    saturation: Float,
    lightness: Float,
    onValueChange: (Float) -> Unit
) {

    val sliderHueSelectionHSLGradient = sliderSaturationHSLGradient(
        hue, lightness
    )

    CheckeredColorfulSlider(
        modifier = modifier,
        value = saturation,
        onValueChange = onValueChange,
        brush = sliderHueSelectionHSLGradient
    )
}

@Composable
fun SliderLightnessHSL(
    modifier: Modifier = Modifier,
    hue: Float = 0f,
    lightness: Float,
    onValueChange: (Float) -> Unit
) {

    val sliderLightnessGradient = sliderLightnessGradient(hue)

    CheckeredColorfulSlider(
        modifier = modifier,
        value = lightness,
        onValueChange = onValueChange,
        brush = sliderLightnessGradient
    )
}

@Composable
fun SliderAlphaHSL(
    modifier: Modifier = Modifier,
    hue: Float, alpha: Float,
    onValueChange: (Float) -> Unit
) {
    val sliderAlphaHSLGradient = sliderAlphaHSLGradient(hue = hue)
    CheckeredColorfulSlider(
        modifier = modifier,
        value = alpha,
        onValueChange = onValueChange,
        brush = sliderAlphaHSLGradient,
        drawChecker = true
    )
}

/*
    RGB Sliders
 */

@Composable
fun SliderRedRGB(
    modifier: Modifier = Modifier,
    red: Float,
    onValueChange: (Float) -> Unit
) {
    val sliderRedGradient = sliderRedGradient()
    CheckeredColorfulSlider(
        modifier = modifier,
        value = red,
        onValueChange = onValueChange,
        brush = sliderRedGradient
    )
}

@Composable
fun SliderGreenRGB(
    modifier: Modifier = Modifier,
    green: Float,
    onValueChange: (Float) -> Unit
) {
    val sliderGreenGradient = sliderGreenGradient()
    CheckeredColorfulSlider(
        modifier = modifier,
        value = green,
        onValueChange = onValueChange,
        brush = sliderGreenGradient
    )
}

@Composable
fun SliderBlueRGB(
    modifier: Modifier = Modifier,
    blue: Float,
    onValueChange: (Float) -> Unit
) {
    val sliderBlueGradient = sliderBlueGradient()
    CheckeredColorfulSlider(
        modifier = modifier,
        value = blue,
        onValueChange = onValueChange,
        brush = sliderBlueGradient
    )
}

@Composable
fun SliderAlphaRGB(
    modifier: Modifier = Modifier,
    red: Float,
    green: Float,
    blue: Float,
    alpha: Float,
    onValueChange: (Float) -> Unit
) {
    val sliderAlphaRGBGradient = sliderAlphaRGBGradient(red, green, blue)
    CheckeredColorfulSlider(
        modifier = modifier,
        value = alpha,
        onValueChange = onValueChange,
        brush = sliderAlphaRGBGradient,
        drawChecker = true
    )
}

/**
 * Slider implementation that uses [ColorfulSlider] to have create **Slider** with gradient
 * colors with custom thumb radius and track height, and draws checker pattern behind the
 * track of [ColorfulSlider] if [drawChecker] is set to true
 */
@Composable
fun CheckeredColorfulSlider(
    modifier: Modifier = Modifier,
    value: Float,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    onValueChange: (Float) -> Unit,
    brush: Brush,
    drawChecker: Boolean = false
) {
    BoxWithConstraints(modifier = modifier, contentAlignment = Alignment.CenterStart) {
        if (drawChecker) {
            Box(
                modifier = Modifier
                    .width(maxWidth)
                    .height(12.dp)
                    .drawChecker(shape = RoundedCornerShape(6.dp))
            )
        }

        ColorfulSlider(
            value = value,
            modifier = Modifier,
            thumbRadius = 12.dp,
            trackHeight = 12.dp,
            onValueChange = onValueChange,
            valueRange = valueRange,
            coerceThumbInTrack = true,
            colors = MaterialSliderDefaults.materialColors(
                activeTrackColor = ColorBrush(brush = brush),
            ),
            drawInactiveTrack = false
        )
    }
}
