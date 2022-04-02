package com.smarttoolfactory.colorpicker.model

import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import com.smarttoolfactory.colorpicker.selector.gradient.GradientType
import com.smarttoolfactory.colorpicker.ui.GradientAngle
import com.smarttoolfactory.colorpicker.ui.GradientOffset

@Composable
fun rememberGradientColor(dpSize: DpSize = DpSize.Zero): GradientColor {

    val density = LocalDensity.current

    return remember {

        val size = if (dpSize == DpSize.Zero) {
            Size.Zero
        } else {
            with(density) {
                Size(
                    dpSize.width.toPx(),
                    dpSize.height.toPx()
                )
            }
        }
        GradientColor(size)
    }
}

class GradientColor(size: Size) {

    var size by mutableStateOf(size)
    val brushColor: BrushColor
        get() {
            val colorStops = colorStops.toTypedArray()
            val brush = when (gradientType) {
                GradientType.Linear -> Brush.linearGradient(
                    colorStops = colorStops,
                    start = gradientOffset.start,
                    end = gradientOffset.end,
                    tileMode = tileMode
                )
                GradientType.Radial -> Brush.radialGradient(
                    colorStops = colorStops,
                    center = Offset(
                        x = size.width * centerFriction.x,
                        y = size.height * centerFriction.y
                    ),
                    radius = ((size.width.coerceAtLeast(size.height)) / 2 * radiusFriction)
                        .coerceAtLeast(0.01f),
                    tileMode = tileMode
                )
                GradientType.Sweep -> Brush.sweepGradient(
                    colorStops = colorStops,
                    center = Offset(
                        x = size.width * centerFriction.x,
                        y = size.height * centerFriction.y
                    ),
                )
            }
            return BrushColor(brush = brush)
        }
    var gradientType: GradientType by mutableStateOf(GradientType.Linear)
    var colorStops = mutableStateListOf(
        0.0f to Color.Red,
        0.3f to Color.Green,
        1.0f to Color.Blue,
    )
    var tileMode by mutableStateOf(TileMode.Clamp)
    var gradientOffset by mutableStateOf(GradientOffset(GradientAngle.CW0))
    var centerFriction by mutableStateOf(Offset(.5f, .5f))
    var radiusFriction: Float by mutableStateOf(.5f)
}

/**
 * Data class that contains [Brush] and [Color] and can return either based on user selection.
 */
data class BrushColor(
    var color: Color = Color.Unspecified,
    var brush: Brush? = null
) {
    /**
     * [Brush] that is not **null** [brush] property or [SolidColor] that is not nullable and
     * contains [color] property as [SolidColor.value]
     */
    val activeBrush: Brush
        get() = brush ?: solidColor

    /**
     * [SolidColor] is a [Brush] that
     * wraps [color] property that is used for [activeBrush] if [brush] property is **null**
     */
    val solidColor = SolidColor(color)
}
