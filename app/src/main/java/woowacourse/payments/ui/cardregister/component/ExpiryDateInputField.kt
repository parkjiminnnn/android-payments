package woowacourse.payments.ui.cardregister.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import woowacourse.payments.R
import woowacourse.payments.ui.cardregister.ExpiryDateVisualTransformation

@Composable
fun ExpiryDateInputField(
    text: String,
    onValueChange: (String) -> Unit,
) {
    val maxLength = 4

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(0.5f),
        value = text,
        onValueChange = { newText ->
            val filteredText = newText.filter { it.isDigit() }
            if (filteredText.length <= maxLength) onValueChange(filteredText)
        },
        label = { Text(stringResource(R.string.expiry_date_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.expiry_date_place_holder),
                color = Color.Gray,
            )
        },
        visualTransformation = ExpiryDateVisualTransformation(),
        singleLine = true,
    )
}
