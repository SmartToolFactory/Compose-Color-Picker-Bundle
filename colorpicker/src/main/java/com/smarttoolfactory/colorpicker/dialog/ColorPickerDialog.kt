package com.smarttoolfactory.colorpicker.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.smarttoolfactory.colorpicker.picker.*
import com.smarttoolfactory.colorpicker.ui.Blue400
import com.smarttoolfactory.colorpicker.util.colorToHex

@Composable
fun ColorPickerRingDiamondHSLDialog(
    initialColor: Color,
    ringOuterRadiusFraction: Float = .9f,
    ringInnerRadiusFraction: Float = .6f,
    ringBackgroundColor: Color = Color.Transparent,
    ringBorderStrokeColor: Color = Color.Black,
    ringBorderStrokeWidth: Dp = 4.dp,
    selectionRadius: Dp = 8.dp,
    onDismiss: (Color, String) -> Unit
) {

    var color by remember { mutableStateOf(initialColor.copy()) }
    var hexString by remember { mutableStateOf(colorToHex(color)) }

    Dialog(
        onDismissRequest = {
            onDismiss(color, hexString)
        }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            ColorPickerRingDiamondHSL(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color(0xcc212121), shape = RoundedCornerShape(5.dp))
                    .padding(horizontal = 10.dp, vertical = 2.dp),
                initialColor = initialColor,
                ringOuterRadiusFraction = ringOuterRadiusFraction,
                ringInnerRadiusFraction = ringInnerRadiusFraction,
                ringBackgroundColor = ringBackgroundColor,
                ringBorderStrokeColor = ringBorderStrokeColor,
                ringBorderStrokeWidth = ringBorderStrokeWidth,
                selectionRadius = selectionRadius
            ) { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }

            FloatingActionButton(
                onClick = {
                    onDismiss(color, hexString)
                },
                backgroundColor = Color.Black
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    tint = Blue400
                )
            }
        }
    }
}

@Composable
fun ColorPickerRingDiamondHEXDialog(
    initialColor: Color,
    ringOuterRadiusFraction: Float = .9f,
    ringInnerRadiusFraction: Float = .6f,
    ringBackgroundColor: Color = Color.Transparent,
    ringBorderStrokeColor: Color = Color.Black,
    ringBorderStrokeWidth: Dp = 4.dp,
    selectionRadius: Dp = 8.dp,
    onDismiss: (Color, String) -> Unit
) {

    var color by remember { mutableStateOf(initialColor.copy()) }
    var hexString by remember { mutableStateOf(colorToHex(color)) }

    Dialog(
        onDismissRequest = {
            onDismiss(color, hexString)
        }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            ColorPickerRingDiamondHEX(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color(0xcc212121), shape = RoundedCornerShape(5.dp))
                    .padding(horizontal = 10.dp, vertical = 2.dp),
                initialColor = initialColor,
                ringOuterRadiusFraction = ringOuterRadiusFraction,
                ringInnerRadiusFraction = ringInnerRadiusFraction,
                ringBackgroundColor = ringBackgroundColor,
                ringBorderStrokeColor = ringBorderStrokeColor,
                ringBorderStrokeWidth = ringBorderStrokeWidth,
                selectionRadius = selectionRadius
            ) { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }

            FloatingActionButton(
                onClick = {
                    onDismiss(color, hexString)
                },
                backgroundColor = Color.Black
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    tint = Blue400
                )
            }
        }
    }
}

@Composable
fun ColorPickerRingRectHSLDialog(
    initialColor: Color,
    ringOuterRadiusFraction: Float = .9f,
    ringInnerRadiusFraction: Float = .6f,
    ringBackgroundColor: Color = Color.Transparent,
    ringBorderStrokeColor: Color = Color.Black,
    ringBorderStrokeWidth: Dp = 4.dp,
    selectionRadius: Dp = 8.dp,
    onDismiss: (Color, String) -> Unit
) {

    var color by remember { mutableStateOf(initialColor.copy()) }
    var hexString by remember { mutableStateOf(colorToHex(color)) }

    Dialog(
        onDismissRequest = {
            onDismiss(color, hexString)
        }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            ColorPickerRingRectHSL(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color(0xcc212121), shape = RoundedCornerShape(5.dp))
                    .padding(horizontal = 10.dp, vertical = 2.dp),
                initialColor = initialColor,
                ringOuterRadiusFraction = ringOuterRadiusFraction,
                ringInnerRadiusFraction = ringInnerRadiusFraction,
                ringBackgroundColor = ringBackgroundColor,
                ringBorderStrokeColor = ringBorderStrokeColor,
                ringBorderStrokeWidth = ringBorderStrokeWidth,
                selectionRadius = selectionRadius
            ) { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }

            FloatingActionButton(
                onClick = { onDismiss(color, hexString) },
                backgroundColor = Color.Black
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    tint = Blue400
                )
            }
        }
    }
}


