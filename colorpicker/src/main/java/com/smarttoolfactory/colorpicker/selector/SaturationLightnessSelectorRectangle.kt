package com.smarttoolfactory.colorpicker.selector

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.consumeDownChange
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.drawBlendingRectGradient
import com.smarttoolfactory.colorpicker.ui.brush.saturationHSVGradient
import com.smarttoolfactory.colorpicker.ui.brush.valueGradient
import com.smarttoolfactory.gesture.pointerMotionEvents

/**
 * @param hue
 * @param saturation
 * @param value
 * @param selectionRadius
 * @param onChange
 */
@Composable
fun SVSelectorFromHSVRectangle(
    modifier: Modifier = Modifier,
    hue: Float,
    saturation: Float = 0.5f,
    value: Float = 0.5f,
    selectionRadius: Dp = (-1).dp,
    onChange: (Float, Float) -> Unit
) {

    BoxWithConstraints(modifier) {

        val density = LocalDensity.current.density


        val width = constraints.maxWidth.toFloat()
        val height = constraints.maxHeight.toFloat()

        // Center position of color picker
        val center = Offset(width / 2, height / 2)

        /**
         * Circle selector radius for setting [saturation] and [value] by gesture
         */
        val selectorRadius =
            if (selectionRadius != Dp.Unspecified) selectionRadius.value * density
            else width.coerceAtMost(height) * .04f

        /**
         *  Current position is initially set by [saturation] and [value] that is bound
         *  in diamond since (1,1) points to bottom left corner of a rectangle but it's bounded
         *  in diamond by [setSelectorPositionFromColorParams].
         *  When user touches anywhere in diamond current position is updaed and
         *  this composable is recomposed
         */
        var currentPosition by remember {
            mutableStateOf(center)
        }

        val posX = saturation * width
        val posY = (1 - value) * height
        currentPosition = Offset(posX, posY)


        val colorPickerModifier = modifier
            .pointerMotionEvents(
                onDown = {
                    val position = it.position
                    val saturationChange = (position.x / width).coerceIn(0f, 1f)
                    val valueChange = (1 - (position.y / height)).coerceIn(0f, 1f)
                    onChange(saturationChange, valueChange)
                    it.consumeDownChange()

                },
                onMove = {
                    val position = it.position
                    val saturationChange = (position.x / width).coerceIn(0f, 1f)
                    val valueChange = (1 - (position.y / height)).coerceIn(0f, 1f)
                    onChange(saturationChange, valueChange)
                    it.consumePositionChange()
                },
                delayAfterDownInMillis = 20
            )

        val valueGradient = valueGradient(hue)
        val hueSaturation = saturationHSVGradient(hue)

        Canvas(modifier = colorPickerModifier) {
            drawBlendingRectGradient(dst = valueGradient, src = hueSaturation)
            // Saturation and Value/Lightness or value selector
            drawHueSelectionCircle(
                center = currentPosition,
                radius = selectorRadius
            )
        }
    }
}

