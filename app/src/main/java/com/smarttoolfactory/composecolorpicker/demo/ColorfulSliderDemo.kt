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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.slider.*
import com.smarttoolfactory.colorpicker.ui.Blue400
import com.smarttoolfactory.colorpicker.widget.drawChecker
import com.smarttoolfactory.composecolorpicker.ui.theme.backgroundColor

@Composable
fun ColorfulSliderDemo() {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(backgroundColor)
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
            .fillMaxWidth(.8f)
            .height(40.dp)

        // Colorful sliders that change color based on the current properties they had
        HSVSliderExamples(modifier, sliderModifier, boxModifier)
        HSLSliderExamples(modifier, sliderModifier, boxModifier)
        RGBSliderExamples(modifier, sliderModifier, boxModifier)

        // Sliders with Title on left, and value display on right
        HSVSliderDisplayExamples(modifier, sliderModifier, boxModifier)
        HSLSliderDisplayExamples(modifier, sliderModifier, boxModifier)
        RGBASliderDisplayExamples(modifier, sliderModifier, boxModifier)

        // Panels that contain 0 to 4 sliders based on which callback is implemented
        HSVSliderDisplayPanelExample(modifier, sliderModifier, boxModifier)
        HSLSliderDisplayPanelExample(modifier, sliderModifier, boxModifier)
        RGBASliderDisplayPanelExample(modifier, sliderModifier, boxModifier)

        // Sliders with Circle Color display on left
        SliderCircleColorDisplayHSLExamples(modifier, sliderModifier)
        SliderCircleColorDisplayHSVExamples(modifier, sliderModifier)
    }
}

@Composable
private fun HSVSliderExamples(modifier: Modifier, sliderModifier: Modifier, boxModifier: Modifier) {

    var hue by remember { mutableStateOf(0f) }
    var saturation by remember { mutableStateOf(.5f) }
    var value by remember { mutableStateOf(.5f) }
    var alpha by remember { mutableStateOf(1f) }

    val colorHSV = Color.hsv(hue = hue, saturation = saturation, value = value, alpha = alpha)

    Title(text = "HSV Sliders")
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

    Title(text = "HSL Sliders")
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

    Title(text = "RGB Sliders")
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

@Composable
private fun HSVSliderDisplayExamples(
    modifier: Modifier,
    sliderModifier: Modifier,
    boxModifier: Modifier
) {

    var hue by remember { mutableStateOf(0f) }
    var saturation by remember { mutableStateOf(.5f) }
    var value by remember { mutableStateOf(.5f) }
    var alpha by remember { mutableStateOf(1f) }

    val colorHSV = Color.hsv(hue = hue, saturation = saturation, value = value, alpha = alpha)

    Title(text = "HSV SliderDisplays")

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = boxModifier.background(colorHSV))

        SliderDisplayHueHSV(
            modifier = sliderModifier,
            hue = hue,
            saturation = saturation,
            value = value,
            onValueChange = {
                hue = it
            }
        )


        SliderDisplaySaturationHSV(
            modifier = sliderModifier,
            hue = hue,
            saturation = saturation,
            value = value,
            onValueChange = { value ->
                saturation = value
            }
        )

        SliderDisplayValueHSV(
            modifier = sliderModifier,
            hue = hue,
            value = value,
            onValueChange = { result ->
                value = result
            }
        )

        SliderDisplayAlphaHSV(
            modifier = sliderModifier,
            hue = hue, alpha = alpha,
            onValueChange = {
                alpha = it
            }
        )
    }

}

