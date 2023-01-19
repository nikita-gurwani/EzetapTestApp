package com.razorpay.ezetap_rest_sdk.di

import com.razorpay.ezetap_rest_sdk.CustomUIRestService
import com.razorpay.ezetap_rest_sdk.CustomUIService
import com.razorpay.ezetap_rest_sdk.NetworkConfig
import com.razorpay.ezetap_rest_sdk.resource.ResponseHandler
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    fun provideResponseHandler() = ResponseHandler()

    @Provides
    fun provideCustomUIRestService(sService: CustomUIService) = CustomUIRestService(sService)

    @CustomServicesRetrofit
    @Singleton
    @Provides
    fun provideServicesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        loggingInterceptor: HttpLoggingInterceptor,
        okHttpClient: OkHttpClient,
        networkConfig: NetworkConfig
    ): Retrofit {
        val client = okHttpClient.newBuilder().addInterceptor(loggingInterceptor)
        return Retrofit.Builder().baseUrl(networkConfig.baseUrl)
            .addConverterFactory(gsonConverterFactory).client(client.build()).build()
    }


    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS).build()
    }


    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()


    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }

    @Provides
    @Singleton
    fun provideNetworkConfig(): NetworkConfig {
        return NetworkConfig("https://demo.ezetap.com/")
    }
}