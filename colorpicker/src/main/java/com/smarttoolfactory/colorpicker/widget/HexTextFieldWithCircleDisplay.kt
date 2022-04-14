package com.smarttoolfactory.colorpicker.widget

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.slider.CircleDisplay
import com.smarttoolfactory.colorpicker.util.colorToHex
import com.smarttoolfactory.colorpicker.util.colorToHexAlpha

@Composable
fun HexTextFieldWithCircleDisplay(
    modifier: Modifier = Modifier,
    circleModifier: Modifier = Modifier,
    color: Color,
    useAlpha: Boolean = false,
    onColorChange: (Color) -> Unit
) {

    var hexString by remember(color) {
        mutableStateOf(
            if (useAlpha) {
                colorToHexAlpha(color)
            } else {
                colorToHex(color)
            }
        )
    }

    Row(
        modifier = modifier.requiredHeightIn(min = 100.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        CircleDisplay(
            modifier = circleModifier
                .widthIn(min = 70.dp)
                .heightIn(min = 70.dp),
            color = color
        )
        Spacer(modifier = Modifier.width(10.dp))
        HexTextField(
            modifier = Modifier.weight(1f),
            useAlpha = useAlpha,
            hexString = hexString,
            onTextChange = {
                hexString = it
            },
            onColorChange = onColorChange
        )
    }
}