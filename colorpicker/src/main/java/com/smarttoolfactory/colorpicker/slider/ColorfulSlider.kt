package com.smarttoolfactory.colorpicker.slider

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.LayoutDirection
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
            val strokeRadius = trackHeight.toPx() / 2
            trackStart = thumbRadiusInPx.coerceAtLeast(strokeRadius)
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
                    println("🍏 Touch pos: ${it.position.x}")
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
            thumbRadius = thumbRadiusInPx,
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
    thumbRadius: Float,
    coerceThumbInTrack: Boolean,
    modifier: Modifier,
) {

    Box(
        modifier.then(DefaultSliderConstraints)
    ) {

        val trackStrokeWidth: Float
        val thumbSize: Dp

        with(LocalDensity.current) {
            trackStrokeWidth = trackHeight.toPx()
            thumbSize = (2 * thumbRadius).toDp()
        }

        val thumbCenterPos = (trackStart + (trackEnd - trackStart) * fraction)


        Track(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .fillMaxSize(),
            fraction = fraction,
            thumbRadius = thumbRadius,
            trackStart = trackStart,
            trackEnd = trackEnd,
            trackHeight = trackStrokeWidth,
            coerceThumbInTrack = coerceThumbInTrack,
            colors = colors,
            enabled = enabled
        )

        Thumb(
            modifier = Modifier.align(Alignment.CenterStart),
            offset = thumbCenterPos - thumbRadius,
            thumbSize = thumbSize,
            colors = colors,
            enabled = enabled
        )
    }
}

@Composable
private fun Track(
    modifier: Modifier,
    fraction: Float,
    thumbRadius: Float,
    trackStart: Float,
    trackEnd: Float,
    trackHeight: Float,
    coerceThumbInTrack: Boolean,
    colors: MaterialSliderDefaults.MaterialSliderColors,
    enabled: Boolean
) {

    val activeTrackColor: ColorBrush? =
        colors.trackColor(enabled = enabled, active = true).value
    val inactiveTrackColor: ColorBrush? =
        colors.trackColor(enabled = enabled, active = false).value

    val strokeRadius = trackHeight / 2
    val thumbCenter = (trackStart + (trackEnd - trackStart) * fraction)

    val trackDrawLength =
        if (coerceThumbInTrack) trackStart - thumbRadius + strokeRadius else trackStart

    Canvas(modifier = modifier) {
        val width = size.width
        val isRtl = layoutDirection == LayoutDirection.Rtl

        val centerY = center.y

        val sliderLeft = Offset((width - trackDrawLength).coerceAtLeast(0f), centerY)
        val sliderRight = Offset(width - sliderLeft.x, centerY)
        val sliderStart = if (isRtl) sliderRight else sliderLeft
        val sliderEnd = if (isRtl) sliderLeft else sliderRight

        val sliderValue = Offset(thumbCenter, centerY)

        drawLine(
            Color.Yellow, start = Offset(trackStart, 20f),
            end = Offset(trackEnd, 20f),
            strokeWidth = 20f
        )

        var isInactiveSliderDrawn = false

        inactiveTrackColor?.let { colorBrush: ColorBrush ->
            isInactiveSliderDrawn = true
            var drawWithBrush = false
            colorBrush.brush?.let { brush: Brush ->
                drawLine(
                    brush,
                    start = sliderStart,
                    end = sliderEnd,
                    strokeWidth = trackHeight,
                    cap = StrokeCap.Round
                )
                drawWithBrush = true
            }

            if (!drawWithBrush) {

                drawLine(
                    colorBrush.color,
                    start = sliderStart,
                    end = sliderEnd,
                    strokeWidth = trackHeight,
                    cap = StrokeCap.Round
                )
            }
        }

        activeTrackColor?.let { colorBrush: ColorBrush ->

            var drawWithBrush = false
            colorBrush.brush?.let { brush: Brush ->
                drawLine(
                    brush,
                    start = sliderStart,
                    end = if (isInactiveSliderDrawn) sliderEnd else sliderValue,
                    strokeWidth = trackHeight,
                    cap = StrokeCap.Round
                )
                drawWithBrush = true
            }

            if (!drawWithBrush) {
                drawLine(
                    colorBrush.color,
                    start = sliderStart,
                    end = if (isInactiveSliderDrawn) sliderValue else sliderEnd,
                    strokeWidth = trackHeight,
                    cap = StrokeCap.Round
                )
            }
        }

        drawCircle(
            Color.Black,
            center = Offset(sliderValue.x, center.y),
            radius = 10f
        )

        drawCircle(
            Color.Green,
            center = Offset(sliderStart.x, center.y),
            radius = 5f
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
//            .border(2.dp, Color.Red, shape = CircleShape)
            .shadow(1.dp, shape = CircleShape)
            .size(thumbSize)
            .then(
                colorBrush.brush?.let { brush: Brush ->
                    Modifier.background(brush)
                } ?: Modifier.background(colorBrush.color)
            )
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
private val SliderHeight = 60.dp
private val SliderMinWidth = 144.dp

// Internal to be referred to in tests
internal val ThumbRadius = 10.dp

private val DefaultSliderConstraints =
    Modifier
        .widthIn(min = SliderMinWidth)
        .heightIn(max = SliderHeight)
