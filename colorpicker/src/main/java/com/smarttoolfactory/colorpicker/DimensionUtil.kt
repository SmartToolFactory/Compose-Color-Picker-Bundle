package com.smarttoolfactory.colorpicker

import androidx.compose.ui.geometry.Offset
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt
import kotlin.math.sqrt

/**
 * [Linear Interpolation](https://en.wikipedia.org/wiki/Linear_interpolation) function that moves
 * amount from it's current position to start and amount
 * @param start of interval
 * @param end of interval
 * @param amount e closed unit interval [0, 1]
 */
fun lerp(start: Float, end: Float, amount: Float): Float {
    return (1 - amount) * start + amount * (end - start)
}

/**
 * Scale x1 from start1..end1 range to start2..end2 range

 */
fun scale(start1: Float, end1: Float, pos: Float, start2: Float, end2: Float) =
    lerp(start2, end2, calculateFraction(start1, end1, pos))

/**
 * Scale x.start, x.endInclusive from a1..b1 range to a2..b2 range
 */
fun scale(
    start1: Float,
    end1: Float,
    range: ClosedFloatingPointRange<Float>,
    start2: Float,
    end2: Float
) =
    scale(start1, end1, range.start, start2, end2)..scale(
        start1,
        end1,
        range.endInclusive,
        start2,
        end2
    )


/**
 * Calculate fraction for value between a range [end] and [start] coerced into 0f-1f range
 */
fun calculateFraction(start: Float, end: Float, pos: Float) =
    (if (end - start == 0f) 0f else (pos - start) / (end - start)).coerceIn(0f, 1f)


/**
 * Calculate distance from center to touch position
 */
fun calculateDistanceFromCenter(center: Offset, position: Offset): Float {
    val dy = center.y - position.y
    val dx = position.x - center.x
    return sqrt(dx * dx + dy * dy)
}

/**
 * Get angle between 0 and 360 degrees from local coordinate system of a composable
 * Local coordinates of touch are equal to Composable position when in bounds, when
 * touch position is above this composable it returns minus in y axis.
 */
fun calculateAngleFomLocalCoordinates(center: Offset, position: Offset): Int {
    if (center == Offset.Unspecified || position == Offset.Unspecified) return -1

    val dy = center.y - position.y
    val dx = position.x - center.x
    return ((360 + ((atan2(dy, dx) * 180 / PI))) % 360).roundToInt()

}

/**
 * Get angle between 0 and 360 degrees using coordinates of the root composable. Root composable
 * needs to cover whole screen to return correct results.
 */
fun calculateAngleFromRootCoordinates(center: Offset, position: Offset): Int {
    if (center == Offset.Unspecified || position == Offset.Unspecified) return -1

    val dy = (position.y - center.y)
    val dx = (center.x - position.x)
    return ((360 + ((atan2(dy, dx) * 180 / PI))) % 360).roundToInt()
}
