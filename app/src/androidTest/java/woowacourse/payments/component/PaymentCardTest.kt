package woowacourse.payments.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.component.PaymentCard

class PaymentCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드에_등록한_카드정보가_보인다`() {
        // given
        val result =
            Card.create(
                cardNumber = "1234567812345678",
                expiryDate = "1234",
                cardOwner = "뭉치",
                password = "1234",
            )

        // when
        val card = result.getOrNull()

        Assertions.assertNotNull(card)
        composeTestRule.setContent {
            PaymentCard(card = card!!)
        }

        // then
        composeTestRule
            .onNodeWithText("1234 - 5678 - **** - ****")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("12/34")
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("뭉치")
            .assertIsDisplayed()
    }
}
