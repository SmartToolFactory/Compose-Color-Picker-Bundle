package com.smarttoolfactory.colorpicker.selector.gradient

import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.ui.*
import com.smarttoolfactory.colorpicker.widget.ExposedSelectionMenu
import com.smarttoolfactory.slider.ui.ActiveTrackColor
import com.smarttoolfactory.slider.ui.InactiveTrackColor
import com.smarttoolfactory.slider.ui.ThumbColor
import kotlin.math.roundToInt

@Composable
internal fun GradientOffsetSelection(
    size: Size,
    onGradientOffsetChange: (GradientOffset) -> Unit
) {
    ExpandableColumn(
        title = "Gradient Position/Angle",
        color = Pink400
    ) {
        GradientOffsetTypeSelection(size, onGradientOffsetChange)
    }
}

@Composable
private fun GradientOffsetTypeSelection(
    size: Size,
    onGradientOffsetChange: (GradientOffset) -> Unit,
) {
    val gradientOffsetOptions = listOf("Angle", "Position")

    var gradientOffsetOption by remember { mutableStateOf(0) }

    Column() {
        ExposedSelectionMenu(
            modifier = Modifier.fillMaxWidth(),
            index = gradientOffsetOption,
            title = "Gradient Offset Type",
            options = gradientOffsetOptions,
            onSelected = {
                gradientOffsetOption = it
            }
        )

        when (gradientOffsetOption) {
            0 -> {
                GradientOffsetAngleSelectionSlider(onGradientOffsetChange = onGradientOffsetChange)
            }
            else -> {
                GradientOffsetPositionSelection(
                    size = size,
                    onGradientOffsetChange = onGradientOffsetChange
                )
            }
        }
    }

}

@Composable
private fun GradientOffsetPositionSelection(
    size: Size,
    onGradientOffsetChange: (GradientOffset) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        var startX by remember { mutableStateOf(0f) }
        var startY by remember { mutableStateOf(0f) }
        var endX by remember { mutableStateOf(1f) }
        var endY by remember { mutableStateOf(1f) }

        onGradientOffsetChange(
            GradientOffset(
                start = Offset(
                    x = size.width * startX,
                    y = size.height * startY
                ),
                end = Offset(
                    x = size.width * endX,
                    y = size.height * endY
                )
            )
        )

        SliderWithPercent(
            modifier = Modifier.fillMaxWidth(),
            title = "StartX",
            value = startX
        ) {
            startX = it
        }

        SliderWithPercent(
            modifier = Modifier.fillMaxWidth(),
            title = "StartY",
            value = startY
        ) {
            startY = it
        }
        SliderWithPercent(
            modifier = Modifier.fillMaxWidth(),
            title = "EndX",
            value = endX
        ) {
            endX = it
        }
        SliderWithPercent(
            modifier = Modifier.fillMaxWidth(),
            title = "EndY",
            value = endY
        ) {
            endY = it
        }
    }
}

@Composable
private fun GradientOffsetAngleSelection(
    onGradientOffsetChange: (GradientOffset) -> Unit
) {
    var gradientOffsetSelection by remember { mutableStateOf(0) }

    ExposedSelectionMenu(
        modifier = Modifier.fillMaxWidth(),
        index = gradientOffsetSelection,
        title = "Angle",
        options = gradientAngleOptions,
        onSelected = {
            gradientOffsetSelection = it

            val gradientOffset = when (gradientOffsetSelection) {
                0 -> GradientOffset(GradientAngle.CW0)
                1 -> GradientOffset(GradientAngle.CW45)
                2 -> GradientOffset(GradientAngle.CW90)
                3 -> GradientOffset(GradientAngle.CW135)
                4 -> GradientOffset(GradientAngle.CW180)
                5 -> GradientOffset(GradientAngle.CW225)
                6 -> GradientOffset(GradientAngle.CW270)
                else -> GradientOffset(GradientAngle.CW315)
            }

            onGradientOffsetChange(gradientOffset)
        }
    )
}

@Composable
private fun GradientOffsetAngleSelectionSlider(
    onGradientOffsetChange: (GradientOffset) -> Unit
) {

    // Offsets for gradients based on selected angle
    var gradientOffset by remember {
        mutableStateOf(GradientOffset(GradientAngle.CW0))
    }


    var angleSelection by remember { mutableStateOf(0f) }
    var angleText by remember { mutableStateOf("0°") }


    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.width(10.dp))

        Slider(
            modifier = Modifier.weight(1f),
            value = angleSelection,
            onValueChange = {
                angleSelection = it

                gradientOffset = when (angleSelection.roundToInt()) {
                    0 -> {
                        angleText = "0°"
                        GradientOffset(GradientAngle.CW0)
                    }
                    1 -> {
                        angleText = "45°"
                        GradientOffset(GradientAngle.CW45)
                    }
                    2 -> {
                        angleText = "90°"
                        GradientOffset(GradientAngle.CW90)
                    }
                    3 -> {
                        angleText = "135°"
                        GradientOffset(GradientAngle.CW135)
                    }
                    4 -> {
                        angleText = "180°"
                        GradientOffset(GradientAngle.CW180)
                    }

                    5 -> {
                        angleText = "225°"
                        GradientOffset(GradientAngle.CW225)
                    }
                    6 -> {
                        angleText = "270°"
                        GradientOffset(GradientAngle.CW270)
                    }
                    else -> {
                        angleText = "315°"
                        GradientOffset(GradientAngle.CW315)
                    }
                }

                onGradientOffsetChange(gradientOffset)
            },
            steps = 6,
            valueRange = 0f..7f,
            colors = SliderDefaults.colors(
                thumbColor = ThumbColor,
                activeTrackColor = ActiveTrackColor,
                inactiveTrackColor = InactiveTrackColor,
                activeTickColor = Red400,
                inactiveTickColor = Red400
            )
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = angleText,
            fontSize = 12.sp,
            color = Grey400,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(36.dp)
        )
    }

}

private val gradientAngleOptions = listOf(
    "0°",
    "45°",
    "90°",
    "135°",
    "180°",
    "225°",
    "270°",
    "315°",
)