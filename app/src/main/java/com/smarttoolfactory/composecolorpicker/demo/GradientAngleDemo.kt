package com.smarttoolfactory.composecolorpicker.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.ui.*
import com.smarttoolfactory.colorpicker.ui.brush.*
import com.smarttoolfactory.composecolorpicker.ui.CanvasWithTitle
import com.smarttoolfactory.composecolorpicker.ui.theme.backgroundColor
import kotlin.math.roundToInt

/**
 * Demo for creating gradients for different type of pickers or sliders
 */
@Composable
fun GradientAngleDemo() {

    val canvasModifier = Modifier.size(300.dp)

    // Offsets for gradients based on selected angle
    var gradientOffset by remember {
        mutableStateOf(GradientOffset(GradientAngle.CW0))
    }

    var angleSelection by remember { mutableStateOf(0f) }
    var angleText by remember { mutableStateOf("0 Degrees") }


    val whiteToBlackGradient = whiteToBlackGradient()

    val hsvGradient = saturationHSVGradient(
        hue = 0f,
        start = gradientOffset.start,
        end = gradientOffset.end
    )
    val hslGradient = saturationHSLGradient(
        hue = 0f,
        start = gradientOffset.start,
        end = gradientOffset.end
    )

    //  Red, Magenta, Blue, Cyan, Green, Yellow, Red
    val colorScaleHSVGradient = Brush.linearGradient(
        colors = gradientColorScaleHSV,
        start = gradientOffset.start,
        end = gradientOffset.end
    )
    val colorScaleHSLGradient = Brush.linearGradient(
        colors = gradientColorScaleHSL,
        start = gradientOffset.start,
        end = gradientOffset.end
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(8.dp)
    ) {

        Text(
            text = angleText,
            color = Blue400,
            modifier = Modifier
                .padding(8.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Slider(
            modifier = Modifier.height(50.dp),
            value = angleSelection,
            onValueChange = {
                angleSelection = it

                println("🔥Angle selection: $angleSelection")

                gradientOffset = when (angleSelection.roundToInt()) {
                    0 -> {
                        angleText = "0 Degrees"
                        GradientOffset(GradientAngle.CW0)
                    }
                    1 -> {
                        angleText = "45 Degrees"
                        GradientOffset(GradientAngle.CW45)
                    }
                    2 -> {
                        angleText = "90 Degrees"
                        GradientOffset(GradientAngle.CW90)
                    }
                    3 -> {
                        angleText = "135 Degrees"
                        GradientOffset(GradientAngle.CW135)
                    }
                    4 -> {
                        angleText = "180 Degrees"
                        GradientOffset(GradientAngle.CW180)
                    }

                    5 -> {
                        angleText = "225 Degrees"
                        GradientOffset(GradientAngle.CW225)
                    }
                    6 -> {
                        angleText = "270 Degrees"
                        GradientOffset(GradientAngle.CW270)
                    }
                    else -> {
                        angleText = "315 Degrees"
                        GradientOffset(GradientAngle.CW315)
                    }
                }
            },
            steps = 6,
            valueRange = 0f..7f
        )

        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CanvasWithTitle(
                modifier = canvasModifier,
                text = "Gradient Angle"
            ) {
                val redGreenGradient = Brush.linearGradient(
                    colors = listOf(Color.Red, Color.Green, Color.Blue),
                    start = gradientOffset.start,
                    end = gradientOffset.end
                )
                drawRect(redGreenGradient)
            }

            CanvasWithTitle(
                modifier = canvasModifier,
                text = "Lightness/Value"
            ) {
                drawRect(whiteToBlackGradient)
            }

            CanvasWithTitle(
                modifier = canvasModifier,
                text = "HSV Saturation"
            ) {
                drawRect(hsvGradient)
            }

            CanvasWithTitle(
                modifier = canvasModifier,
                text = "HSL Saturation"
            ) {
                drawRect(hslGradient)
            }

            // Create gradients for rectangle hue saturation or value/lightness selector
            RectangleColorPickerGradientExample(
                modifier = canvasModifier,
                colorScaleHSLGradient = colorScaleHSLGradient,
                colorScaleHSVGradient = colorScaleHSVGradient
            )

            // Create gradients for different type of sliders such as Saturation, Value, RGB
            SliderGradientExample(gradientOffset = gradientOffset)
        }
    }
}

@Composable
private fun RectangleColorPickerGradientExample(
    modifier: Modifier,
    colorScaleHSLGradient: Brush,
    colorScaleHSVGradient: Brush
) {

    CanvasWithTitle(
        modifier = modifier,
        text = "HSV Color Scale Gradient"

    ) {
        drawRect(colorScaleHSVGradient)
    }

    CanvasWithTitle(
        modifier = modifier,
        text = "HSL Color Scale Gradient"

    ) {
        drawRect(colorScaleHSLGradient)
    }
}

@Composable
private fun SliderGradientExample(gradientOffset: GradientOffset) {
    val sliderModifier = Modifier
        .width(300.dp)
        .height(20.dp)

    // Colors for HSV
    val sliderHueSelectionHSVGradient = sliderHueHSVGradient(
        start = gradientOffset.start,
        end = gradientOffset.end
    )
    // Saturation for HSV
    val sliderSaturationHSVGradient = sliderSaturationHSVGradient(
        hue = 0f,
        start = gradientOffset.start,
        end = gradientOffset.end
    )

    // Value for HSV
    val sliderValueGradient = sliderValueGradient(
        hue = 0f,
        start = gradientOffset.start,
        end = gradientOffset.end
    )

    // Colors for HSL
    val sliderColorScaleHSLGradient = sliderHueHSLGradient(
        start = gradientOffset.start,
        end = gradientOffset.end
    )
    // Saturation for HSL
    val sliderSaturationHSLGradient = sliderSaturationHSLGradient(
        hue = 0f,
        start = gradientOffset.start,
        end = gradientOffset.end
    )

    // Lightness for HSL with 2 stops, 0f->1f
    val sliderLightnessGradient2Stops = sliderLightnessGradient(
        hue = 0f,
        start = gradientOffset.start,
        end = gradientOffset.end
    )

    // Lightness for HSL with 3 stops, 0f->0.5f->1f
    val sliderLightnessGradient3Stops =
        sliderLightnessGradient3Stops(
            hue = 0f,
            start = gradientOffset.start,
            end = gradientOffset.end
        )

    // Black to Red
    val sliderRedGradient = sliderRedGradient(
        start = gradientOffset.start,
        end = gradientOffset.end
    )

    // Black to Green
    val sliderGreenGradient = sliderGreenGradient(
        start = gradientOffset.start,
        end = gradientOffset.end
    )

    // Black to Blue
    val sliderBlueGradient = sliderBlueGradient(
        start = gradientOffset.start,
        end = gradientOffset.end
    )

    /*
        HSV
     */
    CanvasWithTitle(modifier = sliderModifier, text = "sliderHueSelectionHSVGradient") {
        drawRoundRect(sliderHueSelectionHSVGradient, cornerRadius = CornerRadius(25f, 25f))
    }

    CanvasWithTitle(modifier = sliderModifier, text = "sliderSaturationHSVGradient") {
        drawRoundRect(sliderSaturationHSVGradient, cornerRadius = CornerRadius(25f, 25f))

    }

    CanvasWithTitle(modifier = sliderModifier, text = "sliderValueGradient") {
        drawRoundRect(sliderValueGradient, cornerRadius = CornerRadius(25f, 25f))
    }

    /*
        HSL
     */
    CanvasWithTitle(modifier = sliderModifier, text = "sliderColorScaleHSLGradient") {
        drawRoundRect(sliderColorScaleHSLGradient, cornerRadius = CornerRadius(25f, 25f))
    }

    CanvasWithTitle(modifier = sliderModifier, text = "sliderSaturationHSLGradient") {
        drawRoundRect(sliderSaturationHSLGradient, cornerRadius = CornerRadius(25f, 25f))
    }

    CanvasWithTitle(modifier = sliderModifier, text = "sliderLightnessGradient2") {
        drawRoundRect(sliderLightnessGradient3Stops, cornerRadius = CornerRadius(25f, 25f))
    }

    CanvasWithTitle(modifier = sliderModifier, text = "sliderLightnessGradient") {
        drawRoundRect(sliderLightnessGradient2Stops, cornerRadius = CornerRadius(25f, 25f))
    }


    /*
        RGB
     */

    CanvasWithTitle(modifier = sliderModifier, text = "Slider RGB Red") {
        drawRoundRect(sliderRedGradient, cornerRadius = CornerRadius(25f, 25f))
    }

    CanvasWithTitle(modifier = sliderModifier, text = "Slider RGB Green") {
        drawRoundRect(sliderGreenGradient, cornerRadius = CornerRadius(25f, 25f))
    }

    CanvasWithTitle(modifier = sliderModifier, text = "Slider RGB Blue") {
        drawRoundRect(sliderBlueGradient, cornerRadius = CornerRadius(25f, 25f))
    }
}
