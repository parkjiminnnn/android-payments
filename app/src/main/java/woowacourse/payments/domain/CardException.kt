package woowacourse.payments.domain

sealed class CardException : IllegalArgumentException() {
    data object InvalidCardNumber : CardException()

    data object InvalidExpiryDate : CardException()

    data object InvalidCardOwner : CardException()

    data object InvalidPassword : CardException()
}
