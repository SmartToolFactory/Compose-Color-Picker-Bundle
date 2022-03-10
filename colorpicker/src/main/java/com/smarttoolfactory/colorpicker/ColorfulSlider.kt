package com.smarttoolfactory.colorpicker

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    trackHeight: Dp = TrackHeight,
    thumbRadius: Dp = ThumbRadius,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
) {

    val onValueChangeState = rememberUpdatedState(onValueChange)


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

        // Start of the track used for measuring progress,
        // it's line + radius of cap which is half of height of track
        // to draw this on canvas starting point of line
        // should be at trackStart + trackHeightInPx / 2 while drawing
        val trackStart: Float
        // End of the track that is used for measuring progress
        val trackEnd: Float
        val trackLength: Float

        // Current progress
        var progress by remember { mutableStateOf(0f) }

        with(LocalDensity.current) {
            widthInPx = width.toPx()
            thumbRadiusInPx = thumbRadius.toPx()
            thumbSize = thumbRadiusInPx * 2

            trackHeightInPx = trackHeight.toPx()
            trackStart = thumbRadiusInPx + trackHeightInPx / 2
            trackEnd = widthInPx - trackStart
            trackLength = trackEnd - trackStart
        }

        // Horizontal center position of thumb
        var thumbCenterX by remember { mutableStateOf(trackStart) }

        // Smallest position thumb center can move to
        val thumbStart = trackStart - thumbRadiusInPx
        // Highest position in x axis thumb center move to
        val thumbEnd = trackEnd - thumbRadiusInPx

        val offsetX = remember { mutableStateOf(thumbStart) }

        val dragModifier = Modifier.pointerInput(Unit) {

            detectHorizontalDragGestures(

                onDragStart = {
                    boxColor = Green400
                },

                onDragEnd = {
                    boxColor = Blue400
                },
                onHorizontalDrag = { pointerInputChange, dragAmount ->
                    val originalX = offsetX.value
                    val newValue =
                        (originalX + dragAmount).coerceIn(
                            thumbStart, thumbEnd

                        )
                    thumbCenterX = newValue + thumbRadiusInPx
                    offsetX.value = newValue

                    progress = ((thumbCenterX - trackStart) / trackLength).coerceIn(0f, 1f)
                    onValueChange(progress)

                    println(
                        "🚀 Drag\n" +
                                "trackStart: $trackStart, trackEnd: $trackEnd, trackLength: $trackLength\n" +
                                "newValue: $newValue, thumbCenterX: $thumbCenterX, " +
                                "PROGRESS: $progress"
                    )
                }
            )
        }

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {

            drawLine(
                Color.Yellow, start = Offset(trackStart, 0f),
                end = Offset(trackEnd, 0f),
                strokeWidth = trackHeightInPx
            )

            drawLine(
                Color.Red, start = Offset(thumbStart, -30f),
                end = Offset(thumbEnd, -30f),
                strokeWidth = trackHeightInPx
            )

            drawLine(
                Brush.linearGradient(gradientColorsReversed),
                start = Offset(trackStart + trackHeightInPx / 2, center.y),
                end = Offset(trackEnd - trackHeightInPx / 2, center.y),
                strokeWidth = trackHeightInPx,
                cap = StrokeCap.Round
            )

            drawCircle(Color.Black, center = Offset(thumbCenterX, center.y), radius = 10f)

        }

        Spacer(
            modifier = Modifier
                .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                .border(2.dp, Color.Red, CircleShape)
//                .shadow(2.dp, shape = CircleShape)
                .size(thumbRadius * 2)

//                .background(boxColor)
                .then(dragModifier)

        )
    }
}

// Calculate the 0..1 fraction that `pos` value represents between `a` and `b`
private fun calcFraction(a: Float, b: Float, pos: Float) =
    (if (b - a == 0f) 0f else (pos - a) / (b - a)).coerceIn(0f, 1f)

// Internal to be referred to in tests
internal val ThumbRadius = 10.dp
internal val TrackHeight = 8.dp
private val SliderHeight = 48.dp
