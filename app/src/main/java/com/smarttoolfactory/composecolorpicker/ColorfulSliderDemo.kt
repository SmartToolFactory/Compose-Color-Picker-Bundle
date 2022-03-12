package com.smarttoolfactory.composecolorpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.slider.ColorfulSlider

@Composable
fun ColorfulSliderDemo() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(vertical = 20.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val density = LocalDensity.current.density


        var progress by remember { mutableStateOf(0f) }

        ColorfulSlider(
            value = progress,
            modifier = Modifier.width((1000/density).dp).border(1.dp, Color.Green),
            onValueChange = {
                progress = it
//                println("RED ColorSliderNew() PROGRESS $it")
            },
        )


        ColorfulSlider(
            value = progress,
            modifier = Modifier.width((1000/density).dp).border(1.dp, Color.Green),
            thumbRadius = (40/density).dp,
            trackHeight = (50/density).dp,
            onValueChange = {
                progress = it
//                println("RED ColorSliderNew() PROGRESS $it")
            },
        )

        ColorfulSlider(
            value = progress,
            modifier = Modifier.width((1000/density).dp).border(1.dp, Color.Green),
            thumbRadius = (40/density).dp,
            trackHeight = (100/density).dp,
            onValueChange = {
                progress = it
//                println("RED ColorSliderNew() PROGRESS $it")
            },
        )

        ColorfulSlider(
            value = progress,
            modifier = Modifier.width((1000/density).dp).border(1.dp, Color.Green),
            thumbRadius = (50/density).dp,
            trackHeight = (30/density).dp,
            onValueChange = {
                progress = it
//                println("RED ColorSliderNew() PROGRESS $it")
            },
            coerceThumbInTrack = true
        )

        var progress2 by remember { mutableStateOf(0f) }

//        ColorfulSlider(
//            value = progress2,
//            modifier = Modifier.width(340.dp),
//            thumbRadius = 14.dp,
//            trackHeight = 20.dp,
//            onValueChange = {
//                progress2 = it
////                println("RED ColorSliderNew() PROGRESS $it")
//            },
//            enabled = true,
//            colors = MaterialSliderDefaults.defaultColors(),
//        )
        var counter by remember { mutableStateOf(0f) }

        Slider(
            modifier = Modifier.width((1000/density).dp).border(1.dp, Color.Green),
            value = counter,
            onValueChange = { counter = it }
        )

        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

//            ColorfulSlider(
//                value = progress,
//                modifier = Modifier.width(240.dp),
//                thumbRadius = 10.dp,
//                trackHeight = 12.dp,
//                onValueChange = {
//                    progress = it
//                    println("RED ColorSliderNew() PROGRESS $it")
//                },
//                enabled = true,
//                colors = MaterialSliderDefaults.defaultColors()
//            )

            Slider(value = counter, onValueChange = { counter = it })
        }

//        ColorfulSlider(
//            value = progress,
//            modifier = Modifier.width(240.dp),
//            thumbRadius = 10.dp,
//            trackHeight = 12.dp,
//            onValueChange = {
//                progress = it
//                println("RED ColorSliderNew() PROGRESS $it")
//            },
//            enabled = false,
//            colors = MaterialSliderDefaults.defaultColors()
//        )
//
//
//        var red by remember { mutableStateOf(0f) }
//        var green by remember { mutableStateOf(0f) }
//        var blue by remember { mutableStateOf(0f) }
//
//        ColorfulSlider(
//            value = red,
//            modifier = Modifier.width(240.dp),
//            thumbRadius = 10.dp,
//            trackHeight = 12.dp,
//            onValueChange = {
//                red = it
//                println("RED ColorSliderNew() PROGRESS $it")
//            },
//            valueRange = 0f..255f,
//            coerceThumbInTrack = true,
//            colors = MaterialSliderDefaults.materialColors(
//                activeTrackColor = ColorBrush(
//                    brush = Brush.linearGradient(gradientColorsReversed),
//                    color = Color.Transparent
//                ),
//                inactiveTrackColor = ColorBrush(
//                    brush = Brush.linearGradient(gradientColors),
//                    color = Color.Transparent
//                )
//            )
//        )
//
//        ColorfulSlider(
//            value = green,
//            modifier = Modifier.width(240.dp),
//            thumbRadius = 10.dp,
//            trackHeight = 12.dp,
//            onValueChange = {
//                green = it
//                println("GREN ColorSliderNew() PROGRESS $it")
//            },
//            valueRange = 0f..255f,
//            coerceThumbInTrack = true,
//            colors = MaterialSliderDefaults.materialColors(
//                activeTrackColor = ColorBrush(
//                    brush = Brush.linearGradient(gradientColorsReversed),
//                    color = Color.Transparent
//                ),
//                inactiveTrackColor = ColorBrush(color = Color.Transparent)
//            )
//        )
//
//
//        ColorfulSlider(
//            value = blue,
//            modifier = Modifier.width(240.dp),
//            thumbRadius = 10.dp,
//            trackHeight = 12.dp,
//            onValueChange = {
//                blue = it
//                println("BLUE ColorSliderNew() PROGRESS $it")
//            },
//            valueRange = 0f..255f,
//            coerceThumbInTrack = true,
//            colors = MaterialSliderDefaults.materialColors(
//                activeTrackColor = ColorBrush(
//                    brush = Brush.linearGradient(listOf(Color.Black, Color.Blue)),
//                    color = Color.Transparent
//                ),
//                inactiveTrackColor = ColorBrush(color = Color.Transparent)
//            )
//        )

    }
}


