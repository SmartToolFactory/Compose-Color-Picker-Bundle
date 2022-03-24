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
 * [CheckeredColorfulSlider] is a slider to select
 * [hue] in [HSV](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param hue in [0..1f]
 * @param saturation in [0..1f]
 * @param value in [0..1f]
 * @param onValueChange callback that returns change in [hue] when Slider is dragged
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
 * [CheckeredColorfulSlider] is a slider to select
 * [saturation] in [HSV](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param hue in [0..1f]
 * @param saturation in [0..1f]
 * @param value in [0..1f]
 * @param onValueChange callback that returns change in [saturation] when Slider is dragged
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

/**
 * [CheckeredColorfulSlider] is a slider to select
 * [value] in [HSV](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param hue in [0..1f]
 * @param value in [0..1f]
 * @param onValueChange callback that returns change in [value] when Slider is dragged
 */
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

/**
 * [CheckeredColorfulSlider] that displays [alpha] change in
 * [HSV](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 */
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

/**
 * [CheckeredColorfulSlider] is a slider to select
 * [hue] in [HSL](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param hue in [0..1f]
 * @param saturation in [0..1f]
 * @param lightness in [0..1f]
 * @param onValueChange callback that returns change in [hue] when Slider is dragged
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

/**
 * [CheckeredColorfulSlider] is a slider to select
 * [saturation] in [HSL](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param hue in [0..1f]
 * @param saturation in [0..1f]
 * @param lightness in [0..1f]
 * @param onValueChange callback that returns change in [saturation] when Slider is dragged
 */
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

/**
 * [CheckeredColorfulSlider] is a slider to select
 * [lightness] in [HSL](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param hue in [0..1f]
 * @param lightness in [0..1f]
 * @param gradientStops number of stops for gradient of this slider. 2 stops return
 * white-black in hsl, others transition from hue with lightness.
 * @param onValueChange callback that returns change in [lightness] when Slider is dragged
 */
@Composable
fun SliderLightnessHSL(
    modifier: Modifier = Modifier,
    hue: Float = 0f,
    saturation: Float = 0f,
    lightness: Float,
    onValueChange: (Float) -> Unit
) {

//    val sliderLightnessGradient = if (gradientStops!=2)
//        sliderLightnessGradient3Stops(hue)
//        else sliderLightnessGradient(hue)

    val sliderLightnessGradient = sliderLightnessGradient(hue, saturation)

    CheckeredColorfulSlider(
        modifier = modifier,
        value = lightness,
        onValueChange = onValueChange,
        brush = sliderLightnessGradient
    )
}

/**
 * [CheckeredColorfulSlider] is a slider to select
 * [alpha] in [HSL](https://en.wikipedia.org/wiki/HSL_and_HSV) color model.
 * @param hue in [0..1f]
 * @param onValueChange callback that returns change in [alpha] when Slider is dragged
 */
@Composable
fun SliderAlphaHSL(
    modifier: Modifier = Modifier,
    hue: Float,
    alpha: Float,
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

/**
 * [CheckeredColorfulSlider] that displays [red] change in
 * [RGB](https://en.wikipedia.org/wiki/RGB_color_model) color model.
 * @param red in [0..1f]
 * @param onValueChange callback that returns change in [red] when Slider is dragged
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

/**
 * [CheckeredColorfulSlider] that displays [green] change in
 * [RGB](https://en.wikipedia.org/wiki/RGB_color_model) color model.
 * @param green in [0..1f]
 * @param onValueChange callback that returns change in [green] when Slider is dragged
 */
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

/**
 * [CheckeredColorfulSlider] that displays [blue] change in
 * [RGB](https://en.wikipedia.org/wiki/RGB_color_model) color model.
 * @param blue in [0..1f]
 * @param onValueChange callback that returns change in [blue] when Slider is dragged
 */
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

/**
 * [CheckeredColorfulSlider] that displays [alpha] change in
 * [RGB](https://en.wikipedia.org/wiki/RGB_color_model) color model.
 * @param red in [0..1f]
 * @param green in [0..1f]
 * @param blue in [0..1f]
 * @param alpha in [0..1f]
 * @param onValueChange callback that returns change in [alpha] when Slider is dragged
 */
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
 * @param value that is read by [ColorfulSlider]
 * @param valueRange is the values are selected from
 * @param onValueChange is triggered when slider is dragged returns float between [valueRange]
 * @param brush is used for drawing gradient for track of [ColorfulSlider]
 * @param drawChecker when set to true draws checker behind track of [ColorfulSlider] to
 * display alpha
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
