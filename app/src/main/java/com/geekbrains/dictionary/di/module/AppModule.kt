package com.geekbrains.dictionary.di.module

import com.geekbrains.dictionary.data.repositories.SearchRepository
import com.geekbrains.dictionary.data.repositories.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AppModule {

    @Singleton
    @Binds
    fun bindSearchRepository(searchRepository: SearchRepositoryImpl): SearchRepository
}