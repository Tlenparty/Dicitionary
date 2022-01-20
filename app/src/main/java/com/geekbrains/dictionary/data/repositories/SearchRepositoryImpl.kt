package com.geekbrains.dictionary.data.repositories

import com.geekbrains.dictionary.api.DictionaryApi
import com.geekbrains.dictionary.data.entities.SearchData

class SearchRepositoryImpl(private val dictionaryApi: DictionaryApi) : SearchRepository {

    override suspend fun search(word: String): List<SearchData> = dictionaryApi.search(word).await()
}