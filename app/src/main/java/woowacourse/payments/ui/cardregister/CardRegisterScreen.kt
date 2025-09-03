package woowacourse.payments.ui.cardregister

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.component.PaymentCard

@Composable
fun CardRegisterScreen() {
    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = {},
                onSaveClick = {},
            )
        },
        content = { innerPadding ->
            CardRegisterScreenContent(innerPadding)
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardTopBar(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text("카드 추가") },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "뒤로 가기",
                )
            }
        },
        actions = {
            IconButton(onClick = { onSaveClick() }) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "완료",
                )
            }
        },
        modifier = modifier,
    )
}

@Composable
fun CardRegisterScreenContent(padding: PaddingValues) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PaymentCard(modifier = Modifier.padding(top = 14.dp))
        InputFields()
    }
}

@Composable
fun InputFieldItem(
    labelText: String,
    placeholderText: String,
    weight: Float = 1f,
) {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(weight),
        value = text,
        onValueChange = { newText -> text = newText },
        label = { Text(text = labelText) },
        placeholder = { Text(text = placeholderText, color = Color.Gray) },
    )
}

@Composable
fun InputFields() {
    Column(
        modifier = Modifier.padding(vertical = 40.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        InputFieldItem("카드 번호", "0000 - 0000 - 0000 - 0000")
        InputFieldItem("만료일", "MM/YY", 0.5f)
        InputFieldItem("카드 소유자 이름(선택)", "카드에 표시된 이름을 입력하세요.")
        InputFieldItem("비밀번호", "0000", 0.5f)
    }
}

@Preview
@Composable
fun ShowCardRegisterScreenPreview() {
    CardRegisterScreen()
}
