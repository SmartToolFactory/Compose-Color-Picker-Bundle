package com.smarttoolfactory.colorpicker

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.ui.Blue400
import com.smarttoolfactory.colorpicker.ui.Green400
import com.smarttoolfactory.colorpicker.ui.gradientColorsReversed
import kotlin.math.roundToInt

@Composable
fun ColorfulSlider(
    modifier: Modifier = Modifier,
    color: Color = Color.Red,
    trackHeight: Dp = TrackHeight,
    thumbRadius: Dp = ThumbRadius
) {

    BoxWithConstraints(
        modifier = modifier.requiredSizeIn(minWidth = TrackHeight, minHeight = ThumbRadius * 2),
        contentAlignment = Alignment.CenterStart
    ) {

        var boxColor by remember { mutableStateOf(Color.White) }

        val width = maxWidth

        val widthInPx: Float
        val thumbRadiusInPx: Float
        val thumbSize: Float
        val trackHeightInPx: Float
        val trackStart: Float
        val trackEnd: Float

        with(LocalDensity.current) {
            widthInPx = width.toPx()
            thumbRadiusInPx = thumbRadius.toPx()
            thumbSize = thumbRadiusInPx * 2

            trackHeightInPx = trackHeight.toPx()
            trackStart = thumbSize
            trackEnd = widthInPx - trackStart
        }

        val offsetX = remember { mutableStateOf(trackStart - thumbRadiusInPx) }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
//            .onSizeChanged { width = it.width.toFloat() }
        ) {

            val canvasWidth = size.width
            val canvasHeight = size.height

            drawLine(
                Color.Yellow, start = Offset(trackHeightInPx, 0f),
                end = Offset(canvasWidth - trackHeightInPx, 0f),
                strokeWidth = trackHeightInPx
            )

            drawLine(
                Brush.linearGradient(gradientColorsReversed),
                start = Offset(trackStart, center.y),
                end = Offset(trackEnd, center.y),
                strokeWidth = trackHeightInPx,
                cap = StrokeCap.Round
            )
        }

        Spacer(
            modifier = Modifier
                .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                .border(2.dp, Color.Red, CircleShape)
//                .shadow(2.dp, shape = CircleShape)
                .size(thumbRadius * 2)
//                .background(boxColor)
                .pointerInput(Unit) {

                    detectHorizontalDragGestures(

                        onDragStart = {
                            boxColor = Green400
                        },

                        onDragEnd = {
                            boxColor = Blue400
                        },
                        onHorizontalDrag = { _, dragAmount ->
                            val originalX = offsetX.value
                            val newValue =
                                (originalX + dragAmount).coerceIn(
                                    trackStart - thumbRadiusInPx,
                                    trackEnd - thumbRadiusInPx
                                )
                            offsetX.value = newValue
                        }
                    )
                }
        )
    }
}

// Internal to be referred to in tests
internal val ThumbRadius = 10.dp
internal val TrackHeight = 8.dp
private val SliderHeight = 48.dp