@Composable
fun ColorPickerRingRectHSVDialog(
    initialColor: Color,
    ringOuterRadiusFraction: Float = .9f,
    ringInnerRadiusFraction: Float = .6f,
    ringBackgroundColor: Color = Color.Transparent,
    ringBorderStrokeColor: Color = Color.Black,
    ringBorderStrokeWidth: Dp = 4.dp,
    selectionRadius: Dp = 8.dp,
    onDismiss: (Color, String) -> Unit
) {

    var color by remember { mutableStateOf(initialColor.copy()) }
    var hexString by remember { mutableStateOf(colorToHex(color)) }

    Dialog(
        onDismissRequest = {
            onDismiss(color, hexString)
        }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ColorPickerRingRectHSV(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color(0xcc212121), shape = RoundedCornerShape(5.dp))
                    .padding(horizontal = 10.dp, vertical = 2.dp),
                initialColor = initialColor,
                ringOuterRadiusFraction = ringOuterRadiusFraction,
                ringInnerRadiusFraction = ringInnerRadiusFraction,
                ringBackgroundColor = ringBackgroundColor,
                ringBorderStrokeColor = ringBorderStrokeColor,
                ringBorderStrokeWidth = ringBorderStrokeWidth,
                selectionRadius = selectionRadius
            ) { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }

            FloatingActionButton(
                onClick = {
                    onDismiss(color, hexString)
                },
                backgroundColor = Color.Black
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    tint = Blue400
                )
            }
        }
    }
}

@Composable
fun ColorPickerRingHexHSVDialog(
    modifier: Modifier = Modifier,
    initialColor: Color,
    selectionRadius: Dp = 8.dp,
    dialogBackgroundColor: Color = Color.White,
    dialogShape: Shape = RoundedCornerShape(5.dp),
    onDismiss: (Color, String) -> Unit
) {

    var color by remember { mutableStateOf(initialColor.copy()) }
    var hexString by remember { mutableStateOf(colorToHex(color)) }

    Dialog(
        onDismissRequest = {
            onDismiss(color, hexString)
        }
    ) {
        Surface(
            modifier = modifier,
            color = dialogBackgroundColor,
            shape = dialogShape,
            elevation = 2.dp
        ) {
            ColorPickerRingRectHex(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                initialColor = initialColor,
                selectionRadius = selectionRadius
            ) { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }
        }
    }
}

@Composable
fun ColorPickerCircleHSVDialog(
    modifier: Modifier = Modifier,
    initialColor: Color,
    selectionRadius: Dp = 8.dp,
    dialogBackgroundColor: Color = Color.White,
    dialogShape: Shape = RoundedCornerShape(5.dp),
    onDismiss: (Color, String) -> Unit
) {

    var color by remember { mutableStateOf(initialColor.copy()) }
    var hexString by remember { mutableStateOf(colorToHex(color)) }

    Dialog(
        onDismissRequest = {
            onDismiss(color, hexString)
        }
    ) {
        Surface(
            modifier = modifier,
            color = dialogBackgroundColor,
            shape = dialogShape,
            elevation = 2.dp
        ) {
            ColorPickerCircleValueHSV(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                initialColor = initialColor,
                selectionRadius = selectionRadius
            ) { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }
        }
    }
}

@Composable
fun ColorPickerSVRectHSVDialog(
    modifier: Modifier = Modifier,
    initialColor: Color,
    selectionRadius: Dp = 8.dp,
    dialogBackgroundColor: Color = Color.White,
    dialogShape: Shape = RoundedCornerShape(5.dp),
    onDismiss: (Color, String) -> Unit
) {

    var color by remember { mutableStateOf(initialColor.copy()) }
    var hexString by remember { mutableStateOf(colorToHex(color)) }

    Dialog(
        onDismissRequest = {
            onDismiss(color, hexString)
        }
    ) {
        Surface(
            modifier = modifier,
            color = dialogBackgroundColor,
            shape = dialogShape,
            elevation = 2.dp
        ) {
            ColorPickerRectSaturationValueHSV(
                modifier = Modifier,
                initialColor = initialColor,
                selectionRadius = selectionRadius
            ) { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }
        }
    }
}

