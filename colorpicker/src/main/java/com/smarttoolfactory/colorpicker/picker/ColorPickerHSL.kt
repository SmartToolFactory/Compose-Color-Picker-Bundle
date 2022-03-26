package com.smarttoolfactory.colorpicker.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.argbToHex
import com.smarttoolfactory.colorpicker.colorToHSL
import com.smarttoolfactory.colorpicker.model.ColorHSL
import com.smarttoolfactory.colorpicker.model.ColorModel
import com.smarttoolfactory.colorpicker.selector.HueSelectorRing
import com.smarttoolfactory.colorpicker.selector.SLSelectorFromHSLDiamond
import com.smarttoolfactory.colorpicker.slider.CompositeSliderPanel
import com.smarttoolfactory.colorpicker.widget.drawChecker

@Composable
fun ColorPickerHSL(modifier: Modifier = Modifier, initialColor: Color) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val hslArray = colorToHSL(initialColor)
        var hue by remember { mutableStateOf(hslArray[0]) }
        var saturation by remember { mutableStateOf(hslArray[1]) }
        var lightness by remember { mutableStateOf(hslArray[2]) }
        var alpha by remember { mutableStateOf(initialColor.alpha) }

        val currentColor =
            Color.hsl(hue = hue, saturation = saturation, lightness = lightness, alpha = alpha)

        var hexString by remember { mutableStateOf("") }

        hexString = argbToHex(
            currentColor.alpha,
            currentColor.red,
            currentColor.green,
            currentColor.blue
        )

        var inputColorModel by remember { mutableStateOf(ColorModel.HSL) }

        Spacer(modifier = Modifier.height(10.dp))

        // Initial and Current Colors
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp, vertical = 10.dp)
        ) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .background(
                        initialColor,
                        shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
                    )
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .drawChecker(RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
                    .background(
                        currentColor,
                        shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
                    )
            )
        }

        // ColorWheel for hue selection
        // SaturationDiamond for saturation and lightness selections
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {

            HueSelectorRing(
                modifier = Modifier
                    .width(350.dp)
                    .aspectRatio(1f),
                hue = hue,
                selectionRadius = 8.dp
            ) { hueChange ->
                hue = hueChange

            }

            Column {
                SLSelectorFromHSLDiamond(
                    modifier = Modifier.size(200.dp),
                    hue = hue,
                    saturation = saturation,
                    lightness = lightness,
                    selectionRadius = 8.dp
                ) { s, l ->
                    saturation = s
                    lightness = l
                }
            }
        }

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


        CompositeSliderPanel(
            modifier = Modifier.widthIn(340.dp),
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

        Text(
            text = hexString,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = currentColor
        )

    }
}
