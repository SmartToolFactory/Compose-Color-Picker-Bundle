package com.smarttoolfactory.colorpicker

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.input.pointer.consumeDownChange
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.gesture.pointerMotionEvents
import com.smarttoolfactory.colorpicker.ui.gradientColorsReversed
import kotlin.math.abs

@Composable
fun ColorfulSliderNew(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    trackHeight: Dp = TrackHeight,
    thumbRadius: Dp = ThumbRadius,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    thumbOverflowTrack: Boolean = false
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
            trackStart =thumbRadiusInPx
//                if (!thumbOverflowTrack) thumbRadiusInPx else 2 * thumbRadiusInPx + trackHeightInPx
            trackEnd = width - trackStart
        }

        fun scaleToUserValue(offset: Float) =
            scale(trackStart, trackEnd, offset, valueRange.start, valueRange.endInclusive)

        fun scaleToOffset(userValue: Float) =
            scale(valueRange.start, valueRange.endInclusive, userValue, trackStart, trackEnd)

        val rawOffset = remember { mutableStateOf(scaleToOffset(value)) }

//        CorrectValueSideEffect(::scaleToOffset, valueRange, trackStart..trackEnd, rawOffset, value)


        val coerced = value.coerceIn(valueRange.start, valueRange.endInclusive)
        val fraction = calculateFraction(valueRange.start, valueRange.endInclusive, coerced)

        println(
            "🌈ColorfulSlider()\n" +
                    "trackStart: $trackStart, trackEnd: $trackEnd\n" +
                    "coerced: $coerced, fraction: $fraction\n" +
                    "value: $value, rawOffset: ${rawOffset.value}"
        )

        val thumbModifier = Modifier.pointerMotionEvents(
            onDown = {
                rawOffset.value = it.position.x
                val offsetInTrack = rawOffset.value.coerceIn(trackStart, trackEnd)
                onValueChangeState.value.invoke(scaleToUserValue(offsetInTrack))
                it.consumeDownChange()
                println(
                    "🍒 ColorSliderNew() Down()\n" +
                            "trackStart: $trackStart, trackEnd: $trackEnd\n" +
                            "rawOffset: ${rawOffset.value}, offsetInTrack: $offsetInTrack"
                )
            },
            onMove = {
                rawOffset.value = it.position.x
                val offsetInTrack = rawOffset.value.coerceIn(trackStart, trackEnd)
                onValueChangeState.value.invoke(scaleToUserValue(offsetInTrack))
                it.consumePositionChange()
                println(
                    "🎃 ColorSliderNew() Move()\n" +
                            "trackStart: $trackStart, trackEnd: $trackEnd\n" +
                            "rawOffset: ${rawOffset.value}, offsetInTrack: $offsetInTrack"
                )
            },
            onUp = {
                rawOffset.value = it.position.x
                val offsetInTrack = rawOffset.value.coerceIn(trackStart, trackEnd)
                onValueChangeState.value.invoke(scaleToUserValue(offsetInTrack))
                it.consumeDownChange()
            }
        )

//            .pointerInput(Unit) {
//
//            detectHorizontalDragGestures(
//
//                onHorizontalDrag = { _, dragAmount ->
//
//                    rawOffset.value = (rawOffset.value + dragAmount)
//                    val offsetInTrack = rawOffset.value.coerceIn(trackStart, trackEnd)
//
//                    onValueChangeState.value.invoke(scaleToUserValue(offsetInTrack))
//
//                    println(
//                        "🍒 ColorSliderNew() Drag\n" +
//                                "trackStart: $trackStart, trackEnd: $trackEnd\n" +
//                                "rawOffset: ${rawOffset.value}, offsetInTrack: $offsetInTrack"
//                    )
//                }
//            )
//        }


        SliderImpl(
            modifier = thumbModifier,
            fraction = fraction,
            trackStart = trackStart,
            trackEnd = trackEnd,
            trackHeight = trackHeight,
            thumbRadius = thumbRadius
        )
    }
}

@Composable
private fun SliderImpl(
    modifier: Modifier,
    fraction: Float,
    trackStart: Float,
    trackEnd: Float,
    trackHeight: Dp,
    thumbRadius: Dp
) {

    Box(modifier) {


        val trackStrokeWidth: Float
        val thumbPx: Float
        val widthDp: Dp

        with(LocalDensity.current) {
            trackStrokeWidth = trackHeight.toPx()
            thumbPx = thumbRadius.toPx()
            widthDp = (trackEnd - trackStart).toDp()
        }

        val thumbCenterPos = trackStart + (trackEnd - trackStart) * fraction

        val center = Modifier.align(Alignment.CenterStart)

        Track(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .fillMaxSize(),
            fraction = fraction,
            trackStart = trackStart,
            trackEnd = trackEnd,
            trackHeight = trackStrokeWidth
        )

        Thumb(
            modifier = center,
            offset = thumbCenterPos - thumbPx,
            thumbSize = thumbRadius * 2
        )
    }
}

@Composable
private fun Track(
    modifier: Modifier,
    fraction: Float,
    trackStart: Float,
    trackEnd: Float,
    trackHeight: Float
) {
    Canvas(modifier = modifier) {

        val thumbCenterPos = trackStart + (trackEnd - trackStart) * fraction

        // DEBUG Function to display trackable range
        drawLine(
            Color.Yellow, start = Offset(trackStart, 0f),
            end = Offset(trackEnd, 0f),
            strokeWidth = trackHeight
        )

        drawLine(
            Brush.linearGradient(gradientColorsReversed),
            start = Offset(trackStart + trackHeight / 2, center.y),
            end = Offset(trackEnd - trackHeight / 2, center.y),
            strokeWidth = trackHeight,
            cap = StrokeCap.Round
        )


        drawLine(
            Color.Magenta,
            start = Offset(thumbCenterPos, center.y),
            end = Offset(trackEnd - trackHeight / 2, center.y),
            strokeWidth = trackHeight,
            cap = StrokeCap.Round
        )


//            drawCircle(Color.Black, center = Offset(rawOffset.value, center.y), radius = 10f)
        drawCircle(
            Color.Black,
            center = Offset(thumbCenterPos, center.y),
            radius = 10f
        )

    }
}


@Composable
private fun Thumb(
    modifier: Modifier,
    offset: Float,
    thumbSize: Dp
) {
    Spacer(
        modifier = modifier
            .offset { IntOffset(offset.toInt(), 0) }
            .border(2.dp, Color.Red, CircleShape)
//            .shadow(2.dp, shape = CircleShape)
//            .background(Color.White, CircleShape)
            .size(thumbSize)
    )

}

@Composable
private fun CorrectValueSideEffect(
    scaleToOffset: (Float) -> Float,
    valueRange: ClosedFloatingPointRange<Float>,
    trackRange: ClosedFloatingPointRange<Float>,
    valueState: MutableState<Float>,
    value: Float
) {
    SideEffect {
        val error = (valueRange.endInclusive - valueRange.start) / 1000
        val newOffset = scaleToOffset(value)
        if (abs(newOffset - valueState.value) > error) {
            if (valueState.value in trackRange) {
                valueState.value = newOffset
            }
        }
    }
}



