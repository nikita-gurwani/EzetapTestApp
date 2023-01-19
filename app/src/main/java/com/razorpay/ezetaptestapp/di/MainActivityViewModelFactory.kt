package com.razorpay.ezetaptestapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.razorpay.ezetap_rest_sdk.CustomUIRepository
import com.razorpay.ezetaptestapp.MainActivityViewModel
import com.razorpay.ezetaptestapp.MainApplication

class MainActivityViewModelFactory(
    private val app: MainApplication,
    private val repository: CustomUIRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST") return MainActivityViewModel(app, repository) as T
    }
}