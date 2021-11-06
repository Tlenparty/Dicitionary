package com.geekbrains.dictionary.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.dictionary.data.interactors.SearchInteractor
import com.geekbrains.dictionary.data.interactors.SearchInteractorImpl
import com.geekbrains.dictionary.di.ViewModelFactory
import com.geekbrains.dictionary.di.ViewModelKey
import com.geekbrains.dictionary.viewmodel.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindSearchInteractor(searchInteractor: SearchInteractorImpl): SearchInteractor

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    protected abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel
}