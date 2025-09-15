package woowacourse.payments.ui.cardregister

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.R
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardRegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val bankViewType =
            intent.getSerializableExtra(KEY_BANK_VIEW_TYPE) as? BankViewType ?: run {
                Toast
                    .makeText(
                        this,
                        getString(R.string.error_invalid_bank_message),
                        Toast.LENGTH_SHORT,
                    ).show()
                finish()
                return
            }

        setContent {
            AndroidpaymentsTheme {
                CardRegisterScreen(
                    bankViewType = bankViewType,
                    onBackClick = { finish() },
                    onSaveClick = { card ->
                        val intent =
                            Intent().apply {
                                putExtra(KEY_NEW_CARD, card)
                            }

                        setResult(RESULT_OK, intent)
                        finish()
                    },
                )
            }
        }
    }

    companion object {
        const val KEY_NEW_CARD: String = "new_card"
        private const val KEY_BANK_VIEW_TYPE: String = "key_bank_view_type"

        fun newIntent(
            context: Context,
            bankViewType: BankViewType,
        ): Intent =
            Intent(context, CardRegisterActivity::class.java).apply {
                putExtra(KEY_BANK_VIEW_TYPE, bankViewType)
            }
    }
}
