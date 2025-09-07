package woowacourse.payments.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.theme.Gray10
import woowacourse.payments.ui.theme.Gray57

@Composable
fun CardsScreen(onCardAddClick: () -> Unit = {}) {
    Scaffold(
        topBar = {
            CardsTopBar(
                onAddClick = { onCardAddClick() },
            )
        },
        content = { innerPadding ->
            CardsScreenContent(
                onCardAddClick = onCardAddClick,
                paddingValues = innerPadding,
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsTopBar(
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = { Text("Payments") },
        modifier = modifier,
        actions = {
            Text(
                modifier =
                    Modifier
                        .clickable { onAddClick() }
                        .padding(end = 20.dp),
                text = "추가",
            )
        },
    )
}

@Composable
fun CardsScreenContent(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    onCardAddClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
            modifier
                .padding(paddingValues)
                .fillMaxSize(),
    ) {
        Text(
            text = "새로운 카드를 등록해주세요.",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 32.dp),
        )
        NewCard(onCardAddClick = onCardAddClick)
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
            contentDescription = "카드 추가",
        )
    }
}

@Preview
@Composable
fun ShowCardScreenPreview() {
    CardsScreen()
}
