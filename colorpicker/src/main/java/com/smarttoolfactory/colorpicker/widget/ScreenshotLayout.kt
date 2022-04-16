package com.smarttoolfactory.colorpicker.widget

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalView
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

@Composable
fun rememberScreenshotState(timeInMillis: Long = 20) = remember {
    ScreenshotState(timeInMillis)
}

class ScreenshotState(
    private val timeInMillis: Long = 20
) {
    internal var callback: (() -> Bitmap?)? = null

    private val bitmapState = mutableStateOf(callback?.invoke())

    fun refresh() {
        bitmapState.value = callback?.invoke()
    }

    val liveScreenshotState = flow {
        while (true) {
            delay(timeInMillis)
            val bmp = callback?.invoke()
            bmp?.let {
                emit(it)
            }
        }
    }

    val bitmap: Bitmap?
        get() = bitmapState.value

    val imageBitmap: ImageBitmap?
        get() = bitmap?.asImageBitmap()
}


@Composable
fun ScreenshotBox(
    modifier: Modifier = Modifier,
    screenshotState: ScreenshotState,
    content: @Composable () -> Unit,
) {
    val view = LocalView.current

    var composableBounds by remember {
        mutableStateOf<Rect?>(null)
    }

    DisposableEffect(Unit) {

        var bitmap: Bitmap? = null

        screenshotState.callback = {

            composableBounds?.let { bounds ->

                bitmap = Bitmap.createBitmap(
                    bounds.width.toInt(),
                    bounds.height.toInt(),
                    Bitmap.Config.ARGB_8888
                )

                bitmap?.let { bmp ->
                    val canvas = Canvas(bmp)
                        .apply {
                            translate(-bounds.left, -bounds.top)
                        }
                    view.draw(canvas)
                }
            }
            bitmap
        }

        onDispose {
            bitmap?.apply {
                if (!isRecycled) {
                    recycle()
                    bitmap = null
                }
            }
            screenshotState.callback = null
        }
    }

    Box(modifier = modifier
        .onGloballyPositioned {
            composableBounds = it.boundsInRoot()
        }
    ) {
        content()
    }
}


@Composable
fun ScreenshotBox(
    modifier: Modifier = Modifier,
    content: @Composable ScreenshotScope.() -> Unit,
) {

    val view = LocalView.current

    var composableBounds by remember {
        mutableStateOf<Rect?>(null)
    }
    var bitmap by remember {
        mutableStateOf<Bitmap?>(
            null
        )
    }

    var callback: (() -> Bitmap?)? = remember {
        {
            composableBounds?.let { bounds ->
                bitmap = Bitmap.createBitmap(
                    bounds.width.toInt(),
                    bounds.height.toInt(),
                    Bitmap.Config.ARGB_8888
                )

                bitmap?.let { bmp ->
                    val canvas = Canvas(bmp)
                        .apply {
                            translate(-bounds.left, -bounds.top)
                        }
                    view.draw(canvas)
                }
            }
            bitmap
        }
    }

    val screenShotScopeImpl = remember {
        ScreenShotScopeImpl(callback)
    }

    DisposableEffect(Unit) {
        onDispose {
            bitmap?.apply {
                if (!isRecycled) recycle()
                bitmap = null
            }

            callback = null
        }
    }


    Box(modifier = modifier
        .onGloballyPositioned {
            composableBounds = it.boundsInRoot()
        }
    ) {
        screenShotScopeImpl.content()
    }
}


internal class ScreenShotScopeImpl(private val callback: (() -> Bitmap?)?) : ScreenshotScope {
    override fun getBitmap(): Bitmap? {
        return callback?.invoke()
    }
}

interface ScreenshotScope {
    fun getBitmap(): Bitmap?
}