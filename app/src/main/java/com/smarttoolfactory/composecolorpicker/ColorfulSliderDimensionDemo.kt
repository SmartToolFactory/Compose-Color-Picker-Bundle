package com.smarttoolfactory.composecolorpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.slider.ColorfulSlider
import com.smarttoolfactory.colorpicker.slider.MaterialSliderDefaults

@Composable
fun ColorfulSliderDimensionDemo() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val density = LocalDensity.current.density


        var progress by remember { mutableStateOf(0f) }

        var offsetX by remember { mutableStateOf(0) }
        val offsetY by remember { mutableStateOf(0) }
        Column(
            Modifier
                .background(Color.Green)
                .fillMaxWidth()
                .height(100.dp)) {
//            Text(
//                text = "Progress:$progress, offsetX $offsetX",
//                modifier = Modifier.offset {
//                    IntOffset(offsetX, offsetY)
//                }.background(Color.Yellow)
//            )
            ColorfulSlider(
                value = progress,
                modifier = Modifier
                    .size(10.dp)
                    .border(1.dp, Color.Green)
//                .padding(16.dp)
                ,
                onValueChange = { value, offset, event ->
                    offsetX = offset.x.toInt()
                    println("🌈 ColorfulSlider() value: $value, offset: $offset, event: $event")
                    progress = value
                },
                colors = MaterialSliderDefaults.materialColors(),
                steps = 5,
                drawInactiveTrack = false
            )
        }

        Column(
            Modifier
                .padding(top = 10.dp)
                .background(Color.Red)
                .fillMaxWidth()
                .height(100.dp)) {

            Slider(modifier = Modifier.size(10.dp)
                .border(1.dp, Color.Green), value = progress, onValueChange = { progress = it })
        }

//
//        ColorfulSlider(
//            value = progress,
//            modifier = Modifier
//                .width((1000 / density).dp)
//                .border(1.dp, Color.Green),
//            thumbRadius = (40 / density).dp,
//            trackHeight = (50 / density).dp,
//            onValueChange = { value, _, _ ->
//                progress = value
//            },
//            steps = 10
//        )
//
//        ColorfulSlider(
//            value = progress,
//            modifier = Modifier
//                .width((1000 / density).dp)
//                .border(1.dp, Color.Green),
//            thumbRadius = (40 / density).dp,
//            trackHeight = (100 / density).dp,
//            onValueChange = { value, _, _ ->
//                progress = value
//            },
//            steps = 10
//        )
//
//        ColorfulSlider(
//            value = progress,
//            modifier = Modifier
//                .width((1000 / density).dp)
//                .border(1.dp, Color.Green)
////                .padding(10.dp)
//            ,
//            thumbRadius = (40 / density).dp,
//            trackHeight = (100 / density).dp,
//            onValueChange = { value, _, _ ->
//                progress = value
//            },
//            coerceThumbInTrack = true,
//            steps = 10
//        )
//
//
//
//        ColorfulSlider(
//            value = progress,
//            modifier = Modifier
//                .width((1000 / density).dp)
//                .border(1.dp, Color.Green)
////                .padding(10.dp)
//            ,
//            thumbRadius = (50 / density).dp,
//            trackHeight = (100 / density).dp,
//            onValueChange = { value, _, _ ->
//                progress = value
//            },
//        )
//
//        ColorfulSlider(
//            value = progress,
//            modifier = Modifier
//                .width((1000 / density).dp)
//                .border(1.dp, Color.Green)
////                .padding(10.dp)
//            ,
//            thumbRadius = (50 / density).dp,
//            trackHeight = (100 / density).dp,
//            onValueChange = { value, _, _ ->
//                progress = value
//            },
//            coerceThumbInTrack = true,
//            steps = 10
//        )
//
//        ColorfulSlider(
//            value = progress,
//            modifier = Modifier
//                .width((1000 / density).dp)
//                .border(1.dp, Color.Green),
//            thumbRadius = (50 / density).dp,
//            trackHeight = (30 / density).dp,
//            onValueChange = { value, _, _ ->
//                progress = value
//            },
//            steps = 5
//        )
//
//        ColorfulSlider(
//            value = progress,
//            modifier = Modifier
//                .width((1000 / density).dp)
//                .border(1.dp, Color.Green),
//            thumbRadius = (50 / density).dp,
//            trackHeight = (30 / density).dp,
//            onValueChange = { value, _, _ ->
//                progress = value
//            },
//            colors = MaterialSliderDefaults.materialColors(
//                inactiveTrackColor = ColorBrush(
//                    color = ActiveTrackColor.copy(.6f)
//                )
//            ),
//            steps = 8
//        )
//
//
//        var progress2 by remember { mutableStateOf(0f) }
//
//        var counter by remember { mutableStateOf(0f) }
//
//        Slider(
//            modifier = Modifier
//                .width((1000 / density).dp)
//                .border(1.dp, Color.Green),
//            value = counter,
//            onValueChange = { counter = it }
//        )
//
//        Slider(
//            modifier = Modifier
//                .width((1000 / density).dp)
//                .border(1.dp, Color.Green)
//                .padding(16.dp),
//            value = counter,
//            onValueChange = { counter = it },
//            steps = 5
//        )
//
//        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
//
//            ColorfulSlider(
//                value = progress,
//                modifier = Modifier
//                    .width((1000 / density).dp)
//                    .border(1.dp, Color.Green),
//                thumbRadius = (50 / density).dp,
//                trackHeight = (30 / density).dp,
//                onValueChange = { value, _, _ ->
//                    progress = value
//                },
//                colors = MaterialSliderDefaults.materialColors(
//                    inactiveTrackColor = ColorBrush(color = Color.Red)
//                )
//            )
//
//            ColorfulSlider(
//                value = progress2,
//                onValueChange = { value, _, _ ->
//                    progress = value
//                },
//                colors = MaterialSliderDefaults.defaultColors()
//            )
//
//            Slider(value = counter, onValueChange = { counter = it })
//        }

//        Text("Progress: $progress, progress2: $progress2 counter: $counter")
    }
}


