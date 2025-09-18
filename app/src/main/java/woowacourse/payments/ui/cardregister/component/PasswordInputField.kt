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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.cardregister.CardRegisterStateHolder

@Composable
fun PasswordInputField(stateHolder: CardRegisterStateHolder) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(0.5f),
        value = stateHolder.password,
        onValueChange = { newText ->
            stateHolder.onPasswordChange(newText)
        },
        label = { Text(stringResource(R.string.password_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.password_place_holder),
                color = Color.Gray,
            )
        },
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        isError = stateHolder.isPasswordError,
    )
}

@Preview(showBackground = true)
@Composable
private fun ShowPasswordInputFieldPreview() {
    PasswordInputField(stateHolder = CardRegisterStateHolder())
}
