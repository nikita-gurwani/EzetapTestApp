package com.razorpay.ezetap_rest_sdk.resource

import androidx.annotation.Keep
import com.razorpay.ezetap_rest_sdk.Status
import com.razorpay.ezetap_rest_sdk.Status.*

@Keep
data class Resource<out T, out P>(val status: Status, val data: T?, val errorMessage: String?, val errorData: P?) {
    companion object {
        fun <T, P> success(data: T?): Resource<T, P> {
            return Resource(SUCCESS, data, null, null)
        }

        fun <T, P> error(msg: String, errorData: P?): Resource<T, P> {
            return Resource(ERROR, null, msg, errorData)
        }

        fun <T, P> loading(data: T?): Resource<T, P> {
            return Resource(LOADING, data, null, null)
        }
    }
}