package com.geekbrains.dictionary.data.repositories

import com.geekbrains.dictionary.data.entities.SearchData
import io.reactivex.Single

interface SearchRepository {
    //выполнить перевод слова
    fun search(word: String): Single<List<SearchData>>
}