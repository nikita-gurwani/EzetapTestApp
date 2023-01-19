package com.razorpay.ezetap_rest_sdk

import retrofit2.http.GET

interface CustomUIService {

    @GET("mobileapps/android_assignment.json")
    suspend fun getCustomUIList(): CustomUIResponse
}