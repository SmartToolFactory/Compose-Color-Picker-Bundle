package com.smarttoolfactory.colorpicker.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.smarttoolfactory.slider.ColorfulSlider

/*
    Slider Panels
 */

/**
 * Composable that contains variant number of [ColorfulSlider],
 * and current value from slider depending on **onChange** callbacks are null or not.
 *
 * Based on availability of callbacks order of sliders are placed in order from top to bottom
 * ```
 * Hue Slider
 * Saturation Slider
 * Value Slider
 * Alpha Slider
 * ```
 * @param hue Hue of HSV
 * @param saturation Hue of HSV
 * @param value Hue of HSV
 * @param alpha Hue of HSV
 * @param onHueChange lambda in which [hue] should be updated. when this lambda is not nul
 * [SliderHueHSV] is added to Column of sliders.
 * @param onSaturationChange lambda in which [saturation] should be updated. when this lambda is not nul
 * [[SliderSaturationHSV] is added to Column of sliders.
 * @param onValueChange lambda in which [value] should be updated. when this lambda is not nul
 * [[SliderValueHSV] is added to Column of sliders.
 * @param onAlphaChange lambda in which [alpha] should be updated. when this lambda is not nul
 * [[SliderAlphaHSV] is added to Column of Sliders.
 */
@Composable
fun SliderPanelHSV(
    modifier: Modifier,
    hue: Float = 0f,
    saturation: Float = .5f,
    value: Float = .5f,
    alpha: Float = 1f,
    onHueChange: ((Float) -> Unit)? = null,
    onSaturationChange: ((Float) -> Unit)? = null,
    onValueChange: ((Float) -> Unit)? = null,
    onAlphaChange: ((Float) -> Unit)? = null,
) {
    Column(modifier) {

        onHueChange?.let { onHueChanged ->
            SliderHueHSV(
                modifier = Modifier,
                hue = hue,
                saturation = saturation,
                value = value,
                onValueChange = onHueChanged
            )
        }

        onSaturationChange?.let { onSaturationChanged ->
            SliderSaturationHSV(
                modifier = Modifier,
                hue = hue,
                saturation = saturation,
                value = value,
                onValueChange = onSaturationChanged
            )
        }

        onValueChange?.let { onValueChanged ->
            SliderValueHSV(
                modifier = Modifier,
                hue = hue,
                value = value,
                onValueChange = onValueChanged
            )
        }

        onAlphaChange?.let { onAlphaChanged ->
            SliderAlphaHSV(
                modifier = Modifier,
                hue = hue,
                alpha = alpha,
                onValueChange = onAlphaChanged
            )
        }
    }
}

/**
 * Composable that contains variant number of [ColorfulSlider],
 * and current value from slider depending on **onChange** callbacks are null or not.
 *
 * Based on availability of callbacks order of sliders are placed in order from top to bottom
 * ```
 * Hue Slider
 * Saturation Slider
 * Lightness Slider
 * Alpha Slider
 * ```
 * @param hue Hue of HSL
 * @param saturation Hue of HSL
 * @param lightness Hue of HSL
 * @param alpha Hue of HSL
 * @param onHueChange lambda in which [hue] should be updated. when this lambda is not nul
 * [SliderHueHSL] is added to Column of sliders.
 * @param onSaturationChange lambda in which [saturation] should be updated. when this lambda is not nul
 * [[SliderSaturationHSL] is added to Column of sliders.
 * @param onLightnessChange lambda in which [lightness] should be updated. when this lambda is not nul
 * [[SliderLightnessHSL] is added to Column of sliders.
 * @param onAlphaChange lambda in which [alpha] should be updated. when this lambda is not nul
 * [SliderAlphaHSL] is added to Column of sliders.
 */
@Composable
fun SliderPanelHSL(
    modifier: Modifier,
    hue: Float = 0f,
    saturation: Float = .5f,
    lightness: Float = .5f,
    alpha: Float = 1f,
    onHueChange: ((Float) -> Unit)? = null,
    onSaturationChange: ((Float) -> Unit)? = null,
    onLightnessChange: ((Float) -> Unit)? = null,
    onAlphaChange: ((Float) -> Unit)? = null,
) {
    Column(modifier) {

        onHueChange?.let { onHueChanged ->
            SliderHueHSL(
                modifier = Modifier,
                hue = hue,
                saturation = saturation,
                lightness = lightness,
                onValueChange = onHueChanged
            )
        }

        onSaturationChange?.let { onSaturationChanged ->
            SliderSaturationHSL(
                modifier = Modifier,
                hue = hue,
                saturation = saturation,
                lightness = lightness,
                onValueChange = onSaturationChanged
            )
        }

        onLightnessChange?.let { onValueChanged ->
            SliderLightnessHSL(
                modifier = Modifier,
                lightness = lightness,
                onValueChange = onValueChanged
            )
        }

        onAlphaChange?.let { onAlphaChanged ->
            SliderAlphaHSL(
                modifier = Modifier,
                hue = hue,
                alpha = alpha,
                onValueChange = onAlphaChanged
            )
        }
    }
}

/**
 * Composable that contains variant number of Sliders with Title on left, [ColorfulSlider],
 * and current value from slider depending on **onChange** callbacks are null or not.
 *
 * Based on availability of callbacks order of sliders are placed in order from top to bottom
 * ```
 * Red Slider
 * Green Slider
 * Blue Slider
 * Alpha Slider
 * ```
 * @param red Red of RGBA
 * @param green Hue of RGBA
 * @param blue Hue of RGBA
 * @param alpha Hue of RGBA
 * @param onRedChange lambda in which [red] should be updated. when this lambda is not nul
 * [SliderRedRGB] is added to Column of sliders.
 * @param onGreenChange lambda in which [green] should be updated. when this lambda is not nul
 * [[SliderGreenRGB] is added to Column of sliders.
 * @param onBlueChange lambda in which [blue] should be updated. when this lambda is not nul
 * [[SliderBlueRGB] is added to Column of sliders.
 * @param onAlphaChange lambda in which [alpha] should be updated. when this lambda is not nul
 * [SliderAlphaRGB] is added to Column of sliders.
 */
@Composable
fun SliderPanelRGBA(
    modifier: Modifier,
    red: Float = 1f,
    green: Float = 0f,
    blue: Float = 0f,
    alpha: Float = 1f,
    onRedChange: ((Float) -> Unit)? = null,
    onGreenChange: ((Float) -> Unit)? = null,
    onBlueChange: ((Float) -> Unit)? = null,
    onAlphaChange: ((Float) -> Unit)? = null,
) {
    Column(modifier) {

        onRedChange?.let { onRedChanged ->
            SliderRedRGB(
                modifier = Modifier,
                red = red,
                onValueChange = onRedChanged
            )
        }

        onGreenChange?.let { onGreenChanged ->
            SliderGreenRGB(
                modifier = Modifier,
                green = green,
                onValueChange = onGreenChanged
            )
        }

        onBlueChange?.let { onBlueChanged ->
            SliderBlueRGB(
                modifier = Modifier,
                blue = blue,
                onValueChange = onBlueChanged
            )
        }

        onAlphaChange?.let { onAlphaChanged ->
            SliderAlphaRGB(
                modifier = Modifier,
                red = red,
                green = green,
                blue = blue,
                alpha = alpha,
                onValueChange = onAlphaChanged
            )
        }
    }
}