@Composable
private fun HSLSliderDisplayExamples(
    modifier: Modifier,
    sliderModifier: Modifier,
    boxModifier: Modifier
) {

    var hue by remember { mutableStateOf(0f) }
    var saturation by remember { mutableStateOf(.5f) }
    var lightness by remember { mutableStateOf(.5f) }
    var alpha by remember { mutableStateOf(1f) }

    val colorHSL =
        Color.hsl(hue = hue, saturation = saturation, lightness = lightness, alpha = alpha)

    Title(text = "HSL SliderDisplays")
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = boxModifier.background(colorHSL))

        SliderDisplayHueHSL(
            modifier = sliderModifier,
            hue = hue,
            saturation = saturation,
            lightness = lightness,
            onValueChange = {
                hue = it
            }
        )

        SliderDisplaySaturationHSL(
            modifier = sliderModifier,
            hue = hue,
            saturation = saturation,
            lightness = lightness,
            onValueChange = { value ->
                saturation = value
            }
        )

        SliderDisplayLightnessHSL(
            modifier = sliderModifier,
            lightness = lightness,
            onValueChange = { result ->
                lightness = result
            }
        )

        SliderDisplayAlphaHSL(
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
private fun RGBASliderDisplayExamples(
    modifier: Modifier,
    sliderModifier: Modifier,
    boxModifier: Modifier
) {

    var red by remember { mutableStateOf(1f) }
    var green by remember { mutableStateOf(0f) }
    var blue by remember { mutableStateOf(0f) }
    var alpha by remember { mutableStateOf(1f) }

    val colorRGB = Color(red, green, blue, alpha = alpha)

    Title(text = "RGB SliderDisplays")
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = boxModifier.background(colorRGB))

        SliderDisplayRedRGB(
            modifier = sliderModifier,
            red = red,
            onValueChange = { result ->
                red = result.coerceIn(0f, 1f)
            }
        )

        SliderDisplayGreenRGB(
            modifier = sliderModifier,
            green = green,
            onValueChange = { result ->
                green = result.coerceIn(0f, 1f)
            }
        )

        SliderDisplayBlueRGB(
            modifier = sliderModifier,
            blue = blue,
            onValueChange = { result ->
                blue = result.coerceIn(0f, 1f)
            }
        )

        SliderDisplayAlphaRGB(
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

@Composable
private fun HSVSliderDisplayPanelExample(
    modifier: Modifier,
    sliderModifier: Modifier,
    boxModifier: Modifier
) {
    var hue by remember { mutableStateOf(0f) }
    var saturation by remember { mutableStateOf(.5f) }
    var value by remember { mutableStateOf(.5f) }
    var alpha by remember { mutableStateOf(1f) }

    val colorHSV = Color.hsv(hue = hue, saturation = saturation, value = value, alpha = alpha)

    Title(text = "HSV SliderDisplayPanel")

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = boxModifier.background(colorHSV))

        Title(text = "SliderDisplayPanel with 4 Sliders")

        SliderDisplayPanelHSV(
            modifier = sliderModifier,
            hue = hue,
            saturation = saturation,
            value = value,
            alpha = alpha,
            onHueChange = { hue = it },
            onSaturationChange = { saturation = it },
            onValueChange = { value = it },
            onAlphaChange = { alpha = it },
        )

        Title(text = "SliderDisplayPanel with 3 Sliders")
        SliderDisplayPanelHSV(
            modifier = sliderModifier,
            hue = hue,
            saturation = saturation,
            value = value,
            alpha = alpha,
            onHueChange = { hue = it },
            onSaturationChange = { saturation = it },
            onAlphaChange = { alpha = it }
        )

        Title(text = "SliderDisplayPanel with 2 Sliders")
        SliderDisplayPanelHSV(
            modifier = sliderModifier,
            hue = hue,
            saturation = saturation,
            value = value,
            alpha = alpha,
            onValueChange = { value = it },
            onAlphaChange = { alpha = it }
        )

        Title(text = "SliderDisplayPanel with 1 Slider")
        SliderDisplayPanelHSV(
            modifier = sliderModifier,
            hue = hue,
            saturation = saturation,
            value = 1f,
            alpha = alpha,
            onSaturationChange = { saturation = it }
        )
    }
}

