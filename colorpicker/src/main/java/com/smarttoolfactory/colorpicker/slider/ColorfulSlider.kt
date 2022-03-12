package com.smarttoolfactory.colorpicker.slider

import androidx.compose.foundation.Canvas
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
import com.smarttoolfactory.colorpicker.calculateFraction
import com.smarttoolfactory.colorpicker.gesture.pointerMotionEvents
import com.smarttoolfactory.colorpicker.scale
import kotlin.math.abs

@Composable
fun ColorfulSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    trackHeight: Dp = TrackHeight,
    thumbRadius: Dp = ThumbRadius,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    coerceThumbInTrack: Boolean = false,
    colors: MaterialSliderDefaults.MaterialSliderColors = MaterialSliderDefaults.materialColors()
) {

    val onValueChangeState = rememberUpdatedState(onValueChange)

    // TODO Check these modifiers with different slider width, height and size params
    BoxWithConstraints(
        modifier = modifier
            .minimumTouchTargetSize()
            .requiredSizeIn(minWidth = ThumbRadius * 2, minHeight = ThumbRadius * 2),
        contentAlignment = Alignment.CenterStart
    ) {

        val width = constraints.maxWidth.toFloat()
        val thumbRadiusInPx: Float

        // Start of the track used for measuring progress,
        // it's line + radius of cap which is half of height of track
        // to draw this on canvas starting point of line
        // should be at trackStart + trackHeightInPx / 2 while drawing
        val trackStart: Float
        // End of the track that is used for measuring progress
        val trackEnd: Float

        with(LocalDensity.current) {
            thumbRadiusInPx = thumbRadius.toPx()
            trackStart = thumbRadiusInPx
            trackEnd = width - trackStart
        }

        fun scaleToUserValue(offset: Float) =
            scale(trackStart, trackEnd, offset, valueRange.start, valueRange.endInclusive)

        fun scaleToOffset(userValue: Float) =
            scale(valueRange.start, valueRange.endInclusive, userValue, trackStart, trackEnd)

        val rawOffset = remember { mutableStateOf(scaleToOffset(value)) }

        CorrectValueSideEffect(::scaleToOffset, valueRange, trackStart..trackEnd, rawOffset, value)

        val coerced = value.coerceIn(valueRange.start, valueRange.endInclusive)
        val fraction = calculateFraction(valueRange.start, valueRange.endInclusive, coerced)

        val dragModifier = Modifier.pointerMotionEvents(
            onDown = {
                if (enabled) {
                    rawOffset.value = it.position.x
                    val offsetInTrack = rawOffset.value.coerceIn(trackStart, trackEnd)
                    onValueChangeState.value.invoke(scaleToUserValue(offsetInTrack))
                    it.consumeDownChange()
                }
            },
            onMove = {
                if (enabled) {
                    rawOffset.value = it.position.x
                    val offsetInTrack = rawOffset.value.coerceIn(trackStart, trackEnd)
                    onValueChangeState.value.invoke(scaleToUserValue(offsetInTrack))
                    it.consumePositionChange()
                }

            },
            onUp = {
                if (enabled) {
                    rawOffset.value = it.position.x
                    val offsetInTrack = rawOffset.value.coerceIn(trackStart, trackEnd)
                    onValueChangeState.value.invoke(scaleToUserValue(offsetInTrack))
                    it.consumeDownChange()
                }
            }
        )

        SliderImpl(
            enabled = enabled,
            fraction = fraction,
            trackStart = trackStart,
            trackEnd = trackEnd,
            colors = colors,
            trackHeight = trackHeight,
            thumbRadius = thumbRadius,
            coerceThumbInTrack = coerceThumbInTrack,
            modifier = dragModifier
        )
    }
}

