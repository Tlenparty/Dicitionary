package com.geekbrains.dictionary

import com.geekbrains.dictionary.di.DaggerAppComponent
import com.geekbrains.dictionary.helpers.AppSchedulersImpl
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent
            .builder()
            .apply {
                withContext(applicationContext)
                withAppScheduler(AppSchedulersImpl())
            }
            .build()
}