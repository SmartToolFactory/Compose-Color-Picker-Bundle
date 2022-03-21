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
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.selector.SLSelectorFromHSLDiamond
import com.smarttoolfactory.colorpicker.selector.SVSelectorFromHSVRectangle
import com.smarttoolfactory.colorpicker.ui.Blue400
import com.smarttoolfactory.colorpicker.ui.brush.sliderSaturationHSVGradient
import com.smarttoolfactory.colorpicker.ui.brush.sliderValueGradient
import com.smarttoolfactory.colorpicker.widget.*
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
        var value by remember { mutableStateOf(.5f) }

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
                    SLSelectorFromHSLDiamond(
                        modifier = Modifier.size(200.dp),
                        hue = hue,
                        saturation = saturationHSL,
                        lightness = lightness,
                        selectionRadius = 8.dp
                    ) { s, l ->
                        saturationHSL = s
                        lightness = l
                    }


                    Spacer(modifier = Modifier.height(20.dp))
                    SVSelectorFromHSVRectangle(
                        modifier = Modifier.size(200.dp),
                        hue = hue,
                        saturation = saturationHSV,
                        value = value,
                        selectionRadius = 8.dp
                    ) { s, v ->
                        saturationHSV = s
                        value = v
                    }
                }
            }

            /*
                Sliders
             */

            SliderHueHSL(
                modifier = Modifier.width(300.dp),
                hue = hue,
                saturation = saturationHSL,
                lightness = lightness,
                onValueChange = {
                    hue = it
                }
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text("HSL Sliders", fontSize = 16.sp, color = Blue400)

            SliderSaturationHSL(
                modifier = Modifier.width(300.dp),
                hue = hue,
                saturation = saturationHSL,
                lightness = lightness,
                onValueChange = { value ->
                    saturationHSL = value
                }
            )

            SliderLightnessHSL(
                modifier = Modifier.width(300.dp),
                lightness = lightness,
                onValueChange = { result ->
                    lightness = result
                }
            )

            Text("HSV Sliders", fontSize = 16.sp, color = Blue400)
            SliderSaturationHSV(
                modifier = Modifier.width(300.dp),
                hue = hue,
                saturation = saturationHSV,
                value = value,
                onValueChange = { value ->
                    saturationHSV = value
                }
            )

            SliderValueHSV(
                modifier = Modifier.width(300.dp),
                hue = hue,
                value = value,
                onValueChange = { result ->
                    value = result
                }
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}
