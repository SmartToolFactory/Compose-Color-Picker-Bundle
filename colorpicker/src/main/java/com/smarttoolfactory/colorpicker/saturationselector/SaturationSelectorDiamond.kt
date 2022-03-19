package com.smarttoolfactory.colorpicker.saturationselector

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.consumeDownChange
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.drawIntoLayer
import com.smarttoolfactory.colorpicker.gesture.pointerMotionEvents
import com.smarttoolfactory.colorpicker.ui.GradientAngle
import com.smarttoolfactory.colorpicker.ui.GradientOffset


@Composable
fun SaturationPickerDiamondHSV(
    modifier: Modifier = Modifier,
    hue: Float,
    saturation: Float = 0.5f,
    value: Float = 0.5f,
    selectionRadius: Dp = (-1).dp,
    onChange: (Float, Float) -> Unit
) {

}

/**
 * This is a [HSL](https://en.wikipedia.org/wiki/HSL_and_HSV)
 * saturation and lightness selector in shape of [Diamond]
 *
 *  * Since this is not a rectangle, initially current position of selector is
 *  set by [setSelectorPositionFromColorParams] which limits position of saturation to a range
 *  determined by 1-length of dimension since sum of x and y position should be equal to
 *  **length of  diamond**.
 *
 *  * On touch and selection process same principle applies to bound positions.
 *
 *  * Since **lightness* should be on top but position system of **Canvas** starts from top-left
 *  corner(0,0) we need to reverse **lightness**.
 *
 */
@Composable
fun SaturationPickerDiamond(
    modifier: Modifier = Modifier,
    hue: Float,
    saturation: Float = .5f,
    lightness: Float = .5f,
    selectionRadius: Dp = (-1).dp,
    onChange: (Float, Float) -> Unit
) {

    BoxWithConstraints(modifier) {

        val density = LocalDensity.current.density

        /**
         * Width and height of the diamond is geometrically equal so it's sufficient to
         * use either width or height to have a length parameter
         */
        val length = maxWidth.value * density

        /**
         * Circle selector radius for setting [saturation] and [lightness] by gesture
         */
        val selectorRadius =
            if (selectionRadius > 0.dp) selectionRadius.value * density else length * .04f

        /**
         *  Current position is initially set by [saturation] and [lightness] that is bound
         *  in diamond since (1,1) points to bottom left corner of a rectangle but it's bounded
         *  in diamond by [setSelectorPositionFromColorParams].
         *  When user touches anywhere in diamond current position is updaed and
         *  this composable is recomposed
         */
        var currentPosition by remember(saturation, lightness) {
            mutableStateOf(
                setSelectorPositionFromColorParams(saturation, lightness, length)
            )
        }

        /**
         * Check if first pointer that touched this compsable inside bounds of diamond
         */
        var isTouched by remember { mutableStateOf(false) }


        val canvasModifier = Modifier
            .size(maxWidth)
            .pointerMotionEvents(
                onDown = {
                    val position = it.position
                    val posX = position.x
                    val posY = position.y

                    // Horizontal range for keeping x position in diamond bounds
                    val range = getRangeForPositionInDiamond(length, posY)
                    val start = range.start - selectorRadius
                    val end = range.endInclusive + selectorRadius

                    isTouched = posX in start..end
                    if (isTouched) {

                        val posXInPercent = (posX / length).coerceIn(0f, 1f)
                        val posYInPercent = (posY / length).coerceIn(0f, 1f)

                        // Send x position as saturation and reverse of y position as lightness
                        // lightness increases while going up but android drawing system is opposite
                        onChange(posXInPercent, 1 - posYInPercent)
                        currentPosition = Offset(posX, posY)
                    }
                    it.consumeDownChange()

                },
                onMove = {
                    if (isTouched) {

                        val position = it.position
                        val posX = position.x.coerceIn(0f, length)
                        val posY = position.y.coerceIn(0f, length)

                        // Horizontal range for keeping x position in diamond bounds
                        val range = getRangeForPositionInDiamond(length, posY)

                        val posXInPercent = (posX / length).coerceIn(0f, 1f)
                        val posYInPercent = (posY / length).coerceIn(0f, 1f)

                        // Send x position as saturation and reverse of y position as lightness
                        // lightness increases while going up but android drawing system is opposite
                        onChange(posXInPercent, 1 - posYInPercent)

                        currentPosition = Offset(posX.coerceIn(range), posY)
                    }
                    it.consumePositionChange()
                },
                onUp = {
                    isTouched = false
                    it.consumeDownChange()
                }
            )

        val diamondPath = remember { diamondPath(Size(length, length)) }

        val lightnessGradient = remember {
            Brush.verticalGradient(
                colors = listOf(
                    Color.hsl(hue = hue, saturation = .5f, lightness = 1f),
                    Color.hsl(hue = hue, saturation = .5f, lightness = 0f)
                )
            )
        }

        val saturationHSLGradient = remember(hue) {
            val gradientOffset = GradientOffset(GradientAngle.CW0)

            Brush.linearGradient(
                colors = listOf(
                    Color.hsl(hue, 0f, .5f),
                    Color.hsl(hue, 1f, .5f)
                ),
                start = gradientOffset.start,
                end = gradientOffset.end
            )
        }

        Canvas(modifier = canvasModifier) {

            drawIntoLayer {
                drawPath(
                    path = diamondPath,
                    lightnessGradient,
                )
                drawPath(
                    path = diamondPath,
                    saturationHSLGradient,
                    blendMode = BlendMode.Overlay
                )
            }

            // Saturation and Value or Lightness selector
            drawCircle(
                Color.White,
                radius = selectorRadius,
                center = currentPosition,
                style = Stroke(width = selectorRadius / 2)
            )
        }
    }
}

