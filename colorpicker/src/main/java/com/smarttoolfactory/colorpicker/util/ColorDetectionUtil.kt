package com.smarttoolfactory.colorpicker.util

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color

/**
 * Calculates color of pixel in ([offsetX], [offsetY]) in  [bitmap] that is in a Composable with
 * [width] and [height].
 *
 * @param offsetX x position of bitmap
 * @param offsetY y position of bitmap
 * @param startImageX x coordinate of top left position of image.
 * @param startImageY y coordinate of top left position of image.
 * @param width width of Composable that contains [bitmap].
 * @param height height of Composable that contains [bitmap].
 * might not match each other.
 * @param bitmap image used for detecting colors of.
 */
fun calculateColorInPixel(
    offsetX: Float,
    offsetY: Float,
    startImageX: Float = 0f,
    startImageY: Float = 0f,
    width: Int,
    height: Int,
    bitmap: Bitmap,
): Color {

    val bitmapWidth = bitmap.width
    val bitmapHeight = bitmap.height

    // End positions, this might be less than Image dimensions if bitmap doesn't fit Image
    val endImageX = width - startImageX
    val endImageY = height - startImageY

    val scaledX =
        scale(
            start1 = startImageX,
            end1 = endImageX,
            pos = offsetX,
            start2 = 0f,
            end2 = bitmapWidth.toFloat()
        ).toInt().coerceIn(0, bitmapWidth - 1)

    val scaledY =
        scale(
            start1 = startImageY,
            end1 = endImageY,
            pos = offsetY,
            start2 = 0f,
            end2 = bitmapHeight.toFloat()
        ).toInt().coerceIn(0, bitmapHeight - 1)

    val pixel: Int = bitmap.getPixel(scaledX, scaledY)

    val red = android.graphics.Color.red(pixel)
    val green = android.graphics.Color.green(pixel)
    val blue = android.graphics.Color.blue(pixel)

    return (Color(red, green, blue))
}

/**
 * Calculates color in a [radius] in ([offsetX], [offsetY]) in  [bitmap] that is in a Composable with
 * [width] and [height].
 *
 * @param offsetX x position of bitmap
 * @param offsetY y position of bitmap
 * @param startImageX x coordinate of top left position of image.
 * @param startImageY y coordinate of top left position of image.
 * @param width width of Composable that contains [bitmap].
 * @param height height of Composable that contains [bitmap].
 * @param radius of color detection circle. Based on this radius colors are calculated and
 * average of red, green, blue is returned as [Color]
 * @param bitmap image used for detecting colors of.
 */
fun calculateColorInRadius(
    offsetX: Float,
    offsetY: Float,
    startImageX: Float = 0f,
    startImageY: Float = 0f,
    width: Int,
    height: Int,
    radius: Float = 0f,
    bitmap: Bitmap,
): Color {

    val bitmapWidth = bitmap.width
    val bitmapHeight = bitmap.height

    // End positions, this might be less than Image dimensions if bitmap doesn't fit Image
    val endImageX = width - startImageX
    val endImageY = height - startImageY

    val scaledX =
        scale(
            start1 = startImageX,
            end1 = endImageX,
            pos = offsetX,
            start2 = 0f,
            end2 = bitmapWidth.toFloat()
        ).toInt().coerceIn(0, bitmapWidth - 1)

    val scaledY =
        scale(
            start1 = startImageY,
            end1 = endImageY,
            pos = offsetY,
            start2 = 0f,
            end2 = bitmapHeight.toFloat()
        ).toInt().coerceIn(0, bitmapHeight - 1)

    val color = if (radius <= 0f) {
        val pixel: Int = bitmap.getPixel(scaledX, scaledY)

        val red = android.graphics.Color.red(pixel)
        val green = android.graphics.Color.green(pixel)
        val blue = android.graphics.Color.blue(pixel)
        Color(red, green, blue)
    } else {
        val startX = (scaledX - radius).toInt().coerceAtLeast(0)
        val endX = (scaledX + radius).toInt().coerceAtMost(width)

        val startY = (scaledY - radius).toInt().coerceAtLeast(0)
        val endY = (scaledY + radius).toInt().coerceAtMost(height)

        val averageColor = floatArrayOf(0f, 0f, 0f)

        var count = 1

        for (x in startX..endX) {
            for (y in startY..endY) {

                // TODO Add a distance check to make sure that pixels are inside a circle
                val pixel: Int = bitmap.getPixel(x, y)

                val red = android.graphics.Color.red(pixel)
                val green = android.graphics.Color.green(pixel)
                val blue = android.graphics.Color.blue(pixel)

                averageColor[0] += (red - averageColor[0]) / count
                averageColor[1] += (green - averageColor[1]) / count
                averageColor[2] += (blue - averageColor[2]) / count

                count++
            }
        }

        Color(averageColor[0].toInt(), averageColor[1].toInt(), averageColor[2].toInt())
    }

    return color
}
