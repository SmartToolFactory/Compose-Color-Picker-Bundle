package com.smarttoolfactory.composecolorpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.slider.ColorBrush
import com.smarttoolfactory.colorpicker.slider.ColorfulSlider
import com.smarttoolfactory.colorpicker.slider.MaterialSliderDefaults
import com.smarttoolfactory.colorpicker.ui.ActiveTrackColor

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

        ColorfulSlider(
            value = progress,
            modifier = Modifier
                .border(1.dp, Color.Green)
//                .padding(16.dp)
            ,
            onValueChange = {
                progress = it
            },
            colors = MaterialSliderDefaults.materialColors(),
            steps = 5
        )


        ColorfulSlider(
            value = progress,
            modifier = Modifier
                .width((1000 / density).dp)
                .border(1.dp, Color.Green),
            thumbRadius = (40 / density).dp,
            trackHeight = (50 / density).dp,
            onValueChange = {
                progress = it
            },
            steps = 10
        )

        ColorfulSlider(
            value = progress,
            modifier = Modifier
                .width((1000 / density).dp)
                .border(1.dp, Color.Green),
            thumbRadius = (40 / density).dp,
            trackHeight = (100 / density).dp,
            onValueChange = {
                progress = it
            },
            steps = 10
        )

        ColorfulSlider(
            value = progress,
            modifier = Modifier
                .width((1000 / density).dp)
                .border(1.dp, Color.Green)
//                .padding(10.dp)
            ,
            thumbRadius = (40 / density).dp,
            trackHeight = (100 / density).dp,
            onValueChange = {
                progress = it
            },
            coerceThumbInTrack = true,
            steps = 10
        )



        ColorfulSlider(
            value = progress,
            modifier = Modifier
                .width((1000 / density).dp)
                .border(1.dp, Color.Green)
//                .padding(10.dp)
            ,
            thumbRadius = (50 / density).dp,
            trackHeight = (100 / density).dp,
            onValueChange = {
                progress = it
            }
        )

        ColorfulSlider(
            value = progress,
            modifier = Modifier
                .width((1000 / density).dp)
                .border(1.dp, Color.Green)
//                .padding(10.dp)
            ,
            thumbRadius = (50 / density).dp,
            trackHeight = (100 / density).dp,
            onValueChange = {
                progress = it
            },
            coerceThumbInTrack = true,
            steps = 10
        )

        ColorfulSlider(
            value = progress,
            modifier = Modifier
                .width((1000 / density).dp)
                .border(1.dp, Color.Green),
            thumbRadius = (50 / density).dp,
            trackHeight = (30 / density).dp,
            onValueChange = {
                progress = it
            },
            steps = 5
        )

        ColorfulSlider(
            value = progress,
            modifier = Modifier
                .width((1000 / density).dp)
                .border(1.dp, Color.Green),
            thumbRadius = (50 / density).dp,
            trackHeight = (30 / density).dp,
            onValueChange = {
                progress = it
            },
            colors = MaterialSliderDefaults.materialColors(
                inactiveTrackColor = ColorBrush(
                    color = ActiveTrackColor.copy(.6f)
                )
            ),
            steps = 8
        )


        var progress2 by remember { mutableStateOf(0f) }

        var counter by remember { mutableStateOf(0f) }

        Slider(
            modifier = Modifier
                .width((1000 / density).dp)
                .border(1.dp, Color.Green),
            value = counter,
            onValueChange = { counter = it }
        )

        Slider(
            modifier = Modifier
                .width((1000 / density).dp)
                .border(1.dp, Color.Green)
                .padding(16.dp),
            value = counter,
            onValueChange = { counter = it },
            steps = 5
        )

        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

            ColorfulSlider(
                value = progress,
                modifier = Modifier
                    .width((1000 / density).dp)
                    .border(1.dp, Color.Green),
                thumbRadius = (50 / density).dp,
                trackHeight = (30 / density).dp,
                onValueChange = {
                    progress = it
                },
                colors = MaterialSliderDefaults.materialColors(
                    inactiveTrackColor = ColorBrush(color = Color.Red)
                )
            )

            ColorfulSlider(
                value = progress2,
                onValueChange = {
                    progress2 = it
                },
                colors = MaterialSliderDefaults.defaultColors()
            )

            Slider(value = counter, onValueChange = { counter = it })
        }

        Text("Progress: $progress, progress2: $progress2 counter: $counter")
    }
}


