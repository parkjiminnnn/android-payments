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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.component.PaymentCard

@Composable
fun CardRegisterScreen() {
    Scaffold(
        containerColor = Color.White,
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
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
        title = { Text(stringResource(R.string.title_card_register)) },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.content_desc_back),
                )
            }
        },
        actions = {
            IconButton(onClick = { onSaveClick() }) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.content_desc_done),
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
fun CardNumberInputField() {
    var text by remember { mutableStateOf("") }
    val maxLength = 16

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = { newText ->
            val filteredText = newText.filter { it.isDigit() }
            if (filteredText.length <= maxLength) text = filteredText
        },
        label = { Text(stringResource(R.string.card_number_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.card_number_place_holder),
                color = Color.Gray,
            )
        },
        visualTransformation = CardNumberVisualTransformation(stringResource(R.string.card_number_separator)),
        singleLine = true,
    )
}

@Composable
fun ExpiryDateInputField() {
    var text by remember { mutableStateOf("") }
    val maxLength = 4

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(0.5f),
        value = text,
        onValueChange = { newText ->
            val filteredText = newText.filter { it.isDigit() }
            if (filteredText.length <= maxLength) text = filteredText
        },
        label = { Text(stringResource(R.string.expriy_date_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.expiry_date_place_holder),
                color = Color.Gray,
            )
        },
        visualTransformation = ExpiryDateVisualTransformation(stringResource(R.string.expiry_date_separator)),
        singleLine = true,
    )
}

@Composable
fun CardOwnerInputField() {
    var text by remember { mutableStateOf("") }
    val maxLength = 30

    Column {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { newText ->
                if (newText.length <= maxLength) text = newText
            },
            label = { Text(stringResource(R.string.card_owner_label)) },
            placeholder = {
                Text(
                    text = stringResource(R.string.card_owner_placeholder),
                    color = Color.Gray,
                )
            },
            singleLine = true,
        )
        Text(
            stringResource(R.string.card_owner_length, text.length),
            modifier =
                Modifier
                    .align(Alignment.End)
                    .padding(end = 16.dp, top = 4.dp),
        )
    }
}

@Composable
fun PasswordInputField() {
    var text by remember { mutableStateOf("") }
    val maxLength = 4

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(0.5f),
        value = text,
        onValueChange = { newText ->
            val filteredText = newText.filter { it.isDigit() }
            if (filteredText.length <= maxLength) text = filteredText
        },
        label = { Text(stringResource(R.string.password_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.password_place_holder),
                color = Color.Gray,
            )
        },
        visualTransformation = PasswordVisualTransformation(stringResource(R.string.password_mask_char)),
        singleLine = true,
    )
}

@Composable
fun InputFields() {
    Column(
        modifier = Modifier.padding(top = 40.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        CardNumberInputField()
        ExpiryDateInputField()
        CardOwnerInputField()
        PasswordInputField()
    }
}

@Preview
@Composable
fun ShowCardRegisterScreenPreview() {
    CardRegisterScreen()
}
