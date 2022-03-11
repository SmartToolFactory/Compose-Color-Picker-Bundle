package com.smarttoolfactory.composecolorpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.ColorBrush
import com.smarttoolfactory.colorpicker.ColorfulSlider
import com.smarttoolfactory.colorpicker.MaterialSliderDefaults

@Composable
fun ColorfulSliderDemo() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding( 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var red by remember { mutableStateOf(0f) }
        var green by remember { mutableStateOf(0f) }
        var blue by remember { mutableStateOf(0f) }

        ColorfulSlider(
            value = red,
            modifier = Modifier.width(240.dp),
            thumbRadius = 10.dp,
            trackHeight = 12.dp,
            onValueChange = {
                red = it
                println("RED ColorSliderNew() PROGRESS $it")
            },
            valueRange = 0f..255f,
            coerceThumbInTrack = true,
            colors = MaterialSliderDefaults.colors(
                activeTrackColor = ColorBrush(
                    brush = Brush.linearGradient(listOf(Color.Black, Color.Red)),
                    color = Color.Transparent
                ),
                inactiveTrackColor = ColorBrush(color = Color.Transparent)
            )
        )

        ColorfulSlider(
            value = green,
            modifier = Modifier.width(240.dp),
            thumbRadius = 10.dp,
            trackHeight = 12.dp,
            onValueChange = {
                green = it
                println("GREN ColorSliderNew() PROGRESS $it")
            },
            valueRange = 0f..255f,
            coerceThumbInTrack = true,
            colors = MaterialSliderDefaults.colors(
                activeTrackColor = ColorBrush(
                    brush = Brush.linearGradient(listOf(Color.Black, Color.Green)),
                    color = Color.Transparent
                ),
                inactiveTrackColor = ColorBrush(color = Color.Transparent)
            )
        )

        ColorfulSlider(
            value = blue,
            modifier = Modifier.width(240.dp),
            thumbRadius = 10.dp,
            trackHeight = 12.dp,
            onValueChange = {
                blue = it
                println("BLUE ColorSliderNew() PROGRESS $it")
            },
            valueRange = 0f..255f,
            coerceThumbInTrack = true,
            colors = MaterialSliderDefaults.colors(
                activeTrackColor = ColorBrush(
                    brush = Brush.linearGradient(listOf(Color.Black, Color.Blue)),
                    color = Color.Transparent
                ),
                inactiveTrackColor = ColorBrush(color = Color.Transparent)
            )
        )
    }
}


