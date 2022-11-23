package com.kamal.sahlsample

import android.app.Application
import com.kamal.sahlsample.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

class App : Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>


    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        appInject()
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    private fun appInject() {
        AppInjector.init(this)
    }



}