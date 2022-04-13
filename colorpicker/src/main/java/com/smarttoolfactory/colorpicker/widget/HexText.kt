package com.smarttoolfactory.colorpicker.widget

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.smarttoolfactory.colorpicker.util.hexRegexSingleChar
import com.smarttoolfactory.colorpicker.util.hexToColor
import com.smarttoolfactory.colorpicker.util.hexWithAlphaRegex

@Composable
fun HexAlphaTextField(
    modifier: Modifier = Modifier,
    hexString: String,
    textStyle: TextStyle = LocalTextStyle.current,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    ),
    shape: Shape = RoundedCornerShape(25),
    onTextChange: (String) -> Unit,
    onColorChange: (Color) -> Unit
) {

    TextField(
        modifier = modifier,
        visualTransformation = HexVisualTransformation(),
        singleLine = true,
        textStyle = textStyle,
        colors = colors,
        shape = shape,
        value = hexString.removePrefix("#"),
        onValueChange = {

            if (it.length <= 8) {

                if (it.isNotEmpty()) {
                    val lastChar = it.last()
                    val isHexChar = hexRegexSingleChar.matches(lastChar.toString())
                    if (isHexChar) {
                        onTextChange(it)
                    }
                } else {
                    onTextChange(it)
                }

                // Hex String with 6 or 8 chars matches a Color
                if (hexWithAlphaRegex.matches(it)) {
                    onColorChange(hexToColor(it))
                }
            }
        }
    )
}

@Composable
internal fun BasicHexTextField(
    modifier: Modifier = Modifier,
    value: String,
    textStyle: TextStyle = LocalTextStyle.current,
    textColor: Color,
    backgroundColor: Color,
    cursorColor: Color = Color.Black,
    hint: String,
    shape: Shape = RoundedCornerShape(25),
    onValueChange: (String) -> Unit,
) {

    Surface(
        shape = shape,
        color = backgroundColor,
        contentColor = textColor
    ) {
        BasicTextField(
            modifier = modifier,
            textStyle = textStyle,
            value = value,
            cursorBrush = SolidColor(cursorColor),
            onValueChange = onValueChange
        ) {
            if (value.isEmpty()) {
                Text(hint)
            }
        }
    }
}

class HexVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {

        val trimmed = if (text.text.length >= 8) text.text.substring(0..7) else text.text

        val output = if (trimmed.isEmpty()) {
            trimmed
        } else {
            "#$trimmed"
        }

        return TransformedText(AnnotatedString(output), hexOffsetMapping)
    }

    private val hexOffsetMapping = object : OffsetMapping {

        override fun originalToTransformed(offset: Int): Int {

            // when empty return only 1 char for #
            if (offset == 0) return offset
            if (offset <= 7) return offset + 1
            return 9
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset == 0) return offset
            // #ffABCABC
            if (offset <= 8) return offset - 1
            return 8
        }
    }
}
