package woowacourse.payments.ui.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.BankViewType
import woowacourse.payments.ui.cards.component.NewCard
import woowacourse.payments.ui.cards.component.RegisteredCards
import woowacourse.payments.ui.cards.component.SelectBankBottomSheet
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardsScreen(
    cardsState: List<Card> = listOf(),
    onCardAddClick: (BankViewType) -> Unit,
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            CardsTopBar(
                onCardAddClick = {
                    showBottomSheet = true
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
                    showBottomSheet = true
                },
                cards = cardsState,
            )
            SelectBankBottomSheet(
                shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
                showBottomSheet = { showBottomSheet = it },
                onCardAddClick = { bankViewType -> onCardAddClick(bankViewType) },
                show = showBottomSheet,
                onDismiss = { showBottomSheet = false },
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CardsTopBar(
    modifier: Modifier = Modifier,
    cards: List<Card>,
    onCardAddClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = { Text(stringResource(R.string.card_top_bar_title)) },
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
private fun CardsScreenContent(
    modifier: Modifier = Modifier,
    onCardAddClick: () -> Unit,
    cards: List<Card>,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (cards.size) {
            0 -> {
                Text(
                    modifier = Modifier.padding(vertical = 32.dp),
                    text = stringResource(R.string.card_register_message),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
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

@Preview
@Composable
private fun ShowCardScreenPreview() {
    AndroidpaymentsTheme {
        CardsScreen(onCardAddClick = {})
    }
}
