package woowacourse.payments.ui.cards.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.BankViewType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectBankBottomSheet(
    modifier: Modifier = Modifier,
    shape: Shape,
    containerColor: Color = Color.White,
    showBottomSheet: (Boolean) -> Unit,
    onCardAddClick: (BankViewType) -> Unit,
    show: Boolean,
    onDismiss: () -> Unit,
) {
    if (show) {
        ModalBottomSheet(
            modifier = modifier,
            shape = shape,
            containerColor = containerColor,
            content = {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(342.dp),
                    contentAlignment = Alignment.Center,
                    content = {
                        SelectBankButtons(onBankClick = { bankType ->
                            showBottomSheet(false)
                            onCardAddClick(bankType)
                        })
                    },
                )
            },
            onDismissRequest = onDismiss,
        )
    }
}

@Preview
@Composable
private fun ShowBottomSheetPreview() {
    SelectBankBottomSheet(
        modifier = Modifier.height(342.dp),
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        show = true,
        onDismiss = {},
        showBottomSheet = {},
        onCardAddClick = {},
    )
}
