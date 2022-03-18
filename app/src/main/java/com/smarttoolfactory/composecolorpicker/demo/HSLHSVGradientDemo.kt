package com.smarttoolfactory.composecolorpicker.demo

import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.drawBlendingRectGradient
import com.smarttoolfactory.colorpicker.drawIntoLayer
import com.smarttoolfactory.colorpicker.rhombusPath
import com.smarttoolfactory.colorpicker.ui.*
import com.smarttoolfactory.composecolorpicker.ui.theme.backgroundColor
import kotlin.math.roundToInt
import kotlin.math.sqrt

/**
 * Demo for creating gradients for different type of pickers or sliders
 */
@Composable
fun HSVHSLGradientsDemo() {

    val canvasModifier = Modifier.size(300.dp)

    // Offsets for gradients based on selected angle
    var gradientOffset by remember {
        mutableStateOf(GradientOffset(GradientAngle.CCW0))
    }

    var angleSelection by remember { mutableStateOf(0f) }
    var angleText by remember { mutableStateOf("0 Degrees") }


    val whiteToBlackGradient = whiteToBlackGradient()

    val hsvGradient = whiteToSaturationForHSVGradient(
        hue = 0f,
        start = gradientOffset.start,
        end = gradientOffset.end
    )
    val hslGradient = whiteToSaturationForHSLGradient(
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

                println("🔥 Angle selection: $angleSelection")

                gradientOffset = when (angleSelection.roundToInt()) {
                    0 -> {
                        angleText = "0 Degrees"
                        GradientOffset(GradientAngle.CCW0)
                    }
                    1 -> {
                        angleText = "45 Degrees"
                        GradientOffset(GradientAngle.CCW45)
                    }
                    2 -> {
                        angleText = "90 Degrees"
                        GradientOffset(GradientAngle.CCW90)
                    }
                    3 -> {
                        angleText = "135 Degrees"
                        GradientOffset(GradientAngle.CCW135)
                    }
                    4 -> {
                        angleText = "180 Degrees"
                        GradientOffset(GradientAngle.CCW180)
                    }

                    5 -> {
                        angleText = "225 Degrees"
                        GradientOffset(GradientAngle.CCW225)
                    }
                    6 -> {
                        angleText = "270 Degrees"
                        GradientOffset(GradientAngle.CCW270)
                    }
                    else -> {
                        angleText = "315 Degrees"
                        GradientOffset(GradientAngle.CCW315)
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

            // Create gradients for rectangle Hue selector
            HuePickerRectangleGradientExample(
                modifier = canvasModifier,
                hsvGradient = hsvGradient,
                hslGradient = hslGradient
            )

            // Create gradients for rhombus Hue selector
            HuePickerRhombusGradientExample(modifier = canvasModifier)

            // Create gradients for rectangle Hue selector using
            // Saturation and Value points
            HuePickerPointsGradientExample(modifier = canvasModifier)

            // Create gradients for rectangle hue saturation or value/lightness selector
            RectangleColorPickerGradientExample(
                modifier = canvasModifier,
                colorScaleHSLGradient = colorScaleHSLGradient,
                colorScaleHSVGradient = colorScaleHSVGradient
            )

            // Creat gradients for circle hue-saturation selector
            CirclePickerGradientExample(modifier = canvasModifier)

            // Create gradients for different type of sliders such as Saturation, Value, RGB
            SliderGradientExample(gradientOffset = gradientOffset)
        }
    }
}

@Composable
private fun HuePickerRectangleGradientExample(
    modifier: Modifier,
    hsvGradient: Brush,
    hslGradient: Brush
) {
    val whiteToBlackGradient = whiteToBlackGradient()

    CanvasWithTitle(
        modifier = modifier,
        text = "HSV Selection Brush"
    ) {
        drawBlendingRectGradient(
            dst = whiteToBlackGradient,
            src = hsvGradient
        )
    }

    CanvasWithTitle(
        modifier = modifier.background(Color.LightGray),
        text = "HSL Selection Brush"
    ) {
        drawBlendingRectGradient(
            dst = whiteToBlackGradient,
            src = hslGradient
        )
    }
}

@Composable
private fun HuePickerRhombusGradientExample(modifier: Modifier) {

    // 🔥 This is a rectangle within borders of canvas that is rotate 45 degrees
    // with a gradient starts at left top position of rectangle and ends right top
    // position of rectangle
    CanvasWithTitle(
        modifier = modifier.background(Color.LightGray),
        text = "HSL Rhombus\n" +
                "Rotated Rectangle"
    ) {

        val canvasWidth = size.width
        /*
            Calculate a square positions and length
            inside square shaped Canvas with each
            sides have length of rhombus
         */

        // calc square root of sum of two sides with
        // half of width of canvas to find rhombus length
        val canvasCenHalfWidth = canvasWidth / 2
        val res = canvasCenHalfWidth * canvasCenHalfWidth

        val rhombusLength = sqrt(res + res)
        val start = (canvasWidth - rhombusLength) / 2
        val end = canvasWidth - start

        val whiteBlackGradient = Brush.linearGradient(
            colors = listOf(Color.Black, Color.White),
            start = Offset(start, end),
            end = Offset(start, start)
        )

        val rhombusHsl = Brush.linearGradient(
            colors = listOf(
                Color.White,
                Color.hsv(0f, 1f, 1f)
            ),
            start = Offset(start, start),
            end = Offset(end, start)
        )

        // 🔥 Rotate rectangle with rhombus length to have a rhombus
        withTransform(
            {
                rotate(45f)
            }
        ) {

            drawIntoLayer {
                drawRect(
                    brush = whiteBlackGradient,
                    topLeft = Offset(start, start),
                    size = Size(rhombusLength, rhombusLength)
                )

                drawRect(
                    brush = rhombusHsl,
                    topLeft = Offset(start, start),
                    size = Size(rhombusLength, rhombusLength),
                    blendMode = BlendMode.Multiply
                )
            }
        }
    }

    CanvasWithTitle(
        modifier = modifier.background(Color.LightGray),
        text = "HSL Rhombus"
    ) {

        val canvasWidth = size.width

        // Positions for start and end for white to hue gradient
        val startHueX = canvasWidth / 2
        val startHueY = 0f
        val endHueX = canvasWidth
        val endHueY = canvasWidth / 2

        // Positions for start and end for white to black gradient
        val startBWX = canvasWidth / 4
        val startBWY = canvasWidth * 3 / 4
        val endBWX = canvasWidth * 3 / 4
        val endBWY = canvasWidth / 4

        val whiteBlackGradient = Brush.linearGradient(
            colors = listOf(Color.Black, Color.White),
            start = Offset(startBWX, startBWY),
            end = Offset(endBWX, endBWY)
        )

        val rhombusHsl = Brush.linearGradient(
            colors = listOf(
                Color.White,
                Color.hsl(0f, 1f, .5f)
            ),
            start = Offset(startHueX, startHueY),
            end = Offset(endHueX, endHueY)
        )

        val rhombusPath = rhombusPath(Size(canvasWidth, canvasWidth))

        drawIntoLayer {
            drawPath(
                path = rhombusPath(size = Size(canvasWidth, canvasWidth)),
                whiteBlackGradient
            )
            drawPath(
                path = rhombusPath,
                rhombusHsl,
                blendMode = BlendMode.Multiply
            )
        }

        // Debug Position
        drawCircle(Color.Green, center = Offset(startBWX, startBWY), radius = 8f)
        drawCircle(Color.Yellow, center = Offset(endBWX, endBWY), radius = 8f)

        drawCircle(Color.Cyan, center = Offset(startHueX, startHueY), radius = 8f)
        drawCircle(Color.Magenta, center = Offset(endHueX, endHueY), radius = 8f)
    }
}

@Composable
private fun HuePickerPointsGradientExample(modifier: Modifier) {
    CanvasWithTitle(
        modifier = modifier,
        text = "Draw Rectangles on positions\n" +
                "50 samples"
    ) {
        drawSaturationLightnessWithSampling(sampleRate = 50)
    }

    CanvasWithTitle(
        modifier = modifier,
        text = "Draw Rectangles on positions\n" +
                "10 samples"
    ) {
        drawSaturationLightnessWithSampling(sampleRate = 10)
    }
}

@Composable
private fun RectangleColorPickerGradientExample(
    modifier: Modifier,
    colorScaleHSLGradient: Brush,
    colorScaleHSVGradient: Brush
) {

    // Transparent to White or Black or from White to Black with transparent colors between
    val transparentToBlackGradient = transparentToBlackVerticalGradient()
    val transparentToWhiteGradient = transparentToWhiteVerticalGradient()
    val transparentToGrayGradient = transparentToGrayVerticalGradient()
    val transitionGradient = whiteToTransparentToBlackVerticalGradient()

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



    CanvasWithTitle(
        modifier = modifier,
        text = "HSL Color Scale Gradient\n" +
                "with black gradient"

    ) {
        drawRect(colorScaleHSLGradient)
        drawRect(transparentToBlackGradient)
    }

    CanvasWithTitle(
        modifier = modifier,
        text = "HSL Color Scale Gradient\n" +
                "with white gradient"

    ) {
        drawRect(colorScaleHSLGradient)
        drawRect(transparentToWhiteGradient)
    }

    CanvasWithTitle(
        modifier = modifier,
        text = "HSL Color Scale Gradient\n" +
                "with white gradient"

    ) {
        drawRect(colorScaleHSLGradient)
        drawRect(transparentToGrayGradient)
    }

    CanvasWithTitle(
        modifier = modifier,
        text = "HSL Color Scale with\n" +
                "white to black transition"

    ) {
        drawRect(colorScaleHSLGradient)
        drawRect(transitionGradient)
    }

}

@Composable
private fun CirclePickerGradientExample(modifier: Modifier) {
    CanvasWithTitle(
        modifier = modifier,
        text = "Hue Saturation Circle(HSV)"

    ) {
        val colorScaleHSVSweep = Brush.sweepGradient(gradientColorScaleHSVReversed)
        val whiteToTransparentRadial = Brush.radialGradient(
            colors = listOf(Color.White, Color(0x00FFFFFF))
        )
        drawCircle(colorScaleHSVSweep)
        drawCircle(whiteToTransparentRadial)
    }

    CanvasWithTitle(
        modifier = modifier,
        text = "Hue Saturation Circle(HSL)"

    ) {
        val colorScaleHSLSweep = Brush.sweepGradient(gradientColorScaleHSLReversed)
        val whiteToTransparentRadial = Brush.radialGradient(
            colors = listOf(Color.White, Color(0x00FFFFFF))
        )
        drawCircle(colorScaleHSLSweep)
        drawCircle(whiteToTransparentRadial)
    }
}

@Composable
private fun SliderGradientExample(gradientOffset: GradientOffset) {
    val sliderModifier = Modifier
        .width(300.dp)
        .height(20.dp)

    // Colors for HSV
    val sliderHueSelectionHSVGradient = sliderHueSelectionHSVGradient(
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
    val sliderColorScaleHSLGradient = sliderHueSelectionHSLGradient(
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
    val sliderLightnessGradient2Stops = sliderLightnessGradient2Stops(
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

@Composable
private fun CanvasWithTitle(
    modifier: Modifier = Modifier,
    text: String,
    onDraw: DrawScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentWidth()
    ) {

        Text(
            text = text,
            color = Blue400,
            modifier = Modifier
                .padding(8.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Canvas(modifier = modifier, onDraw = onDraw)
    }
}

private fun DrawScope.drawSaturationLightnessWithSampling(sampleRate: Int) {

    val width = size.width
    val samplingRate = (width / sampleRate).toInt()

    for (x in 0 until width.toInt() - samplingRate step samplingRate)
        for (y in 0 until width.toInt() - samplingRate step samplingRate) {
            val saturation = x / width
            val value = 1 - (y / width)
            drawRect(
                color = Color.hsv(0f, saturation, value),
                size = Size(samplingRate.toFloat(), samplingRate.toFloat()),
                topLeft = Offset(x.toFloat(), y.toFloat())
            )
        }
}
