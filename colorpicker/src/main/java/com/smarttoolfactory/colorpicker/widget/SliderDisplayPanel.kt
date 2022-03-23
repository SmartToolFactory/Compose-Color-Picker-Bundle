package com.smarttoolfactory.colorpicker.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/*
    Slider Display Panels that contain Title, Slider and Property as %, degree or Int
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
