package com.geekbrains.dictionary.di.module

import com.geekbrains.dictionary.base_logic.search.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface UIModule {
    @ContributesAndroidInjector
    fun bindSearchActivity(): SearchActivity
}