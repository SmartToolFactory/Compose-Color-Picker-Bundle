package com.smarttoolfactory.composecolorpicker.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.selector.SelectorRectHueLightnessHSLHorizontal
import com.smarttoolfactory.colorpicker.widget.drawChecker
import com.smarttoolfactory.extendedcolors.util.ColorUtil
import com.smarttoolfactory.extendedcolors.util.roundToTwoDigits
import com.smarttoolfactory.slider.ColorfulSlider
import com.smarttoolfactory.slider.MaterialSliderDefaults
import com.smarttoolfactory.slider.SliderBrushColor
import kotlin.math.roundToInt

@Composable
fun ColorPickerRectHueLightnessHSLHorizontal(
    modifier: Modifier = Modifier,
    selectionRadius: Dp = 8.dp,
    initialColor: Color,
    onColorChange: (Color, String) -> Unit
) {
    val hslArray = ColorUtil.colorToHSL(initialColor)

    var hue by remember { mutableStateOf(hslArray[0]) }
    val saturation by remember { mutableStateOf(hslArray[1]) }
    var lightness by remember { mutableStateOf(hslArray[2]) }
    var alpha by remember { mutableStateOf(initialColor.alpha) }


    val currentColor =
        Color.hsl(hue = hue, saturation = saturation, lightness = lightness, alpha = alpha)


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        SelectorRectHueLightnessHSLHorizontal(
            modifier = Modifier
                .aspectRatio(1f, true)
                .fillMaxWidth(),
            hue = hue,
            lightness = lightness,
            selectionRadius = selectionRadius,
            onChange = { h, l ->
                hue = h
                lightness = l

                onColorChange(currentColor, ColorUtil.colorToHexAlpha(currentColor))
            },
        )

        Text(
            text = "OPACITY",
            modifier = Modifier
                .padding(top = 8.dp)
                .alpha(0.38f),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.labelSmall,
        )
        Slider(
            modifier = Modifier,
            value = alpha,
            onValueChange = { alpha = it },
            brush = Brush.linearGradient(
                colors = listOf(
                    currentColor.copy(alpha = 0f),
                    currentColor.copy(alpha = 1f),
                )
            ),
            drawChecker = true
        )
    }
}


@Composable
@Preview
private fun Preview() {
    MaterialTheme {
        Box(modifier = Modifier.background(Color.Red)) {
            Surface(
                modifier = Modifier
                    .background(Color(0xFFF9F9F9))
                    .padding(16.dp)
            ) {
                var color by remember {
                    mutableStateOf(Color(0xFF00FFF7))
                }
                Column {
                    ColorPickerRectHueLightnessHSLHorizontal(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp)),
                        initialColor = color,
                        onColorChange = { c, _ -> color = c.copy(alpha = color.alpha) }
                    )


                }
            }
        }
    }
}

@Composable
private fun Slider(
    modifier: Modifier = Modifier,
    value: Float,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    onValueChange: (Float) -> Unit,
    brush: Brush,
    drawChecker: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Box(
            modifier = modifier.weight(1f),
            contentAlignment = Alignment.CenterStart
        ) {
            if (drawChecker) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp)
                        .drawChecker(shape = RoundedCornerShape(50))
                )
            }

            ColorfulSlider(
                value = value,
                modifier = Modifier,
                thumbRadius = 18.dp,
                trackHeight = 36.dp,
                onValueChange = { value ->
                    onValueChange(value.roundToTwoDigits())
                },
                valueRange = valueRange,
                coerceThumbInTrack = true,
                colors = MaterialSliderDefaults.materialColors(
                    activeTrackColor = SliderBrushColor(brush = brush),
                    inactiveTrackColor = SliderBrushColor(color = Color.Transparent)
                ),
                drawInactiveTrack = false
            )
        }

        Surface(shape = MaterialTheme.shapes.small, color = Color.White) {
            Text(
                modifier = Modifier
                    .padding(4.dp)
                    .padding(horizontal = 16.dp),
                text = "${(value * 100).roundToInt()}%",
                fontWeight = FontWeight.Bold,
            )
        }
    }

}