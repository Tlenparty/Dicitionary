package com.geekbrains.dictionary.di

import android.content.Context
import com.geekbrains.dictionary.App
import com.geekbrains.dictionary.di.module.AppModule
import com.geekbrains.dictionary.di.module.DictionaryApiModule
import com.geekbrains.dictionary.di.module.UIModule
import com.geekbrains.dictionary.di.module.ViewModelModule
import com.geekbrains.dictionary.helpers.scheduler.AppSchedulers
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
  @Inject указывает на метод, конструктор или поле класса, которые (или в которые) надо что-то внедрить.
  1. Через конструктор(Основной вариант). 2. Поле с исп. @Inject и lateinit
  @Provides указывает на метод, который предоставляет (возвращает) зависимость для дальнейшего внедрения.
  @Module помечает класс с набором методов, которые отмечаются аннотациями @Provides.
  @Component отмечает интерфейс, который позже используется для генерации кода. В нём определяется,
  куда что-либо внедрять, а также методы для прямого доступа к зависимостям.*/
@Singleton
@Component(
    modules =
    [
        AndroidInjectionModule::class,
        ViewModelModule::class,
        UIModule::class,
        DictionaryApiModule::class,
        AppModule::class
    ]
)

interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withAppScheduler(appSchedulers: AppSchedulers): Builder

        fun build(): AppComponent
    }


}