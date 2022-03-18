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
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.rhombusPath
import com.smarttoolfactory.colorpicker.ui.*
import com.smarttoolfactory.composecolorpicker.ui.theme.backgroundColor
import kotlin.math.roundToInt
import kotlin.math.sqrt


@Composable
fun HSVHSLGradientsDemo() {

    val canvasSize = 300.dp

    val dimension = with(LocalDensity.current) {
        canvasSize.toPx()
    }

    val canvasModifier = Modifier
        .size(canvasSize)

    val sliderModifier = Modifier
        .width(300.dp)
        .height(20.dp)


    val whiteToBlackGradient = whiteToBlackGradient()

    // Offsets for gradients based on selected angle
    var gradientOffset by remember {
        mutableStateOf(GradientOffset(GradientAngle.CCW0))
    }

    var angleSelection by remember { mutableStateOf(0f) }
    var angleText by remember { mutableStateOf("0 Degrees") }


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

    // Transparent to White or Black or from White to Black with transparent colors between
    val transparentToBlackGradient = transparentToBlackVerticalGradient()
    val transparentToWhiteGradient = transparentToWhiteVerticalGradient()
    val transparentToGrayGradient = transparentToGrayVerticalGradient()
    val transitionGradient = whiteToTransparentToBlackVerticalGradient()

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

            CanvasWithTitle(
                modifier = canvasModifier,
                text = "HSV Selection Brush"
            ) {
                drawBlendingRectGradient(
                    dst = whiteToBlackGradient,
                    src = hsvGradient
                )
            }

            CanvasWithTitle(
                modifier = canvasModifier.background(Color.LightGray),
                text = "HSL Selection Brush"
            ) {
                drawBlendingRectGradient(
                    dst = whiteToBlackGradient,
                    src = hslGradient
                )
            }



            CanvasWithTitle(
                modifier = canvasModifier.background(Color.LightGray),
                text = "HSL Rhombus"
            ) {

                val canvasWidth = size.width
                val rhombusLength = sqrt(canvasWidth * canvasWidth)
                val rhombusPath = rhombusPath(size = Size(rhombusLength, rhombusLength))

                val res = (canvasWidth / 2) * (canvasWidth / 2)
                val r = sqrt(res + res)
                val start = (canvasWidth - r) / 2
                val end = canvasWidth -start

                println("🔥 Dimension: $dimension, canvasWidth: $canvasWidth, rhombusLength: $rhombusLength, res: $res, r: $r")


                val whiteBlackGradient = Brush.linearGradient(
                    colors = listOf(Color.Black, Color.White),
                    start = Offset(start,end),
                    end =  Offset(start,start)
                )


                val rhombusHsl = Brush.linearGradient(
                    colors = listOf(
                        Color.White,
                        Color.hsl(0f, 1f, .5f)
                    ),
                    start = Offset(start,start),
                    end =  Offset(end,start)
                )


                withTransform(
                    {
                        rotate(45f)
                    }
                ) {



                    with(drawContext.canvas.nativeCanvas) {
                        val checkPoint = saveLayer(null, null)

                        drawRect(
                            brush = whiteBlackGradient,
                            topLeft = Offset(start, start),
                            size = Size(r, r)
                        )

                        drawRect(
                            brush = rhombusHsl,
                            topLeft = Offset(start, start),
                            size = Size(r, r),
                            blendMode = BlendMode.Multiply
                        )

//                        drawPath(
//                            path = rhombusPath(size = Size(dimension, dimension)),
//                            whiteBlackGradient
//                        )
//
//                        drawPath(
//                            path = rhombusPath,
//                            rhombusHsl,
//                            blendMode = BlendMode.Multiply
//                        )
                        restoreToCount(checkPoint)
                    }
                }
            }

            CanvasWithTitle(
                modifier = canvasModifier,
                text = "Draw Rectangles on positions\n" +
                        "50 samples"
            ) {
                drawSaturationLightnessWithSampling(sampleRate = 50)
            }

            CanvasWithTitle(
                modifier = canvasModifier,
                text = "Draw Rectangles on positions\n" +
                        "10 samples"
            ) {
                drawSaturationLightnessWithSampling(sampleRate = 10)
            }

            CanvasWithTitle(
                modifier = canvasModifier,
                text = "HSV Color Scale Gradient"

            ) {
                drawRect(colorScaleHSVGradient)
            }

            CanvasWithTitle(
                modifier = canvasModifier,
                text = "HSL Color Scale Gradient"

            ) {
                drawRect(colorScaleHSLGradient)
            }

            CanvasWithTitle(
                modifier = canvasModifier,
                text = "HSL Color Scale Gradient\n" +
                        "with black gradient"

            ) {
                drawRect(colorScaleHSLGradient)
                drawRect(transparentToBlackGradient)
            }

            CanvasWithTitle(
                modifier = canvasModifier,
                text = "HSL Color Scale Gradient\n" +
                        "with white gradient"

            ) {
                drawRect(colorScaleHSLGradient)
                drawRect(transparentToWhiteGradient)
            }

            CanvasWithTitle(
                modifier = canvasModifier,
                text = "HSL Color Scale Gradient\n" +
                        "with white gradient"

            ) {
                drawRect(colorScaleHSLGradient)
                drawRect(transparentToGrayGradient)
            }

            CanvasWithTitle(
                modifier = canvasModifier,
                text = "HSL Color Scale with\n" +
                        "white to black transition"

            ) {
                drawRect(colorScaleHSLGradient)
                drawRect(transitionGradient)
            }

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
        }
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

private fun DrawScope.drawBlendingRectGradient(
    dst: Brush,
    dstTopLeft: Offset = Offset.Zero,
    dstSize: Size = this.size,
    src: Brush,
    srcTopLeft: Offset = Offset.Zero,
    srcSize: Size = this.size,
) {
    with(drawContext.canvas.nativeCanvas) {
        val checkPoint = saveLayer(null, null)
        drawRect(dst, dstTopLeft, dstSize)
        drawRect(src, srcTopLeft, srcSize, blendMode = BlendMode.Multiply)
        restoreToCount(checkPoint)
    }
}
