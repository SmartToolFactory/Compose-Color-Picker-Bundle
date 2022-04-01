package com.smarttoolfactory.colorpicker.selector.gradient

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset

@Composable
internal fun SweepGradientSelection(
    onCenterChange: (Offset) -> Unit
) {
    var centerX by remember { mutableStateOf(.5f) }
    var centerY by remember { mutableStateOf(.5f) }

    onCenterChange(Offset(centerX, centerY))

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
}

