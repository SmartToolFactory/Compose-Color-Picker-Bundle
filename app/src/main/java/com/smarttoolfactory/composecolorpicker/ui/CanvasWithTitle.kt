package com.smarttoolfactory.composecolorpicker.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.ui.Blue400

@Composable
fun CanvasWithTitle(
    modifier: Modifier = Modifier,
    text: String,
    onDraw: DrawScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentWidth()
    ) {

        Text(
            text = text,
            color = Blue400,
            modifier = Modifier
                .padding(8.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Canvas(modifier = modifier, onDraw = onDraw)
    }
}