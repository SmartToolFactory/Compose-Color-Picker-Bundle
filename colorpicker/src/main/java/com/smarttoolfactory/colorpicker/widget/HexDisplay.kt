package com.smarttoolfactory.colorpicker.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.model.ColorModel
import com.smarttoolfactory.colorpicker.model.CompositeColor
import com.smarttoolfactory.colorpicker.util.*

@Composable
fun HexDisplay(compositeColor: CompositeColor, colorModel: ColorModel) {

    val options = listOf("HEX", "HSV", "HSL")
    var index by remember { mutableStateOf(0) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
    ) {
        ExposedSelectionMenu(
            index = index,
            options = options,
            onSelected = {
                index = it
            }
        )
    }
}

@Composable
fun HexDisplay(color: Color, colorModel: ColorModel, onColorModelChange: (ColorModel) -> Unit) {

    val options = listOf("HEX", "HSV", "HSL")

    var selectedIndex by remember {
        mutableStateOf(
            when (colorModel) {
                ColorModel.RGB -> 0
                ColorModel.HSV -> 1
                ColorModel.HSL -> 2
            }
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(2.dp)
    ) {
        ExposedSelectionMenu(
            modifier = Modifier.width(100.dp),
            index = selectedIndex,
            options = options,
            onSelected = {
                selectedIndex = it
                onColorModelChange(ColorModel.values()[selectedIndex])
            }
        )

        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.Start
        ) {

            when (colorModel) {
                ColorModel.RGB -> {
                    ColorText(
                        title = "R",
                        value = color.red.fractionToRGBString(),
                        modifier = Modifier.weight(1f)
                    )
                    ColorText(
                        title = "G", value = color.green.fractionToRGBString(),
                        modifier = Modifier.weight(1f)
                    )
                    ColorText(
                        title = "B", value = color.blue.fractionToRGBString(),
                        modifier = Modifier.weight(1f)
                    )
                    ColorText(
                        title = "A", value = "${color.alpha.fractionToPercent()}%",
                        modifier = Modifier.weight(1f)
                    )
                }
                ColorModel.HSV -> {
                    val hsvArray = colorToHSV(color)
                    ColorText(
                        title = "H", value = "${hsvArray[0].round()}°",
                        modifier = Modifier.weight(1f)
                    )
                    ColorText(
                        title = "S", value = "${hsvArray[1].fractionToPercent()}%",
                        modifier = Modifier.weight(1f)
                    )
                    ColorText(
                        title = "V", value = "${hsvArray[2].fractionToPercent()}%",
                        modifier = Modifier.weight(1f)
                    )
                    ColorText(
                        title = "A", value = "${color.alpha.fractionToPercent()}%",
                        modifier = Modifier.weight(1f)
                    )
                }

                ColorModel.HSL -> {
                    val hslArray = colorToHSL(color)
                    ColorText(
                        title = "H", value = "${hslArray[0].round()}°",
                        modifier = Modifier.weight(1f)
                    )
                    ColorText(
                        title = "S", value = "${hslArray[1].fractionToPercent()}%",
                        modifier = Modifier.weight(1f)
                    )
                    ColorText(
                        title = "L", value = "${hslArray[2].fractionToPercent()}%",
                        modifier = Modifier.weight(1f)
                    )
                    ColorText(
                        title = "A", value = "${color.alpha.fractionToPercent()}%",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun ColorText(modifier: Modifier = Modifier, title: String, value: String) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title.substring(0, 1),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp

        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, fontSize = 14.sp)
    }
}