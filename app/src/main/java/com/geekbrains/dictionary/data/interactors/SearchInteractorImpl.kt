package com.geekbrains.dictionary.data.interactors

import com.geekbrains.dictionary.data.entities.SearchData
import com.geekbrains.dictionary.data.repositories.SearchRepository
import io.reactivex.Single

class SearchInteractorImpl(private val searchRepository: SearchRepository) : SearchInteractor {
    //выполнить перевод слова
    override suspend fun search(word: String): List<SearchData> = searchRepository.search(word)
}