package com.smarttoolfactory.colorpicker.slider

import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import com.smarttoolfactory.colorpicker.ui.ActiveTrackColor
import com.smarttoolfactory.colorpicker.ui.ThumbColor

/**
 * Data class that contains color or/and brush property for drawing track section of
 * [ColorfulSlider]
 */
data class ColorBrush(val brush: Brush? = null, val color: Color)

object MaterialSliderDefaults {

    /**
     * [ColorfulSlider] [ColorBrush] with [MaterialTheme]'s default [Color]s.
     */
    @Composable
    fun defaultColors(
        thumbColor: ColorBrush = ColorBrush(color = MaterialTheme.colors.primary),
        disabledThumbColor: ColorBrush = ColorBrush(
            color = MaterialTheme.colors.onSurface
                .copy(alpha = ContentAlpha.disabled)
                .compositeOver(MaterialTheme.colors.surface)
        ),
        activeTrackColor: ColorBrush = ColorBrush(color = MaterialTheme.colors.primary),
        disabledActiveTrackColor: ColorBrush = ColorBrush(
            color = MaterialTheme.colors.onSurface.copy(alpha = DisabledActiveTrackAlpha)
        ),
        inactiveTrackColor: ColorBrush = ColorBrush(
            color = activeTrackColor.color.copy(alpha = InactiveTrackAlpha)
        ),
        disabledInactiveTrackColor: ColorBrush = ColorBrush(
            color = disabledActiveTrackColor.color.copy(alpha = DisabledInactiveTrackAlpha)
        ),
    ): MaterialSliderColors {

        return DefaultMaterialSliderColors(
            thumbColor,
            disabledThumbColor,
            activeTrackColor,
            inactiveTrackColor,
            disabledActiveTrackColor,
            disabledInactiveTrackColor
        )
    }

    /**
     * [ColorfulSlider] [ColorBrush] set with blue, white and gray [Color] theme.
     */
    @Composable
    fun materialColors(
        thumbColor: ColorBrush = ColorBrush(color = ThumbColor),
        disabledThumbColor: ColorBrush = ColorBrush(color = ThumbColor),
        activeTrackColor: ColorBrush = ColorBrush(color = ActiveTrackColor),
        disabledActiveTrackColor: ColorBrush =
            ColorBrush(color = ActiveTrackColor.copy(alpha = DisabledActiveTrackAlpha)),
        inactiveTrackColor: ColorBrush? = null,
        disabledInactiveTrackColor: ColorBrush? = null
    ): MaterialSliderColors {

        return DefaultMaterialSliderColors(
            thumbColor,
            disabledThumbColor,
            activeTrackColor,
            inactiveTrackColor,
            disabledActiveTrackColor,
            disabledInactiveTrackColor
        )
    }

    /**
     * [ColorfulSlider] [ColorBrush] set with no predefined [Color]s.
     */
    @Composable
    fun customColors(
        thumbColor: ColorBrush,
        disabledThumbColor: ColorBrush,
        activeTrackColor: ColorBrush,
        disabledActiveTrackColor: ColorBrush,
        inactiveTrackColor: ColorBrush?,
        disabledInactiveTrackColor: ColorBrush?,
    ): MaterialSliderColors {
        return DefaultMaterialSliderColors(
            thumbColor,
            disabledThumbColor,
            activeTrackColor,
            inactiveTrackColor,
            disabledActiveTrackColor,
            disabledInactiveTrackColor
        )
    }

    /*
        Brushes
     */
    @Composable
    fun greenBrush() {

    }

    @Composable
    fun redBrush() {

    }

    @Composable
    fun blueBrush() {

    }

    @Composable
    fun rainbowBrush() {

    }


    @Stable
    interface MaterialSliderColors {

        @Composable
        fun thumbColor(enabled: Boolean): State<ColorBrush>

        @Composable
        fun trackColor(enabled: Boolean, active: Boolean): State<ColorBrush?>

    }

    class DefaultMaterialSliderColors(
        private val thumbColor: ColorBrush,
        private val disabledThumbColor: ColorBrush,
        private val activeTrackColor: ColorBrush,
        private val inactiveTrackColor: ColorBrush?,
        private val disabledActiveTrackColor: ColorBrush,
        private val disabledInactiveTrackColor: ColorBrush?,
    ) : MaterialSliderColors {

        @Composable
        override fun thumbColor(enabled: Boolean): State<ColorBrush> {
            return rememberUpdatedState(if (enabled) thumbColor else disabledThumbColor)
        }

        @Composable
        override fun trackColor(enabled: Boolean, active: Boolean): State<ColorBrush?> {
            return rememberUpdatedState(
                if (enabled) {
                    if (active) activeTrackColor else inactiveTrackColor
                } else {
                    if (active) disabledActiveTrackColor else disabledInactiveTrackColor
                }
            )
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false

            other as DefaultMaterialSliderColors

            if (thumbColor != other.thumbColor) return false
            if (disabledThumbColor != other.disabledThumbColor) return false
            if (activeTrackColor != other.activeTrackColor) return false
            if (inactiveTrackColor != other.inactiveTrackColor) return false
            if (disabledActiveTrackColor != other.disabledActiveTrackColor) return false
            if (disabledInactiveTrackColor != other.disabledInactiveTrackColor) return false

            return true
        }

        override fun hashCode(): Int {
            var result = thumbColor.hashCode()
            result = 31 * result + disabledThumbColor.hashCode()
            result = 31 * result + activeTrackColor.hashCode()
            result = 31 * result + inactiveTrackColor.hashCode()
            result = 31 * result + disabledActiveTrackColor.hashCode()
            result = 31 * result + disabledInactiveTrackColor.hashCode()
            return result
        }
    }

    /**
     * Default alpha of the inactive part of the track
     */
    private const val InactiveTrackAlpha = 0.24f

    /**
     * Default alpha for the track when it is disabled but active
     */
    private const val DisabledInactiveTrackAlpha = 0.12f

    /**
     * Default alpha for the track when it is disabled and inactive
     */
    private const val DisabledActiveTrackAlpha = 0.32f

    /**
     * Default alpha of the ticks that are drawn on top of the track
     */
    private const val TickAlpha = 0.54f

    /**
     * Default alpha for tick marks when they are disabled
     */
    private const val DisabledTickAlpha = 0.12f

}