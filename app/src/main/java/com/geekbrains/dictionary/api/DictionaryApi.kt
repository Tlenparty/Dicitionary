package com.geekbrains.dictionary.api

import com.geekbrains.dictionary.data.entities.SearchData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface DictionaryApi {
    //выполнить поиск
    @GET("words/search")
    fun search(@Query("search") text: String): Deferred<List<SearchData>>
}