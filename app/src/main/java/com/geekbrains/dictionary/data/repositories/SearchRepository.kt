package com.geekbrains.dictionary.data.repositories

import com.geekbrains.dictionary.data.entities.SearchData
import io.reactivex.Single

interface SearchRepository {
    //выполнить перевод слова
    suspend fun search(word: String): List<SearchData>
}