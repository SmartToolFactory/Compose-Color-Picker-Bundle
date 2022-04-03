package com.smarttoolfactory.colorpicker.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.model.ColorModel
import com.smarttoolfactory.colorpicker.ui.Grey400
import com.smarttoolfactory.colorpicker.ui.Grey600

/**
 * Tab Section for changing between HSL, HSV and RGB color models.
 * @param colorModel current color model.
 * @param onColorModelChange callback that is triggered when user touches buttons.
 */
@Composable
fun ColorModelChangeTabRow(
    modifier: Modifier = Modifier,
    colorModel: ColorModel,
    onColorModelChange: (ColorModel) -> Unit
) {
    var selectedIndex by remember {
        mutableStateOf(
            when (colorModel) {
                ColorModel.HSL -> 0
                ColorModel.HSV -> 1
                ColorModel.RGB -> 2
            }
        )
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        modelList.forEachIndexed { index, s ->
            Box(modifier = Modifier
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    onClick = {
                        selectedIndex = index
                        val newColorModel = when (selectedIndex) {
                            0 -> ColorModel.HSL
                            1 -> ColorModel.HSV
                            else -> ColorModel.RGB
                        }
                        onColorModelChange(newColorModel)
                    }
                )
                .padding(10.dp)
            ) {
                Text(
                    text = s,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = if (index == selectedIndex) Grey400 else Grey600
                )
            }
        }
    }
}

private val modelList = listOf("HSL", "HSV", "RGB")
