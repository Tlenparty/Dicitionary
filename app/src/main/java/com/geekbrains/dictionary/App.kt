package com.geekbrains.dictionary

import android.app.Application
import com.geekbrains.dictionary.di.module.DictionaryApiModule
import com.geekbrains.dictionary.di.module.KoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                DictionaryApiModule.create(),
                KoinModule.application,
                KoinModule.searchModule
            )
        }
    }

}