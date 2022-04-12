package com.geekbrains.dictionary.data.interactors

import com.geekbrains.dictionary.data.entities.SearchData
import io.reactivex.Single

interface SearchInteractor {
    //выполнить перевод слова
    suspend fun search(word: String): List<SearchData>
}