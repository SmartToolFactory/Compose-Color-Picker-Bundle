package com.smarttoolfactory.colorpicker.selector.gradient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.ui.GradientAngle
import com.smarttoolfactory.colorpicker.ui.GradientOffset
import com.smarttoolfactory.colorpicker.ui.Grey800
import com.smarttoolfactory.colorpicker.ui.Orange400
import com.smarttoolfactory.colorpicker.util.fractionToIntPercent
import com.smarttoolfactory.colorpicker.widget.ExposedSelectionMenu
import com.smarttoolfactory.slider.ColorBrush
import com.smarttoolfactory.slider.ColorfulSlider
import com.smarttoolfactory.slider.MaterialSliderDefaults
import com.smarttoolfactory.slider.ui.InactiveTrackColor

internal val gradientOptions = listOf(
    "Linear",
    "Radial",
    "Sweep"
)
internal val gradientTileModeOptions = listOf("Clamp", "Repeated", "Mirror", "Decal")

@Composable
fun GradientSelector(color: Color, size: Size = Size.Unspecified) {

    var gradientSelection by remember { mutableStateOf(0) }
    var tileModeSelection by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ExposedSelectionMenu(
            modifier = Modifier.fillMaxWidth(),
            index = gradientSelection,
            title = "Gradient",
            options = gradientOptions,
            onSelected = {
                gradientSelection = it
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        ExposedSelectionMenu(
            modifier = Modifier.fillMaxWidth(),
            index = tileModeSelection,
            title = "Tile Mode",
            options = gradientTileModeOptions,
            onSelected = {
                tileModeSelection = it
            }
        )

        val tileMode = when (tileModeSelection) {
            0 -> TileMode.Clamp
            1 -> TileMode.Repeated
            2 -> TileMode.Mirror
            else -> TileMode.Decal
        }
        LinearGradientSelection(currentColor = color, tileMode = tileMode)
    }
}

@Composable
internal fun LinearGradientSelection(currentColor: Color, tileMode: TileMode) {

    val colorStops = remember {
        mutableStateListOf(
            0.0f to Color.Red,
            0.3f to Color.Green,
            1.0f to Color.Blue,
        )
    }

    var gradientOffset by remember {
        mutableStateOf(
            GradientOffset(GradientAngle.CW0)
        )
    }

    val brush = if (colorStops.size == 1) {
        val color = colorStops.first().second
        Brush.linearGradient(listOf(color, color))
    } else {
        Brush.linearGradient(
            colorStops = colorStops.toTypedArray(),
            start = gradientOffset.start,
            end = gradientOffset.end,
            tileMode = tileMode
        )
    }

    var size by remember {
        mutableStateOf(Size.Zero)
    }
    // Display Brush
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(brush)
            .onSizeChanged {
                size = Size(it.width.toFloat(), it.height.toFloat())
            }
    )

    GradientOffsetSelection(
        size = size
    ) { offset ->
        gradientOffset = offset
    }

    ColorStopSelection(
        color = currentColor,
        colorStops = colorStops,
        onRemoveClick = { index: Int ->
            if (colorStops.size > 2) {
                colorStops.removeAt(index)
            }
        },
        onValueChange = { index: Int, pair: Pair<Float, Color> ->
            colorStops[index] = pair.copy()
        },
        onAddColorStop = { pair: Pair<Float, Color> ->
            colorStops.add(pair)
        }
    )
}

@Composable
internal fun ColorStopSelection(
    color: Color,
    colorStops: List<Pair<Float, Color>>,
    onRemoveClick: (Int) -> Unit,
    onAddColorStop: (Pair<Float, Color>) -> Unit,
    onValueChange: (Int, Pair<Float, Color>) -> Unit

) {

    ExpandableColumn(title = "Colors and Stops", color = Orange400) {
        Column {
            colorStops.forEachIndexed { index: Int, pair: Pair<Float, Color> ->
                GradientColorStopSelection(
                    index = index,
                    color = pair.second,
                    value = pair.first,
                    onRemoveClick = {
                        onRemoveClick(it)

                    },
                    onValueChange = { newPair: Pair<Float, Color> ->
                        onValueChange(index, newPair)
                        println("onValueChange: ")

                    }
                )
            }

            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onAddColorStop(Pair(1f, color))
                }) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Add new Color",
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
private fun GradientColorStopSelection(
    index: Int,
    color: Color,
    value: Float,
    onRemoveClick: (Int) -> Unit,
    onValueChange: (Pair<Float, Color>) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(percent = 25))
                    .background(color)
                    .size(40.dp)
            )

            Text(
                text = "${value.fractionToIntPercent()}%",
                fontSize = 10.sp,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.width(10.dp))

        ColorfulSlider(
            modifier = Modifier.weight(1f),
            value = value,
            onValueChange = { value ->
                onValueChange(
                    Pair(value, color)
                )
            },
            thumbRadius = 10.dp,
            trackHeight = 8.dp,
            colors = MaterialSliderDefaults.materialColors(
                inactiveTrackColor = ColorBrush(InactiveTrackColor)
            )
        )

        Spacer(modifier = Modifier.width(10.dp))
        FloatingActionButton(
            modifier = Modifier
                .padding(start = 4.dp, end = 12.dp)
                .size(20.dp),
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 0.dp
            ),
            backgroundColor = Grey800,
            contentColor = Color.White,
            onClick = { onRemoveClick(index) }) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "close",
            )
        }
    }
}
