package com.smarttoolfactory.colorpicker.saturationselector

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.consumeDownChange
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.drawBlendingRectGradient
import com.smarttoolfactory.colorpicker.gesture.pointerMotionEvents
import com.smarttoolfactory.colorpicker.ui.brush.saturationHSVGradient
import com.smarttoolfactory.colorpicker.ui.brush.valueGradient

@Composable
fun SaturationPickerRectangleHSL(
    modifier: Modifier = Modifier,
    hue: Float,
    saturation: Float = 0.5f,
    lightness: Float = 1f,
    selectionRadius: Dp = (-1).dp,
    onChange: (Float, Float) -> Unit
){

}


@Composable
fun SaturationPickerRectangle(
    modifier: Modifier = Modifier,
    hue: Float,
    saturation: Float = 0.5f,
    value: Float = 0.5f,
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
         * Circle selector radius for setting [saturation] and [value] by gesture
         */
        val selectorRadius =
            if (selectionRadius > 0.dp) selectionRadius.value * density else length * .04f

        /**
         *  Current position is initially set by [saturation] and [value] that is bound
         *  in diamond since (1,1) points to bottom left corner of a rectangle but it's bounded
         *  in diamond by [setSelectorPositionFromColorParams].
         *  When user touches anywhere in diamond current position is updaed and
         *  this composable is recomposed
         */
        var currentPosition by remember(saturation, value) {
            mutableStateOf(
                Offset(
                    (saturation.coerceIn(0f, 1f) * length),
                    (1 - value).coerceIn(0f, 1f) * length
                )
            )
        }

        val canvasModifier = Modifier
            .size(maxWidth)
            .pointerMotionEvents(
                onDown = {
                    val position = it.position
                    val posX = position.x
                    val posY = position.y

                    val posXInPercent = (posX / length).coerceIn(0f, 1f)
                    val posYInPercent = (posY / length).coerceIn(0f, 1f)

                    // Send x position as saturation and reverse of y position as value
                    // value increases while going up but android drawing system is opposite
                    onChange(posXInPercent, 1 - posYInPercent)
                    currentPosition = Offset(posX, posY)
                    it.consumeDownChange()

                },
                onMove = {
                    val position = it.position
                    val posX = position.x
                    val posY = position.y

                    val posXInPercent = (posX / length).coerceIn(0f, 1f)
                    val posYInPercent = (posY / length).coerceIn(0f, 1f)

                    // Send x position as saturation and reverse of y position as value
                    // value increases while going up but android drawing system is opposite
                    onChange(posXInPercent, 1 - posYInPercent)
                    currentPosition = Offset(posX, posY)
                    it.consumePositionChange()
                },
                onUp = {
                    it.consumeDownChange()
                }
            )

        Canvas(modifier = canvasModifier) {

            val valueGradient = valueGradient(hue)

            val hueSaturation = saturationHSVGradient(hue)

            drawBlendingRectGradient(dst = valueGradient, src = hueSaturation)

            // Saturation and Value/Lightness or value selector
            drawCircle(
                Color.White,
                radius = selectorRadius,
                center = currentPosition,
                style = Stroke(width = selectorRadius / 2)
            )
        }
    }
}

