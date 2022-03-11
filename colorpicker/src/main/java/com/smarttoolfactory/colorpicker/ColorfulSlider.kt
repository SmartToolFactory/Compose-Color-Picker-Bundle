package com.smarttoolfactory.colorpicker

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalMinimumTouchTargetEnforcement
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.consumeDownChange
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.*
import com.smarttoolfactory.colorpicker.gesture.pointerMotionEvents
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
fun ColorfulSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    trackHeight: Dp = TrackHeight,
    thumbRadius: Dp = ThumbRadius,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    coerceThumbInTrack: Boolean = false,
    colors: MaterialSliderColors = MaterialSliderDefaults.colors()
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
                rawOffset.value = it.position.x
                val offsetInTrack = rawOffset.value.coerceIn(trackStart, trackEnd)
                onValueChangeState.value.invoke(scaleToUserValue(offsetInTrack))
                it.consumeDownChange()
            },
            onMove = {
                rawOffset.value = it.position.x
                val offsetInTrack = rawOffset.value.coerceIn(trackStart, trackEnd)
                onValueChangeState.value.invoke(scaleToUserValue(offsetInTrack))
                it.consumePositionChange()
            },
            onUp = {
                rawOffset.value = it.position.x
                val offsetInTrack = rawOffset.value.coerceIn(trackStart, trackEnd)
                onValueChangeState.value.invoke(scaleToUserValue(offsetInTrack))
                it.consumeDownChange()
            }
        )

        SliderImpl(
            modifier = dragModifier,
            fraction = fraction,
            trackStart = trackStart,
            trackEnd = trackEnd,
            colors = colors,
            trackHeight = trackHeight,
            thumbRadius = thumbRadius,
            coerceThumbInTrack = coerceThumbInTrack
        )
    }
}

@Composable
private fun SliderImpl(
    modifier: Modifier,
    fraction: Float,
    trackStart: Float,
    trackEnd: Float,
    colors: MaterialSliderColors,
    trackHeight: Dp,
    thumbRadius: Dp,
    coerceThumbInTrack: Boolean
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
            colors = colors
        )

        Thumb(
            modifier = Modifier.align(Alignment.CenterStart),
            offset = thumbCenterPos - thumbPx,
            thumbSize = thumbRadius * 2,
            colors = colors
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
    colors: MaterialSliderColors
) {
    Canvas(modifier = modifier) {

        val activeTrackColor = colors.trackColor(active = true)
        val inactiveTrackColor = colors.trackColor(active = false)

        // DEBUG Function to display trackable range
//        drawLine(
//            Color.Yellow, start = Offset(trackStart, 0f),
//            end = Offset(trackEnd, 0f),
//            strokeWidth = trackHeight
//        )


        activeTrackColor.brush?.let { brush: Brush ->
            drawLine(
                brush,
                start = Offset(trackStart + trackHeight / 2, center.y),
                end = Offset(trackEnd - trackHeight / 2, center.y),
                strokeWidth = trackHeight,
                cap = StrokeCap.Round
            )
        } ?: drawLine(
            activeTrackColor.color,
            start = Offset(trackStart + trackHeight / 2, center.y),
            end = Offset(trackEnd - trackHeight / 2, center.y),
            strokeWidth = trackHeight,
            cap = StrokeCap.Round
        )


        inactiveTrackColor.brush?.let { brush: Brush ->
            drawLine(
                brush,
                start = Offset(thumbCenter, center.y),
                end = Offset(trackEnd - trackHeight / 2, center.y),
                strokeWidth = trackHeight,
                cap = StrokeCap.Round
            )
        } ?: drawLine(
            inactiveTrackColor.color,
            start = Offset(thumbCenter, center.y),
            end = Offset(trackEnd - trackHeight / 2, center.y),
            strokeWidth = trackHeight,
            cap = StrokeCap.Round
        )



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
    colors: MaterialSliderColors
) {

    val colorBrush = colors.thumbColor()
    Spacer(
        modifier = modifier
            .offset { IntOffset(offset.toInt(), 0) }
            .shadow(2.dp, shape = CircleShape)
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
private val SliderHeight = 48.dp
private val SliderMinWidth = 144.dp

// Internal to be referred to in tests
internal val ThumbRadius = 10.dp

private val DefaultSliderConstraints =
    Modifier
        .widthIn(min = SliderMinWidth)
        .heightIn(max = SliderHeight)


@OptIn(ExperimentalMaterialApi::class)
@Suppress("ModifierInspectorInfo")
fun Modifier.minimumTouchTargetSize(): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "minimumTouchTargetSize"

        properties["README"] = "Adds outer padding to measure at least 48.dp (default) in " +
                "size to disambiguate touch interactions if the element would measure smaller"
    }
) {
    if (LocalMinimumTouchTargetEnforcement.current) {
        MinimumTouchTargetModifier(DpSize(48.dp, 48.dp))
    } else {
        Modifier
    }
}

private class MinimumTouchTargetModifier(val size: DpSize) : LayoutModifier {
    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {

        val placeable = measurable.measure(constraints)

        // Be at least as big as the minimum dimension in both dimensions
        val width = maxOf(placeable.width, size.width.roundToPx())
        val height = maxOf(placeable.height, size.height.roundToPx())

        println("MinimumTouchTargetModifier $width, height: $height")

        return layout(width, height) {
            val centerX = ((width - placeable.width) / 2f).roundToInt()
            val centerY = ((height - placeable.height) / 2f).roundToInt()
            placeable.place(centerX, centerY)
        }
    }

    override fun equals(other: Any?): Boolean {
        val otherModifier = other as? MinimumTouchTargetModifier ?: return false
        return size == otherModifier.size
    }

    override fun hashCode(): Int {
        return size.hashCode()
    }
}

