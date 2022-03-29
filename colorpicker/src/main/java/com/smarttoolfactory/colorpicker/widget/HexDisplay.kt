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
                onColorModelChange(ColorModel.values()[index])
            }
        )

        Row(
            modifier = Modifier
                .weight(1f)
                .background(Color.White),
            horizontalArrangement = Arrangement.Start
        ) {

            when (colorModel) {
                ColorModel.RGB -> {
                    ColorText(title = "R", value = color.red.fractionToRGBString())
                    Spacer(modifier = Modifier.width(20.dp))
                    ColorText(title = "G", value = color.green.fractionToRGBString())
                    Spacer(modifier = Modifier.width(20.dp))
                    ColorText(title = "B", value = color.blue.fractionToRGBString())
                    Spacer(modifier = Modifier.width(20.dp))
                    ColorText(title = "A", value = "${color.alpha.fractionToPercent()}%")
                }
                ColorModel.HSV -> {
                    val hsvArray = colorToHSV(color)
                    ColorText(title = "H", value = "${hsvArray[0].round()}")
                    Spacer(modifier = Modifier.width(20.dp))
                    ColorText(title = "S", "${hsvArray[1].fractionToPercent()}")
                    Spacer(modifier = Modifier.width(20.dp))
                    ColorText(title = "V", "${hsvArray[2].fractionToPercent()}")
                    Spacer(modifier = Modifier.width(20.dp))
                    ColorText(title = "A", value = "${color.alpha.fractionToPercent()}%")
                }

                ColorModel.HSL -> {
                    val hslArray = colorToHSL(color)
                    ColorText(title = "H", value = "${hslArray[0].round()}")
                    Spacer(modifier = Modifier.width(20.dp))
                    ColorText(title = "S", "${hslArray[1].fractionToPercent()}")
                    Spacer(modifier = Modifier.width(20.dp))
                    ColorText(title = "L", "${hslArray[2].fractionToPercent()}")
                    Spacer(modifier = Modifier.width(20.dp))
                    ColorText(title = "A", value = "${color.alpha.fractionToPercent()}%")
                }
            }
        }
    }
}

@Composable
private fun ColorText(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title.substring(0, 1),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp

        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = value, fontSize = 16.sp)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ExposedSelectionMenu(
    modifier: Modifier = Modifier,
    index: Int,
    options: List<String>,
    onSelected: (Int) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[index]) }

    ExposedDropdownMenuBox(
        modifier = Modifier.width(120.dp),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            modifier = Modifier.wrapContentWidth(),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            textStyle = TextStyle(
                fontWeight = FontWeight.W600,
                fontSize = 18.sp
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false

            }
        ) {
            options.forEachIndexed { index: Int, selectionOption: String ->
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                        onSelected(index)
                    }
                ) {
                    Text(text = selectionOption)
                }
            }
        }
    }
}
