package com.razorpay.ezetaptestapp

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.razorpay.ezetap_rest_sdk.CustomUIRepository
import com.razorpay.ezetap_rest_sdk.CustomUIResponse
import com.razorpay.ezetap_rest_sdk.Status
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    application: MainApplication, private val repository: CustomUIRepository
) : AndroidViewModel(application) {

    lateinit var customUIResponse: CustomUIResponse
    init {
        loadCustomUIRequestCall()
    }

    fun loadCustomUIRequestCall() {
        viewModelScope.launch {
            val response = repository.loadCustomUI()
            if (response.status == Status.SUCCESS) {
                response.data?.let {
                    customUIResponse = it
                }
            } else {
                response.errorMessage?.let {
                    customUIResponse = CustomUIResponse(null)
                }
            }
        }
    }
}