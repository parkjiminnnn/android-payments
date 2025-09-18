package woowacourse.payments.ui.cardregister.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.cardregister.CardNumberVisualTransformation
import woowacourse.payments.ui.cardregister.CardRegisterStateHolder

@Composable
fun CardNumberInputField(stateHolder: CardRegisterStateHolder) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = stateHolder.cardNumber,
        onValueChange = { newText ->
            stateHolder.onCardNumberChange(newText)
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        isError = stateHolder.isCardNumberError,
    )
}

@Preview(showBackground = true)
@Composable
private fun ShowCardNumberInputFieldPreview() {
    CardNumberInputField(stateHolder = CardRegisterStateHolder())
}
