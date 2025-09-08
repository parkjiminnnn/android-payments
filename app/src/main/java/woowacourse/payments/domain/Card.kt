package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.YearMonth

@Parcelize
class Card private constructor(
    val cardNumber: String,
    val expiryDate: YearMonth,
    val cardOwner: String? = null,
    val password: String,
) : Parcelable {
    init {
        require(cardNumber.length == 16) { IllegalArgumentException("카드번호는 16자입니다.") }
        require(!expiryDate.isBefore(YearMonth.now())) {
            IllegalArgumentException("올바르지 않은 만료일입니다.")
        }
        if (cardOwner != null) {
            require(cardOwner.length <= 30) { IllegalArgumentException("카드 소유자 이름은 30자 이하입니다.") }
        }
        require(password.length == 4) { IllegalArgumentException("비밀번호는 4자리입니다.") }
    }

    companion object {
        fun create(
            cardNumber: String,
            expiryDate: String?,
            cardOwner: String?,
            password: String,
        ): Result<Card> =
            runCatching {
                val formattedYearMonth =
                    expiryDate?.toYearMonth() ?: throw IllegalArgumentException("올바르지 않은 만료일입니다.")

                Card(
                    cardNumber = cardNumber,
                    expiryDate = formattedYearMonth,
                    cardOwner = cardOwner,
                    password = password,
                )
            }

        private fun String.toYearMonth(): YearMonth? {
            val year = substring(2, 4).toIntOrNull()
            val month = substring(0, 2).toIntOrNull()
            return if (year == null || month == null) null else YearMonth.of(2000 + year, month)
        }
    }
}
