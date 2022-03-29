package com.smarttoolfactory.colorpicker.picker

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.model.ColorHSL
import com.smarttoolfactory.colorpicker.model.ColorModel
import com.smarttoolfactory.colorpicker.selector.HueSelectorRing
import com.smarttoolfactory.colorpicker.selector.SaturationLightnessSelectorDiamondHSL
import com.smarttoolfactory.colorpicker.slider.CompositeSliderPanel
import com.smarttoolfactory.colorpicker.util.argbToHex
import com.smarttoolfactory.colorpicker.util.colorToHSL
import com.smarttoolfactory.colorpicker.widget.ColorDisplayRoundedRect
import com.smarttoolfactory.colorpicker.widget.HexDisplay

@Composable
fun ColorPickerRingDiamondHSL(
    modifier: Modifier = Modifier,
    initialColor: Color,
    outerRadiusFraction: Float = .9f,
    innerRadiusFraction: Float = .6f,
    onColorChange: (Color) -> Unit
) {

    var inputColorModel by remember { mutableStateOf(ColorModel.HSL) }

    val hslArray = colorToHSL(initialColor)

    var hue by remember { mutableStateOf(hslArray[0]) }
    var saturation by remember { mutableStateOf(hslArray[1]) }
    var lightness by remember { mutableStateOf(hslArray[2]) }
    var alpha by remember { mutableStateOf(initialColor.alpha) }

    val currentColor =
        Color.hsl(hue = hue, saturation = saturation, lightness = lightness, alpha = alpha)

    onColorChange(currentColor)

    var hexString by remember(currentColor) {
        mutableStateOf(
            argbToHex(
                currentColor.alpha,
                currentColor.red,
                currentColor.green,
                currentColor.blue
            )
        )
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        // Initial and Current Colors
        ColorDisplayRoundedRect(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp, vertical = 10.dp),
            initialColor = initialColor,
            currentColor = currentColor
        )


        Box(contentAlignment = Alignment.Center) {

            // Ring Shaped Hue Selector
            HueSelectorRing(
                modifier = Modifier.fillMaxWidth(1f),
                hue = hue,
                outerRadiusFraction = outerRadiusFraction,
                innerRadiusFraction = innerRadiusFraction,
                selectionRadius = 8.dp
            ) { hueChange ->
                hue = hueChange
            }

            // Diamond Shaped Saturation and Lightness Selector
            SaturationLightnessSelectorDiamondHSL(
                modifier = Modifier.fillMaxWidth(innerRadiusFraction * .9f),
                hue = hue,
                saturation = saturation,
                lightness = lightness,
                selectionRadius = 8.dp
            ) { s, l ->
                saturation = s
                lightness = l
            }
        }

        // HSL-HSV-RGB Color Model Change Tabs
        Row(
            modifier = Modifier.width(350.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TextButton(
                onClick = { inputColorModel = ColorModel.HSL },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "HSL", fontWeight = FontWeight.Bold)
            }
            TextButton(
                onClick = { inputColorModel = ColorModel.HSV },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "HSV", fontWeight = FontWeight.Bold)
            }

            TextButton(
                onClick = { inputColorModel = ColorModel.RGB },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "RGB", fontWeight = FontWeight.Bold)
            }
        }

        // HSL-HSV-RGB Sliders
        CompositeSliderPanel(
            compositeColor = ColorHSL(
                hue = hue,
                saturation = saturation,
                lightness = lightness,
                alpha = alpha
            ),
            onColorChange = {
                (it as? ColorHSL)?.let { color ->
                    hue = color.hue
                    saturation = color.saturation
                    lightness = color.lightness
                    alpha = color.alpha
                    hexString = color.argbHexString
                }

            },
            showAlphaSlider = true,
            inputColorModel = inputColorModel,
            outputColorModel = ColorModel.HSL
        )
    }
}
