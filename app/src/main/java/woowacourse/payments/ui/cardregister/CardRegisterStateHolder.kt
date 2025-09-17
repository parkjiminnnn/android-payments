package woowacourse.payments.ui.cardregister

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import woowacourse.payments.ui.BankViewType

class CardRegisterStateHolder {
    val cardNumber = mutableStateOf("")
    val expiryDate = mutableStateOf("")
    val cardOwner = mutableStateOf("")
    val password = mutableStateOf("")
    val selectedBankViewType = mutableStateOf(BankViewType.NONE)

    fun <T> onChange(
        currentValue: MutableState<T>,
        newValue: T,
    ) {
        currentValue.value = newValue
    }
}
