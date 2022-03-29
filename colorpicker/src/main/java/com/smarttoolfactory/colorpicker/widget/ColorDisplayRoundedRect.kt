package com.smarttoolfactory.colorpicker.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Color display that displays both colors horizontally aligned with half [RoundedCornerShape]
 * with [Modifier.drawChecker] to display checker pattern when any color's alpha is less than 1.0f
 * @param initialColor color that should be static
 * @param currentColor color that is changed based on user actions
 */
@Composable
fun ColorDisplayRoundedRect(
    modifier: Modifier = Modifier,
    initialColor: Color,
    currentColor: Color
) {
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .background(
                    initialColor,
                    shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
                )
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .drawChecker(RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp))
                .background(
                    currentColor,
                    shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
                )
        )
    }
}