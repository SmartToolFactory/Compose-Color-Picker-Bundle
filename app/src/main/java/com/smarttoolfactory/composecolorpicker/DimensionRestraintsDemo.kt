package com.smarttoolfactory.composecolorpicker

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.colorpicker.slider.minimumTouchTargetSize

@Composable
fun DimensionRestraintsDemo() {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(15.dp)
            .background(Color(0xff424242))
    ) {


        Sample1(
            Modifier
                .background(Color.Yellow)
                .size(20.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Sample2(
            Modifier
                .padding(10.dp)
                .background(Color.Yellow)
                .size(20.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Sample3(
            Modifier
                .background(Color.Yellow)
                .size(20.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Sample4(Modifier.size(20.dp))

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = Color.Red
        )
        Sample1(Modifier)
        Spacer(modifier = Modifier.height(50.dp))
        Sample2(Modifier)
        Spacer(modifier = Modifier.height(50.dp))
        Sample3(Modifier)
        Spacer(modifier = Modifier.height(50.dp))
        Sample4(Modifier)


//        Column(
////            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//
//            var colorValue1 by remember { mutableStateOf(50f) }
//            var colorValue2 by remember { mutableStateOf(50f) }
//            var colorValue3 by remember { mutableStateOf(50f) }
//
//            Spacer(modifier = Modifier.height(50.dp))
//
//            ColorfulSlider(
//                value = colorValue2,
//                modifier = Modifier
////                    .padding(horizontal = 20.dp)
//                    .fillMaxWidth()
////                    .height(40.dp)
//                    .border(2.dp, Color.Green),
////                thumbRadius = 12.dp,
////                trackHeight = 24.dp,
//                onValueChange = {
//                    colorValue2 = it
//                    println("🤡 ColorSliderNew() PROGRESS $it")
//                },
//                valueRange = 0f..100f
//            )
//
//            Spacer(modifier = Modifier.height(20.dp))
//            var progress by remember {
//                mutableStateOf(0f)
//            }
//
//            Row() {
//                Slider(modifier = Modifier
//                    .size(20.dp)
//                    .border(2.dp, Color.Red), value = progress,
//                    onValueChange = {
//                        println("🎃 Slider1 progress $it")
//                        progress = it
//                    }
//                )
//
//                ColorfulSlider(
//                    value = colorValue3,
//                    modifier = Modifier
//                        .size(20.dp)
//                        .border(2.dp, Color.Green),
//                    onValueChange = {
//                        colorValue3 = it
//                        println("🤡🍏 ColorSliderNew() PROGRESS $it")
//                    },
//                    valueRange = 0f..100f,
//                )
//
//
//            }
//
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            Slider(modifier = Modifier
//                .background(Color.Green)
//                .border(2.dp, Color.Red), value = progress,
//                onValueChange = {
//                    println("🎃 Slider2 progress $it")
//                    progress = it
//                }
//            )
//            Spacer(modifier = Modifier.height(20.dp))
//
//            Column(modifier = Modifier.size(90.dp, 50.dp)) {
//                Slider(modifier = Modifier
//                    .border(2.dp, Color.Red), value = progress,
//                    onValueChange = {
//                        println("🎃 Slider3 progress $it")
//                        progress = it
//                    }
//                )
//
//            }
//        }
    }
}

@Composable
private fun Sample1(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier.border(2.dp, Color.Red)
    ) {
        println("🔥 Sample1() $minWidth, maxWidth: $maxWidth, minHeight: $minHeight, maxHeight: $maxHeight, constraints: $constraints")
        Column(Modifier.background(Color.Red)) {
//            Text(text = "Text1")
//            Text(text = "Text2")
//            Text(text = "Text3")
        }
    }
}

@Composable
private fun Sample2(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        Modifier
            .border(2.dp, Color.Blue)
            .widthIn(min = 200.dp)
            .heightIn(min = 200.dp)
            .then(modifier)

    ) {
        println("🍏 Sample2() $minWidth, maxWidth: $maxWidth, minHeight: $minHeight, maxHeight: $maxHeight, constraints: $constraints")

        Column(Modifier.background(Color.Blue)) {
//            Text(text = "Text1")
//            Text(text = "Text2")
//            Text(text = "Text3")
        }
    }
}

@Composable
private fun Sample3(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier
            .border(2.dp, Color.Green)
            .requiredSizeIn(200.dp, 100.dp)

    ) {

        println("🚀 Sample3() $minWidth, maxWidth: $maxWidth, minHeight: $minHeight, maxHeight: $maxHeight, constraints: $constraints")

        Column(Modifier.background(Color.Green)) {
//            Text(text = "Text1")
//            Text(text = "Text2")
//            Text(text = "Text3")
        }
    }
}


@Composable
private fun Sample4(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier
            .border(2.dp, Color.Cyan)
            .minimumTouchTargetSize()
            .requiredSizeIn(minWidth = 40.dp, minHeight = 40.dp)

    ) {
        println("😆 Sample4() $minWidth, maxWidth: $maxWidth, minHeight: $minHeight, maxHeight: $maxHeight, constraints: $constraints")

        Box(
            Modifier
                .background(Color.Red)
                .then(
                    Modifier
                        .widthIn(min = 144.dp)
                        .heightIn(max = 48.dp)
                )
                .background(Color.Green)

        ){
            Canvas(modifier = Modifier.fillMaxWidth(), onDraw = {
                drawRect(Color.Cyan)
            })
        }

    }
}


