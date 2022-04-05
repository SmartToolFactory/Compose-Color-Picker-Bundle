package com.smarttoolfactory.composecolorpicker.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.dialog.ColorPickerRingDiamondGradientHSLDialog
import com.smarttoolfactory.colorpicker.dialog.ColorPickerRingRectGradientHSLDialog
import com.smarttoolfactory.colorpicker.dialog.ColorPickerRingRectGradientHSVDialog
import com.smarttoolfactory.colorpicker.model.BrushColor
import com.smarttoolfactory.colorpicker.model.GradientColorState
import com.smarttoolfactory.colorpicker.model.rememberGradientColorState
import com.smarttoolfactory.colorpicker.widget.drawChecker
import com.smarttoolfactory.composecolorpicker.ui.theme.backgroundColor

@Composable
fun ColorAndGradientPickerDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val size = DpSize(150.dp, 100.dp)

        var previousBrushColor by remember {
            mutableStateOf(
                BrushColor(
                    color = Color.hsl(0f, 0.5f, 0.5f)
                )
            )
        }

        var currentBrushColor by remember {
            mutableStateOf(previousBrushColor.copy())
        }

        Row {
            Box(
                modifier = Modifier
                    .size(size)
                    .fillMaxHeight()
                    .drawChecker(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
                    .background(
                        previousBrushColor.activeBrush,
                        shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
                    )
            )
            Box(
                modifier = Modifier
                    .size(size)
                    .drawChecker(RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
                    .background(
                        currentBrushColor.activeBrush,
                        shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
                    )
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        val buttonModifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)

        val gradientColorState = rememberGradientColorState(
            color = currentBrushColor.color,
            size = size
        )

        DialogRingDiamondGradientHSL(
            modifier = buttonModifier,
            initialBrushColor = currentBrushColor,
            gradientColorState = gradientColorState,
            onPreviousColorChange = {
                previousBrushColor = it.copy()
            },
            onCurrentColorChange = {
                currentBrushColor = it
            }
        )

        DialogRingRectGradientHSL(
            modifier = buttonModifier,
            initialBrushColor = currentBrushColor,
            gradientColorState = gradientColorState,
            onPreviousColorChange = {
                previousBrushColor = it.copy()
            },
            onCurrentColorChange = {
                currentBrushColor = it
            }
        )

        DialogRingRectGradientHSV(
            modifier = buttonModifier,
            initialBrushColor = currentBrushColor,
            gradientColorState = gradientColorState,
            onPreviousColorChange = {
                previousBrushColor = it.copy()
            },
            onCurrentColorChange = {
                currentBrushColor = it
            }
        )
    }
}


@Composable
private fun DialogRingDiamondGradientHSL(
    modifier: Modifier,
    initialBrushColor: BrushColor,
    gradientColorState: GradientColorState,
    onPreviousColorChange: (BrushColor) -> Unit,
    onCurrentColorChange: (BrushColor) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }

    OutlinedButton(
        modifier = modifier,
        onClick = { showDialog = !showDialog },
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent
        )

    ) {
        Text(text = "Ring-Diamond Gradient HSL Dialog")
    }

    if (showDialog) {
        onPreviousColorChange(initialBrushColor.copy())

        ColorPickerRingDiamondGradientHSLDialog(
            initialBrushColor = initialBrushColor,
            gradientColorState = gradientColorState
        ) {
            showDialog = !showDialog
            onCurrentColorChange(it)
        }
    }
}

@Composable
private fun DialogRingRectGradientHSL(
    modifier: Modifier,
    initialBrushColor: BrushColor,
    gradientColorState: GradientColorState,
    onPreviousColorChange: (BrushColor) -> Unit,
    onCurrentColorChange: (BrushColor) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }

    OutlinedButton(
        modifier = modifier,
        onClick = { showDialog = !showDialog },
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent
        )

    ) {
        Text(text = "Ring-Rect Gradient HSL Dialog")
    }

    if (showDialog) {
        onPreviousColorChange(initialBrushColor.copy())

        ColorPickerRingRectGradientHSLDialog(
            initialBrushColor = initialBrushColor,
            gradientColorState = gradientColorState
        ) {
            showDialog = !showDialog
            onCurrentColorChange(it)
        }
    }
}

@Composable
private fun DialogRingRectGradientHSV(
    modifier: Modifier,
    initialBrushColor: BrushColor,
    gradientColorState: GradientColorState,
    onPreviousColorChange: (BrushColor) -> Unit,
    onCurrentColorChange: (BrushColor) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }

    OutlinedButton(
        modifier = modifier,
        onClick = { showDialog = !showDialog },
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent
        )

    ) {
        Text(text = "Ring-Rect Gradient HSV Dialog")
    }

    if (showDialog) {
        onPreviousColorChange(initialBrushColor.copy())

        ColorPickerRingRectGradientHSVDialog(
            initialBrushColor = initialBrushColor,
            gradientColorState = gradientColorState
        ) {
            showDialog = !showDialog
            onCurrentColorChange(it)
        }
    }
}

