package com.razorpay.ezetaptestapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import com.razorpay.ezetaptestapp.ui.DataActivityScreen
import dagger.android.AndroidInjection
import javax.inject.Inject

class DataCollectActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContent {ViewContent()
        }
    }

    @Composable
    fun ViewContent() {
        var values: List<String>? =  intent.getStringArrayListExtra(intentExtra)
        DataActivityScreen(values)
    }

}