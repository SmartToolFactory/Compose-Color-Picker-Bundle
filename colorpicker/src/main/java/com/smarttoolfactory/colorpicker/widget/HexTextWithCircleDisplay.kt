package com.smarttoolfactory.colorpicker.widget

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.slider.CircleDisplay

@Composable
fun HexTextWithCircleDisplay(
    modifier: Modifier = Modifier,
    circleModifier: Modifier = Modifier,
    color: Color,
    hexString: String,
    onTextChange: (String) -> Unit,
    onColorChange: (Color) -> Unit
) {

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
        HexAlphaTextField(
            modifier = Modifier.weight(1f),
            hexString = hexString,
            onTextChange = onTextChange,
            onColorChange = onColorChange
        )
    }
}