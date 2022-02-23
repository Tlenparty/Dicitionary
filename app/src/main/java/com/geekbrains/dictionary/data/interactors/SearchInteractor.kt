package com.geekbrains.dictionary.data.interactors

import com.geekbrains.dictionary.data.entities.SearchData
import io.reactivex.Single

interface SearchInteractor {

    suspend fun search(word: String): List<SearchData>
}