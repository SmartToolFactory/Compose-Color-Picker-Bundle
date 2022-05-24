package com.smarttoolfactory.composecolorpicker.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.widget.HexTextField
import com.smarttoolfactory.colorpicker.widget.HexTextFieldWithClipboard
import com.smarttoolfactory.colorpicker.widget.HexTextFieldWithLabelClipboard
import com.smarttoolfactory.composecolorpicker.ui.theme.backgroundColor
import com.smarttoolfactory.extendedcolors.util.ColorUtil

@Composable
fun HexConversionDemo() {
    Column(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxSize()
            .padding(12.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var color1 by remember { mutableStateOf(Color.hsl(0f, 0.5f, 0.5f)) }
        var hexString1 by remember { mutableStateOf(ColorUtil.colorToHexAlpha(color1)) }

        var color2 by remember { mutableStateOf(Color.hsl(0f, 0.5f, 0.5f)) }
        var hexString2 by remember { mutableStateOf(ColorUtil.colorToHexAlpha(color2)) }

        var color3 by remember { mutableStateOf(Color.hsl(0f, 0.5f, 0.5f)) }
        var hexString3 by remember { mutableStateOf(ColorUtil.colorToHex(color3)) }

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            "HEX: $hexString1",
            color = color1,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        HexTextField(
            hexString = hexString1,
            useAlpha = true,
            onTextChange = {
                hexString1 = it
            },
            onColorChange = {
                color1 = it
            }
        )

        Spacer(modifier = Modifier.height(30.dp))
        Text(
            "HEX: $hexString2",
            color = color2,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        HexTextFieldWithClipboard(
            hexString = hexString2,
            useAlpha = true,
            onTextChange = {
                hexString2 = it
            },
            onColorChange = {
                color2 = it
            }
        )

        Spacer(modifier = Modifier.height(30.dp))
        Text(
            "HEX: $hexString3",
            color = color3,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        HexTextFieldWithLabelClipboard(
            hexString = hexString3,
            onTextChange = {
                hexString3 = it
            },
            onColorChange = {
                color3 = it
            }
        )
    }
}

