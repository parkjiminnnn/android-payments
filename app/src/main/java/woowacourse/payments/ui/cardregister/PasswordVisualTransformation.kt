package woowacourse.payments.ui.cardregister

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PasswordVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformedText = "*".repeat(text.text.length)
        return TransformedText(AnnotatedString(transformedText), OffsetMapping.Identity)
    }
}
