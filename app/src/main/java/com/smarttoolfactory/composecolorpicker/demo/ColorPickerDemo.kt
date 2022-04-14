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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.dialog.*
import com.smarttoolfactory.colorpicker.util.colorToHexAlpha
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
            mutableStateOf(previousColor.copy())
        }

        var hexString by remember { mutableStateOf(colorToHexAlpha(color)) }

        Spacer(modifier = Modifier.height(30.dp))
        ColorDisplayRoundedRect(
            modifier = Modifier.fillMaxWidth(.5f),
            initialColor = previousColor,
            currentColor = color
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = hexString,
            fontSize = 20.sp,
            color = color,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .background(color = Color(0x66000000), shape = RoundedCornerShape(12.dp))
                .padding(6.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        val buttonModifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)

        // Ring Hue, Diamond Saturation-Lightness selector HSL Picker
        DialogRingDiamondHSL(
            modifier = buttonModifier,
            color = color,
            onPreviousColorChange = {
                previousColor = it
            },
            onCurrentColorChange = { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }
        )

        // Ring Hue, Diamond Saturation-Lightness selector HSL and HEX Picker
        DialogRingDiamondHEX(
            modifier = buttonModifier,
            color = color,
            onPreviousColorChange = {
                previousColor = it
            },
            onCurrentColorChange = { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }
        )

        // Ring Hue, Rect Saturation-Lightness selector HSL Picker
        DialogRingRectHSL(
            modifier = buttonModifier,
            color = color,
            onPreviousColorChange = {
                previousColor = it
            },
            onCurrentColorChange = { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }
        )

        // Ring Hue, Rect Saturation-Value selector HSV Picker
        DialogRingRectHSV(
            modifier = buttonModifier,
            color = color,
            onPreviousColorChange = {
                previousColor = it
            },
            onCurrentColorChange = { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }
        )

        // Ring Hue, Rect Saturation-Value selector HexHSV Picker
        DialogRingHexHSV(
            modifier = buttonModifier,
            color = color,
            onPreviousColorChange = {
                previousColor = it
            },
            onCurrentColorChange = { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }
        )

        DialogCircleHSV(
            modifier = buttonModifier,
            color = color,
            onPreviousColorChange = {
                previousColor = it
            },
            onCurrentColorChange = { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }
        )

        // Saturation Value Selector Dialogs
        DialogRectSaturationValueHSV(
            modifier = buttonModifier,
            color = color,
            onPreviousColorChange = {
                previousColor = it
            },
            onCurrentColorChange = { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }
        )

        DialogRectSaturationLightnessHSL(
            modifier = buttonModifier,
            color = color,
            onPreviousColorChange = {
                previousColor = it
            },
            onCurrentColorChange = { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }
        )

        // Hue + Saturation/Value/Lightness Selector Dialogs
        DialogRectHueSaturationHSV(
            modifier = buttonModifier,
            color = color,
            onPreviousColorChange = {
                previousColor = it
            },
            onCurrentColorChange = { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }
        )

        DialogRectHueValueHSV(
            modifier = buttonModifier,
            color = color,
            onPreviousColorChange = {
                previousColor = it
            },
            onCurrentColorChange = { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }
        )

        DialogRectHueSaturationHSL(
            modifier = buttonModifier,
            color = color,
            onPreviousColorChange = {
                previousColor = it
            },
            onCurrentColorChange = { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }
        )

        DialogRectHueLightnessHSL(
            modifier = buttonModifier,
            color = color,
            onPreviousColorChange = {
                previousColor = it
            },
            onCurrentColorChange = { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }
        )
    }
}

