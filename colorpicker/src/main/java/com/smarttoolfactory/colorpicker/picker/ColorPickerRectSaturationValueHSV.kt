package com.smarttoolfactory.colorpicker.picker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.model.ColorModel
import com.smarttoolfactory.colorpicker.selector.SelectorRectSaturationValueHSV
import com.smarttoolfactory.colorpicker.slider.SliderCircleColorDisplayHueHSV
import com.smarttoolfactory.colorpicker.util.colorToHSV
import com.smarttoolfactory.colorpicker.widget.HexDisplay

@Composable
fun ColorPickerRectSaturationValueHSV(
    modifier: Modifier = Modifier,
    selectionRadius: Dp = 8.dp,
    initialColor: Color,
    onColorChange: (Color) -> Unit
) {

    val hsvArray = colorToHSV(initialColor)

    var hue by remember { mutableStateOf(hsvArray[0]) }
    val saturation by remember { mutableStateOf(hsvArray[1]) }
    var value by remember { mutableStateOf(hsvArray[2]) }
    var alpha by remember { mutableStateOf(initialColor.alpha) }

    val currentColor =
        Color.hsv(hue = hue, saturation = saturation, value = value, alpha = alpha)

    var colorModel by remember { mutableStateOf(ColorModel.HSV) }

    onColorChange(currentColor)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SelectorRectSaturationValueHSV(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4 / 3f),
            hue = hue,
            value = value,
            selectionRadius = selectionRadius,
                    onChange = { h, v ->
                hue = h
                value = v
            },
        )

        SliderCircleColorDisplayHueHSV(
            modifier = Modifier.padding(8.dp),
            hue = hue,
            saturation = saturation,
            value = value,
            alpha = alpha,
            onHueChange = {
                hue = it
            },
            onAlphaChange = {
                alpha = it
            }
        )

        HexDisplay(
            color = currentColor,
            colorModel = colorModel,
            onColorModelChange = {
                colorModel = it
            }
        )
    }
}