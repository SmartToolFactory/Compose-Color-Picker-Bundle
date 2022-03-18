package com.smarttoolfactory.colorpicker.slider

import androidx.compose.material.*
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
data class ColorBrush(val brush: Brush? = null, val color: Color = Color.Unspecified)

/**
 * Creates a [MaterialSliderColors] that represents the different colors used in parts of the
 * [Slider] in different states.
 *
 * For the name references below the words "active" and "inactive" are used. Active part of
 * the slider is filled with progress, so if slider's progress is 30% out of 100%, left (or
 * right in RTL) 30% of the track will be active, the rest is not active.
 *
 * @param thumbColor thumb color when enabled
 * @param disabledThumbColor thumb colors when disabled
 * @param activeTrackColor color of the track in the part that is "active", meaning that the
 * thumb is ahead of it
 * @param inactiveTrackColor color of the track in the part that is "inactive", meaning that the
 * thumb is before it
 * @param disabledActiveTrackColor color of the track in the "active" part when the Slider is
 * disabled
 * @param disabledInactiveTrackColor color of the track in the "inactive" part when the
 * Slider is disabled
 * @param activeTickColor colors to be used to draw tick marks on the active track, if `steps`
 * is specified
 * @param inactiveTickColor colors to be used to draw tick marks on the inactive track, if
 * `steps` are specified on the Slider is specified
 * @param disabledActiveTickColor colors to be used to draw tick marks on the active track
 * when Slider is disabled and when `steps` are specified on it
 * @param disabledInactiveTickColor colors to be used to draw tick marks on the inactive part
 * of the track when Slider is disabled and when `steps` are specified on it
 */
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
        activeTickColor: ColorBrush = ColorBrush(
            color = contentColorFor(activeTrackColor.color).copy(alpha = SliderDefaults.TickAlpha)
        ),
        inactiveTickColor: ColorBrush = ColorBrush(
            color = activeTrackColor.color.copy(alpha = SliderDefaults.TickAlpha)
        ),
        disabledActiveTickColor: ColorBrush = ColorBrush(
            color = activeTickColor.color.copy(alpha = SliderDefaults.DisabledTickAlpha)
        ),
        disabledInactiveTickColor: ColorBrush = ColorBrush(
            color = disabledInactiveTrackColor.color
                .copy(alpha = SliderDefaults.DisabledTickAlpha)
        )
    ): MaterialSliderColors {

        return DefaultMaterialSliderColors(
            thumbColor = thumbColor,
            disabledThumbColor = disabledThumbColor,
            activeTrackColor = activeTrackColor,
            inactiveTrackColor = inactiveTrackColor,
            disabledActiveTrackColor = disabledActiveTrackColor,
            disabledInactiveTrackColor = disabledInactiveTrackColor,
            activeTickColor = activeTickColor,
            inactiveTickColor = inactiveTickColor,
            disabledActiveTickColor = disabledActiveTickColor,
            disabledInactiveTickColor = disabledInactiveTickColor
        )
    }

    /**
     * [ColorfulSlider] [ColorBrush] set with blue, white and gray [Color] theme.
     */
    @Composable
    fun materialColors(
        thumbColor: ColorBrush = ColorBrush(color = ThumbColor),
        disabledThumbColor: ColorBrush = ColorBrush(
            color = ThumbColor
                .copy(alpha = ContentAlpha.disabled)
                .compositeOver(MaterialTheme.colors.surface)
        ),
        activeTrackColor: ColorBrush = ColorBrush(color = ActiveTrackColor),
        disabledActiveTrackColor: ColorBrush = ColorBrush(
            color = MaterialTheme.colors.onSurface.copy(alpha = DisabledActiveTrackAlpha)
        ),
        inactiveTrackColor: ColorBrush = ColorBrush(
            color = activeTrackColor.color.copy(alpha = InactiveTrackAlpha)
        ),
        disabledInactiveTrackColor: ColorBrush = ColorBrush(
            color = disabledActiveTrackColor.color.copy(alpha = DisabledInactiveTrackAlpha)
        ),
        activeTickColor: ColorBrush = ColorBrush(
            color = contentColorFor(activeTrackColor.color).copy(alpha = SliderDefaults.TickAlpha)
        ),
        inactiveTickColor: ColorBrush = ColorBrush(
            color = activeTrackColor.color.copy(alpha = SliderDefaults.TickAlpha)
        ),
        disabledActiveTickColor: ColorBrush = ColorBrush(
            color = activeTickColor.color.copy(alpha = SliderDefaults.DisabledTickAlpha)
        ),
        disabledInactiveTickColor: ColorBrush = ColorBrush(
            color = disabledInactiveTrackColor.color
                .copy(alpha = SliderDefaults.DisabledTickAlpha)
        )
    ): MaterialSliderColors {

        return DefaultMaterialSliderColors(
            thumbColor = thumbColor,
            disabledThumbColor = disabledThumbColor,
            activeTrackColor = activeTrackColor,
            inactiveTrackColor = inactiveTrackColor,
            disabledActiveTrackColor = disabledActiveTrackColor,
            disabledInactiveTrackColor = disabledInactiveTrackColor,
            activeTickColor = activeTickColor,
            inactiveTickColor = inactiveTickColor,
            disabledActiveTickColor = disabledActiveTickColor,
            disabledInactiveTickColor = disabledInactiveTickColor
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
        inactiveTrackColor: ColorBrush,
        disabledInactiveTrackColor: ColorBrush,
        activeTickColor: ColorBrush,
        inactiveTickColor: ColorBrush,
        disabledActiveTickColor: ColorBrush,
        disabledInactiveTickColor: ColorBrush

    ): MaterialSliderColors {

        return DefaultMaterialSliderColors(
            thumbColor = thumbColor,
            disabledThumbColor = disabledThumbColor,
            activeTrackColor = activeTrackColor,
            inactiveTrackColor = inactiveTrackColor,
            disabledActiveTrackColor = disabledActiveTrackColor,
            disabledInactiveTrackColor = disabledInactiveTrackColor,
            activeTickColor = activeTickColor,
            inactiveTickColor = inactiveTickColor,
            disabledActiveTickColor = disabledActiveTickColor,
            disabledInactiveTickColor = disabledInactiveTickColor
        )
    }

    /*
        Brushes
     */
    @Composable
    fun greenBrush(
        thumbColor: ColorBrush,
        disabledThumbColor: ColorBrush,
        activeTrackColor: ColorBrush,
        disabledActiveTrackColor: ColorBrush,
        inactiveTrackColor: ColorBrush,
        disabledInactiveTrackColor: ColorBrush,
        activeTickColor: ColorBrush,
        inactiveTickColor: ColorBrush,
        disabledActiveTickColor: ColorBrush,
        disabledInactiveTickColor: ColorBrush
    ) {

    }

    @Composable
    fun redBrush(
        thumbColor: ColorBrush,
        disabledThumbColor: ColorBrush,
        activeTrackColor: ColorBrush,
        disabledActiveTrackColor: ColorBrush,
        inactiveTrackColor: ColorBrush,
        disabledInactiveTrackColor: ColorBrush,
        activeTickColor: ColorBrush,
        inactiveTickColor: ColorBrush,
        disabledActiveTickColor: ColorBrush,
        disabledInactiveTickColor: ColorBrush
    ) {

    }

    @Composable
    fun blueBrush(
        thumbColor: ColorBrush,
        disabledThumbColor: ColorBrush,
        activeTrackColor: ColorBrush,
        disabledActiveTrackColor: ColorBrush,
        inactiveTrackColor: ColorBrush,
        disabledInactiveTrackColor: ColorBrush,
        activeTickColor: ColorBrush,
        inactiveTickColor: ColorBrush,
        disabledActiveTickColor: ColorBrush,
        disabledInactiveTickColor: ColorBrush
    ) {

    }

    @Composable
    fun rainbowBrush(
        thumbColor: ColorBrush,
        disabledThumbColor: ColorBrush,
        activeTrackColor: ColorBrush,
        disabledActiveTrackColor: ColorBrush,
        inactiveTrackColor: ColorBrush,
        disabledInactiveTrackColor: ColorBrush,
        activeTickColor: ColorBrush,
        inactiveTickColor: ColorBrush,
        disabledActiveTickColor: ColorBrush,
        disabledInactiveTickColor: ColorBrush
    ) {

    }

    class DefaultMaterialSliderColors(
        private val thumbColor: ColorBrush,
        private val disabledThumbColor: ColorBrush,
        private val activeTrackColor: ColorBrush,
        private val disabledActiveTrackColor: ColorBrush,
        private val inactiveTrackColor: ColorBrush,
        private val disabledInactiveTrackColor: ColorBrush,
        private val activeTickColor: ColorBrush,
        private val inactiveTickColor: ColorBrush,
        private val disabledActiveTickColor: ColorBrush,
        private val disabledInactiveTickColor: ColorBrush
    ) : MaterialSliderColors {

        @Composable
        override fun thumbColor(enabled: Boolean): State<ColorBrush> {
            return rememberUpdatedState(if (enabled) thumbColor else disabledThumbColor)
        }

        @Composable
        override fun trackColor(enabled: Boolean, active: Boolean): State<ColorBrush> {
            return rememberUpdatedState(
                if (enabled) {
                    if (active) activeTrackColor else inactiveTrackColor
                } else {
                    if (active) disabledActiveTrackColor else disabledInactiveTrackColor
                }
            )
        }

        @Composable
        override fun tickColor(enabled: Boolean, active: Boolean): State<ColorBrush> {
            return rememberUpdatedState(
                if (enabled) {
                    if (active) activeTickColor else inactiveTickColor
                } else {
                    if (active) disabledActiveTickColor else disabledInactiveTickColor
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

/**
 * Interface for providing thumb, track, and tick colors using [ColorBrush]. When providing
 * a [ColorBrush] initially a null check is done whether [ColorBrush.brush] is not null,
 * if it's not null gradient is drawn but when tis non-nullable color is used.
 */
@Stable
interface MaterialSliderColors {

    /**
     * Represents the [ColorBrush] used for the sliders's thumb, depending on [enabled].
     *
     * @param enabled whether the [Slider] is enabled or not
     */
    @Composable
    fun thumbColor(enabled: Boolean): State<ColorBrush>

    /**
     * Represents the [ColorBrush] used for the sliders's track, depending on [enabled] and [active].
     *
     * Active part is filled with progress, so if sliders progress is 30% out of 100%, left (or
     * right in RTL) 30% of the track will be active, the rest is not active.
     *
     * @param enabled whether the [Slider] is enabled or not
     * @param active whether the part of the track is active of not
     */
    @Composable
    fun trackColor(enabled: Boolean, active: Boolean): State<ColorBrush>

    /**
     * Represents the [ColorBrush] used for the sliders's tick which is the dot separating steps,
     * if they are set on the slider, depending on [enabled] and [active].
     *
     * Active tick is the tick that is in the part of the track filled with progress, so if
     * sliders progress is 30% out of 100%, left (or right in RTL) 30% of the track and the ticks
     * in this 30% will be active, the rest is not active.
     *
     * @param enabled whether the [Slider] is enabled or not
     * @param active whether the part of the track this tick is in is active of not
     */
    @Composable
    fun tickColor(enabled: Boolean, active: Boolean): State<ColorBrush>

}