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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.saturationpicker.SaturationPickerRectangle
import com.smarttoolfactory.colorpicker.saturationpicker.SaturationRhombus
import com.smarttoolfactory.colorpicker.slider.ColorBrush
import com.smarttoolfactory.colorpicker.slider.ColorfulSlider
import com.smarttoolfactory.colorpicker.slider.MaterialSliderDefaults
import com.smarttoolfactory.colorpicker.ui.*

@Composable
fun ColorPickerDemo() {
    Column(
        modifier = Modifier
//            .background(Color(0xff424242))
            .background(Color.White)
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {

        var hue by remember { mutableStateOf(0f) }
        var saturation1 by remember { mutableStateOf(.5f) }
        var saturation2 by remember { mutableStateOf(.5f) }
        var lightness by remember { mutableStateOf(.5f) }
        var value by remember { mutableStateOf(1f) }

//        val color = Color.hsl(hue = hue, saturation = saturation, lightness = lightness)
        val colorHSL = Color.hsl(hue = hue, saturation = saturation1, lightness = lightness)
        val colorHSV = Color.hsv(hue = hue, saturation = saturation2, value = value)

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Color",
                color = Blue400,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 12.dp)
            )

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
            // SaturationRhombus for saturation and lightness selections
            Box(
                modifier = Modifier.padding(8.dp),
                contentAlignment = Alignment.Center
            ) {

//                ColorPickerWheel(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .aspectRatio(1f),
//                    selectionRadius = 8.dp
//                ) { hueChange ->
//                    hue = hueChange.toFloat()
//                }

                Column {
                    SaturationRhombus(
                        modifier = Modifier.size(200.dp),
                        hue = hue,
                        saturation = saturation1,
                        lightness = lightness,
                        selectionRadius = 8.dp
                    ) { s, l ->
                        println("CHANGING sat: $s, lightness: $l")
                        saturation1 = s
                        lightness = l
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    SaturationPickerRectangle(
                        modifier = Modifier.size(200.dp),
                        hue = hue,
                        saturation = saturation2,
                        value = value,
                        selectionRadius = 8.dp
                    ) { s, v ->
                        println("CHANGING sat: $s, lightness: $v")
                        saturation2 = s
                        value = v
                    }
                }
            }


            val hueGradientHSL: List<Color> = gradientColorScaleHSL(
                saturation = saturation1,
                lightness = lightness,
            )

            val sliderSaturationHSLGradient = sliderSaturationHSLGradient(hue, lightness)
            val sliderSaturationHSVGradient = sliderSaturationHSVGradient(hue, value)
            val sliderLightnessGradient2Stops = sliderLightnessGradient2Stops(0f)
            val sliderValueGradient = sliderValueGradient(0f)

            ColorfulSlider(
                value = hue,
                modifier = Modifier.width(300.dp),
                thumbRadius = 14.dp,
                trackHeight = 14.dp,
                onValueChange = { value, _, _ ->
                    hue = value
                },
                valueRange = 0f..360f,
                coerceThumbInTrack = true,
                colors = MaterialSliderDefaults.materialColors(
                    activeTrackColor = ColorBrush(brush = Brush.linearGradient(hueGradientHSL)),
                ),
                drawInactiveTrack = false
            )

            Spacer(modifier = Modifier.height(4.dp))
            ColorfulSlider(
                value = saturation1,
                modifier = Modifier.width(300.dp),
                thumbRadius = 14.dp,
                trackHeight = 14.dp,
                onValueChange = { value, _, _ ->
                    saturation1 = value
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
                thumbRadius = 14.dp,
                trackHeight = 14.dp,
                onValueChange = { result, _, _ ->
                    lightness = result
                },
                coerceThumbInTrack = true,
                colors = MaterialSliderDefaults.materialColors(
                    activeTrackColor = ColorBrush(brush = sliderLightnessGradient2Stops),
                ),
                drawInactiveTrack = false
            )

            Text("HSV Sliders")

            ColorfulSlider(
                value = saturation2,
                modifier = Modifier.width(300.dp),
                thumbRadius = 14.dp,
                trackHeight = 14.dp,
                onValueChange = { value, _, _ ->
                    saturation2 = value
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
                thumbRadius = 14.dp,
                trackHeight = 14.dp,
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
