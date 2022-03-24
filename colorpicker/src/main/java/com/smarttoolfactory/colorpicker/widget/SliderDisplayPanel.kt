package com.smarttoolfactory.colorpicker.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.smarttoolfactory.colorpicker.fractionToRGBRange
import com.smarttoolfactory.colorpicker.rgbToHSL
import com.smarttoolfactory.colorpicker.rgbToHSV
import com.smarttoolfactory.slider.ColorfulSlider
import kotlin.math.roundToInt

/*
    Slider Display Panels that contain Title, Slider and Property as %, degree or Int
 */

/**
 * Composable that contains variant number of Slider displays with Title on left, [ColorfulSlider],
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
 * [SliderDisplayHueHSV] is added to Column of sliders.
 * @param onSaturationChange lambda in which [saturation] should be updated. when this lambda is not nul
 * [[SliderDisplaySaturationHSV] is added to Column of sliders.
 * @param onValueChange lambda in which [value] should be updated. when this lambda is not nul
 * [[SliderDisplayValueHSV] is added to Column of sliders.
 * @param onAlphaChange lambda in which [alpha] should be updated. when this lambda is not nul
 * [[SliderDisplayAlphaHSV] is added to Column of slider displays.
 */
@Composable
fun SliderDisplayPanelHSV(
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
            SliderDisplayHueHSV(
                modifier = Modifier,
                hue = hue,
                saturation = saturation,
                value = value,
                onValueChange = onHueChanged
            )
        }

        onSaturationChange?.let { onSaturationChanged ->
            SliderDisplaySaturationHSV(
                modifier = Modifier,
                hue = hue,
                saturation = saturation,
                value = value,
                onValueChange = onSaturationChanged
            )
        }

        onValueChange?.let { onValueChanged ->
            SliderDisplayValueHSV(
                modifier = Modifier,
                hue = hue,
                value = value,
                onValueChange = onValueChanged
            )
        }

        onAlphaChange?.let { onAlphaChanged ->
            SliderDisplayAlphaHSV(
                modifier = Modifier,
                hue = hue,
                alpha = alpha,
                onValueChange = onAlphaChanged
            )
        }
    }
}

/**
 * Composable that contains variant number of Slider displays with Title on left, [ColorfulSlider],
 * and current value from slider depending on **onChange** callbacks are null or not.
 *
 * Based on availability of callbacks order of sliders are placed in order from top to bottom
 * ```
 * Hue Slider Display
 * Saturation Slider Display
 * Lightness Slider Display
 * Alpha Slider Display
 * ```
 * @param hue Hue of HSL
 * @param saturation Hue of HSL
 * @param lightness Hue of HSL
 * @param alpha Hue of HSL
 * @param onHueChange lambda in which [hue] should be updated. when this lambda is not nul
 * [SliderDisplayHueHSL] is added to Column of sliders.
 * @param onSaturationChange lambda in which [saturation] should be updated. when this lambda is not nul
 * [[SliderDisplaySaturationHSV] is added to Column of sliders.
 * @param onLightnessChange lambda in which [lightness] should be updated. when this lambda is not nul
 * [[SliderDisplayLightnessHSL] is added to Column of sliders.
 * @param onAlphaChange lambda in which [alpha] should be updated. when this lambda is not nul
 * [SliderDisplayAlphaHSL] is added to Column of sliders.
 */
@Composable
fun SliderDisplayPanelHSL(
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
            SliderDisplayHueHSL(
                modifier = Modifier,
                hue = hue,
                saturation = saturation,
                lightness = lightness,
                onValueChange = onHueChanged
            )
        }

        onSaturationChange?.let { onSaturationChanged ->
            SliderDisplaySaturationHSL(
                modifier = Modifier,
                hue = hue,
                saturation = saturation,
                lightness = lightness,
                onValueChange = onSaturationChanged
            )
        }

        onLightnessChange?.let { onValueChanged ->
            SliderDisplayLightnessHSL(
                modifier = Modifier,
                lightness = lightness,
                onValueChange = onValueChanged
            )
        }

        onAlphaChange?.let { onAlphaChanged ->
            SliderDisplayAlphaHSL(
                modifier = Modifier,
                hue = hue,
                alpha = alpha,
                onValueChange = onAlphaChanged
            )
        }
    }
}

/**
 * Composable that contains variant number of Slider displays with Title on left, [ColorfulSlider],
 * and current value from slider depending on **onChange** callbacks are null or not.
 *
 * Based on availability of callbacks order of sliders are placed in order from top to bottom
 * ```
 * Red Slider Display
 * Green Slider Display
 * Blue Slider Display
 * Alpha Slider Display
 * ```
 * @param red Red of RGBA
 * @param green Hue of RGBA
 * @param blue Hue of RGBA
 * @param alpha Hue of RGBA
 * @param onRedChange lambda in which [red] should be updated. when this lambda is not nul
 * [SliderDisplayHueHSL] is added to Column of sliders.
 * @param onGreenChange lambda in which [green] should be updated. when this lambda is not nul
 * [[SliderDisplaySaturationHSV] is added to Column of sliders.
 * @param onBlueChange lambda in which [blue] should be updated. when this lambda is not nul
 * [[SliderDisplayLightnessHSL] is added to Column of sliders.
 * @param onAlphaChange lambda in which [alpha] should be updated. when this lambda is not nul
 * [SliderDisplayAlphaHSL] is added to Column of sliders.
 */
