package com.geekbrains.dictionary.data.repositories

import com.geekbrains.dictionary.api.DictionaryApiFactory

object SearchRepositoryFactory {
    fun create():SearchRepository = SearchRepositoryImpl(DictionaryApiFactory.create())
}