@Composable
private fun SliderImpl(
    enabled: Boolean,
    fraction: Float,
    trackStart: Float,
    trackEnd: Float,
    colors: MaterialSliderDefaults.MaterialSliderColors,
    trackHeight: Dp,
    thumbRadius: Dp,
    coerceThumbInTrack: Boolean,
    modifier: Modifier,
) {

    Box(
        modifier.then(DefaultSliderConstraints)
    ) {

        val trackStrokeWidth: Float
        val thumbPx: Float
        val widthDp: Dp

        with(LocalDensity.current) {
            trackStrokeWidth = trackHeight.toPx()
            thumbPx = thumbRadius.toPx()
            widthDp = (trackEnd - trackStart).toDp()
        }

        // TODO when coerce is true set don't let thumb move beyond track range
        val limit = if (coerceThumbInTrack) thumbPx else 0f
        val thumbCenterPos = (trackStart + +(trackEnd - trackStart) * fraction)
//            .coerceIn(
//                trackStart + limit,
//                trackEnd - limit
//            )

        Track(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .fillMaxSize(),
            thumbCenter = thumbCenterPos,
            trackStart = trackStart,
            trackEnd = trackEnd,
            trackHeight = trackStrokeWidth,
            colors = colors,
            enabled = enabled
        )

        Thumb(
            modifier = Modifier.align(Alignment.CenterStart),
            offset = thumbCenterPos - thumbPx,
            thumbSize = thumbRadius * 2,
            colors = colors,
            enabled = enabled
        )
    }
}

@Composable
private fun Track(
    modifier: Modifier,
    thumbCenter: Float,
    trackStart: Float,
    trackEnd: Float,
    trackHeight: Float,
    colors: MaterialSliderDefaults.MaterialSliderColors,
    enabled: Boolean
) {

    val activeTrackColor: ColorBrush? =
        colors.trackColor(enabled = enabled, active = true).value
    val inactiveTrackColor: ColorBrush? =
        colors.trackColor(enabled = enabled, active = false).value


    Canvas(modifier = modifier) {

        val width = trackEnd - trackStart

        // DEBUG Function to display trackable range
        drawLine(
            Color.Yellow, start = Offset(trackStart, 0f),
            end = Offset(trackEnd, 0f),
            strokeWidth = trackHeight
        )

        inactiveTrackColor?.let { colorBrush: ColorBrush ->
            var drawBrush = false

            println("ACTIVE color ${colorBrush.color}")


            colorBrush.brush?.let { brush: Brush ->
                drawLine(
                    brush,
                    start = Offset(thumbCenter, center.y),
                    end = Offset(trackEnd - trackHeight / 2, center.y),
                    strokeWidth = trackHeight,
                    cap = StrokeCap.Round
                )
                drawBrush = true
            }

            if (!drawBrush && colorBrush.color != null) {

                println("INACTIVE color ${colorBrush.color}")
                drawLine(
                    colorBrush.color,
                    start = Offset(thumbCenter, center.y),
                    end = Offset(trackEnd - trackHeight / 2, center.y),
                    strokeWidth = trackHeight,
                    cap = StrokeCap.Round
                )
            }
        }

        activeTrackColor?.let { colorBrush: ColorBrush ->

            var drawBrush = false
            colorBrush.brush?.let { brush: Brush ->
                drawLine(
                    brush,
                    start = Offset(trackStart + trackHeight / 2, center.y),
                    end = Offset(thumbCenter - trackHeight / 2, center.y),
                    strokeWidth = trackHeight,
                    cap = StrokeCap.Round
                )
                drawBrush = true
            }

            if (!drawBrush && colorBrush.color != null) {
                drawLine(
                    colorBrush.color,
                    start = Offset(trackStart + trackHeight / 2, center.y),
                    end = Offset(trackEnd - trackHeight / 2, center.y),
                    strokeWidth = trackHeight,
                    cap = StrokeCap.Round
                )
            }
        }



        drawCircle(
            Color.Black,
            center = Offset(thumbCenter, center.y),
            radius = 10f
        )

    }
}

@Composable
private fun Thumb(
    modifier: Modifier,
    offset: Float,
    thumbSize: Dp,
    colors: MaterialSliderDefaults.MaterialSliderColors,
    enabled: Boolean
) {

    val colorBrush = colors.thumbColor(enabled).value

    Spacer(
        modifier = modifier
            .offset { IntOffset(offset.toInt(), 0) }
            .border(2.dp, Color.Red, shape = CircleShape)
            .shadow(2.dp, shape = CircleShape)
            .size(thumbSize)
//            .then(
//                colorBrush.brush?.let { brush: Brush ->
//                    Modifier.background(brush)
//                } ?: Modifier.background(colorBrush.color)
//            )
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


// Internal to be referred to in tests
internal val TrackHeight = 4.dp
private val SliderHeight = 48.dp
private val SliderMinWidth = 144.dp

// Internal to be referred to in tests
internal val ThumbRadius = 10.dp

private val DefaultSliderConstraints =
    Modifier
        .widthIn(min = SliderMinWidth)
        .heightIn(max = SliderHeight)
