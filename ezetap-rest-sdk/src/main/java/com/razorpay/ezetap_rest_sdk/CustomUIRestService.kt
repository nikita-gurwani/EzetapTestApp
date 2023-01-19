package com.razorpay.ezetap_rest_sdk

import com.razorpay.ezetap_rest_sdk.resource.Resource
import com.razorpay.ezetap_rest_sdk.resource.ResponseHandler
import javax.inject.Inject

class CustomUIRestService @Inject constructor(private val sService: CustomUIService) {

    suspend fun sendRequestForCustomUI(): Resource<CustomUIResponse, ErrorWrapper> {
        return try {
            val response = sService.getCustomUIList()
            ResponseHandler().handleSuccess(response)
        } catch (exception: Exception) {
            ResponseHandler().handleError(exception, ErrorWrapper::class.javaObjectType)
        }
    }
}