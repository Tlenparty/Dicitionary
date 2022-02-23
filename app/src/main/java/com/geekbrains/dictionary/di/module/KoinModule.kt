package com.geekbrains.dictionary.di.module

import com.geekbrains.dictionary.base_logic.search.adapter.SearchDataRVAdapter
import com.geekbrains.dictionary.data.interactors.SearchInteractor
import com.geekbrains.dictionary.data.interactors.SearchInteractorImpl
import com.geekbrains.dictionary.data.repositories.SearchRepository
import com.geekbrains.dictionary.data.repositories.SearchRepositoryImpl
import com.geekbrains.dictionary.helpers.consts.Scopes
import com.geekbrains.dictionary.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object KoinModule {

    val application = module {
        single<SearchRepository>() { SearchRepositoryImpl(dictionaryApi = get(qualifier = named(Scopes.API_MODULE))) }
    }

    val searchModule = module {
        factory<SearchInteractor> { SearchInteractorImpl(searchRepository = get()) }
        viewModel(qualifier = named(Scopes.SEARCH_VIEW_MODEL)) { SearchViewModel(searchInteractor = get()) }

        factory<SearchDataRVAdapter> { SearchDataRVAdapter() }
    }

}