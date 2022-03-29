package com.smarttoolfactory.colorpicker.widget

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.smarttoolfactory.colorpicker.util.argbToHex

@Composable
fun HexText(color: Color, onTextChange: (Color) -> Unit) {
    val hexString = argbToHex(
        color.alpha,
        color.red,
        color.green,
        color.blue
    )
    BasicTextField(
        value = hexString,
        onValueChange = {

        }
    )
}