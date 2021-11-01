package com.geekbrains.dictionary.api

import com.geekbrains.dictionary.data.entities.SearchData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DictionaryApi {
    //выполнить поиск
    @GET("words/search")
    fun search(@Query("search") text: String): Single<List<SearchData>>
}