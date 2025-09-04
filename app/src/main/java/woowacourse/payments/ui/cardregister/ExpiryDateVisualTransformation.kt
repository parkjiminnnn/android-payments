package woowacourse.payments.ui.cardregister

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class ExpiryDateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformedText =
            buildString {
                text.text.forEachIndexed { index, char ->
                    append(char)
                    if (index == 1 && text.text.length > 1) append("/")
                }
            }

        val offsetMapping =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int) = if (offset <= 1) offset else offset + 1

                override fun transformedToOriginal(offset: Int) = if (offset <= 2) offset else offset - 1
            }

        return TransformedText(AnnotatedString(transformedText), offsetMapping)
    }
}
