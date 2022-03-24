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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.*
import com.smarttoolfactory.colorpicker.ui.Orange400
import com.smarttoolfactory.colorpicker.widget.SliderDisplayPanelHSL
import com.smarttoolfactory.colorpicker.widget.SliderDisplayPanelHSV
import com.smarttoolfactory.colorpicker.widget.drawChecker

/**
 * Converting from HSV or HSL to RGB and then from RGB to HSV or HSL doesn't bear correct
 * results all the time. This demo displays when it doesn't work as exptected
 */
@Composable
fun ColorModeConversionDemo() {

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
            .fillMaxWidth(.8f)
            .height(40.dp)


        // Panels that contain 0 to 4 sliders based on which callback is implemented
        HSVSliderDisplayPanelExample(modifier, sliderModifier, boxModifier)
        HSLSliderDisplayPanelExample(modifier, sliderModifier, boxModifier)
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

    Title(
        text = "Conversions from HSV"
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = boxModifier.background(colorHSV))

        CheckColorConversionDetailsFromHSV(
            color = colorHSV,
            hue = hue,
            saturation = saturation,
            value = value,
            alpha = alpha
        )

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

    Title(
        text = "Conversions from HSL"
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = boxModifier.background(colorHSL))

        CheckColorConversionDetailsFromHSL(
            colorHSL,
            hue,
            saturation,
            lightness,
            alpha
        )

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
    }
}

@Composable
private fun Title(text: String) {
    Text(
        text,
        color = Orange400,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        modifier = Modifier.padding(vertical = 12.dp)
    )
}

@Composable
private fun CheckColorConversionDetailsFromHSV(
    color: Color,
    hue: Float,
    saturation: Float,
    value: Float,
    alpha: Float
) {
    val red = color.red.fractionToRGBRange()
    val green = color.green.fractionToRGBRange()
    val blue = color.blue.fractionToRGBRange()


    /*
      🔥 Conversions
    */

    // Convert Color into ColorInt then to alpha, red, green, blue array
    val colorInt = color.toArgb()
    val argbArrayFromColor: IntArray = colorIntToARGBArray(colorInt)

    // Convert to RGB from HSV
    val rgbArray = hsvToRGB(hue = hue, saturation = saturation, value = value)

    // Convert to HSL from HSV
    val hslArrayFromHSV = hsvToHSL(hue, saturation, value)
    val hueHslFromHSV = hslArrayFromHSV[0].toInt()
    val saturationHslFromHSV = hslArrayFromHSV[1].fractionToPercent()
    val lightnessHslFromHSV = hslArrayFromHSV[2].fractionToPercent()

    // Convert to HSL from Color's red, green, blue
    // 🔥 This conversion does not give correct result all the time
    val hslArrayFromRGB = rgbToHSL(red, green, blue)
    val hueHslFromRGB = hslArrayFromRGB[0].toInt()
    val saturationHslFromRGB = hslArrayFromRGB[1].fractionToPercent()
    val lightnessHslFromRGB = hslArrayFromRGB[2].fractionToPercent()

    // Convert to HSV from Color's red, green, blue
    // 🔥 This conversion does not give correct result all the time
    val hsvArrayFromRGB = rgbToHSV(red, green, blue)
    val hueHsvFromRGB = hsvArrayFromRGB[0].toInt()
    val saturationHsvFromRGB = hsvArrayFromRGB[1].fractionToPercent()
    val valueHsvFromRGB = hsvArrayFromRGB[2].fractionToPercent()

    Text(
        "RAW Hue: ${hue}\n" +
                "RAW sat $saturation\n" +
                "RAW lightness: $value\n" +
                "HEX: ${colorInt.toArgbString()}"
    )

    Text(
        "RGB from Color.toArgb()\n" +
                "alpha: ${argbArrayFromColor[0]}, " +
                "red: ${argbArrayFromColor[1]}, " +
                "green: ${argbArrayFromColor[2]}, " +
                "blue: ${argbArrayFromColor[3]}\n",
    )

    // RGB Result Comparison
    val isRGBColorsSame = (red != rgbArray[0] || green != rgbArray[1] || blue != rgbArray[2])

    Text(
        "RGB from Color\n" +
                "red: $red, green: $green, blue: $blue",
        color = if (isRGBColorsSame) Color.Red else Color.LightGray
    )

    Text(
        "RGB from HSV\n" +
                "red: ${rgbArray[0]}, green: ${rgbArray[1]}, blue: ${rgbArray[2]}\n",
        color = if (isRGBColorsSame) Color.Red else Color.LightGray
    )

    // 🔥 When RGB is Converted to HSV or HSL colors don't match with actual one
    // This applies to any color picker
    // colorpicker.me/ Check colors in this link

    // HSL Result Comparison
    val areHslColorsNotSame =
        (hueHslFromHSV != hueHslFromRGB ||
                saturationHslFromHSV != saturationHslFromRGB ||
                lightnessHslFromHSV != lightnessHslFromRGB
                )

    Text(
        "HSL from HSV Conversion\n" +
                "hue: $hueHslFromHSV, " +
                "sat: $saturationHslFromHSV, " +
                "light: $lightnessHslFromHSV",
        color = if (areHslColorsNotSame) Color.Red else Color.LightGray
    )

    Text(
        "HSL from RGB Conversion\n" +
                "hue: $hueHslFromRGB, " +
                "sat: $saturationHslFromRGB, " +
                "light: $lightnessHslFromRGB\n",
        color = if (areHslColorsNotSame) Color.Red else Color.LightGray
    )

    // HSV Result Comparison
    val hueRounded = hue.toInt()
    val saturationRounded = saturation.fractionToPercent()
    val valueRounded = value.fractionToPercent()

    val areHSVColorsSame = (hueRounded == hueHsvFromRGB &&
            saturationRounded == saturationHsvFromRGB &&
            valueRounded == valueHsvFromRGB
            )

    Text(
        "HSL RAW rounded\n" +
                "hue: $hueRounded, " +
                "sat: $saturationRounded, " +
                "value: $valueRounded",
        color = if (!areHSVColorsSame) Color.Red else Color.LightGray
    )
    Text(
        "HSL from RGB Conversion\n" +
                "hue: $hueHsvFromRGB, " +
                "sat: $saturationHsvFromRGB, " +
                "value: $valueHsvFromRGB",
        color = if (!areHSVColorsSame) Color.Red else Color.LightGray
    )

}


