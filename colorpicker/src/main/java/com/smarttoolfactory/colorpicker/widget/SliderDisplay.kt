package com.smarttoolfactory.colorpicker.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.fractionToRGBString
import com.smarttoolfactory.colorpicker.fractionToPercent

/*
    HSV Slider Displays
 */
@Composable
fun SliderDisplayHueHSV(
    modifier: Modifier,
    hue: Float,
    saturation: Float,
    value: Float,
    onValueChange: (Float) -> Unit
) {

    TitledSliderDisplay(
        modifier = modifier,
        title = "Hue",
        description = "${hue.toInt()}°"
    ) {
        SliderHueHSL(
            hue = hue,
            saturation = saturation,
            lightness = value,
            onValueChange = onValueChange
        )
    }
}

@Composable
fun SliderDisplaySaturationHSV(
    modifier: Modifier,
    hue: Float,
    saturation: Float,
    value: Float,
    onValueChange: (Float) -> Unit
) {
    TitledSliderDisplay(
        modifier = modifier,
        title = "Saturation",
        description = "${saturation.fractionToPercent()}"
    ) {
        SliderSaturationHSV(
            hue = hue,
            saturation = saturation,
            value = value,
            onValueChange = onValueChange
        )
    }
}

@Composable
fun SliderDisplayValueHSV(
    modifier: Modifier,
    hue: Float,
    value: Float,
    onValueChange: (Float) -> Unit
) {
    TitledSliderDisplay(
        modifier = modifier,
        title = "Value",
        description = "${value.fractionToPercent()}"
    ) {
        SliderValueHSV(
            hue = hue,
            value = value,
            onValueChange = onValueChange
        )
    }
}

@Composable
fun SliderDisplayAlphaHSV(
    modifier: Modifier,
    hue: Float,
    alpha: Float,
    onValueChange: (Float) -> Unit
) {
    TitledSliderDisplay(
        modifier = modifier,
        title = "Alpha",
        description = "${alpha.fractionToPercent()}"
    ) {
        SliderAlphaHSV(
            hue = hue,
            alpha = alpha,
            onValueChange = onValueChange
        )
    }
}

/*
    HSL Slider Displays
 */
@Composable
fun SliderDisplayHueHSL(
    modifier: Modifier,
    hue: Float,
    saturation: Float,
    lightness: Float,
    onValueChange: (Float) -> Unit
) {

    TitledSliderDisplay(
        modifier = modifier,
        title = "Hue",
        description = "${hue.toInt()}°"

    ) {
        SliderHueHSL(
            hue = hue,
            saturation = saturation,
            lightness = lightness,
            onValueChange = onValueChange
        )
    }
}

@Composable
fun SliderDisplaySaturationHSL(
    modifier: Modifier,
    hue: Float,
    saturation: Float,
    lightness: Float,
    onValueChange: (Float) -> Unit
) {
    TitledSliderDisplay(
        modifier = modifier,
        title = "Saturation",
        description = "${saturation.fractionToPercent()}"
    ) {
        SliderSaturationHSL(
            hue = hue,
            saturation = saturation,
            lightness = lightness,
            onValueChange = { value ->
                onValueChange(value)
            }
        )
    }
}

@Composable
fun SliderDisplayLightnessHSL(
    modifier: Modifier,
    lightness: Float,
    onValueChange: (Float) -> Unit
) {
    TitledSliderDisplay(
        modifier = modifier,
        title = "Lightness",
        description = "${lightness.fractionToPercent()}"
    ) {
        SliderLightnessHSL(
            lightness = lightness,
            onValueChange = onValueChange
        )
    }
}

@Composable
fun SliderDisplayAlphaHSL(
    modifier: Modifier,
    hue: Float,
    alpha: Float,
    onValueChange: (Float) -> Unit
) {
    TitledSliderDisplay(
        modifier = modifier,
        title = "Alpha",
        description = "${alpha.fractionToPercent()}"
    ) {
        SliderAlphaHSV(
            hue = hue,
            alpha = alpha,
            onValueChange = onValueChange
        )
    }
}

/*
    RGB Slider Displays
 */
@Composable
fun SliderDisplayRedRGB(
    modifier: Modifier,
    red: Float,
    onValueChange: (Float) -> Unit
) {
    TitledSliderDisplay(
        modifier = modifier,
        titleColor = Color.Red,
        title = "Red",
        description = red.fractionToRGBString()
    ) {
        SliderRedRGB(
            red = red,
            onValueChange = onValueChange
        )
    }
}

@Composable
fun SliderDisplayGreenRGB(
    modifier: Modifier,
    green: Float,
    onValueChange: (Float) -> Unit
) {
    TitledSliderDisplay(
        modifier = modifier,
        titleColor = Color.Green,
        title = "Green",
        description = green.fractionToRGBString()
    ) {
        SliderGreenRGB(
            green = green,
            onValueChange = onValueChange
        )
    }
}

@Composable
fun SliderDisplayBlueRGB(
    modifier: Modifier,
    blue: Float,
    onValueChange: (Float) -> Unit
) {
    TitledSliderDisplay(
        modifier = modifier,
        titleColor = Color.Blue,
        title = "Blue",
        description = blue.fractionToRGBString()
    ) {
        SliderBlueRGB(
            blue = blue,
            onValueChange = onValueChange
        )
    }
}

@Composable
fun SliderDisplayAlphaRGB(
    modifier: Modifier,
    red: Float,
    green: Float,
    blue: Float,
    alpha: Float,
    onValueChange: (Float) -> Unit
) {
    TitledSliderDisplay(
        modifier = modifier,
        title = "Alpha",
        description = "${alpha.fractionToPercent()}"
    ) {
        SliderAlphaRGB(
            red = red,
            green = green,
            blue = blue,
            alpha = alpha,

            onValueChange = onValueChange
        )
    }
}



/**
 * Composable that shows a title as initial letter, title color and a Slider to pick color.
 * @param title Title is positioned left side of the slider and presented with only initial letter
 * @param titleColor color of the [title] string
 * @param description shows the value retrieved from [slider] value change.
 * @param slider is Composable that uses [CheckeredColorfulSlider]
 */
@Composable
fun TitledSliderDisplay(
    modifier: Modifier,
    title: String,
    titleColor: Color = Color.LightGray,
    description: String,
    slider: @Composable () -> Unit
) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = title.substring(0, 1),
            color = titleColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box(modifier = Modifier.weight(1f)) {
            slider()
        }
        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = description,
            fontSize = 12.sp,
            color = Color.LightGray,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(30.dp)
        )
    }
}