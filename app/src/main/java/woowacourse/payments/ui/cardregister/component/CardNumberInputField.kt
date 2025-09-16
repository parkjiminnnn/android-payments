package woowacourse.payments.ui.cardregister.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.cardregister.CardNumberVisualTransformation

@Composable
fun CardNumberInputField(
    text: String,
    onValueChange: (String) -> Unit,
) {
    val maxLength = 16

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = { newText ->
            val filteredText = newText.filter { it.isDigit() }
            if (filteredText.length <= maxLength) onValueChange(filteredText)
        },
        label = { Text(stringResource(R.string.card_number_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.card_number_place_holder),
                color = Color.Gray,
            )
        },
        visualTransformation = CardNumberVisualTransformation(),
        singleLine = true,
    )
}

@Preview(showBackground = true)
@Composable
private fun ShowCardNumberInputFieldPreview() {
    CardNumberInputField(text = "1234567812345678") {}
}
