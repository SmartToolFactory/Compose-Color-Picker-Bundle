package com.smarttoolfactory.composecolorpicker.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.dialog.ColorPickerRingDiamondHSLDialog
import com.smarttoolfactory.colorpicker.dialog.ColorPickerRingRectHSVDialog
import com.smarttoolfactory.colorpicker.widget.ColorDisplayRoundedRect
import com.smarttoolfactory.composecolorpicker.ui.theme.backgroundColor

@Composable
fun ColorPickerDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var previousColor by remember {
            mutableStateOf(
                Color.hsl(0f, 0.5f, 0.5f)
            )
        }
        var color by remember {
            mutableStateOf(Color.hsl(0f, 0.5f, 0.5f))
        }

        Spacer(modifier = Modifier.height(30.dp))
        ColorDisplayRoundedRect(
            modifier = Modifier.fillMaxWidth(.5f),
            initialColor = previousColor,
            currentColor = color
        )

        val buttonModifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)

        var showColorDialogRingDiamondHSL by remember { mutableStateOf(false) }

        OutlinedButton(
            modifier = buttonModifier,
            onClick = { showColorDialogRingDiamondHSL = !showColorDialogRingDiamondHSL },
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = Color.Transparent
            )

        ) {
            Text(text = "Hue Ring-Diamond HSL Dialog")
        }

        if (showColorDialogRingDiamondHSL) {
            previousColor = color.copy()

            ColorPickerRingDiamondHSLDialog(color) {
                showColorDialogRingDiamondHSL = !showColorDialogRingDiamondHSL
                color = it
            }
        }


        var showColorDialogRingRectHSV by remember { mutableStateOf(false) }

        OutlinedButton(
            modifier = buttonModifier,
            onClick = { showColorDialogRingRectHSV = !showColorDialogRingRectHSV },
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = Color.Transparent
            )

        ) {
            Text(text = "Hue Ring-Rect HSV Dialog")
        }

        if (showColorDialogRingRectHSV) {
            previousColor = color.copy()

            ColorPickerRingRectHSVDialog(color) {
                showColorDialogRingRectHSV = !showColorDialogRingRectHSV
                color = it
            }
        }
    }
}

