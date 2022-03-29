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
import com.smarttoolfactory.colorpicker.picker.ColorPickerCircleValueHSV
import com.smarttoolfactory.colorpicker.picker.ColorPickerRingDiamondHSL
import com.smarttoolfactory.colorpicker.picker.ColorPickerRingRectHSV
import com.smarttoolfactory.colorpicker.ui.Blue400

@Composable
fun ColorPickerRingDiamondHSLDialog(
    initialColor: Color,
    selectionRadius: Dp = 8.dp,
    onDismiss: (Color) -> Unit
) {

    var color by remember { mutableStateOf(initialColor.copy()) }
    Dialog(
        onDismissRequest = {
            onDismiss(color)
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
                selectionRadius = selectionRadius
            ) {
                color = it
            }

            FloatingActionButton(
                onClick = { onDismiss(color) },
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
    selectionRadius: Dp = 8.dp,
    onDismiss: (Color) -> Unit
) {

    var color by remember { mutableStateOf(initialColor.copy()) }
    Dialog(
        onDismissRequest = {
            onDismiss(color)
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
                selectionRadius = selectionRadius
            ) {
                color = it
            }

            FloatingActionButton(
                onClick = { onDismiss(color) },
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
fun ColorPickerCircleHSVDialog(
    modifier: Modifier = Modifier,
    initialColor: Color,
    selectionRadius: Dp = 8.dp,
    dialogBackgroundColor: Color = Color.White,
    dialogShape: Shape = RoundedCornerShape(5.dp),
    onDismiss: (Color) -> Unit
) {

    var color by remember { mutableStateOf(initialColor.copy()) }

    Dialog(
        onDismissRequest = {
            onDismiss(color)
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
                    .padding(horizontal = 12.dp, vertical = 12.dp),
                initialColor = initialColor,
                selectionRadius = selectionRadius
            ) {
                color = it
            }
        }
    }
}