@Composable
private fun HSLSliderDisplayPanelExample(
    modifier: Modifier,
    sliderModifier: Modifier,
    boxModifier: Modifier
) {

    var hue by remember { mutableStateOf(0f) }
    var saturation by remember { mutableStateOf(.5f) }
    var lightness by remember { mutableStateOf(.5f) }
    var alpha by remember { mutableStateOf(1f) }

    val colorHSL =
        Color.hsl(hue = hue, saturation = saturation, lightness = lightness, alpha = alpha)

    Title(text = "HSL SliderDisplayPanel")
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = boxModifier.background(colorHSL))
        Title(text = "SliderDisplayPanelHSL with 4 sliders")
        SliderDisplayPanelHSL(
            modifier = sliderModifier,
            hue = hue,
            saturation = saturation,
            lightness = lightness,
            alpha = alpha,
            onHueChange = { hue = it },
            onSaturationChange = { saturation = it },
            onLightnessChange = { lightness = it },
            onAlphaChange = { alpha = it },
        )

        Title(text = "SliderDisplayPanelHSL with 3 sliders")
        SliderDisplayPanelHSL(
            modifier = sliderModifier,
            hue = hue,
            saturation = saturation,
            lightness = lightness,
            alpha = alpha,
            onSaturationChange = { saturation = it },
            onLightnessChange = { lightness = it },
            onAlphaChange = { alpha = it }
        )
        Title(text = "SliderDisplayPanelHSL with 2 sliders")
        SliderDisplayPanelHSL(
            modifier = sliderModifier,
            hue = hue,
            saturation = saturation,
            lightness = lightness,
            alpha = alpha,
            onLightnessChange = { lightness = it },
            onAlphaChange = { alpha = it }
        )

        Title(text = "SliderDisplayPanelHSL with 1 slider")
        SliderDisplayPanelHSL(
            modifier = sliderModifier,
            hue = hue,
            saturation = saturation,
            lightness = .5f,
            alpha = alpha,
            onHueChange = { hue = it },
        )
    }
}

@Composable
private fun RGBASliderDisplayPanelExample(
    modifier: Modifier,
    sliderModifier: Modifier,
    boxModifier: Modifier
) {

    var red by remember { mutableStateOf(1f) }
    var green by remember { mutableStateOf(0f) }
    var blue by remember { mutableStateOf(0f) }
    var alpha by remember { mutableStateOf(1f) }

    val colorRGB = Color(red, green, blue, alpha = alpha)

    Title(text = "RGB SliderDisplayPanel")
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = boxModifier.background(colorRGB))
        SliderDisplayPanelRGBA(
            modifier = sliderModifier,
            red = red,
            green = green,
            blue = blue,
            alpha = alpha,
            onRedChange = { red = it },
            onGreenChange = { green = it },
            onBlueChange = { blue = it },
            onAlphaChange = { alpha = it },
        )
    }
}


@Composable
private fun SliderCircleColorDisplayHSVExamples(
    modifier: Modifier,
    sliderModifier: Modifier
) {
    val hue by remember { mutableStateOf(0f) }
    var saturation by remember { mutableStateOf(.5f) }
    var value by remember { mutableStateOf(.5f) }
    var alpha by remember { mutableStateOf(1f) }

    Title(text = "HSV SliderCircleColorDisplays")
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(text = "SliderCircleColorDisplay Saturation-Alpha")
        SliderCircleColorDisplaySaturationHSV(
            modifier = sliderModifier,
            hue = hue,
            saturation = saturation,
            value = value,
            alpha = alpha,
            onSaturationChange = { saturation = it },
            onAlphaChange = { alpha = it }
        )

        Title(text = "SliderCircleColorDisplay Value-Alpha")
        SliderCircleColorDisplayValueHSV(
            modifier = sliderModifier,
            hue = hue,
            saturation = saturation,
            value = value,
            alpha = alpha,
            onValueChange = { value = it },
            onAlphaChange = { alpha = it }
        )
    }
}

@Composable
private fun SliderCircleColorDisplayHSLExamples(
    modifier: Modifier,
    sliderModifier: Modifier
) {
    val hue by remember { mutableStateOf(0f) }
    var saturation by remember { mutableStateOf(.5f) }
    var lightness by remember { mutableStateOf(.5f) }
    var alpha by remember { mutableStateOf(1f) }

    Title(text = "HSL SliderCircleColorDisplays")
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(text = "SliderCircleColorDisplay Saturation-Alpha")
        SliderCircleColorDisplaySaturationHSL(
            modifier = sliderModifier,
            hue = hue,
            saturation = saturation,
            lightness = lightness,
            alpha = alpha,
            onSaturationChange = { saturation = it },
            onAlphaChange = { alpha = it }
        )
        Title(text = "SliderCircleColorDisplay Lightness-Alpha")
        SliderCircleColorDisplayLightnessHSL(
            modifier = sliderModifier,
            hue = hue,
            saturation = saturation,
            lightness = lightness,
            alpha = alpha,
            onLightnessChange = { lightness = it },
            onAlphaChange = { alpha = it }
        )
    }
}

@Composable
private fun Title(text: String) {
    Text(
        text,
        color = Blue400,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}