/**
 * This is for setting initial position of selector when saturation and lightness is set by
 * an external Composable. Without setting a bound
 * `saturation=1f and lightness
 *
 * @param saturation of the current color from Composable parameters
 * @param lightness of the current color from Composable parameters
 * @param length of the diamond
 */
fun setSelectorPositionFromColorParams(
    saturation: Float,
    lightness: Float,
    length: Float
): Offset {
    // Get possible horizontal range for the current position of lightness on diamond
    val range = getRangeForPositionInDiamond(length, lightness * length)
    // Since lightness must increase while going up we need to reverse position
    val verticalPositionOnDiamond = (1 - lightness) * length
    // limit saturation bounds to range to not overflow from diamond
    val horizontalPositionOnDiamond = (saturation * length).coerceIn(range)
    return Offset(horizontalPositionOnDiamond, verticalPositionOnDiamond)
}

/**
 * Returns [ClosedFloatingPointRange] that this position can be in a diamond wit.
 * Limits available range inside diamond.
 * For instance if y position is 10, then x should either be center - 10 or center + 10 to maintain
 * triangular bounds in both axes.
 *
 * @param length of the diamond
 * @param position current position in x,y coordinates in diamond
 */
fun getRangeForPositionInDiamond(
    length: Float,
    position: Float
): ClosedFloatingPointRange<Float> {

    val center = length / 2
    // If it's at top half length in y axis is the same as left and right part in x axis
    return if (position <= center) {
        (center - position)..(center + position)
    } else {
        // If vertical position is below center we just need to use length between bottom and
        // current position to get horizontal range
        val heightAfterCenter = length - position
        (center - heightAfterCenter)..(center + heightAfterCenter)
    }
}


/**
 * Diamond path as below with equal length and width
 * ```
 *      / \
 *     /   \
 *    /     \
 *    \     /
 *     \   /
 *      \ /
 * ```
 */
fun diamondPath(size: Size) = Path().apply {

    moveTo(size.width / 2f, 0f)
    lineTo(size.width, size.height / 2f)
    lineTo(size.width / 2f, size.height)
    lineTo(0f, size.height / 2f)
}

val diamondShape = GenericShape { size: Size, _: LayoutDirection ->
    moveTo(size.width / 2f, 0f)
    lineTo(size.width, size.height / 2f)
    lineTo(size.width / 2f, size.height)
    lineTo(0f, size.height / 2f)
}


