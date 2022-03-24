package com.smarttoolfactory.colorpicker.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun SliderCircleColorDisplaySaturationHSV(
    modifier: Modifier = Modifier,
    hue: Float,
    saturation: Float,
    value: Float,
    alpha: Float,
    onSaturationChange: (Float) -> Unit,
    onAlphaChange: (Float) -> Unit,
) {

    Row(
        modifier = modifier
            .requiredHeightIn(min = 100.dp, max = 120.dp)
            .padding(horizontal = 8.dp, vertical = 0.dp)
            .padding(horizontal = 8.dp, vertical = 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .padding(10.dp)
                .clip(CircleShape)
                .fillMaxHeight()
                .aspectRatio(1f)
                .drawChecker(CircleShape)
                .background(Color.hsv(hue, saturation, value, alpha))
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround
        ) {
            SliderSaturationHSV(
                hue = hue,
                saturation = saturation,
                value = 1f,
                onValueChange = onSaturationChange
            )
            SliderAlphaHSV(
                hue = hue,
                alpha = alpha,
                onValueChange = onAlphaChange
            )
        }
    }
}

@Composable
fun SliderCircleColorDisplayValueHSV(
    modifier: Modifier = Modifier,
    hue: Float,
    saturation: Float,
    value: Float,
    alpha: Float,
    onValueChange: (Float) -> Unit,
    onAlphaChange: (Float) -> Unit,
) {
    Row(
        modifier = modifier
            .requiredHeightIn(min = 100.dp, max = 120.dp)
            .padding(horizontal = 8.dp, vertical = 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .padding(10.dp)
                .clip(CircleShape)
                .fillMaxHeight()
                .aspectRatio(1f)
                .drawChecker(CircleShape)
                .background(Color.hsv(hue, saturation, value, alpha))
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround
        ) {
            SliderValueHSV(
                hue = hue,
                value = value,
                onValueChange = onValueChange
            )
            SliderAlphaHSV(
                hue = hue,
                alpha = alpha,
                onValueChange = onAlphaChange
            )
        }
    }
}

@Composable
fun SliderCircleColorDisplaySaturationHSL(
    modifier: Modifier = Modifier,
    hue: Float,
    saturation: Float,
    lightness: Float,
    alpha: Float,
    onSaturationChange: (Float) -> Unit,
    onAlphaChange: (Float) -> Unit,
) {
    Row(modifier = modifier) {

        Row(
            modifier = modifier
                .requiredHeightIn(min = 100.dp, max = 120.dp)
                .padding(horizontal = 8.dp, vertical = 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(CircleShape)
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .drawChecker(CircleShape)
                    .background(Color.hsl(hue, saturation, lightness, alpha))
            )

            Column(
                verticalArrangement = Arrangement.SpaceAround
            ) {
                SliderSaturationHSL(
                    hue = hue,
                    saturation = saturation,
                    lightness = lightness,
                    onValueChange = onSaturationChange
                )
                SliderAlphaHSL(hue = hue, alpha = alpha, onValueChange = onAlphaChange)
            }
        }
    }
}

@Composable
fun SliderCircleColorDisplayLightnessHSL(
    modifier: Modifier = Modifier,
    hue: Float,
    saturation: Float,
    lightness: Float,
    alpha: Float,
    onLightnessChange: (Float) -> Unit,
    onAlphaChange: (Float) -> Unit,
) {
    Row(
        modifier = modifier
            .requiredHeightIn(min = 100.dp, max = 120.dp)
            .padding(horizontal = 8.dp, vertical = 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .padding(10.dp)
                .clip(CircleShape)
                .fillMaxHeight()
                .aspectRatio(1f)
                .drawChecker(CircleShape)
                .background(Color.hsl(hue, saturation, lightness, alpha))
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround
        ) {
            SliderLightnessHSL(
                hue = hue,
                saturation = saturation,
                lightness = lightness,
                onValueChange = onLightnessChange
            )
            SliderAlphaHSL(hue = hue, alpha = alpha, onValueChange = onAlphaChange)
        }
    }

}
