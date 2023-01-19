package com.razorpay.ezetaptestapp

import android.app.Application
import com.razorpay.ezetap_rest_sdk.di.RetrofitModule
import com.razorpay.ezetap_rest_sdk.di.ServicesModule
import com.razorpay.ezetaptestapp.di.MainApplicationModule
import com.razorpay.ezetaptestapp.di.MainModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, MainModule::class, RetrofitModule::class, ServicesModule::class, MainApplicationModule::class]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: MainApplication)
}