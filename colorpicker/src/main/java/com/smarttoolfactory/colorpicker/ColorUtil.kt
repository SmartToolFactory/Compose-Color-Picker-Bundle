package com.smarttoolfactory.colorpicker



/*
function hsv_to_hsl(h, s, v) {
    // both hsv and hsl values are in [0, 1]
    var l = (2 - s) * v / 2;

    if (l != 0) {
        if (l == 1) {
            s = 0;
        } else if (l < 0.5) {
            s = s * v / (l * 2);
        } else {
            s = s * v / (2 - l * 2);
        }
    }

    return [h, s, l];
}
 */


/**
 * Convert HSV (hue-saturation-value) components to HSL (hue-saturation-lightness)
 */
fun HSVtoHSL(hue: Float, saturation: Float, value: Float): FloatArray {
    val l = (2 - saturation) * value / 2
    var s = saturation

    if (l != 0f) {
        s = when {
            l == 1f -> {
                0f
            }
            l < 0.5f -> {
                s * value / (l * 2)
            }
            else -> {
                s * value / (2 - l * 2)
            }
        }
    }

    return floatArrayOf(hue, s, l)
}

/**
 * Convert HSV (hue-saturation-value) components to HSL (hue-saturation-lightness)
 */
fun HSVtoHSL(floatArray: FloatArray): FloatArray {
    require(floatArray.size == 3)
    return HSVtoHSL(floatArray[0], floatArray[1], floatArray[2])
}