/**
 * This is an example for conversions from HSL to HSV, HSV to RGB and from RGB to HSV nd HSL
 *
 *  🔥 When RGB is Converted to HSV or HSL colors don't match with actual one
 *  Check this online [color picker](colorpicker.me/) to cross-check results
 */
@Composable
private fun CheckColorConversionDetailsFromHSL(
    color: Color,
    hue: Float,
    saturation: Float,
    lightness: Float,
    alpha: Float
) {

    val red = color.red.fractionToRGBRange()
    val green = color.green.fractionToRGBRange()
    val blue = color.blue.fractionToRGBRange()


    /*
        🔥 Conversions
     */

    // Convert Color into ColorInt then to alpha, red, green, blue array
    val colorInt = color.toArgb()
    val argbArrayFromColor: IntArray = colorIntToARGBArray(colorInt)

    // Convert to RGB from HSL
    val rgbArray = hslToRGB(hue = hue, saturation = saturation, lightness = lightness)

    // Convert to HSV from HSL
    val hsvArrayFromHSL = hslToHSV(hue, saturation, lightness)
    val hueHsvFromHSL = hsvArrayFromHSL[0].toInt()
    val saturationHsvFromHSL = hsvArrayFromHSL[1].fractionToPercent()
    val valueHsvFromHSL = hsvArrayFromHSL[2].fractionToPercent()

    // Convert to HSV from Color's red, green, blue
    // 🔥 This conversion does not give correct result all the time
    val hsvArrayFromRGB = rgbToHSV(red, green, blue)
    val hueHsvFromRGB = hsvArrayFromRGB[0].toInt()
    val saturationHsvFromRGB = hsvArrayFromRGB[1].fractionToPercent()
    val valueHsvFromRGB = hsvArrayFromRGB[2].fractionToPercent()

    // Convert to HSL from Color's red, green, blue
    // 🔥 This conversion does not give correct result all the time
    val hslArrayFromRGB = rgbToHSL(red, green, blue)
    val hueHslFromRGB = hslArrayFromRGB[0].toInt()
    val saturationHslFromRGB = hslArrayFromRGB[1].fractionToPercent()
    val lightnessHslFromRGB = hslArrayFromRGB[2].fractionToPercent()


    Text(
        "RAW Hue: ${hue}\n" +
                "RAW sat $saturation\n" +
                "RAW lightness: $lightness\n" +
                "HEX: ${colorInt.toArgbString()}"
    )

    Text(
        "RGB from Color.toArgb()\n" +
                "alpha: ${argbArrayFromColor[0]}, " +
                "red: ${argbArrayFromColor[1]}, " +
                "green: ${argbArrayFromColor[2]}, " +
                "blue: ${argbArrayFromColor[3]}\n",
    )

    // RGB Result Comparison
    val areRGBColorsSame = (red != rgbArray[0] || green != rgbArray[1] || blue != rgbArray[2])
    Text(
        "RGB from Color\n" +
                "red: $red, green: $green, blue: $blue",
        color = if (areRGBColorsSame) Color.Red else Color.LightGray
    )

    Text(
        "RGB from HSL\n" +
                "red: ${rgbArray[0]}, green: ${rgbArray[1]}, blue: ${rgbArray[2]}\n",
        color = if (areRGBColorsSame) Color.Red else Color.LightGray
    )

    // HSV Result Comparison
    val areHsvColorsSame =
        (hueHsvFromHSL == hueHsvFromRGB &&
                saturationHsvFromHSL == saturationHsvFromRGB &&
                valueHsvFromHSL == valueHsvFromRGB)

    Text(
        "HSV from HSL Conversion\n" +
                "hue: $hueHsvFromHSL, " +
                "sat: $saturationHsvFromHSL, " +
                "value: $valueHsvFromHSL",
        color = if (!areHsvColorsSame) Color.Red else Color.LightGray
    )

    Text(
        "HSV from RGB Conversion\n" +
                "hue: $hueHsvFromRGB, " +
                "sat: $saturationHsvFromRGB, " +
                "value: $valueHsvFromRGB\n",
        color = if (!areHsvColorsSame) Color.Red else Color.LightGray
    )

    // HSL Result Comparison
    val hueRounded = hue.toInt()
    val saturationRounded = saturation.fractionToPercent()
    val lightnessRounded = lightness.fractionToPercent()

    val areHSLColorsSame = (hueRounded == hueHslFromRGB &&
            saturationRounded == saturationHslFromRGB &&
            lightnessRounded == lightnessHslFromRGB
            )

    Text(
        "HSL RAW rounded\n" +
                "hue: $hueRounded, " +
                "sat: $saturationRounded, " +
                "light: $lightnessRounded",
        color = if (!areHSLColorsSame) Color.Red else Color.LightGray
    )
    Text(
        "HSL from RGB Conversion\n" +
                "hue: $hueHslFromRGB, " +
                "sat: $saturationHslFromRGB, " +
                "light: $lightnessHslFromRGB",
        color = if (!areHSLColorsSame) Color.Red else Color.LightGray
    )
}
