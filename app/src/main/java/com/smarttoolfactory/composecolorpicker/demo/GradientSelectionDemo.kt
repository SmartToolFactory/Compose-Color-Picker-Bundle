package com.smarttoolfactory.composecolorpicker.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.model.rememberGradientColor
import com.smarttoolfactory.colorpicker.selector.SelectorDiamondSaturationLightnessHSL
import com.smarttoolfactory.colorpicker.selector.gradient.BrushDisplay
import com.smarttoolfactory.colorpicker.selector.gradient.GradientSelector
import com.smarttoolfactory.colorpicker.slider.SliderCircleColorDisplayHueHSL
import com.smarttoolfactory.colorpicker.ui.Blue400


/**
 * Demo for creating selecting gradient
 */
@Composable
fun GradientSelectionDemo() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var hue by remember { mutableStateOf(0f) }
        var saturation by remember { mutableStateOf(.5f) }
        var lightness by remember { mutableStateOf(.5f) }
        var alpha by remember { mutableStateOf(1f) }

        val currentColor = Color.hsl(hue, saturation, lightness, alpha)

        SelectorDiamondSaturationLightnessHSL(
            modifier = Modifier.size(150.dp),
            hue = hue,
            saturation = saturation,
            lightness = lightness,
            selectionRadius = 8.dp
        ) { s, l ->
            saturation = s
            lightness = l
        }

        SliderCircleColorDisplayHueHSL(
            modifier = Modifier.padding(8.dp),
            hue = hue,
            saturation = saturation,
            lightness = lightness,
            alpha = alpha,
            onHueChange = {
                hue = it
            },
            onAlphaChange = {
                alpha = it
            }
        )

        Text(
            text = "GRADIENT SELECTION",
            modifier = Modifier.padding(5.dp),
            color = Blue400,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val size = DpSize(150.dp, 100.dp)
            val gradientColor = rememberGradientColor(size)

//            Box(
//                modifier = Modifier
//                    .size(size)
//                    .drawChecker(RoundedCornerShape(20))
//                    .background(gradientColor.brushColor.activeBrush),
//            )
            BrushDisplay(gradientColor = gradientColor)
            GradientSelector(
                modifier = Modifier.padding(horizontal = 8.dp),
                color = currentColor,
                gradientColor = gradientColor
            )
        }
    }
}