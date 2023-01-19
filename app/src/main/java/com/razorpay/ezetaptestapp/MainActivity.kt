package com.razorpay.ezetaptestapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import com.razorpay.ezetaptestapp.ui.CustomListener
import com.razorpay.ezetaptestapp.ui.FirstActivityMainScreen
import dagger.android.AndroidInjection
import java.util.ArrayList
import javax.inject.Inject


class MainActivity : AppCompatActivity(), CustomListener{

    @Inject
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContent {ViewContent()
        }
    }

    @Composable
    fun ViewContent() {
        FirstActivityMainScreen(viewModel, this)
    }

    override fun onButtonClick(keysValue: Map<String, String>) {
        val intent = Intent(this, DataCollectActivity::class.java)
        intent.putStringArrayListExtra(intentExtra, keysValue.values.toList() as ArrayList<String>)
    }

}