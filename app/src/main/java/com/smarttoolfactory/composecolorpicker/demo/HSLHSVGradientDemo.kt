package com.smarttoolfactory.composecolorpicker.demo

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.ui.*
import com.smarttoolfactory.composecolorpicker.ui.theme.backgroundColor


@Composable
fun HSVHSLGradientsDemo() {


    val canvasSize = 300.dp
    val dimension = with(LocalDensity.current) {
        canvasSize.toPx()
    }

    val whiteToBlackGradient = whiteToBlackGradient(endy = dimension)
    val hsvGradient = whiteToSaturationForHSVGradient(hue = 0f, end = Offset(dimension, 0f))
    val hslGradient = whiteToSaturationForHSLGradient(hue = 0f, end = Offset(dimension, 0f))

    //  Red, Magenta, Blue, Cyan, Green, Yellow, Red
    val colorScaleHSVGradient = Brush.linearGradient(
        colors = gradientColorScaleHSV,
        end = Offset(dimension, 0f)
    )
    val colorScaleHSLGradient =
        Brush.linearGradient(
            colors = gradientColorScaleHSL,
            end = Offset(dimension, 0f)
        )

    // Transparent to White or Black or from White to Black with transparent colors between
    val transparentToBlackGradient = transparentToBlackVerticalGradient()
    val transparentToWhiteGradient = transparentToWhiteVerticalGradient()
    val transparentToGrayGradient = transparentToGrayVerticalGradient()
    val transitionGradient = whiteToTransparentToBlackVerticalGradient()

    val canvasModifier = Modifier
        .size(canvasSize)

    val sliderModifier = Modifier
        .width(300.dp)
        .height(12.dp)

    Column(
        Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        CanvasWithTitle(
            modifier = canvasModifier,
            text = "Lightness/Value"
        ) {
            drawRect(whiteToBlackGradient)
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
            modifier = canvasModifier,
            text = "HSL Selection Brush"
        ) {
            drawBlendingRectGradient(
                dst = whiteToBlackGradient,
                src = hslGradient
            )
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
            text = "Draw Rectangles on positions\n" +
                    "50 samples"
        ) {
            drawSaturationLightnessWithSampling(sampleRate = 50)
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

        val sliderHueSelectionHSVGradient =
            sliderHueSelectionHSVGradient(end = Offset(dimension, 0f))
        val sliderSaturationHSVGradient =
            sliderSaturationHSVGradient(hue = 0f, end = Offset(dimension, 0f))
        val sliderValueGradient = sliderValueGradient(hue = 0f, end = Offset(dimension, 0f))

        val sliderColorScaleHSLGradient = sliderHueSelectionHSLGradient(end = Offset(dimension, 0f))
        val sliderSaturationHSLGradient =
            sliderSaturationHSLGradient(hue = 0f, end = Offset(dimension, 0f))
        val sliderLightnessGradient = sliderLightnessGradient(hue = 0f, end = Offset(dimension, 0f))
        val sliderLightnessGradient2 =
            sliderLightnessGradient2(hue = 0f, end = Offset(dimension, 0f))

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
            drawRoundRect(sliderLightnessGradient2, cornerRadius = CornerRadius(25f, 25f))
        }

        CanvasWithTitle(modifier = sliderModifier, text = "sliderLightnessGradient") {
            drawRoundRect(sliderLightnessGradient, cornerRadius = CornerRadius(25f, 25f))
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
