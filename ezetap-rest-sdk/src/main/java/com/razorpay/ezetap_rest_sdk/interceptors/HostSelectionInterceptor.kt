package com.razorpay.ezetap_rest_sdk.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class HostSelectionInterceptor(private val prefix: String) : Interceptor {

    @Volatile
    var host: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val originalHost = request.url.host
        if (originalHost.startsWith(prefix)) {
            host?.let {
                val url = request.url.newBuilder().host(it).build()
                request = request.newBuilder().url(url).build()
            }
        }
        return chain.proceed(request)
    }
}