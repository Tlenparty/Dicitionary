package com.geekbrains.dictionary.base_logic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

// HasAndroidInjector - чтобы модуль(android injection module) мог найти и запровайдить зависимости
// Обратите внимание на dispatchingAndroidInjector и интерфейс Dagger'а
// HasAndroidInjector: мы переопределяем его метод androidInjector. Они
// нужны для внедрения зависимостей в Activity. По своей сути — это вспомогательные методы для разработчиков под Андроид для эффективного внедрения компонентов платформы, таких как Активити, Сервис и т. п.

abstract class BaseDaggerActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}