@Composable
fun SliderDisplayPanelRGBA(
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
            SliderDisplayRedRGB(
                modifier = Modifier,
                red = red,
                onValueChange = onRedChanged
            )
        }

        onGreenChange?.let { onGreenChanged ->
            SliderDisplayGreenRGB(
                modifier = Modifier,
                green = green,
                onValueChange = onGreenChanged
            )
        }

        onBlueChange?.let { onBlueChanged ->
            SliderDisplayBlueRGB(
                modifier = Modifier,
                blue = blue,
                onValueChange = onBlueChanged
            )
        }

        onAlphaChange?.let { onAlphaChanged ->
            SliderDisplayAlphaRGB(
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


@Composable
fun SliderDisplayPanelColorFromHSV(
    modifier: Modifier,
    color: Color,
    onColorChange: (Color) -> Unit,
    showAlpha: Boolean
) {
    val red = color.red
    val green = color.green
    val blue = color.blue
    val alpha = color.alpha

    val hsvArray =
        rgbToHSV(red.fractionToRGBRange(), green.fractionToRGBRange(), blue.fractionToRGBRange())

    val hue = hsvArray[0]
    val saturation = hsvArray[1]
    val value = hsvArray[2]

    val alphaLambda: ((Float) -> Unit)? = if (showAlpha) { alphaChange ->
        onColorChange(
            Color.hsv(hue, saturation, value, alphaChange)
        )

    } else null

    SliderDisplayPanelHSV(
        modifier = modifier,
        hue = hue,
        saturation = saturation,
        value = value,
        alpha = alpha,
        onHueChange = { hueChange ->
            onColorChange(
                Color.hsv(hueChange, saturation, value, alpha)
            )
        },
        onSaturationChange = { saturationChange ->
            onColorChange(
                Color.hsv(hue, saturationChange, value, alpha)
            )

        },
        onValueChange = { valueChange ->
            onColorChange(
                Color.hsv(hue, saturation, valueChange, alpha)
            )

        },
        onAlphaChange = alphaLambda,
    )
}


@Composable
fun SliderDisplayPanelColorFromHSL(
    modifier: Modifier,
    color: Color,
    onColorChange: (Color) -> Unit,
    showAlpha: Boolean
) {
    val red = color.red
    val green = color.green
    val blue = color.blue
    val alpha = color.alpha


    val hslArray =
        rgbToHSL(red.fractionToRGBRange(), green.fractionToRGBRange(), blue.fractionToRGBRange())

    val hue = hslArray[0]
    val saturation = (hslArray[1] * 100).roundToInt() / 100f
    val lightness = (hslArray[2] * 100).roundToInt() / 100f

//    println("🔥 SliderDisplayPanelColorFromHSL red: $red, green: $green, blue: $blue")
    println("🔥 SliderDisplayPanelColorFromHSL red: $red, green: $green, blue: $blue, hue: $hue, saturation: $saturation, lightness: $lightness")
//    println("🔥 SliderDisplayPanelColorFromHSL2 hue: ${hslArray2[0]}, saturation: ${hslArray2[1]}, lightness: ${hslArray2[2]}")


    val alphaLambda: ((Float) -> Unit)? = if (showAlpha) { alphaChange ->
        onColorChange(
            Color.hsl(hue, saturation, lightness, alphaChange)
        )

    } else null

    SliderDisplayPanelHSL(
        modifier = modifier,
        hue = hue,
        saturation = saturation,
        lightness = lightness,
        alpha = alpha,
        onHueChange = { hueChange ->
            onColorChange(
                Color.hsl(hueChange, saturation, lightness, alpha)
            )
        },
        onSaturationChange = { saturationChange ->
            onColorChange(
                Color.hsl(hue, saturationChange, lightness, alpha)
            )

        },
        onLightnessChange = { lightnessChange ->

            val newLightness = ((lightnessChange * 100).roundToInt()) / 100f
            val newSaturation = ((saturation * 100).roundToInt()) / 100f
            val lightnessColor = Color.hsl(hue, newSaturation, newLightness, alpha)
            println("😱SliderDisplayPanelColorFromHSL hue: $hue, saturation: $saturation, newSaturation: $newSaturation, lightnessChange: $lightnessChange, newLightness: $newLightness")
//            val hslArrayNew = colorToHSL(lightnessColor)
//
//            println("😱😱SliderDisplayPanelColorFromHSL NEW hue: ${hslArrayNew[0]}, sat: ${hslArrayNew[1]}, light: ${hslArrayNew[2]}")
            onColorChange(
                lightnessColor
            )

        },
        onAlphaChange = alphaLambda,
    )
}

@Composable
fun SliderDisplayPanelColorFromRGBA(
    modifier: Modifier,
    color: Color,
    onColorChange: (Color) -> Unit,
    showAlpha: Boolean
) {
    val red = color.red
    val green = color.green
    val blue = color.blue
    val alpha = color.alpha

    println("🔥 SliderDisplayPanelColorFromRGBA red: $red, green: $green, blue: $blue")

    val alphaLambda: ((Float) -> Unit)? = if (showAlpha) { alphaChange ->
        onColorChange(
            Color(red, green, blue, alpha)
        )

    } else null

    SliderDisplayPanelRGBA(
        modifier = modifier,
        red = red,
        green = green,
        blue = blue,
        alpha = alpha,
        onRedChange = { redChange ->
            onColorChange(
                Color(redChange, green, blue, alpha)
            )
        },
        onGreenChange = { greenChange ->
            onColorChange(
                Color(red, greenChange, blue, alpha)
            )

        },
        onBlueChange = { blueChange ->
            onColorChange(
                Color(red, green, blueChange, alpha)
            )

        },
        onAlphaChange = alphaLambda,
    )
}
