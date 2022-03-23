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
import com.smarttoolfactory.colorpicker.widget.SliderDisplayPanelHSL
import com.smarttoolfactory.colorpicker.widget.drawChecker

@Composable
fun ColorPickerDemo() {
    Column(
        modifier = Modifier
//            .background(Color.White)
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            var hue by remember { mutableStateOf(0f) }
            var saturation by remember { mutableStateOf(.5f) }
            var lightness by remember { mutableStateOf(.5f) }
            var alpha by remember { mutableStateOf(1f) }

            val color =
                Color.hsl(hue = hue, saturation = saturation, lightness = lightness, alpha = alpha)

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
                        .drawChecker(RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
                        .background(
                            color,
                            shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
                        )

                )
            }

            // ColorWheel for hue selection
            // SaturationDiamond for saturation and lightness selections
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.Center
            ) {

                HueSelectorRing(
                    modifier = Modifier
                        .width(350.dp)
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
                        saturation = s
                        lightness = l
                    }
                }
            }

            SliderDisplayPanelHSL(
                modifier = Modifier.width(340.dp),
                hue = hue,
                saturation = saturation,
                lightness = lightness,
                alpha = alpha,
                onHueChange = { hue = it },
                onSaturationChange = { saturation = it },
                onLightnessChange = { lightness = it },
                onAlphaChange = { alpha = it },
            )
        }
    }
}
