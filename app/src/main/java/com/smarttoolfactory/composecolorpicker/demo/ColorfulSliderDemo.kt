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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.ui.Blue400
import com.smarttoolfactory.colorpicker.widget.*

@Composable
fun ColorfulSliderDemo() {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val modifier = Modifier
            .padding(8.dp)
            .shadow(2.dp, RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(4.dp)

        val sliderModifier = Modifier.padding(horizontal = 8.dp)

        val boxModifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .drawChecker(RoundedCornerShape(8.dp))
            .fillMaxWidth(.5f)
            .height(40.dp)

        HSVSliderExamples(modifier, sliderModifier, boxModifier)
        HSLSliderExamples(modifier, sliderModifier, boxModifier)
        RGBSliderExamples(modifier, sliderModifier, boxModifier)
    }
}

@Composable
private fun HSVSliderExamples(modifier: Modifier, sliderModifier: Modifier, boxModifier: Modifier) {

    var hue by remember { mutableStateOf(0f) }
    var saturation by remember { mutableStateOf(.5f) }
    var value by remember { mutableStateOf(.5f) }
    var alpha by remember { mutableStateOf(1f) }

    val colorHSV = Color.hsv(hue = hue, saturation = saturation, value = value, alpha = alpha)

    Text(text = "HSV Sliders", fontSize = 18.sp, color = Blue400, modifier =Modifier.padding(2.dp))

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = boxModifier.background(colorHSV))

        SliderHueHSV(
            modifier = sliderModifier,
            hue = hue,
            saturation = saturation,
            value = value,
            onValueChange = {
                hue = it
            }
        )


        SliderSaturationHSV(
            modifier = sliderModifier,
            hue = hue,
            saturation = saturation,
            value = value,
            onValueChange = { value ->
                saturation = value
            }
        )

        SliderValueHSV(
            modifier = sliderModifier,
            hue = hue,
            value = value,
            onValueChange = { result ->
                value = result
            }
        )

        SliderAlphaHSV(
            modifier = sliderModifier,
            hue = hue, alpha = alpha,
            onValueChange = {
                alpha = it
            }
        )
    }

}

@Composable
private fun HSLSliderExamples(modifier: Modifier, sliderModifier: Modifier, boxModifier: Modifier) {

    var hue by remember { mutableStateOf(0f) }
    var saturation by remember { mutableStateOf(.5f) }
    var lightness by remember { mutableStateOf(.5f) }
    var alpha by remember { mutableStateOf(1f) }

    val colorHSL =
        Color.hsl(hue = hue, saturation = saturation, lightness = lightness, alpha = alpha)

    Text(text = "HSL Sliders", fontSize = 18.sp, color = Blue400, modifier = Modifier.padding(2.dp))
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = boxModifier.background(colorHSL))

        SliderHueHSL(
            modifier = sliderModifier,
            hue = hue,
            saturation = saturation,
            lightness = lightness,
            onValueChange = {
                hue = it
            }
        )

        SliderSaturationHSL(
            modifier = sliderModifier,
            hue = hue,
            saturation = saturation,
            lightness = lightness,
            onValueChange = { value ->
                saturation = value
            }
        )

        SliderLightnessHSL(
            modifier = sliderModifier,
            lightness = lightness,
            onValueChange = { result ->
                lightness = result
            }
        )

        SliderAlphaHSL(
            modifier = sliderModifier,
            hue = hue,
            alpha = alpha,
            onValueChange = {
                alpha = it
            }
        )
    }
}

@Composable
private fun RGBSliderExamples(modifier: Modifier, sliderModifier: Modifier, boxModifier: Modifier) {

    var red by remember { mutableStateOf(1f) }
    var green by remember { mutableStateOf(0f) }
    var blue by remember { mutableStateOf(0f) }
    var alpha by remember { mutableStateOf(1f) }

    val colorRGB = Color(red, green, blue, alpha = alpha)

    Text(text = "RGB Sliders", fontSize = 18.sp, color = Blue400, modifier = Modifier.padding(2.dp))
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = boxModifier.background(colorRGB))

        SliderRedRGB(
            modifier = sliderModifier,
            red = red,
            onValueChange = { result ->
                red = result.coerceIn(0f, 1f)
            }
        )

        SliderGreenRGB(
            modifier = sliderModifier,
            green = green,
            onValueChange = { result ->
                green = result.coerceIn(0f, 1f)
            }
        )

        SliderBlueRGB(
            modifier = sliderModifier,
            blue = blue,
            onValueChange = { result ->
                blue = result.coerceIn(0f, 1f)
            }
        )

        SliderAlphaRGB(
            modifier = sliderModifier,
            red = red,
            green = green,
            blue = blue,
            alpha = alpha,
            onValueChange = {
                alpha = it
            }
        )

    }
}


