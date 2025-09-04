package woowacourse.payments.ui.cardregister

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformedText = text.text.chunked(4).joinToString("-")

        val offsetMapping =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int = (offset + offset / 4).coerceAtMost(transformedText.length)

                override fun transformedToOriginal(offset: Int): Int = (offset - offset / 5).coerceAtMost(text.text.length)
            }
        return TransformedText(AnnotatedString(transformedText), offsetMapping)
    }
}
