package com.smarttoolfactory.colorpicker.selector

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeDownChange
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.smarttoolfactory.colorpicker.calculateAngleFomLocalCoordinates
import com.smarttoolfactory.colorpicker.calculateDistanceFromCenter
import com.smarttoolfactory.colorpicker.calculatePositionFromAngleAndDistance
import com.smarttoolfactory.colorpicker.ui.gradientColorScaleHSVReversed
import com.smarttoolfactory.gesture.pointerMotionEvents

/**
 * Circle Hue and Saturation picker. Angle is between touch point and center of this
 * selector is returned as [hue]
 * and distance from center as [saturation].
 * @param hue is in [0f..360f] of HSL color
 * @param saturation is in [0f..1f] of HSL color
 * @param selectionRadius radius of selection circle that moves based on touch position
 * @param onChange callback that returns [hue] and [saturation]
 *  when position of touch in this selector has changed.
 */
@Composable
fun HueSaturationSelectorCircle(
    modifier: Modifier = Modifier,
    hue: Float,
    saturation: Float,
    selectionRadius: Dp = Dp.Unspecified,
    onChange: (Float, Float) -> Unit
) {
    BoxWithConstraints(modifier.background(Color.Red)) {

        require(maxWidth == maxHeight) {
            "Hue selector should have equal width and height"
        }

        val density = LocalDensity.current.density

        // Check if user touches between the valid area of circle
        var isTouched by remember { mutableStateOf(false) }

        val width = constraints.maxWidth.toFloat()
        val radius = width / 2

        // Center position of color picker
        val center = Offset(radius, radius)

        var currentPosition by remember {
            mutableStateOf(center)
        }

        currentPosition = calculatePositionFromAngleAndDistance(
            distance = saturation * radius,
            angle = hue,
            center = center
        )

        val selectorRadius =
            if (selectionRadius != Dp.Unspecified) selectionRadius.value * density
            else width * .04f

        val colorScaleHSVSweep = remember { Brush.sweepGradient(gradientColorScaleHSVReversed) }
        val whiteToTransparentRadial = remember {
            Brush.radialGradient(
                colors = listOf(Color.White, Color(0x00FFFFFF))
            )
        }

        val colorPickerModifier = modifier
            .background(Color.Yellow)
            .pointerMotionEvents(
                onDown = {
                    val position = it.position

                    // Distance from center to touch point
                    val distance =
                        calculateDistanceFromCenter(center, position).coerceAtMost(radius)

                    // if distance is between inner and outer radius then we touched valid area
                    isTouched = (distance < radius)

                    if (isTouched) {
                        val hueChange = calculateAngleFomLocalCoordinates(center, position)
                        val saturationChange = (distance / radius).coerceIn(0f, 1f)
                        onChange(hueChange, saturationChange)
                        it.consumeDownChange()
                    }

                },
                onMove = {
                    if (isTouched) {

                        val position = it.position
                        val hueChange = calculateAngleFomLocalCoordinates(center, position)
                        val distance =
                            calculateDistanceFromCenter(center, position).coerceAtMost(radius)

                        val saturationChange = (distance / radius).coerceIn(0f, 1f)
                        onChange(hueChange, saturationChange)
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
            // Draw hue selection circle with sweep gradient
            drawCircle(colorScaleHSVSweep)
            drawCircle(whiteToTransparentRadial)
            drawHueSelectionCircle(center = currentPosition, radius = selectorRadius)
        }
    }
}
