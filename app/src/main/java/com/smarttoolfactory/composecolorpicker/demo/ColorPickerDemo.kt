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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.selector.HueSelectorRing
import com.smarttoolfactory.colorpicker.selector.SLSelectorFromHSLDiamond
import com.smarttoolfactory.colorpicker.ui.Blue400
import com.smarttoolfactory.colorpicker.ui.brush.sliderHueHSLGradient
import com.smarttoolfactory.colorpicker.ui.brush.sliderLightnessGradient
import com.smarttoolfactory.colorpicker.ui.brush.sliderSaturationHSLGradient
import com.smarttoolfactory.slider.ColorBrush
import com.smarttoolfactory.slider.ColorfulSlider
import com.smarttoolfactory.slider.MaterialSliderDefaults

@Composable
fun ColorPickerDemo() {
    Column(
        modifier = Modifier
            .background(Color(0xff424242))
//            .background(Color.White)
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {

        var hue by remember { mutableStateOf(0f) }
        var saturation by remember { mutableStateOf(.5f) }
        var lightness by remember { mutableStateOf(.5f) }

        val color = Color.hsl(hue = hue, saturation = saturation, lightness = lightness)

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
                            Color.Black,
                            shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
                        )
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .background(
                            color,
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

                HueSelectorRing(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    selectionRadius = 8.dp
                ) { hueChange ->
                    hue = hueChange.toFloat()
                }

                Column {
                    SLSelectorFromHSLDiamond(
                        modifier = Modifier.size(200.dp),
                        hue = hue,
                        saturation = saturation,
                        lightness = lightness,
                        selectionRadius = 8.dp
                    ) { s, l ->
                        println("CHANGING sat: $s, lightness: $l")
                        saturation = s
                        lightness = l
                    }
                }
            }

            val sliderHueSelectionHSLGradient = sliderHueHSLGradient(
                saturation = saturation,
                lightness = lightness,
            )
            val sliderSaturationHSLGradient = sliderSaturationHSLGradient(hue, lightness)
            val sliderLightnessGradient = sliderLightnessGradient(0f)

            ColorfulSlider(
                value = hue,
                modifier = Modifier.width(300.dp),
                thumbRadius = 12.dp,
                trackHeight = 12.dp,
                onValueChange = { value ->
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
            ColorfulSlider(
                value = saturation,
                modifier = Modifier.width(300.dp),
                thumbRadius = 12.dp,
                trackHeight = 12.dp,
                onValueChange = { value ->
                    saturation = value
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
                onValueChange = { result ->
                    lightness = result
                },
                coerceThumbInTrack = true,
                colors = MaterialSliderDefaults.materialColors(
                    activeTrackColor = ColorBrush(brush = sliderLightnessGradient),
                ),
                drawInactiveTrack = false
            )
        }
    }
}
