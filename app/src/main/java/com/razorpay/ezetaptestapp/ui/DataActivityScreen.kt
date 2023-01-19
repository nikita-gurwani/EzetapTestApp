package com.razorpay.ezetaptestapp.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun DataActivityScreen(values: List<String>?) {
    if (values != null) {
        for (data in values) {
            Text(data)
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }else{
        Text("Error Happened")
    }
}
