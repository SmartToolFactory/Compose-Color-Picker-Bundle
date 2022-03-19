package com.smarttoolfactory.composecolorpicker.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.drawIntoLayer
import com.smarttoolfactory.colorpicker.saturationselector.diamondPath
import com.smarttoolfactory.colorpicker.ui.*
import com.smarttoolfactory.colorpicker.ui.brush.lightnessGradient
import com.smarttoolfactory.colorpicker.ui.brush.saturationHSLGradient
import com.smarttoolfactory.colorpicker.ui.brush.saturationHSVGradient
import com.smarttoolfactory.colorpicker.ui.brush.valueGradient
import com.smarttoolfactory.composecolorpicker.ui.CanvasWithTitle
import com.smarttoolfactory.composecolorpicker.ui.theme.backgroundColor
import kotlin.math.roundToInt

@Composable
fun HSVHSLGradientDemo() {

    val canvasModifier = Modifier.size(300.dp)

    // HSV Gradients
    val saturationHSVGradient = saturationHSVGradient(0f)
    val valueGradient = valueGradient(0f)

    // HSL Gradients
    val saturationHSLGradient = saturationHSLGradient(0f)
    val lightnessGradient = lightnessGradient(0f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        // Create gradients for rectangle Hue selector
        HuePickerHSVGradientExample(
            modifier = canvasModifier,
            saturationGradient = saturationHSVGradient,
            valueGradient = valueGradient,
        )

        // Create gradients for diamond Hue selector
        HuePickerHSLGradientExample(
            modifier = canvasModifier,
            saturationGradient = saturationHSLGradient,
            lightnessGradient = lightnessGradient
        )
    }
}

@Composable
private fun HuePickerHSVGradientExample(
    modifier: Modifier,
    saturationGradient: Brush,
    valueGradient: Brush,
) {

    CanvasWithTitle(
        modifier = modifier,
        text = "HSV Selection Brush"
    ) {
        drawIntoLayer {
            drawRect(valueGradient)
            drawRect(saturationGradient, blendMode = BlendMode.Multiply)
        }
    }

    CanvasWithTitle(
        modifier = modifier,
        text = "HSV with Rectangle positions\n" +
                "50 samples"
    ) {
        drawSaturationWithSampling(sampleRate = 50, hsv = true)
    }

    CanvasWithTitle(
        modifier = modifier,
        text = "HSV with Rectangle positions\n" +
                "10 samples"
    ) {
        drawSaturationWithSampling(sampleRate = 10, hsv = true)
    }

    CanvasWithTitle(
        modifier = modifier.background(Color.LightGray),
        text = "HSV Diamond"
    ) {
        val length = size.width
        val diamondPath = diamondPath(Size(length, length))

        drawIntoLayer {
            drawPath(
                path = diamondPath,
                valueGradient,
            )
            drawPath(
                path = diamondPath,
                saturationGradient,
                blendMode = BlendMode.Multiply
            )
        }
    }

    CanvasWithTitle(
        modifier = modifier,
        text = "HSV Diamond positions\n" +
                "50 samples"
    ) {
        drawSaturationPointsInDiamond(sampleRate = 60, hsv = true)
    }

    CanvasWithTitle(
        modifier = modifier,
        text = "HSV Diamond positions\n" +
                "10 samples"
    ) {
        drawSaturationPointsInDiamond(sampleRate =30, hsv = true)
    }


}

