package com.razorpay.ezetap_rest_sdk.di

import com.razorpay.ezetap_rest_sdk.CustomUIService
import com.razorpay.ezetap_rest_sdk.NetworkConfig
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServicesModule {

    @Provides
    @Singleton
    fun provideCustomService(@CustomServicesRetrofit customUIServicesRetrofit: Retrofit): CustomUIService {
        return customUIServicesRetrofit.create(CustomUIService::class.java)
    }

}