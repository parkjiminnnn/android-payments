package woowacourse.payments.ui.cardregister

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardRegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                CardRegisterScreen(onBackClick = { finish() }, onSaveClick = { card ->
                    val intent = Intent().apply { putExtra(KEY_NEW_CARD, card) }
                    setResult(RESULT_OK, intent)
                    finish()
                    Toast.makeText(this, "카드가 추가되었습니다.", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }

    companion object {
        const val KEY_NEW_CARD: String = "new_card"

        fun newIntent(context: Context): Intent = Intent(context, CardRegisterActivity::class.java)
    }
}
