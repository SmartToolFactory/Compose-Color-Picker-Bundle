package com.smarttoolfactory.colorpicker.selector.gradient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.model.GradientColor
import com.smarttoolfactory.colorpicker.ui.Blue400
import com.smarttoolfactory.colorpicker.ui.GradientOffset
import com.smarttoolfactory.colorpicker.ui.Grey800
import com.smarttoolfactory.colorpicker.ui.Orange400
import com.smarttoolfactory.colorpicker.util.fractionToIntPercent
import com.smarttoolfactory.colorpicker.widget.ExposedSelectionMenu
import com.smarttoolfactory.colorpicker.widget.drawChecker
import com.smarttoolfactory.slider.ColorBrush
import com.smarttoolfactory.slider.ColorfulSlider
import com.smarttoolfactory.slider.MaterialSliderDefaults
import com.smarttoolfactory.slider.ui.InactiveTrackColor

enum class GradientType {
    Linear, Radial, Sweep
}

internal val gradientOptions = listOf(
    "Linear",
    "Radial",
    "Sweep"
)

internal val gradientTileModeOptions = listOf("Clamp", "Repeated", "Mirror", "Decal")

@Composable
fun GradientSelector(
    color: Color,
    gradientColor: GradientColor
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        // Display Brush
//        ExpandableColumn(title = "Brush Output", color = Green400) {
//            BrushDisplay(gradientColor = gradientColor)
//
//        }

        GradientProperties(gradientColor = gradientColor)

        // Gradient type selection
        when (gradientColor.gradientType) {
            GradientType.Linear ->
                LinearGradientSelection(gradientColor.size) { offset: GradientOffset ->
                    gradientColor.gradientOffset = offset
                }
            GradientType.Radial ->
                RadialGradientSelection { centerFriction: Offset, radiusFriction: Float ->
                    gradientColor.centerFriction = centerFriction
                    gradientColor.radiusFriction = radiusFriction
                }
            GradientType.Sweep ->
                SweepGradientSelection { centerFriction: Offset ->
                    gradientColor.centerFriction = centerFriction
                }
        }

        // Color Stops and Colors
        ColorStopSelection(
            color = color,
            colorStops = gradientColor.colorStops,
            onRemoveClick = { index: Int ->
                if (gradientColor.colorStops.size > 2) {
                    gradientColor.colorStops.removeAt(index)
                }
            },
            onValueChange = { index: Int, pair: Pair<Float, Color> ->
                gradientColor.colorStops[index] = pair.copy()
            },
            onAddColorStop = { pair: Pair<Float, Color> ->
                gradientColor.colorStops.add(pair)
            }
        )
    }
}

@Composable
private fun GradientProperties(gradientColor: GradientColor) {

    var tileModeSelection by remember { mutableStateOf(0) }
    gradientColor.tileMode = when (tileModeSelection) {
        0 -> TileMode.Clamp
        1 -> TileMode.Repeated
        2 -> TileMode.Mirror
        else -> TileMode.Decal
    }


    ExpandableColumn(
        title = "Gradient Properties",
        color = Blue400,
        initialExpandState = false
    ) {
        Column {
            ExposedSelectionMenu(
//                modifier = Modifier.fillMaxWidth(),
                index = gradientColor.gradientType.ordinal,
                title = "Gradient Type",
                options = if (gradientColor.size == Size.Zero) gradientOptions.subList(
                    0,
                    1
                ) else gradientOptions,
                onSelected = {
                    gradientColor.gradientType = GradientType.values()[it]
                }
            )

            Spacer(modifier = Modifier.height(5.dp))

            if (gradientColor.gradientType != GradientType.Sweep) {
                ExposedSelectionMenu(
//                    modifier = Modifier
//                        .padding(end = 10.dp)
//                        .fillMaxWidth(),
                    index = tileModeSelection,
                    title = "Tile Mode",
                    options = gradientTileModeOptions,
                    onSelected = {
                        tileModeSelection = it
                    }
                )
            }
        }
    }
}

@Composable
fun BrushDisplay(
    modifier: Modifier = Modifier,
    gradientColor: GradientColor
) {
    // Display Brush
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        contentAlignment = Alignment.Center
    ) {

        val size = gradientColor.size
        val contentWidth = size.width
        val contentHeight = size.height
        val contentAspectRatio = contentWidth / contentHeight

        var boxHeight: Float = constraints.maxHeight.toFloat()
        var boxWidth: Float = boxHeight * contentAspectRatio

        if (boxWidth > constraints.maxWidth) {
            boxWidth = constraints.maxWidth.toFloat()
            boxHeight = (boxWidth / contentAspectRatio)

        }

        val gradientType = gradientColor.gradientType
        val colorStops = gradientColor.colorStops
        val gradientOffset = gradientColor.gradientOffset
        val tileMode = gradientColor.tileMode
        val centerFriction = gradientColor.centerFriction
        val radiusFriction = gradientColor.radiusFriction

        val center = Offset(
            boxWidth * centerFriction.x,
            boxHeight * centerFriction.y
        )

        val boxWidthInDp: Dp
        val boxHeightInDp: Dp
        with(LocalDensity.current) {
            boxWidthInDp = boxWidth.toDp()
            boxHeightInDp = boxHeight.toDp()
        }

        val radius = (boxHeight * radiusFriction).coerceAtLeast(0.01f)

        val brush = when (gradientType) {
            GradientType.Linear -> {
                if (colorStops.size == 1) {
                    val brushColor = colorStops.first().second
                    Brush.linearGradient(listOf(brushColor, brushColor))
                } else {
                    Brush.linearGradient(
                        colorStops = colorStops.toTypedArray(),
                        start = gradientOffset.start,
                        end = gradientOffset.end,
                        tileMode = tileMode
                    )
                }
            }
            GradientType.Radial -> {
                Brush.radialGradient(
                    colorStops = colorStops.toTypedArray(),
                    center = center,
                    radius = radius,
                    tileMode = tileMode
                )
            }

            GradientType.Sweep -> {
                Brush.sweepGradient(
                    colorStops = colorStops.toTypedArray(),
                    center = center
                )
            }
        }

        Box(
            modifier = Modifier
                .height(boxHeightInDp)
                .width(boxWidthInDp)
                .drawChecker(RoundedCornerShape(5.dp))
                .background(brush, RoundedCornerShape(5.dp))
        )
    }
}

@Composable
internal fun ColorStopSelection(
    color: Color,
    colorStops: List<Pair<Float, Color>>,
    onRemoveClick: (Int) -> Unit,
    onAddColorStop: (Pair<Float, Color>) -> Unit,
    onValueChange: (Int, Pair<Float, Color>) -> Unit
) {

    ExpandableColumn(
        title = "Colors and Stops",
        color = Orange400
    ) {
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
                    .drawChecker(RoundedCornerShape(percent = 25))
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
