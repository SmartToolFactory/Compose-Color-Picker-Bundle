package com.smarttoolfactory.colorpicker.widget

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import com.smarttoolfactory.colorpicker.util.calculateColorInPixel
import com.smarttoolfactory.gesture.pointerMotionEvents
import com.smarttoolfactory.screenshot.ScreenshotBox
import com.smarttoolfactory.screenshot.rememberScreenshotState

@Composable
fun ScreenColorDetector(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit,
    onColorChange: (Color) -> Unit
) {
//    BoxWithConstraints {

    val screenshotState = rememberScreenshotState()

    var size by remember { mutableStateOf(IntSize.Zero) }
//        var size by remember {
//            mutableStateOf(
//                IntSize(
//                    constraints.maxWidth,
//                    constraints.maxHeight
//                )
//            )
//        }
    var offset by remember() {
        mutableStateOf(
            Offset(
                (size.width / 2).toFloat(),
                (size.height / 2).toFloat()
            )
        )
    }
    var color by remember { mutableStateOf(Color.Unspecified) }

    val colorDetectionModifier = modifier
        .pointerMotionEvents(Unit,
            onDown = { pointerInputChange ->
                if (screenshotState.bitmap == null) screenshotState.capture()

                screenshotState.bitmap?.let { bitmap ->

                    if (bitmap.width == 0 || bitmap.height == 0 || size == IntSize.Zero)
                        return@pointerMotionEvents


                    val offsetX = pointerInputChange.position.x.coerceIn(
                        0f,
                        size.width.toFloat()
                    )
                    val offsetY = pointerInputChange.position.y.coerceIn(
                        0f,
                        size.height.toFloat()
                    )

                    offset = Offset(offsetX, offsetY)

                    color = calculateColorInPixel(
                        offsetX = offsetX,
                        offsetY = offsetY,
                        width = size.width,
                        height = size.height,
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
                        size.width.toFloat()
                    )
                    val offsetY = pointerInputChange.position.y.coerceIn(
                        0f,
                        size.height.toFloat()
                    )

                    offset = Offset(offsetX, offsetY)

                    color = calculateColorInPixel(
                        offsetX = offsetX,
                        offsetY = offsetY,
                        width = size.width,
                        height = size.height,
                        bitmap = bitmap
                    )

                    onColorChange(color)
                }
            }
        )
        .drawWithContent {
            drawContent()

            drawCircle(Color.Black, radius = 36f, center = offset, style = Stroke(10f))
            drawCircle(Color.White, radius = 24f, center = offset, style = Stroke(10f))

            if (color != Color.Unspecified) {

                val colorDisplaySize = 150f
                val isTouchOnLeftSide =
                    (offset.x < colorDisplaySize + 50f && offset.y < colorDisplaySize + 50f)

                val colorOffset = Offset(
                    if (isTouchOnLeftSide) size.width - (colorDisplaySize + 20) else 20f,
                    20f
                )

                screenshotState.imageBitmap?.let { imageBitmap ->
//                    drawImage(
//                        image = imageBitmap,
//                        srcOffset = IntOffset((offset.x.toInt() - 100), offset.y.toInt()-100),
//                        srcSize = IntSize(200, 200),
//                        dstOffset = IntOffset(20, 20),
//                        dstSize = IntSize(200, 200)
//                    )

                    drawImage(
                        image = imageBitmap,
                        srcOffset = IntOffset(
                            (offset.x.toInt() - 100).coerceIn(0, size.width - 200),
                            (offset.y.toInt() - 100).coerceIn(0, size.height - 200)
                        ),
                        srcSize = IntSize(200, 200),
                        dstOffset = IntOffset(20, 20),
                        dstSize = IntSize(200, 200)
                    )
                }

//                drawRoundRect(
//                    color = color,
//                    topLeft = colorOffset,
//                    size = Size(colorDisplaySize, colorDisplaySize),
//                    cornerRadius = CornerRadius(colorDisplaySize / 4, colorDisplaySize / 4)
//                )
            }
        }
        .onSizeChanged {
            size = it
            offset = Offset((size.width / 2).toFloat(), (size.height / 2).toFloat())
        }

    ScreenshotBox(
        modifier = if (enabled) colorDetectionModifier else modifier,
        screenshotState = screenshotState
    ) {
        content()
    }
//    }
}



