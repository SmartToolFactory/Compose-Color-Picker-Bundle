package com.smarttoolfactory.composecolorpicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.smarttoolfactory.composecolorpicker.demo.ColorPickerDemo
import com.smarttoolfactory.composecolorpicker.demo.HSVHSLGradientsDemo
import com.smarttoolfactory.composecolorpicker.ui.theme.ComposeColorPickerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeColorPickerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    ColorfulSliderDemo()
//                    ColorfulSliderDimensionDemo()
                    HSVHSLGradientsDemo()
//                    ColorPickerDemo()

                }
            }
        }
    }
}