@Composable
private fun DialogRingDiamondHSL(
    modifier: Modifier,
    color: Color,
    onPreviousColorChange: (Color) -> Unit,
    onCurrentColorChange: (Color, String) -> Unit

) {
    var showDialog by remember { mutableStateOf(false) }

    OutlinedButton(
        modifier = modifier,
        onClick = { showDialog = !showDialog },
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent
        )

    ) {
        Text(text = "Hue Ring-Diamond HSL Dialog")
    }

    if (showDialog) {
        onPreviousColorChange(color.copy())

        ColorPickerRingDiamondHSLDialog(color) { colorChange, hexChange ->
            showDialog = !showDialog
            onCurrentColorChange(colorChange, hexChange)
        }
    }
}

@Composable
private fun DialogRingDiamondHEX(
    modifier: Modifier,
    color: Color,
    onPreviousColorChange: (Color) -> Unit,
    onCurrentColorChange: (Color, String) -> Unit

) {
    var showDialog by remember { mutableStateOf(false) }

    OutlinedButton(
        modifier = modifier,
        onClick = { showDialog = !showDialog },
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent
        )

    ) {
        Text(text = "Hue Ring-Diamond HEX Dialog")
    }

    if (showDialog) {
        onPreviousColorChange(color.copy())

        ColorPickerRingDiamondHEXDialog(color) { colorChange, hexChange ->
            showDialog = !showDialog
            onCurrentColorChange(colorChange, hexChange)
        }
    }
}

@Composable
private fun DialogRingRectHSL(
    modifier: Modifier,
    color: Color,
    onPreviousColorChange: (Color) -> Unit,
    onCurrentColorChange: (Color, String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    OutlinedButton(
        modifier = modifier,
        onClick = { showDialog = !showDialog },
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent
        )

    ) {
        Text(text = "Hue Ring-Rect HSL Dialog")
    }

    if (showDialog) {
        onPreviousColorChange(color.copy())

        ColorPickerRingRectHSLDialog(color) { colorChange, hexChange ->
            showDialog = !showDialog
            onCurrentColorChange(colorChange, hexChange)
        }
    }
}

@Composable
private fun DialogRingRectHSV(
    modifier: Modifier,
    color: Color,
    onPreviousColorChange: (Color) -> Unit,
    onCurrentColorChange: (Color, String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    OutlinedButton(
        modifier = modifier,
        onClick = { showDialog = !showDialog },
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent
        )

    ) {
        Text(text = "Hue Ring-Rect HSV Dialog")
    }

    if (showDialog) {
        onPreviousColorChange(color.copy())

        ColorPickerRingRectHSVDialog(color) { colorChange, hexChange ->
            showDialog = !showDialog
            onCurrentColorChange(colorChange, hexChange)
        }
    }
}

@Composable
private fun DialogRingHexHSV(
    modifier: Modifier,
    color: Color,
    onPreviousColorChange: (Color) -> Unit,
    onCurrentColorChange: (Color, String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    OutlinedButton(
        modifier = modifier,
        onClick = { showDialog = !showDialog },
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent
        )

    ) {
        Text(text = "Hue Ring-Hex HSV Dialog")
    }

    if (showDialog) {
        onPreviousColorChange(color.copy())

        ColorPickerRingHexHSVDialog(
            initialColor = color
        ) { colorChange, hexChange ->
            showDialog = !showDialog
            onCurrentColorChange(colorChange, hexChange)
        }
    }
}

@Composable
private fun DialogCircleHSV(
    modifier: Modifier,
    color: Color,
    onPreviousColorChange: (Color) -> Unit,
    onCurrentColorChange: (Color, String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    OutlinedButton(
        modifier = modifier,
        onClick = { showDialog = !showDialog },
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent
        )

    ) {
        Text(text = "Hue Circle HSV Dialog")
    }

    if (showDialog) {
        onPreviousColorChange(color.copy())

        ColorPickerCircleHSVDialog(
            initialColor = color
        ) { colorChange, hexChange ->
            showDialog = !showDialog
            onCurrentColorChange(colorChange, hexChange)
        }
    }
}