@Composable
fun ColorPickerSLRectHSLDialog(
    modifier: Modifier = Modifier,
    initialColor: Color,
    selectionRadius: Dp = 8.dp,
    dialogBackgroundColor: Color = Color.White,
    dialogShape: Shape = RoundedCornerShape(5.dp),
    onDismiss: (Color, String) -> Unit
) {

    var color by remember { mutableStateOf(initialColor.copy()) }
    var hexString by remember { mutableStateOf(colorToHex(color)) }

    Dialog(
        onDismissRequest = {
            onDismiss(color, hexString)
        }
    ) {
        Surface(
            modifier = modifier,
            color = dialogBackgroundColor,
            shape = dialogShape,
            elevation = 2.dp
        ) {
            ColorPickerRectSaturationLightnessHSL(
                modifier = Modifier,
                initialColor = initialColor,
                selectionRadius = selectionRadius
            ) { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }
        }
    }
}

@Composable
fun ColorPickerHSRectHSVDialog(
    modifier: Modifier = Modifier,
    initialColor: Color,
    selectionRadius: Dp = 8.dp,
    dialogBackgroundColor: Color = Color.White,
    dialogShape: Shape = RoundedCornerShape(5.dp),
    onDismiss: (Color, String) -> Unit
) {

    var color by remember { mutableStateOf(initialColor.copy()) }
    var hexString by remember { mutableStateOf(colorToHex(color)) }

    Dialog(
        onDismissRequest = {
            onDismiss(color, hexString)
        }
    ) {
        Surface(
            modifier = modifier,
            color = dialogBackgroundColor,
            shape = dialogShape,
            elevation = 2.dp
        ) {
            ColorPickerRectHueSaturationHSV(
                modifier = Modifier,
                initialColor = initialColor,
                selectionRadius = selectionRadius
            ) { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }
        }
    }
}

@Composable
fun ColorPickerHVRectHSVDialog(
    modifier: Modifier = Modifier,
    initialColor: Color,
    selectionRadius: Dp = 8.dp,
    dialogBackgroundColor: Color = Color.White,
    dialogShape: Shape = RoundedCornerShape(5.dp),
    onDismiss: (Color, String) -> Unit
) {

    var color by remember { mutableStateOf(initialColor.copy()) }
    var hexString by remember { mutableStateOf(colorToHex(color)) }

    Dialog(
        onDismissRequest = {
            onDismiss(color, hexString)
        }
    ) {
        Surface(
            modifier = modifier,
            color = dialogBackgroundColor,
            shape = dialogShape,
            elevation = 2.dp
        ) {
            ColorPickerRectHueValueHSV(
                modifier = Modifier,
                initialColor = initialColor,
                selectionRadius = selectionRadius
            ) { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }
        }
    }
}

@Composable
fun ColorPickerHSRectHSLDialog(
    modifier: Modifier = Modifier,
    initialColor: Color,
    selectionRadius: Dp = 8.dp,
    dialogBackgroundColor: Color = Color.White,
    dialogShape: Shape = RoundedCornerShape(5.dp),
    onDismiss: (Color, String) -> Unit
) {

    var color by remember { mutableStateOf(initialColor.copy()) }
    var hexString by remember { mutableStateOf(colorToHex(color)) }

    Dialog(
        onDismissRequest = {
            onDismiss(color, hexString)
        }
    ) {
        Surface(
            modifier = modifier,
            color = dialogBackgroundColor,
            shape = dialogShape,
            elevation = 2.dp
        ) {
            ColorPickerRectHueSaturationHSL(
                modifier = Modifier,
                initialColor = initialColor,
                selectionRadius = selectionRadius
            ) { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }
        }
    }
}

@Composable
fun ColorPickerHLRectHSLDialog(
    modifier: Modifier = Modifier,
    initialColor: Color,
    selectionRadius: Dp = 8.dp,
    dialogBackgroundColor: Color = Color.White,
    dialogShape: Shape = RoundedCornerShape(5.dp),
    onDismiss: (Color, String) -> Unit
) {

    var color by remember { mutableStateOf(initialColor.copy()) }
    var hexString by remember { mutableStateOf(colorToHex(color)) }

    Dialog(
        onDismissRequest = {
            onDismiss(color, hexString)
        }
    ) {
        Surface(
            modifier = modifier,
            color = dialogBackgroundColor,
            shape = dialogShape,
            elevation = 2.dp
        ) {
            ColorPickerRectHueLightnessHSL(
                modifier = Modifier,
                initialColor = initialColor,
                selectionRadius = selectionRadius
            ) { colorChange, hexChange ->
                color = colorChange
                hexString = hexChange
            }
        }
    }
}