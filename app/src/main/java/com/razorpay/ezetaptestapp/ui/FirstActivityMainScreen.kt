package com.razorpay.ezetaptestapp.ui

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import com.razorpay.ezetaptestapp.MainActivityViewModel

var keyValue = HashMap<String, String>()

@Composable
fun FirstActivityMainScreen(viewModel: MainActivityViewModel, listener: CustomListener) {
    val data = viewModel.customUIResponse.customUI?.uiData
    if (data != null) {
        for (uiData in data) {

            when (uiData.uitype) {
                CustomUi.label.toString() -> {
                    ContentText(textValue = uiData.value)
                    keyValue[uiData.key] = uiData.value
                }
                CustomUi.edittext.toString() -> {
                    ContentEditText(hint = uiData.hint, uiData.key)
                }
                CustomUi.button.toString() -> {
                    ContentButton(textValue = uiData.value) {
                        listener.onButtonClick(keyValue)
                    }
                }
            }
        }
    }
}

@Composable
fun ContentText(
    textValue: String
) {
    Text(textValue)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentEditText(
    hint: String, key: String
) {
    var text by remember { mutableStateOf("") }

    TextField(value = text, onValueChange = {
        text = it
    }, label = { Text(hint) })
    keyValue[key] = text
}

@Composable
fun ContentButton(
    textValue: String, onClick: () -> Unit
) {
    Button(onClick = { onClick }) {
        Text(textValue)
    }
}