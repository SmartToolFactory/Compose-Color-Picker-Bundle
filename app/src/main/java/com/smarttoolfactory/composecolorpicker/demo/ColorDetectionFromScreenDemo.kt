package com.smarttoolfactory.composecolorpicker.demo

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.isUnspecified
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.ui.Blue400
import com.smarttoolfactory.colorpicker.widget.ScreenColorDetector
import com.smarttoolfactory.composecolorpicker.R

@Composable
fun ColorDetectionFromScreenDemo() {

    var colorAtTouchPosition by remember { mutableStateOf(Color.Unspecified) }

    var enableColorDetection by remember {mutableStateOf(true)}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
        Text(
            text = "Simple demonstration to detect colors in ImageColorDetection composable " +
                    "covered with green border. Bitmap of the composable is created when after " +
                    "globally positioned",
            fontSize = 14.sp,
            color = Blue400,
            modifier = Modifier.padding(2.dp)
        )

        ScreenColorDetector(
            enabled = enableColorDetection,
            modifier = Modifier.border(2.dp, Color.Cyan).padding(25.dp),
            content = {
                Column(
                    modifier = Modifier.border(1.dp, Color.Magenta)
                ) {

                    Image(
                        bitmap = ImageBitmap.imageResource(
                            LocalContext.current.resources,
                            R.drawable.landscape
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .background(Color.LightGray)
                            .fillMaxWidth()
                            // This is for displaying different ratio, optional
                            .aspectRatio(4f / 3),
                        contentScale = ContentScale.Crop
                    )

                    Button(onClick = { enableColorDetection=!enableColorDetection }) {
                        Text(text =if (enableColorDetection) "Disable" else "Enable")
                    }
                }
            }
        ) { color: Color ->
            colorAtTouchPosition = color
        }

        Box(
            modifier = Modifier
                .then(
                    if (colorAtTouchPosition.isUnspecified) {
                        Modifier
                    } else {
                        Modifier
                            .clip(RoundedCornerShape(20))
                            .border(2.dp, Color.Black, shape = RoundedCornerShape(20))
                            .background(colorAtTouchPosition)
                    }
                )
                .size(100.dp)

        )
    }
}
