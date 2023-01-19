package com.razorpay.ezetaptestapp.di

import androidx.lifecycle.ViewModelProvider
import com.razorpay.ezetap_rest_sdk.CustomUIRepository
import com.razorpay.ezetap_rest_sdk.CustomUIRestService
import com.razorpay.ezetaptestapp.MainActivity
import com.razorpay.ezetaptestapp.MainActivityViewModel
import com.razorpay.ezetaptestapp.MainApplication
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideMainViewModelForActivity(
        activity: MainActivity,
        factory: MainActivityViewModelFactory
    ): MainActivityViewModel {
        return ViewModelProvider(activity, factory).get(MainActivityViewModel::class.java)
    }

    @Provides
    fun provideMainViewModelFactory(application: MainApplication, repository: CustomUIRepository): MainActivityViewModelFactory {
        return MainActivityViewModelFactory(application, repository)
    }

    @Provides
    fun provideCustomRepository(cuRestService: CustomUIRestService) = CustomUIRepository(cuRestService)
}