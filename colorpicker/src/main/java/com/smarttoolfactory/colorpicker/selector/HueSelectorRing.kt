package com.smarttoolfactory.colorpicker.selector

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.consumeDownChange
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.calculateAngleFomLocalCoordinates
import com.smarttoolfactory.colorpicker.calculateDistanceFromCenter
import com.smarttoolfactory.colorpicker.ui.gradientColorScaleRGB
import com.smarttoolfactory.gesture.pointerMotionEvents

/**
 * Hue selector Ring that selects hue based on rotation of circle selector.
 *
 * @param hue
 * @param outerRadiusRatio
 * @param innerRadiusRatio
 * @param borderStroke
 * @param borderStrokeWidth
 * @param backgroundColor
 * @param selectionRadius radius of selection circle that moves based on touch position
 */
@Composable
fun HueSelectorRing(
    modifier: Modifier = Modifier,
    hue: Float,
    outerRadiusRatio: Float = .9f,
    innerRadiusRatio: Float = .6f,
    borderStroke: Color = Color.Black,
    borderStrokeWidth: Dp = 2.dp,
    backgroundColor: Color = Color.Black,
    selectionRadius: Dp = Dp.Unspecified,
    onChange: (Int) -> Unit
) {

    BoxWithConstraints(modifier) {

        require(maxWidth == maxHeight)

        // Angle from center is required to get Hue which is between 0-360
        var angle by remember { mutableStateOf(hue.toInt()) }
        angle = hue.toInt()

        val density = LocalDensity.current.density

        // Check if user touches between the valid area of circle
        var isTouched by remember { mutableStateOf(false) }

        val width = constraints.maxWidth.toFloat()
        val radius = width / 2

        // Center position of color picker
        val center = Offset(radius, radius)

        val radiusInner: Float = (radius * innerRadiusRatio).coerceAtLeast(0f)
        val radiusOuter: Float = (radius * outerRadiusRatio).coerceAtLeast(radiusInner)

        /**
         * Circle selector radius for setting [angle] which sets hue
         */
        val selectorRadius =
            if (selectionRadius == Dp.Unspecified)
                (radiusInner * 2 * .04f).coerceAtMost(radiusOuter - radiusInner)
            else selectionRadius.value * density

        val colorPickerModifier = modifier
            .clipToBounds()
            .pointerMotionEvents(
                onDown = {
                    val position = it.position
                    // Distance from center to touch point
                    val distance = calculateDistanceFromCenter(center, position)

                    // if distance is between inner and outer radius then we touched valid area
                    isTouched = (distance in radiusInner..radiusOuter)
                    if (isTouched) {
                        angle = calculateAngleFomLocalCoordinates(center, position)
                        onChange(angle)
                        it.consumeDownChange()
                    }

                },
                onMove = {
                    if (isTouched) {
                        val position = it.position
                        angle = calculateAngleFomLocalCoordinates(center, position)
                        onChange(angle)
                        it.consumePositionChange()
                    }

                },
                onUp = {
                    if (isTouched) {
                        it.consumeDownChange()
                    }
                    isTouched = false

                },
                delayAfterDownInMillis = 20
            )

        Canvas(modifier = colorPickerModifier) {

            val strokeWidth = (radiusOuter - radiusInner)

            drawCircle(
                brush = Brush.sweepGradient(colors = gradientColorScaleRGB, center = center),
                radius = radiusInner + strokeWidth / 2,

                style = Stroke(
                    width = strokeWidth
                )
            )

            // This is background color from center to inner radius
            drawCircle(color = backgroundColor, radius = radiusInner)

            // Stroke draws half in and half out of the current radius.
            // with 200 radius 20 stroke width starts from 190 and ends at 210
            drawCircle(Color.Black, radiusInner - 7f, style = Stroke(width = 14f))
            drawCircle(Color.Black, radiusOuter + 7f, style = Stroke(width = 14f))

            // rotate selection circle based on hue value
            withTransform(
                {
                    rotate(degrees = -angle.toFloat())
                }
            ) {
                // draw hue selection circle
                drawCircle(
                    Color.White,
                    radius = selectorRadius,
                    center = Offset(center.x + radiusInner + strokeWidth / 2f, center.y),
                    style = Stroke(width = selectorRadius / 2)
                )
            }
        }
    }
}
