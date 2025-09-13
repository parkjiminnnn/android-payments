package woowacourse.payments.ui.cardregister

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardRegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardRegisterScreen(onBackClick = { finish() }, onSaveClick = { card ->
                    val intent =
                        Intent().apply {
                            putExtra(KEY_NEW_CARD, card)
                        }

                    setResult(RESULT_OK, intent)
                    finish()
                })
            }
        }
    }

    companion object {
        const val KEY_NEW_CARD: String = "new_card"

        fun newIntent(
            context: Context,
            card: Card? = null,
        ): Intent =
            Intent(context, CardRegisterActivity::class.java).apply {
                card?.let { putExtra(KEY_NEW_CARD, it) }
            }
    }
}
