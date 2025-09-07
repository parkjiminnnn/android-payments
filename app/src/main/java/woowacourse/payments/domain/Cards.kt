package woowacourse.payments.domain

object Cards {
    private val allCards: MutableList<Card> = mutableListOf()

    fun register(card: Card) {
        allCards.add(card)
    }

    fun getCount(): CardCount =
        when (allCards.size) {
            0 -> CardCount.NONE
            1 -> CardCount.ONE
            else -> CardCount.MULTIPLE
        }
}
