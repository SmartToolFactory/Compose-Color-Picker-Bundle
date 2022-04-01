package com.smarttoolfactory.colorpicker.selector.gradient

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import com.smarttoolfactory.colorpicker.ui.GradientOffset

@Composable
internal fun RadialGradientSelection(
    size: Size,
    onGradientOffsetChange: (GradientOffset) -> Unit
) {

    var centerX by remember { mutableStateOf(.5f) }
    var centerY by remember { mutableStateOf(.5f) }
    var radius by remember { mutableStateOf(.5f) }

    onGradientOffsetChange(
        GradientOffset(
            start = Offset(
                x = size.width * centerX,
                y = size.height * centerY
            ),
            end = Offset(
                x = size.width * centerX,
                y = size.height * centerY
            )
        )
    )

    SliderWithPercent(
        modifier = Modifier.fillMaxWidth(),
        title = "CenterX",
        value = centerX
    ) {
        centerX = it
    }

    SliderWithPercent(
        modifier = Modifier.fillMaxWidth(),
        title = "CenterY",
        value = centerY
    ) {
        centerY = it
    }

    SliderWithPercent(
        modifier = Modifier.fillMaxWidth(),
        title = "Radius",
        value = radius
    ) {
        radius = it
    }
}