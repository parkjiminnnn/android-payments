package woowacourse.payments.ui.cardregister

import androidx.compose.foundation.Image
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
import woowacourse.payments.R

private const val COLUMN_COUNT: Int = 4

@Composable
fun SelectCardButtons(modifier: Modifier = Modifier) {
    FlowRow(
        modifier = modifier.height(157.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(23.dp),
        maxItemsInEachRow = COLUMN_COUNT,
    ) {
        SelectCardButtonType.entries.forEach {
            SelectCardButton(
                modifier = Modifier.size(width = 70.dp, height = 67.dp),
                imageRes = it.imageRes,
                nameRes = it.nameRes,
            )
        }
    }
}

@Composable
private fun SelectCardButton(
    modifier: Modifier = Modifier,
    imageRes: Int,
    nameRes: Int,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier.size(37.dp),
            painter = painterResource(imageRes),
            contentDescription = null,
        )
        Text(text = stringResource(nameRes))
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowSelectCardButton() {
    SelectCardButton(imageRes = R.drawable.img_bc, nameRes = R.string.bc_card)
}

@Preview(showBackground = true)
@Composable
private fun ShowSelectCardButtonsPreview() {
    SelectCardButtons()
}
