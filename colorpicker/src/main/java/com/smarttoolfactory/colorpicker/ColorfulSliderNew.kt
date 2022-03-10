package com.smarttoolfactory.colorpicker

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
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
import com.smarttoolfactory.colorpicker.ui.gradientColorsReversed

@Composable
fun ColorfulSliderNew(
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

        val density = LocalDensity.current
        val width = constraints.maxWidth.toFloat()

        val thumbRadiusInPx: Float
        val trackHeightInPx: Float

        // Start of the track used for measuring progress,
        // it's line + radius of cap which is half of height of track
        // to draw this on canvas starting point of line
        // should be at trackStart + trackHeightInPx / 2 while drawing
        val trackStart: Float
        // End of the track that is used for measuring progress
        val trackEnd: Float

        with(density) {
            thumbRadiusInPx = thumbRadius.toPx()
            trackHeightInPx = trackHeight.toPx()
            trackStart = thumbRadiusInPx
            trackEnd = width - trackStart
        }

        fun scaleToUserValue(offset: Float) =
            scale(trackStart, trackEnd, offset, valueRange.start, valueRange.endInclusive)

        fun scaleToOffset(userValue: Float) =
            scale(valueRange.start, valueRange.endInclusive, userValue, trackStart, trackEnd)

        val rawOffset = remember { mutableStateOf(scaleToOffset(value)) }

        val coerced = value.coerceIn(valueRange.start, valueRange.endInclusive)
        val fraction = calculateFraction(valueRange.start, valueRange.endInclusive, coerced)

        println(
            "🌈ColorfulSlider()\n" +
                    "trackStart: $trackStart, trackEnd: $trackEnd\n" +
                    "coerced: $coerced, fraction: $fraction\n" +
                    "value: $value, rawOffset: ${rawOffset.value}"
        )

        val thumbModifier = Modifier.pointerInput(Unit) {

            detectHorizontalDragGestures(

                onHorizontalDrag = { _, dragAmount ->

                    rawOffset.value = (rawOffset.value + dragAmount)
                    val offsetInTrack = rawOffset.value.coerceIn(trackStart, trackEnd)

                    onValueChangeState.value.invoke(scaleToUserValue(offsetInTrack))

//                    println(
//                        "🍒 ColorSliderNew() Drag\n" +
//                                "trackStart: $trackStart, trackEnd: $trackEnd\n" +
//                                "rawOffset: ${rawOffset.value}, offsetInTrack: $offsetInTrack"
//                    )
                }
            )
        }

        val thumbCenterPos = trackStart  + (trackEnd - trackStart) * fraction

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {

            // DEBUG Function to display trackable range
            drawLine(
                Color.Yellow, start = Offset(trackStart, 0f),
                end = Offset(trackEnd, 0f),
                strokeWidth = trackHeightInPx
            )

            drawLine(
                Brush.linearGradient(gradientColorsReversed),
                start = Offset(trackStart + trackHeightInPx / 2, center.y),
                end = Offset(trackEnd - trackHeightInPx / 2, center.y),
                strokeWidth = trackHeightInPx,
                cap = StrokeCap.Round
            )


            drawLine(
                Color.Magenta,
                start = Offset(thumbCenterPos, center.y),
                end = Offset(trackEnd - trackHeightInPx / 2, center.y),
                strokeWidth = trackHeightInPx,
                cap = StrokeCap.Round
            )


//            drawCircle(Color.Black, center = Offset(rawOffset.value, center.y), radius = 10f)
            drawCircle(
                Color.Black,
                center = Offset(thumbCenterPos, center.y),
                radius = 10f
            )

        }

        Thumb(
            modifier = thumbModifier,
            offset = thumbCenterPos-thumbRadiusInPx,
            thumbSize = thumbRadius * 2
        )
    }
}

@Composable
private fun SliderImpl(modifier:Modifier) {

}

@Composable
private fun Track(modifier: Modifier) {

}


@Composable
private fun Thumb(
    modifier: Modifier,
    offset: Float,
    thumbSize: Dp
) {
    Spacer(
        modifier = Modifier
            .offset { IntOffset(offset.toInt(), 0) }
            .border(2.dp, Color.Red, CircleShape)
//                .shadow(2.dp, shape = CircleShape)
            .size(thumbSize)

//                .background(boxColor)
            .then(modifier)

    )

}




