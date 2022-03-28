package com.smarttoolfactory.colorpicker.selector

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.consumeDownChange
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.smarttoolfactory.colorpicker.drawBlendingRectGradient
import com.smarttoolfactory.colorpicker.ui.brush.lightnessGradient
import com.smarttoolfactory.colorpicker.ui.brush.saturationHSLGradient
import com.smarttoolfactory.colorpicker.ui.brush.saturationHSVGradient
import com.smarttoolfactory.colorpicker.ui.brush.valueGradient
import com.smarttoolfactory.gesture.pointerMotionEvents

/**
 *
 */
@Composable
fun SLSelectorRectHSL(
    modifier: Modifier = Modifier,
    hue: Float,
    saturation: Float = 0.5f,
    lightness: Float = 0.5f,
    selectionRadius: Dp = Dp.Unspecified,
    onChange: (Float, Float) -> Unit
) {
    val lightnessGradient = lightnessGradient(hue)
    val hueSaturation = saturationHSLGradient(hue)

    SelectorRect(
        modifier = modifier,
        saturation = saturation,
        property = lightness,
        brushSrc = hueSaturation,
        brushDst = lightnessGradient,
        selectionRadius = selectionRadius,
        onChange = onChange,
        isHSV = false
    )
}

/**
 * @param hue
 * @param saturation
 * @param value
 * @param selectionRadius
 * @param onChange
 */
@Composable
fun SVSelectorRectHSV(
    modifier: Modifier = Modifier,
    hue: Float,
    saturation: Float = 0.5f,
    value: Float = 0.5f,
    selectionRadius: Dp = Dp.Unspecified,
    onChange: (Float, Float) -> Unit
) {

    val valueGradient = valueGradient(hue)
    val hueSaturation = saturationHSVGradient(hue)

    SelectorRect(
        modifier = modifier,
        saturation = saturation,
        property = value,
        brushSrc = hueSaturation,
        brushDst = valueGradient,
        selectionRadius = selectionRadius,
        onChange = onChange,
        isHSV = true
    )
}


@Composable
private fun SelectorRect(
    modifier: Modifier = Modifier,
    saturation: Float = 0.5f,
    property: Float = 0.5f,
    brushSrc: Brush,
    brushDst: Brush,
    selectionRadius: Dp = Dp.Unspecified,
    onChange: (Float, Float) -> Unit,
    isHSV: Boolean
) {

    BoxWithConstraints(modifier) {

        val density = LocalDensity.current.density
        val width = constraints.maxWidth.toFloat()
        val height = constraints.maxHeight.toFloat()

        // Center position of color picker
        val center = Offset(width / 2, height / 2)

        /**
         * Circle selector radius for setting [saturation] and [property] by gesture
         */
        val selectorRadius =
            if (selectionRadius != Dp.Unspecified) selectionRadius.value * density
            else width.coerceAtMost(height) * .04f

        /**
         *  Current position is initially set by [saturation] and [property] that is bound
         *  in diamond since (1,1) points to bottom left corner of a rectangle but it's bounded
         *  in diamond by [setSelectorPositionFromColorParams].
         *  When user touches anywhere in diamond current position is updated and
         *  this composable is recomposed
         */
        var currentPosition by remember {
            mutableStateOf(center)
        }

        val posX = saturation * width
        val posY = (1 - property) * height
        currentPosition = Offset(posX, posY)


        val canvasModifier = Modifier
            .fillMaxSize()
            .pointerMotionEvents(
                onDown = {
                    val position = it.position
                    val saturationChange = (position.x / width).coerceIn(0f, 1f)
                    val valueChange = (1 - (position.y / height)).coerceIn(0f, 1f)
                    onChange(saturationChange, valueChange)
                    it.consumeDownChange()

                },
                onMove = {
                    val position = it.position
                    val saturationChange = (position.x / width).coerceIn(0f, 1f)
                    val valueChange = (1 - (position.y / height)).coerceIn(0f, 1f)
                    onChange(saturationChange, valueChange)
                    it.consumePositionChange()
                },
                delayAfterDownInMillis = 20
            )

        SelectorRectImpl(
            modifier = canvasModifier,
            brushSrc = brushSrc,
            brushDst = brushDst,
            selectorPosition = currentPosition,
            selectorRadius = selectorRadius,
            isHSV = isHSV
        )
    }
}

@Composable
private fun SelectorRectImpl(
    modifier: Modifier,
    brushSrc: Brush,
    brushDst: Brush,
    selectorPosition: Offset,
    selectorRadius: Float,
    isHSV: Boolean,
) {

    Canvas(modifier = modifier) {
        drawBlendingRectGradient(
            dst = brushDst,
            src = brushSrc,
            blendMode = if (isHSV) BlendMode.Multiply else BlendMode.Overlay
        )
        // Saturation and Value/Lightness or value selector
        drawHueSelectionCircle(
            center = selectorPosition,
            radius = selectorRadius
        )
    }
}
