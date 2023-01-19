package com.razorpay.ezetap_rest_sdk

import javax.inject.Inject

class CustomUIRepository @Inject constructor(private val cuRestService: CustomUIRestService) {

    suspend fun loadCustomUI() = cuRestService.sendRequestForCustomUI()

}