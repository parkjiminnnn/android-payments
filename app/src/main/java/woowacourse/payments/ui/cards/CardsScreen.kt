package woowacourse.payments.ui.cards

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.cardregister.CardRegisterActivity
import woowacourse.payments.ui.cardregister.CardRegisterActivity.Companion.KEY_NEW_CARD
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.getParcelableExtraCompat
import woowacourse.payments.ui.theme.Gray10
import woowacourse.payments.ui.theme.Gray57

@Composable
fun CardsScreen() {
    val cardsState = remember { mutableStateListOf<Card>() }
    val context = LocalContext.current

    val launcher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val newCard =
                    activityResult.data?.getParcelableExtraCompat<Card>(KEY_NEW_CARD)
                        ?: return@rememberLauncherForActivityResult
                cardsState.add(newCard)
            }
        }

    Scaffold(
        topBar = {
            CardsTopBar(
                onCardAddClick = {
                    val intent = CardRegisterActivity.newIntent(context)
                    launcher.launch(intent)
                },
                cards = cardsState,
            )
        },
        content = { innerPadding ->
            CardsScreenContent(
                modifier =
                    Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                onCardAddClick = {
                    val intent = CardRegisterActivity.newIntent(context)
                    launcher.launch(intent)
                },
                cards = cardsState,
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsTopBar(
    modifier: Modifier = Modifier,
    cards: List<Card>,
    onCardAddClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(R.string.card_top_bar_title)) },
        modifier = modifier,
        actions = {
            if (cards.size > 1) {
                Text(
                    modifier =
                        Modifier
                            .clickable { onCardAddClick() }
                            .padding(end = 20.dp),
                    text = stringResource(R.string.register_card_button),
                )
            }
        },
    )
}

@Composable
fun CardsScreenContent(
    modifier: Modifier = Modifier,
    onCardAddClick: () -> Unit,
    cards: List<Card>,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        when (cards.size) {
            0 -> {
                Text(
                    text = stringResource(R.string.card_register_message),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 32.dp),
                )
                NewCard(onCardAddClick = onCardAddClick)
            }

            1 -> {
                RegisteredCards(cards = cards)
                NewCard(onCardAddClick = onCardAddClick)
            }

            else -> {
                RegisteredCards(cards = cards)
            }
        }
    }
}

@Composable
fun RegisteredCards(
    modifier: Modifier = Modifier,
    cards: List<Card>,
) {
    Column(
        modifier = modifier,
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        cards.forEach { card ->
            PaymentCard(modifier = Modifier.padding(bottom = 36.dp), card = card)
        }
    }
}

@Composable
fun NewCard(
    modifier: Modifier = Modifier,
    onCardAddClick: () -> Unit,
) {
    Box(
        modifier =
            modifier
                .size(width = 208.dp, height = 124.dp)
                .clickable { onCardAddClick() }
                .background(color = Gray10, shape = RoundedCornerShape(5.dp)),
    ) {
        Icon(
            tint = Gray57,
            modifier = Modifier.align(Alignment.Center),
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.card_register),
        )
    }
}

@Preview
@Composable
private fun ShowCardScreenPreview() {
    CardsScreen()
}
