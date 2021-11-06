package com.geekbrains.dictionary.data.interactors

import com.geekbrains.dictionary.data.entities.SearchData
import com.geekbrains.dictionary.data.repositories.SearchRepository
import io.reactivex.Single
import javax.inject.Inject

class SearchInteractorImpl @Inject constructor(
    private val searchRepository: SearchRepository
) : SearchInteractor {
    // перевод слова
    override fun search(word: String): Single<List<SearchData>> = searchRepository.search(word)

}