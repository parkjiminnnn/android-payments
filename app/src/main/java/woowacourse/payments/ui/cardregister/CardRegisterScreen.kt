package woowacourse.payments.ui.cardregister

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.BankViewType
import woowacourse.payments.ui.cardregister.component.CardNumberInputField
import woowacourse.payments.ui.cardregister.component.CardOwnerInputField
import woowacourse.payments.ui.cardregister.component.ExpiryDateInputField
import woowacourse.payments.ui.cardregister.component.PasswordInputField
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.toBankType
import java.time.YearMonth

@Composable
fun CardRegisterScreen(
    bankViewType: BankViewType,
    onBackClick: () -> Unit,
    onSaveClick: (Card) -> Unit,
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
                            expiryDate = expiryDate.toYearMonth(),
                            cardOwner = cardOwner,
                            password = password,
                            bankType = bankViewType.toBankType(),
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
                    bankViewType = bankViewType,
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
private fun NewCardTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
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
    )
}

@Preview
@Composable
private fun ShowCardRegisterScreenPreview() {
    AndroidpaymentsTheme {
        CardRegisterScreen(
            bankViewType = BankViewType.BC,
            onBackClick = { },
            onSaveClick = { },
        )
    }
}

fun String.toYearMonth(): YearMonth? {
    val yearOffset = 2000
    if (length != 4) return null
    val year = substring(2, 4).toIntOrNull()
    val month = substring(0, 2).toIntOrNull()
    if (month !in 1..12) return null
    return if (year == null || month == null) {
        null
    } else {
        YearMonth.of(yearOffset + year, month)
    }
}
