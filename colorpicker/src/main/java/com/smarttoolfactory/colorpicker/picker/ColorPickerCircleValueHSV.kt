package com.smarttoolfactory.colorpicker.picker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.model.ColorModel
import com.smarttoolfactory.colorpicker.selector.SelectorCircleHueSaturationHSV
import com.smarttoolfactory.colorpicker.slider.SliderCircleColorDisplayValueHSV
import com.smarttoolfactory.colorpicker.widget.ColorDisplayExposedSelectionMenu
import com.smarttoolfactory.extendedcolors.util.ColorUtil


/**
 *
 */
@Composable
fun ColorPickerCircleValueHSV(
    modifier: Modifier = Modifier,
    selectionRadius: Dp = 8.dp,
    initialColor: Color,
    onColorChange: (Color, String) -> Unit
) {

    val hsvArray = ColorUtil.colorToHSV(initialColor)

    var hue by remember { mutableStateOf(hsvArray[0]) }
    var saturation by remember { mutableStateOf(hsvArray[1]) }
    var value by remember { mutableStateOf(hsvArray[2]) }
    var alpha by remember { mutableStateOf(initialColor.alpha) }

    val currentColor =
        Color.hsv(hue = hue, saturation = saturation, value = value, alpha = alpha)

    var colorModel by remember { mutableStateOf(ColorModel.HSV) }

    onColorChange(currentColor, ColorUtil.colorToHexAlpha(currentColor))

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SelectorCircleHueSaturationHSV(
            modifier = Modifier.padding(8.dp),
            hue = hue,
            saturation = saturation,
            selectionRadius = selectionRadius
        ) { h, s ->
            hue = h
            saturation = s

        }

        Column(modifier = Modifier.padding(8.dp)) {
            SliderCircleColorDisplayValueHSV(
                hue = hue,
                saturation = saturation,
                value = value,
                alpha = alpha,
                onValueChange = {
                    value = it
                },
                onAlphaChange = {
                    alpha = it
                }
            )

            ColorDisplayExposedSelectionMenu(
                color = currentColor,
                colorModel = colorModel,
                onColorModelChange = {
                    colorModel = it
                }
            )
        }
    }
}