package com.geekbrains.dictionary.data.repositories

import com.geekbrains.dictionary.api.DictionaryApi
import com.geekbrains.dictionary.data.entities.SearchData
import io.reactivex.Single

class SearchRepositoryImpl(private val dictionaryApi: DictionaryApi) : SearchRepository {
    //выполнить перевод слова
    override fun search(word: String): Single<List<SearchData>> = dictionaryApi.search(word)
}