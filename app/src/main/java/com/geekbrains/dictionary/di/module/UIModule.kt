package com.geekbrains.dictionary.di.module

import com.geekbrains.dictionary.base_logic.search.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
Модуль для Activity. Так как мы используем дополнительную библиотеку поддержки для Android, то все становится гораздо проще при помощи ContributesAndroidInjector. Он позволяет внедрять зависимости в Activity (нашу ViewModel) благодаря простому AndroidInjection.inject(this) в методе onCreate.*/

@Module
interface UIModule {
    // Чтобы не было ошибки no injector factory bound for Class <...MainActivity>
    @ContributesAndroidInjector
    fun bindSearchActivity(): SearchActivity
}