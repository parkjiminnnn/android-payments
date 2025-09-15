package woowacourse.payments.ui.cardregister.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import woowacourse.payments.R

@Composable
fun CardOwnerInputField(
    text: String,
    onValueChange: (String) -> Unit,
) {
    val maxLength = 30

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = { filteredText ->
            if (filteredText.length <= maxLength) onValueChange(filteredText)
        },
        label = { Text(stringResource(R.string.card_owner_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.card_owner_placeholder),
                color = Color.Gray,
            )
        },
        singleLine = true,
        supportingText = {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(Modifier.weight(1f))
                Text(
                    stringResource(R.string.card_owner_length, text.length),
                )
            }
        },
    )
}