@Composable
private fun HuePickerHSLGradientExample(
    modifier: Modifier,
    saturationGradient: Brush,
    lightnessGradient: Brush
) {

    CanvasWithTitle(
        modifier = modifier,
        text = "HSL Selection Brush"
    ) {
        drawIntoLayer {
            drawRect(lightnessGradient)
            drawRect(saturationGradient, blendMode = BlendMode.Overlay)
        }
    }

    CanvasWithTitle(
        modifier = modifier,
        text = "HSL with Rectangle positions\n" +
                "50 samples"
    ) {
        drawSaturationWithSampling(sampleRate = 50, hsv = false)
    }

    CanvasWithTitle(
        modifier = modifier,
        text = "HSL with Rectangle positions\n" +
                "10 samples"
    ) {
        drawSaturationWithSampling(sampleRate = 10, hsv = false)
    }

    val lg = remember {
        Brush.verticalGradient(
            colors = listOf(
                Color.hsl(hue = 0f, saturation = .5f, lightness = 1f),
                Color.hsl(hue = 0f, saturation = .5f, lightness = 0f)
            )
        )
    }

    val sl = remember {
        val gradientOffset = GradientOffset(GradientAngle.CCW0)

        Brush.linearGradient(
            colors = listOf(
                Color.hsl(0f, 0f, .5f),
                Color.hsl(0f, 1f, .5f)
            ),
            start = gradientOffset.start,
            end = gradientOffset.end
        )
    }

    CanvasWithTitle(
        modifier = modifier.background(Color.LightGray),
        text = "HSL Diamond"
    ) {
        val length = size.width
        val diamondPath = diamondPath(Size(length, length))

        drawIntoLayer {
            drawPath(
                path = diamondPath,
                lightnessGradient,
            )
            drawPath(
                path = diamondPath,
                saturationGradient,
                blendMode = BlendMode.Overlay
            )
        }
    }

    CanvasWithTitle(
        modifier = modifier,
        text = "HSL Diamond positions\n" +
                "50 samples"
    ) {
        drawSaturationPointsInDiamond(sampleRate = 60, hsv = false)
    }

    CanvasWithTitle(
        modifier = modifier,
        text = "HSL Diamond positions\n" +
                "10 samples"
    ) {
        drawSaturationPointsInDiamond(sampleRate =30, hsv = false)
    }
}

/**
 * Draw rectangles with saturation and value/lightness based on parameters correspond to rectangle
 * positions with sampling amount
 */
private fun DrawScope.drawSaturationWithSampling(sampleRate: Int, hsv: Boolean) {

    val width = size.width
    val samplingRate = (width / sampleRate).toInt()

    for (x in 0 until width.toInt() - samplingRate step samplingRate)
        for (y in 0 until width.toInt() - samplingRate step samplingRate) {
            val saturation = x / width
            val value = 1 - (y / width)

            val colors = if (hsv) {
                Color.hsv(0f, saturation, value)
            } else {
                Color.hsl(0f, saturation, value)
            }
            drawRect(
                color = colors,
                size = Size(samplingRate.toFloat(), samplingRate.toFloat()),
                topLeft = Offset(x.toFloat(), y.toFloat())
            )
        }
}

/**
 * Get each point and saturation and lightness of the point. This function is for
 * creating points to draw like gradient effect for HSL color
 */
private fun DrawScope.drawSaturationPointsInDiamond(
    sampleRate: Int,
    hsv: Boolean
) {
    val width = size.width
    val samplingRate = (width / sampleRate).toInt()

    for (yPos in 0..width.toInt() step samplingRate) {
        val range = getIntRangeForPositionInDiamond(length = width, yPos.toFloat())
        for (xPos in range step samplingRate) {

            val saturation = xPos / width
            val value = 1 - (yPos / width)

            val colors = if (hsv) {
                Color.hsv(0f, saturation, value)
            } else {
                Color.hsl(0f, saturation, value)
            }
            drawRect(
                color = colors,
                size = Size(samplingRate.toFloat(), samplingRate.toFloat()),
                topLeft = Offset(xPos.toFloat(), yPos.toFloat())
            )
        }
    }

}

/**
 * Returns [IntRange] that this position can be in a diamond wit.
 * Limits available range inside diamond.
 * For instance if y position is 10, then x should either be center - 10 or center + 10 to maintain
 * triangular bounds in both axes.
 *
 * @param length of the diamond
 * @param position current position in x,y coordinates in diamond
 */
fun getIntRangeForPositionInDiamond(
    length: Float,
    position: Float
): IntRange {

    val center = length / 2
    // If it's at top half length in y axis is the same as left and right part in x axis
    return if (position <= center) {
        (center - position).roundToInt()..(center + position).roundToInt()
    } else {
        // If vertical position is below center we just need to use length between bottom and
        // current position to get horizontal range
        val heightAfterCenter = length - position
        (center - heightAfterCenter).roundToInt()..(center + heightAfterCenter).roundToInt()
    }
}

data class ColorPoint(
    val point: Offset,
    val saturation: Float,
    val lightness: Float
)


