package woowacourse.payments.ui.cards.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.BankViewType

private const val COLUMN_COUNT: Int = 4

@Composable
fun SelectCardButtons(
    modifier: Modifier = Modifier,
    onBankClick: (BankViewType) -> Unit,
) {
    FlowRow(
        modifier = modifier.height(145.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(23.dp),
        maxItemsInEachRow = COLUMN_COUNT,
    ) {
        BankViewType.entries.forEach { bankType ->
            SelectBankButton(
                imageRes = bankType.imageRes,
                nameRes = bankType.nameRes,
                onClick = { onBankClick(bankType) },
            )
        }
    }
}

@Composable
private fun SelectBankButton(
    modifier: Modifier = Modifier,
    imageRes: Int,
    nameRes: Int,
    onClick: () -> Unit,
) {
    Column(
        modifier =
            modifier
                .size(width = 80.dp, height = 60.dp)
                .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(imageRes),
            contentDescription = null,
        )
        Text(text = stringResource(nameRes), fontSize = 16.sp, lineHeight = 20.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowSelectCardButtonPreview() {
    SelectBankButton(imageRes = R.drawable.img_bc, nameRes = R.string.bc_card, onClick = {})
}

@Preview(showBackground = true)
@Composable
private fun ShowSelectCardButtonsPreview() {
    SelectCardButtons(onBankClick = {})
}
