package woowacourse.payments.domain

import java.time.YearMonth

data class Card(
    val cardNumber: String,
    val expiryDate: YearMonth,
    val cardOwner: String,
)
