package com.smarttoolfactory.colorpicker.widget

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
import kotlin.math.roundToInt


@Composable
fun SliderDisplayHueHSV(
    modifier: Modifier,
    hue: Float,
    saturation: Float,
    value: Float,
    onValueChange: (Float) -> Unit){

    ColorSliderDisplay(modifier = modifier, title = "Hue", colorValue = hue) {
        SliderHueHSL(
            modifier = Modifier.width(300.dp),
            hue = hue,
            saturation = saturation,
            lightness = value,
            onValueChange = onValueChange
        )
    }

}

/**
 * Composable that shows a title as initial letter, title color and a Slider to pick color
 */
@Composable
fun ColorSliderDisplay(
    modifier: Modifier,
    title: String,
    titleColor: Color=Color.LightGray,
    colorValue: Float,
    colorValueSuffix: String = "%",
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
        slider()
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = " $colorValue$colorValueSuffix",
            color = Color.LightGray,
            fontSize = 12.sp,
            modifier = Modifier.width(30.dp)
        )

    }
}