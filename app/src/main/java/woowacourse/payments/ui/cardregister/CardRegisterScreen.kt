package woowacourse.payments.ui.cardregister

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.component.PaymentCard

@Composable
fun CardRegisterScreen(
    onBackClick: () -> Unit = {},
    onSaveClick: (Card) -> Unit = {},
) {
    val context = LocalContext.current
    var cardNumber by rememberSaveable { mutableStateOf("") }
    var expiryDate by rememberSaveable { mutableStateOf("") }
    var cardOwner by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = {
                    val result =
                        Card.create(
                            cardNumber = cardNumber,
                            expiryDate = expiryDate,
                            cardOwner = cardOwner,
                            password = password,
                        )

                    result
                        .onSuccess { card ->
                            Toast
                                .makeText(
                                    context,
                                    context.getString(R.string.card_register_complete_message),
                                    Toast.LENGTH_SHORT,
                                ).show()
                            onSaveClick(card)
                        }.onFailure { throwable ->
                            Toast
                                .makeText(
                                    context,
                                    context.getString(R.string.card_info_invalid_message),
                                    Toast.LENGTH_SHORT,
                                ).show()
                            Log.e("TAG", "카드추가 실패: ${throwable.message} ")
                        }
                },
            )
        },
        content = { innerPadding ->
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 24.dp),
            ) {
                PaymentCard(
                    modifier =
                        Modifier
                            .padding(top = 14.dp, bottom = 40.dp)
                            .align(Alignment.CenterHorizontally),
                )
                CardNumberInputField(text = cardNumber, onValueChange = { cardNumber = it })
                Spacer(modifier = Modifier.height(30.dp))
                ExpiryDateInputField(text = expiryDate, onValueChange = { expiryDate = it })
                Spacer(modifier = Modifier.height(30.dp))
                CardOwnerInputField(text = cardOwner, onValueChange = { cardOwner = it })
                Spacer(modifier = Modifier.height(10.dp))
                PasswordInputField(text = password, onValueChange = { password = it })
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardTopBar(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(stringResource(R.string.card_register)) },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.content_desc_back),
                )
            }
        },
        actions = {
            IconButton(onClick = { onSaveClick() }) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.content_desc_done),
                )
            }
        },
        modifier = modifier,
    )
}

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

@Composable
fun PasswordInputField(
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
        label = { Text(stringResource(R.string.password_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.password_place_holder),
                color = Color.Gray,
            )
        },
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
    )
}

@Preview
@Composable
fun ShowCardRegisterScreenPreview() {
    CardRegisterScreen()
}
