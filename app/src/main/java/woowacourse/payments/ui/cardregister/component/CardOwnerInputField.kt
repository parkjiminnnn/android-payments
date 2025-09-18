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
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.cardregister.CardRegisterStateHolder

@Composable
fun CardOwnerInputField(stateHolder: CardRegisterStateHolder) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = stateHolder.cardOwner,
        onValueChange = { newText ->
            stateHolder.onCardOwnerChange(newText)
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
                    stringResource(R.string.card_owner_length, stateHolder.cardOwner.length),
                )
            }
        },
        isError = stateHolder.isCardOwnerError,
    )
}

@Preview(showBackground = true)
@Composable
private fun ShowCardOwnerInputFieldPreview() {
    CardOwnerInputField(stateHolder = CardRegisterStateHolder())
}