@Composable
private fun DialogRectSaturationValueHSV(
    modifier: Modifier,
    color: Color,
    onPreviousColorChange: (Color) -> Unit,
    onCurrentColorChange: (Color, String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    OutlinedButton(
        modifier = modifier,
        onClick = { showDialog = !showDialog },
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent
        )

    ) {
        Text(text = "Saturation-Value Rect HSV Dialog")
    }

    if (showDialog) {
        onPreviousColorChange(color.copy())

        ColorPickerSVRectHSVDialog(
            initialColor = color
        ) { colorChange, hexChange ->
            showDialog = !showDialog
            onCurrentColorChange(colorChange, hexChange)
        }
    }
}

@Composable
private fun DialogRectSaturationLightnessHSL(
    modifier: Modifier,
    color: Color,
    onPreviousColorChange: (Color) -> Unit,
    onCurrentColorChange: (Color, String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    OutlinedButton(
        modifier = modifier,
        onClick = { showDialog = !showDialog },
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent
        )
    ) {
        Text(text = "Saturation-Lightness Rect HSL Dialog")
    }

    if (showDialog) {
        onPreviousColorChange(color.copy())

        ColorPickerSLRectHSLDialog(
            initialColor = color
        ) { colorChange, hexChange ->
            showDialog = !showDialog
            onCurrentColorChange(colorChange, hexChange)
        }
    }
}

@Composable
private fun DialogRectHueSaturationHSV(
    modifier: Modifier,
    color: Color,
    onPreviousColorChange: (Color) -> Unit,
    onCurrentColorChange: (Color, String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    OutlinedButton(
        modifier = modifier,
        onClick = { showDialog = !showDialog },
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent
        )
    ) {
        Text(text = "Hue-Saturation Rect HSV Dialog")
    }

    if (showDialog) {
        onPreviousColorChange(color.copy())

        ColorPickerHSRectHSVDialog(
            initialColor = color
        ) { colorChange, hexChange ->
            showDialog = !showDialog
            onCurrentColorChange(colorChange, hexChange)
        }
    }
}

@Composable
private fun DialogRectHueValueHSV(
    modifier: Modifier,
    color: Color,
    onPreviousColorChange: (Color) -> Unit,
    onCurrentColorChange: (Color, String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    OutlinedButton(
        modifier = modifier,
        onClick = { showDialog = !showDialog },
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent
        )
    ) {
        Text(text = "Hue-Value Rect HSV Dialog")
    }

    if (showDialog) {
        onPreviousColorChange(color.copy())

        ColorPickerHVRectHSVDialog(
            initialColor = color
        ) { colorChange, hexChange ->
            showDialog = !showDialog
            onCurrentColorChange(colorChange, hexChange)
        }
    }
}

@Composable
private fun DialogRectHueSaturationHSL(
    modifier: Modifier,
    color: Color,
    onPreviousColorChange: (Color) -> Unit,
    onCurrentColorChange: (Color, String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    OutlinedButton(
        modifier = modifier,
        onClick = { showDialog = !showDialog },
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent
        )
    ) {
        Text(text = "Hue-Saturation Rect HSL Dialog")
    }

    if (showDialog) {
        onPreviousColorChange(color.copy())

        ColorPickerHSRectHSLDialog(
            initialColor = color
        ) { colorChange, hexChange ->
            showDialog = !showDialog
            onCurrentColorChange(colorChange, hexChange)
        }
    }
}

@Composable
private fun DialogRectHueLightnessHSL(
    modifier: Modifier,
    color: Color,
    onPreviousColorChange: (Color) -> Unit,
    onCurrentColorChange: (Color, String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    OutlinedButton(
        modifier = modifier,
        onClick = { showDialog = !showDialog },
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent
        )
    ) {
        Text(text = "Hue-Lightness Rect HSL Dialog")
    }

    if (showDialog) {
        onPreviousColorChange(color.copy())

        ColorPickerHLRectHSLDialog(
            initialColor = color
        ) { colorChange, hexChange ->
            showDialog = !showDialog
            onCurrentColorChange(colorChange, hexChange)
        }
    }
}

