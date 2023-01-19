package com.razorpay.ezetap_rest_sdk

import androidx.annotation.Keep

@Keep
data class Error(val code: String, val message: String)

@Keep
data class ErrorWrapper(val error: Error)

