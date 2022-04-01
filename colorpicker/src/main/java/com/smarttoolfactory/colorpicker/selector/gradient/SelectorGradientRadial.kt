package com.smarttoolfactory.colorpicker.selector.gradient

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset

@Composable
internal fun RadialGradientSelection(
    onRadialDimensionsChange: (Offset, Float) -> Unit
) {

    var centerX by remember { mutableStateOf(.5f) }
    var centerY by remember { mutableStateOf(.5f) }
    var radius by remember { mutableStateOf(.5f) }

    onRadialDimensionsChange(Offset(centerX, centerY), radius)

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