package com.smarttoolfactory.composecolorpicker.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.util.colorToHex
import com.smarttoolfactory.colorpicker.util.colorToHexAlpha
import com.smarttoolfactory.colorpicker.widget.HexAlphaTextField
import com.smarttoolfactory.composecolorpicker.ui.theme.backgroundColor

@Composable
fun HexConversionDemo() {
    Column(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {

        var color by remember {
            mutableStateOf(
                Color.hsl(0f, 0.5f, 0.5f)
            )
        }

        var hexString by remember { mutableStateOf(colorToHexAlpha(color)) }

        Text(
            "HEX: $hexString",
            color = color,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        HexAlphaTextField(
            modifier = Modifier
                .wrapContentWidth()
                .border(1.dp, Color.Red),
            hexString = hexString,
            onTextChange = {
                hexString = it
            }, onColorChange = {
                color = it
            }
        )
    }
}

