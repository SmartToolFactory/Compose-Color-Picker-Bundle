package com.smarttoolfactory.colorpicker

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.smarttoolfactory.colorpicker.ui.ActiveTrackColor
import com.smarttoolfactory.colorpicker.ui.InactiveTrackColor
import com.smarttoolfactory.colorpicker.ui.ThumbColor

data class ColorBrush(var brush: Brush? = null, var color: Color)

private class DefaultMaterialSliderColors(
    val thumbColor: ColorBrush,
    val activeTrackColor: ColorBrush,
    val inactiveTrackColor: ColorBrush,
) : MaterialSliderColors {
    override fun thumbColor(enabled: Boolean): ColorBrush {
        return thumbColor
    }

    override fun trackColor(enabled: Boolean, active: Boolean): ColorBrush {
        return if (active) activeTrackColor else inactiveTrackColor
    }
}

interface MaterialSliderColors {

    fun thumbColor(enabled: Boolean = true): ColorBrush
    fun trackColor(enabled: Boolean = true, active: Boolean): ColorBrush

}

object MaterialSliderDefaults {
    fun colors(
        thumbColor: ColorBrush = ColorBrush(color = ThumbColor),
        activeTrackColor: ColorBrush = ColorBrush(color = ActiveTrackColor),
        inactiveTrackColor: ColorBrush = ColorBrush(color = InactiveTrackColor)
    ): MaterialSliderColors {
        return DefaultMaterialSliderColors(
            thumbColor,
            activeTrackColor,
            inactiveTrackColor
        )
    }
}