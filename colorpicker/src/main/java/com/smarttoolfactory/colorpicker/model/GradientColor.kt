package com.smarttoolfactory.colorpicker.model

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import com.smarttoolfactory.colorpicker.selector.gradient.GradientType
import com.smarttoolfactory.colorpicker.ui.GradientAngle
import com.smarttoolfactory.colorpicker.ui.GradientOffset


class GradientColor {

    var dpSize: DpSize by mutableStateOf(DpSize.Zero)
    var brushColor = BrushColor()
    var gradientType: GradientType by mutableStateOf( GradientType.Linear)
    val colorStop = mutableStateListOf<Pair<Float, Color>>()
    var tileMode by mutableStateOf(TileMode.Clamp)
    var gradientOffset by mutableStateOf(GradientOffset(GradientAngle.CW0))
    var center = derivedStateOf { DpOffset(dpSize.width / 2, dpSize.height / 2) }
    var radiusFraction: Float = .5f
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
