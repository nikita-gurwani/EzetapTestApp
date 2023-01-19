package com.razorpay.ezetaptestapp.di

import com.razorpay.ezetaptestapp.MainApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class MainApplicationModule {

    @Provides
    @Singleton
    fun provideEpoc(): MainApplication {
        return MainApplication()
    }
}