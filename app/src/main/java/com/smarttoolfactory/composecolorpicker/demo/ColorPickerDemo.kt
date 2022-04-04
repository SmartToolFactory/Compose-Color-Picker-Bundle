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
import com.smarttoolfactory.colorpicker.dialog.*
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

        Spacer(modifier = Modifier.height(30.dp))
        ColorDisplayRoundedRect(
            modifier = Modifier.fillMaxWidth(.5f),
            initialColor = previousColor,
            currentColor = color
        )

        val buttonModifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)

        // Ring Hue, Diamond Saturation-Lightness selector HSL Picker
        DialogRingHSL(
            modifier = buttonModifier,
            color = color,
            onPreviousColorChange = {
                previousColor = it
            },
            onCurrentColorChange = {
                color = it
            }
        )

        // Ring Hue, Rect Saturation-Value selector HSV Picker
        DialogRingHSV(
            modifier = buttonModifier,
            color = color,
            onPreviousColorChange = {
                previousColor = it
            },
            onCurrentColorChange = {
                color = it
            }
        )

        DialogCircleHSV(
            modifier = buttonModifier,
            color = color,
            onPreviousColorChange = {
                previousColor = it
            },
            onCurrentColorChange = {
                color = it
            }
        )

        // Saturation Value Selector Dialogs
        DialogRectSaturationValueHSV(
            modifier = buttonModifier,
            color = color,
            onPreviousColorChange = {
                previousColor = it
            },
            onCurrentColorChange = {
                color = it
            }
        )

        DialogRectSaturationLightnessHSL(
            modifier = buttonModifier,
            color = color,
            onPreviousColorChange = {
                previousColor = it
            },
            onCurrentColorChange = {
                color = it
            }
        )

        // Hue + Saturation/Value/Lightness Selector Dialogs
        DialogRectHueSaturationHSV(
            modifier = buttonModifier,
            color = color,
            onPreviousColorChange = {
                previousColor = it
            },
            onCurrentColorChange = {
                color = it
            }
        )

        DialogRectHueValueHSV(
            modifier = buttonModifier,
            color = color,
            onPreviousColorChange = {
                previousColor = it
            },
            onCurrentColorChange = {
                color = it
            }
        )

        DialogRectHueSaturationHSL(
            modifier = buttonModifier,
            color = color,
            onPreviousColorChange = {
                previousColor = it
            },
            onCurrentColorChange = {
                color = it
            }
        )

        DialogRectHueLightnessHSL(
            modifier = buttonModifier,
            color = color,
            onPreviousColorChange = {
                previousColor = it
            },
            onCurrentColorChange = {
                color = it
            }
        )
    }
}

@Composable
private fun DialogRingHSL(
    modifier: Modifier,
    color: Color,
    onPreviousColorChange: (Color) -> Unit,
    onCurrentColorChange: (Color) -> Unit,

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

        ColorPickerRingDiamondHSLDialog(color) {
            showDialog = !showDialog
            onCurrentColorChange(it)
        }
    }
}

@Composable
private fun DialogRingHSV(
    modifier: Modifier,
    color: Color,
    onPreviousColorChange: (Color) -> Unit,
    onCurrentColorChange: (Color) -> Unit,

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

        ColorPickerRingRectHSVDialog(color) {
            showDialog = !showDialog
            onCurrentColorChange(it)
        }
    }
}

@Composable
private fun DialogCircleHSV(
    modifier: Modifier,
    color: Color,
    onPreviousColorChange: (Color) -> Unit,
    onCurrentColorChange: (Color) -> Unit,

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
        ) {
            showDialog = !showDialog
            onCurrentColorChange(it)
        }
    }
}

@Composable
private fun DialogRectSaturationValueHSV(
    modifier: Modifier,
    color: Color,
    onPreviousColorChange: (Color) -> Unit,
    onCurrentColorChange: (Color) -> Unit,

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
        ) {
            showDialog = !showDialog
            onCurrentColorChange(it)
        }
    }
}

@Composable
private fun DialogRectSaturationLightnessHSL(
    modifier: Modifier,
    color: Color,
    onPreviousColorChange: (Color) -> Unit,
    onCurrentColorChange: (Color) -> Unit,

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
        ) {
            showDialog = !showDialog
            onCurrentColorChange(it)
        }
    }
}

@Composable
private fun DialogRectHueSaturationHSV(
    modifier: Modifier,
    color: Color,
    onPreviousColorChange: (Color) -> Unit,
    onCurrentColorChange: (Color) -> Unit,

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
        ) {
            showDialog = !showDialog
            onCurrentColorChange(it)
        }
    }
}

@Composable
private fun DialogRectHueValueHSV(
    modifier: Modifier,
    color: Color,
    onPreviousColorChange: (Color) -> Unit,
    onCurrentColorChange: (Color) -> Unit,

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
        ) {
            showDialog = !showDialog
            onCurrentColorChange(it)
        }
    }
}

@Composable
private fun DialogRectHueSaturationHSL(
    modifier: Modifier,
    color: Color,
    onPreviousColorChange: (Color) -> Unit,
    onCurrentColorChange: (Color) -> Unit,

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
        ) {
            showDialog = !showDialog
            onCurrentColorChange(it)
        }
    }
}

@Composable
private fun DialogRectHueLightnessHSL(
    modifier: Modifier,
    color: Color,
    onPreviousColorChange: (Color) -> Unit,
    onCurrentColorChange: (Color) -> Unit,

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
        ) {
            showDialog = !showDialog
            onCurrentColorChange(it)
        }
    }
}

