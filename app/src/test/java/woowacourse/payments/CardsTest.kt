package woowacourse.payments

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.assertThrows
import woowacourse.payments.domain.Card
import java.time.YearMonth

class CardsTest {
    @Test
    fun `숫자 외 문자 카드번호 입력시 예외가 발생한다`() {
        // given
        val result =
            Card.create(
                cardNumber = "뭉치바보",
                expiryDate = YearMonth.of(2034, 12),
                cardOwner = "뭉치",
                password = "1234",
            )

        // when & then
        val exception = assertThrows<IllegalArgumentException> { result.getOrThrow() }
        assertNotNull(exception.message)
        Assertions.assertTrue(exception.message!!.contains("카드번호 오류"))
    }

    @Test
    fun `카드번호 16자리 초과 시 예외가 발생한다`() {
        // given
        val cardNumber = "1".repeat(17)
        val result =
            Card.create(
                cardNumber = cardNumber,
                expiryDate = YearMonth.of(2034, 12),
                cardOwner = "뭉치",
                password = "1234",
            )

        // when & then
        val exception = assertThrows<IllegalArgumentException> { result.getOrThrow() }
        assertNotNull(exception.message)
        Assertions.assertTrue(exception.message!!.contains("카드번호 오류"))
    }

    @Test
    fun `만료일이 현재보다 과거일 시 예외가 발생한다`() {
        // given
        val result =
            Card.create(
                cardNumber = "1234567812345678",
                expiryDate = YearMonth.of(2024, 12),
                cardOwner = "뭉치",
                password = "1234",
            )

        // when & then
        val exception = assertThrows<IllegalArgumentException> { result.getOrThrow() }
        assertNotNull(exception.message)
        Assertions.assertTrue(exception.message!!.contains("만료일 오류"))
    }

    @Test
    fun `숫자 외 문자 비밀번호 입력 시 예외가 발생한다`() {
        // given
        val result =
            Card.create(
                cardNumber = "1234567812345678",
                expiryDate = YearMonth.of(2034, 12),
                cardOwner = "뭉치",
                password = "뭉치바보",
            )

        // when & then
        val exception = assertThrows<IllegalArgumentException> { result.getOrThrow() }
        assertNotNull(exception.message)
        Assertions.assertTrue(exception.message!!.contains("비밀번호 오류"))
    }

    @Test
    fun `비밀번호 4자리 초과 시 예외가 발생한다`() {
        // given
        val result =
            Card.create(
                cardNumber = "1234567812345678",
                expiryDate = YearMonth.of(2034, 12),
                cardOwner = "뭉치",
                password = "12345",
            )

        // when & then
        val exception = assertThrows<IllegalArgumentException> { result.getOrThrow() }
        assertNotNull(exception.message)
        Assertions.assertTrue(exception.message!!.contains("비밀번호 오류"))
    }
}
