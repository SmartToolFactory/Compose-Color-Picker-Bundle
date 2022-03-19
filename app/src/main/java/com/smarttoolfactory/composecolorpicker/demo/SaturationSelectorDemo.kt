package com.smarttoolfactory.composecolorpicker.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.saturationselector.SaturationPickerDiamond
import com.smarttoolfactory.colorpicker.saturationselector.SaturationPickerRectangle
import com.smarttoolfactory.colorpicker.slider.ColorBrush
import com.smarttoolfactory.colorpicker.slider.ColorfulSlider
import com.smarttoolfactory.colorpicker.slider.MaterialSliderDefaults
import com.smarttoolfactory.colorpicker.ui.brush.*
import com.smarttoolfactory.composecolorpicker.ui.theme.backgroundColor

@Composable
fun SaturationSelectorDemo() {
    Column(
        modifier = Modifier
            .background(backgroundColor)
//            .background(Color.White)
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {

        var hue by remember { mutableStateOf(0f) }
        var saturationHSL by remember { mutableStateOf(.5f) }
        var saturationHSV by remember { mutableStateOf(.5f) }
        var lightness by remember { mutableStateOf(.5f) }
        var value by remember { mutableStateOf(1f) }

//        val color = Color.hsl(hue = hue, saturation = saturation, lightness = lightness)
        val colorHSL = Color.hsl(hue = hue, saturation = saturationHSL, lightness = lightness)
        val colorHSV = Color.hsv(hue = hue, saturation = saturationHSV, value = value)

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(10.dp))

            // Initial and Current Colors
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp, vertical = 20.dp)
            ) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .background(
                            colorHSL,
                            shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
                        )
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .background(
                            colorHSV,
                            shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
                        )
                )
            }

            // ColorWheel for hue selection
            // SaturationDiamond for saturation and lightness selections
            Box(
                modifier = Modifier.padding(8.dp),
                contentAlignment = Alignment.Center
            ) {

                Column {
                    SaturationPickerDiamond(
                        modifier = Modifier.size(200.dp),
                        hue = hue,
                        saturation = saturationHSL,
                        lightness = lightness,
                        selectionRadius = 8.dp
                    ) { s, l ->
                        println("CHANGING sat: $s, lightness: $l")
                        saturationHSL = s
                        lightness = l
                    }


                    Spacer(modifier = Modifier.height(20.dp))
                    SaturationPickerRectangle(
                        modifier = Modifier.size(200.dp),
                        hue = hue,
                        saturation = saturationHSV,
                        value = value,
                        selectionRadius = 8.dp
                    ) { s, v ->
                        println("CHANGING sat: $s, lightness: $v")
                        saturationHSV = s
                        value = v
                    }
                }
            }

            /*
                Sliders and Gradients
             */
            val sliderHueSelectionHSLGradient = sliderHueHSLGradient(
                saturation = saturationHSL,
                lightness = lightness,
            )

            // HSL Sliders
            val sliderSaturationHSLGradient =
                sliderSaturationHSLGradient(hue = hue, lightness = lightness)
            val sliderLightnessGradient = sliderLightnessGradient(hue = 0f)

            // HSV Sliders
            val sliderSaturationHSVGradient = sliderSaturationHSVGradient(hue = hue, value = value)
            val sliderValueGradient = sliderValueGradient(hue = hue)

            ColorfulSlider(
                value = hue,
                modifier = Modifier.width(300.dp),
                thumbRadius = 12.dp,
                trackHeight = 12.dp,
                onValueChange = { value, _, _ ->
                    hue = value
                },
                valueRange = 0f..360f,
                coerceThumbInTrack = true,
                colors = MaterialSliderDefaults.materialColors(
                    activeTrackColor = ColorBrush(brush = sliderHueSelectionHSLGradient),
                ),
                drawInactiveTrack = false
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text("HSL Sliders")
            ColorfulSlider(
                value = saturationHSL,
                modifier = Modifier.width(300.dp),
                thumbRadius = 12.dp,
                trackHeight = 12.dp,
                onValueChange = { value, _, _ ->
                    saturationHSL = value
                },
                coerceThumbInTrack = true,
                colors = MaterialSliderDefaults.materialColors(
                    activeTrackColor = ColorBrush(brush = sliderSaturationHSLGradient),
                ),
                drawInactiveTrack = false
            )
            Spacer(modifier = Modifier.height(4.dp))

            ColorfulSlider(
                value = lightness,
                modifier = Modifier.width(300.dp),
                thumbRadius = 12.dp,
                trackHeight = 12.dp,
                onValueChange = { result, _, _ ->
                    lightness = result
                },
                coerceThumbInTrack = true,
                colors = MaterialSliderDefaults.materialColors(
                    activeTrackColor = ColorBrush(brush = sliderLightnessGradient),
                ),
                drawInactiveTrack = false
            )

            Text("HSV Sliders")

            ColorfulSlider(
                value = saturationHSV,
                modifier = Modifier.width(300.dp),
                thumbRadius = 12.dp,
                trackHeight = 12.dp,
                onValueChange = { value, _, _ ->
                    saturationHSV = value
                },
                coerceThumbInTrack = true,
                colors = MaterialSliderDefaults.materialColors(
                    activeTrackColor = ColorBrush(brush = sliderSaturationHSVGradient),
                ),
                drawInactiveTrack = false
            )


            ColorfulSlider(
                value = value,
                modifier = Modifier.width(300.dp),
                thumbRadius = 12.dp,
                trackHeight = 12.dp,
                onValueChange = { result, _, _ ->
                    value = result
                },
                coerceThumbInTrack = true,
                colors = MaterialSliderDefaults.materialColors(
                    activeTrackColor = ColorBrush(brush = sliderValueGradient),
                ),
                drawInactiveTrack = false
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}
