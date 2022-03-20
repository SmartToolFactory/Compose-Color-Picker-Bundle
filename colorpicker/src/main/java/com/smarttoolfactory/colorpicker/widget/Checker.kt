package com.smarttoolfactory.colorpicker.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

@Composable
private fun Checker(modifier: Modifier, shape: Shape, content: @Composable () -> Unit) {
    Box(modifier.drawChecker(shape)) {
        content()
    }
}

fun Modifier.drawChecker(shape: Shape, size: DpSize = DpSize.Unspecified) = this
    .clip(shape)
    .then(
        drawWithCache {
            this.onDrawBehind {
                val width = this.size.width
                val height = this.size.height

                val checkerSize = 10.dp
                    .toPx()
                    .coerceAtMost(height / 2)

                val checkerWidth =
                    (if (size != DpSize.Unspecified) size.width.toPx() else 4.dp.toPx()).coerceAtMost(
                        width / 2
                    )

                val checkerHeight =
                    (if (size != DpSize.Unspecified) size.height.toPx() else 10.dp.toPx()).coerceAtMost(
                        height / 2
                    )

                val horizontalSteps = (width / checkerSize).toInt()
                val verticalSteps = (height / checkerSize).toInt()

                for (y in 0..verticalSteps) {
                    for (x in 0..horizontalSteps) {
                        val isGrayTile = ((x + y) % 2 == 1)
                        drawRect(
                            color = if (isGrayTile) Color.LightGray else Color.White,
                            topLeft = Offset(x * checkerSize, y * checkerSize),
                            size = Size(checkerSize, checkerSize)
                        )
                    }
                }
            }
        }
    )