package com.smarttoolfactory.composecolorpicker.demo

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.isUnspecified
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.colorpicker.ui.Blue400
import com.smarttoolfactory.composecolorpicker.R

@Composable
fun ColorDetectionFromScreenDemo() {
    // These are for debugging
    var text by remember { mutableStateOf("") }
    var colorAtTouchPosition by remember { mutableStateOf(Color.Unspecified) }


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

        ImageColorDetection(
            modifier=Modifier.padding(10.dp),
            content = {

                Column(
                    modifier = Modifier
                        .border(2.dp, Color.Green)
                        .padding(5.dp)
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

                    Button(onClick = { /*TODO*/ }) {
                        Text("Sample Button")
                    }
                }
            },
            { colorChange: Color, textChange: String ->
                colorAtTouchPosition = colorChange
                text = textChange
            },

            )

        Text(text = text)
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

@Composable
private fun ImageColorDetection(
    modifier: Modifier=Modifier,
    content: @Composable () -> Unit,
    onColorChange: (Color, String) -> Unit,
) {

    val view = LocalView.current

    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    var color by remember { mutableStateOf(Color.Unspecified) }

    var composableBounds by remember {
        mutableStateOf<Rect?>(null)
    }
    var bitmap by remember {
        mutableStateOf<Bitmap?>(
            null
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            bitmap?.apply {
                if (!isRecycled) recycle()
                bitmap = null
            }
        }
    }

    val colorDetectionModifier = modifier
        .pointerInput(Unit) {
            detectDragGestures(onDrag = { change: PointerInputChange, _: Offset ->

                if (composableBounds == null) return@detectDragGestures

                if (bitmap == null) {
                    val boundsRect = composableBounds!!

                    bitmap = Bitmap.createBitmap(
                        boundsRect.width.toInt(),
                        boundsRect.height.toInt(),
                        Bitmap.Config.ARGB_8888
                    )

                    bitmap?.let { bmp ->
                        val canvas = Canvas(bmp)
                            .apply {
                                translate(-boundsRect.left, -boundsRect.top)
                            }
                        view.draw(canvas)
                    }
                }

                val bmp: Bitmap = bitmap!!
                val bitmapWidth = bmp.width
                val bitmapHeight = bmp.height

                // Touch coordinates on image
                offsetX = change.position.x.coerceIn(0f, bitmapWidth.toFloat())
                offsetY = change.position.y.coerceIn(0f, bitmapHeight.toFloat())

                // Scale from Image touch coordinates to range in Bitmap
                val scaledX = offsetX
                val scaledY = offsetY

                try {
                    val pixel: Int = bmp.getPixel(scaledX.toInt(), scaledY.toInt())

                    val red = android.graphics.Color.red(pixel)
                    val green = android.graphics.Color.green(pixel)
                    val blue = android.graphics.Color.blue(pixel)

                    val text = "Touch offsetX:$offsetX, offsetY: $offsetY\n" +
                            "Bitmap width: ${bitmapWidth}, height: $bitmapHeight\n" +
                            "scaledX: $scaledX, scaledY: $scaledY\n" +
                            "red: $red, green: $green, blue: $blue\n"

                    color = Color(red, green, blue)

                    onColorChange(color, text)
                } catch (e: Exception) {
                    println("Exception e: ${e.message}")
                }
            }
            )
        }
        .drawWithContent {
            drawContent()
            val center = Offset(offsetX, offsetY)
            drawCircle(Color.Black, radius = 20f, style = Stroke(16f), center = center)
            drawCircle(Color.White, radius = 20f, style = Stroke(14f), center = center)
        }
        .onGloballyPositioned {
            composableBounds = it.boundsInRoot()
        }


    Column(modifier = colorDetectionModifier) {
        content()
    }
}
