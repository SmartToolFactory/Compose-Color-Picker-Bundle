package com.smarttoolfactory.colorpicker.widget

import android.graphics.Bitmap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.*
import com.smarttoolfactory.colorpicker.util.calculateColorInPixel
import com.smarttoolfactory.gesture.pointerMotionEvents
import com.smarttoolfactory.screenshot.ScreenshotBox
import com.smarttoolfactory.screenshot.ScreenshotState
import com.smarttoolfactory.screenshot.rememberScreenshotState
import kotlin.math.roundToInt

/**
 * A Composable that detect color at pixel that user touches when [enabled].
 * @param enabled when enabled detect color at user's point of touch
 * @param content is screen/Composable is displayed to user to get color from. [ScreenshotBox]
 * gets [Bitmap] from screen when users first down and stores it.
 * @param onColorChange callback to notify that user moved and picked a color
 */
@Composable
fun ScreenColorDetector(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit,
    onColorChange: (Color) -> Unit
) {
    BoxWithConstraints(modifier = modifier) {

        val screenshotState = rememberScreenshotState()
        var containerSize by remember { mutableStateOf(IntSize.Zero) }

        var offset by remember {
            mutableStateOf(
                Offset(
                    (containerSize.width / 2).toFloat(),
                    (containerSize.height / 2).toFloat()
                )
            )
        }

        var color by remember { mutableStateOf(Color.Unspecified) }

        val colorDetectionModifier = Modifier
            .pointerMotionEvents(Unit,
                onDown = { pointerInputChange ->
                    if (screenshotState.bitmap == null) screenshotState.capture()

                    screenshotState.bitmap?.let { bitmap ->

                        if (bitmap.width == 0 || bitmap.height == 0 || containerSize == IntSize.Zero)
                            return@pointerMotionEvents


                        val offsetX = pointerInputChange.position.x.coerceIn(
                            0f,
                            containerSize.width.toFloat()
                        )
                        val offsetY = pointerInputChange.position.y.coerceIn(
                            0f,
                            containerSize.height.toFloat()
                        )

                        offset = Offset(offsetX, offsetY)

                        color = calculateColorInPixel(
                            offsetX = offsetX,
                            offsetY = offsetY,
                            width = containerSize.width,
                            height = containerSize.height,
                            bitmap = bitmap
                        )
                        onColorChange(color)
                    }
                },
                onMove = { pointerInputChange ->

                    screenshotState.bitmap?.let { bitmap ->
                        if (bitmap.width == 0 || bitmap.height == 0) return@pointerMotionEvents

                        val offsetX = pointerInputChange.position.x.coerceIn(
                            0f,
                            containerSize.width.toFloat()
                        )
                        val offsetY = pointerInputChange.position.y.coerceIn(
                            0f,
                            containerSize.height.toFloat()
                        )

                        offset = Offset(offsetX, offsetY)

                        color = calculateColorInPixel(
                            offsetX = offsetX,
                            offsetY = offsetY,
                            width = containerSize.width,
                            height = containerSize.height,
                            bitmap = bitmap
                        )

                        onColorChange(color)
                    }
                }
            )

            .onSizeChanged {
                containerSize = it
                offset =
                    Offset(
                        (containerSize.width / 2).toFloat(),
                        (containerSize.height / 2).toFloat()
                    )
            }

        ScreenColorDetectorImpl(
            modifier = colorDetectionModifier,
            offset = offset,
            enabled = enabled,
            color = color,
            containerSize = containerSize,
            screenshotState = screenshotState,
            content = content
        )
    }
}

@Composable
private fun ScreenColorDetectorImpl(
    modifier: Modifier,
    offset: Offset,
    enabled: Boolean,
    color: Color,
    containerSize: IntSize,
    screenshotState: ScreenshotState,
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier) {
        ScreenshotBox(
            modifier = modifier,
            screenshotState = screenshotState
        ) {
            content()
        }

        val size = with(LocalDensity.current) {
            DpSize(containerSize.width.toDp(), containerSize.height.toDp())
        }

        if (enabled) {
            screenshotState.imageBitmap?.let {
                ImageThumbWithColor(
                    modifier = Modifier.size(size),
                    bitmap = it,
                    offset = offset,
                    color = color
                )
            }
        }
    }
}

@Composable
private fun ImageThumbWithColor(
    modifier: Modifier,
    bitmap: ImageBitmap,
    thumbSize: Dp = 80.dp,
    offset: Offset,
    color: Color
) {
    Canvas(modifier = modifier) {

        val canvasWidth = size.width
        val canvasHeight = size.height

        if (color != Color.Unspecified) {

            drawCircle(Color.Black, radius = 36f, center = offset, style = Stroke(10f))
            drawCircle(Color.White, radius = 24f, center = offset, style = Stroke(10f))

            val imageThumbSize: Int
            val radius: Float

            with(density) {
                imageThumbSize = thumbSize.toPx().roundToInt()
                radius = 8.dp.toPx()
            }


            // If we are close by 25% of dimension of display on left side
            // move to right side to display image on top lef
            val isTouchOnLeftSide = (offset.x < imageThumbSize * 5 / 4 &&
                    offset.y < imageThumbSize * 5 / 4)

            // top left x coordinate of image thumb which can be either left or
            // right side based on user touch position
            val topLeftImageThumbX: Int = if (isTouchOnLeftSide)
                (canvasWidth - imageThumbSize).toInt() else 0

            // Center of image thumb
            val centerImageThumbX: Float = topLeftImageThumbX + imageThumbSize / 2f
            val centerImageThumbY: Float = imageThumbSize / 2f


            drawImage(
                image = bitmap,
                srcOffset = IntOffset(
                    (offset.x.toInt() - imageThumbSize / 2).coerceIn(
                        0,
                        (canvasWidth - imageThumbSize).toInt()
                    ),
                    (offset.y.toInt() - imageThumbSize / 2).coerceIn(
                        0,
                        (canvasHeight - imageThumbSize).toInt()
                    )
                ),
                srcSize = IntSize(imageThumbSize, imageThumbSize),
                dstOffset = IntOffset(
                    x = topLeftImageThumbX,
                    y = 0
                ),
                dstSize = IntSize(imageThumbSize, imageThumbSize)
            )

            drawCircle(
                color = Color.Black,
                radius = radius,
                center = Offset(centerImageThumbX, centerImageThumbY),
                style = Stroke(radius * .5f)
            )
            drawCircle(
                color = Color.White,
                radius = radius,
                center = Offset(centerImageThumbX, centerImageThumbY),
                style = Stroke(radius * .2f)
            )

            drawCircle(
                color = color,
                radius = imageThumbSize / 10f,
                center = Offset(centerImageThumbX, centerImageThumbY)
            )
        }
    }
}
