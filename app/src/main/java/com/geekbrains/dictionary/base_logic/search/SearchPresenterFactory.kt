package com.geekbrains.dictionary.base_logic.search

import com.geekbrains.dictionary.data.repositories.SearchRepositoryFactory

object SearchPresenterFactory {
    fun create() = SearchPresenter(SearchRepositoryFactory.create())
}