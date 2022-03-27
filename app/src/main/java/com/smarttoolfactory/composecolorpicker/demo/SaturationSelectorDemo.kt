package com.smarttoolfactory.composecolorpicker.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.selector.*
import com.smarttoolfactory.colorpicker.slider.*
import com.smarttoolfactory.colorpicker.ui.Blue400
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
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val selectorModifier = Modifier
                .size(300.dp)
                .padding(8.dp)
            val cardModifier = Modifier
                .padding(8.dp)
                .shadow(1.dp, RoundedCornerShape(2.dp))
                .background(Color.White)

            SLWithHSLDiamondExample(cardModifier, selectorModifier)
            Spacer(modifier = Modifier.height(20.dp))
            SVSelectorWithHSVRectangleExample(cardModifier, selectorModifier)
            Spacer(modifier = Modifier.height(20.dp))
            HSSelectorHSVCircleExample(cardModifier, selectorModifier)
            Spacer(modifier = Modifier.height(20.dp))
            HSSelectorWithHSVRectangleExample(cardModifier, selectorModifier)
            Spacer(modifier = Modifier.height(20.dp))
            HVSelectorWithHSVRectangleExample(cardModifier, selectorModifier)
            Spacer(modifier = Modifier.height(20.dp))
            HSSelectorWithHSLRectangleExample(cardModifier, selectorModifier)
            Spacer(modifier = Modifier.height(20.dp))
            HLSelectorWithHSLRectangleExample(cardModifier, selectorModifier)
        }
    }
}

@Composable
fun SLWithHSLDiamondExample(modifier: Modifier, selectorModifier: Modifier) {
    var hue by remember { mutableStateOf(0f) }
    var saturation by remember { mutableStateOf(.5f) }
    var lightness by remember { mutableStateOf(.5f) }
    var alpha by remember { mutableStateOf(1f) }

    Title(text = "Saturation-Lightness Selector Diamond HSL")

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SLSelectorDiamondHSL(
            modifier = selectorModifier,
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
    }

}

@Composable
private fun SVSelectorWithHSVRectangleExample(modifier: Modifier, selectorModifier: Modifier) {

    var hue by remember { mutableStateOf(0f) }
    var saturation by remember { mutableStateOf(.5f) }
    var value by remember { mutableStateOf(.5f) }
    var alpha by remember { mutableStateOf(1f) }

    Title(text = "Saturation-Value Selector Rect HSV")

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SVSelectorRectHSV(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4/3f),
            hue = hue,
            saturation = saturation,
            value = value,
            selectionRadius = 8.dp
        ) { s, v ->
            saturation = s
            value = v
        }

        SliderCircleColorDisplayHueHSV(
            hue = hue,
            saturation = saturation,
            value = value,
            alpha = alpha,
            onHueChange = {
                hue = it
            },
            onAlphaChange = {
                alpha = it
            }
        )
    }
}

@Composable
private fun HSSelectorHSVCircleExample(modifier: Modifier, selectorModifier: Modifier) {
    var hue by remember { mutableStateOf(0f) }
    var saturation by remember { mutableStateOf(.5f) }
    var value by remember { mutableStateOf(.5f) }
    var alpha by remember { mutableStateOf(1f) }

    Title(text = "Hue-Saturation Selector Circle HSV")

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HueSaturationSelectorCircleHSV(
            modifier = selectorModifier,
            hue = hue,
            saturation = saturation,
            selectionRadius = 8.dp
        ) { h, s ->
            hue = h
            saturation = s

        }

        SliderCircleColorDisplayValueHSV(
            modifier = Modifier.padding(8.dp),
            hue = hue,
            saturation = saturation,
            value = value,
            alpha = alpha,
            onValueChange = {
                value = it
            },
            onAlphaChange = {
                alpha = it
            }
        )
    }
}

@Composable
private fun HSSelectorWithHSVRectangleExample(modifier: Modifier, selectorModifier: Modifier) {
    var hue by remember { mutableStateOf(0f) }
    var saturation by remember { mutableStateOf(.5f) }
    var value by remember { mutableStateOf(.5f) }
    var alpha by remember { mutableStateOf(1f) }

    Title(text = "Hue-Saturation Selector Rect HSV")

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HueSaturationSelectorRectHSV(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4/3f),
            hue = hue,
            saturation = saturation,
            onChange = { h, s ->
                hue = h
                saturation = s
            },
            selectionRadius = 8.dp
        )
        Divider()
        SliderCircleColorDisplayValueHSV(
            modifier = Modifier.padding(8.dp),
            hue = hue,
            saturation = saturation,
            value = value,
            alpha = alpha,
            onValueChange = {
                value = it
            },
            onAlphaChange = {
                alpha = it
            }
        )
    }
}

@Composable
private fun HVSelectorWithHSVRectangleExample(modifier: Modifier, selectorModifier: Modifier) {
    var hue by remember { mutableStateOf(0f) }
    var saturation by remember { mutableStateOf(.5f) }
    var value by remember { mutableStateOf(.5f) }
    var alpha by remember { mutableStateOf(1f) }

    Title(text = "Hue-Value Selector Rect HSV")

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HueValueSelectorRectHSV(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4/3f),
            hue = hue,
            value = value,
            onChange = { h, v ->
                hue = h
                value = v
            },
            selectionRadius = 8.dp
        )
        Divider()
        SliderCircleColorDisplaySaturationHSV(
            modifier = Modifier.padding(8.dp),
            hue = hue,
            saturation = saturation,
            value = value,
            alpha = alpha,
            onSaturationChange = {
                saturation = it
            },
            onAlphaChange = {
                alpha = it
            }
        )
    }
}

@Composable
private fun HSSelectorWithHSLRectangleExample(modifier: Modifier, selectorModifier: Modifier) {
    var hue by remember { mutableStateOf(0f) }
    var saturation by remember { mutableStateOf(.5f) }
    var lightness by remember { mutableStateOf(.5f) }
    var alpha by remember { mutableStateOf(1f) }

    Title(text = "Hue-Saturation Selector Rect HSL")

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HueSaturationSelectorRectHSL(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4/3f),
            hue = hue,
            saturation = saturation,
            onChange = { h, s ->
                hue = h
                saturation = s
            },
            selectionRadius = 8.dp
        )
        Divider()
        SliderCircleColorDisplayLightnessHSL(
            modifier = Modifier.padding(8.dp),
            hue = hue,
            saturation = saturation,
            lightness = lightness,
            alpha = alpha,
            onLightnessChange = {
                lightness = it
            },
            onAlphaChange = {
                alpha = it
            }
        )
    }
}

@Composable
private fun HLSelectorWithHSLRectangleExample(modifier: Modifier, selectorModifier: Modifier) {
    var hue by remember { mutableStateOf(0f) }
    var saturation by remember { mutableStateOf(.5f) }
    var lightness by remember { mutableStateOf(.5f) }
    var alpha by remember { mutableStateOf(1f) }

    Title(text = "Hue-Lightness Selector Rect HSL")

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HueLightnessSelectorRectHSL(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4/3f),
            hue = hue,
            lightness = lightness,
            onChange = { h, l ->
                hue = h
                lightness = l
            },
            selectionRadius = 8.dp
        )
        Divider()
        SliderCircleColorDisplaySaturationHSL(
            modifier = Modifier.padding(8.dp),
            hue = hue,
            saturation = saturation,
            lightness = lightness,
            alpha = alpha,
            onSaturationChange = {
                saturation = it
            },
            onAlphaChange = {
                alpha = it
            }
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
