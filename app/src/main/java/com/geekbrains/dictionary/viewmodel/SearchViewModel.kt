package com.geekbrains.dictionary.viewmodel

import com.geekbrains.dictionary.data.interactors.SearchInteractor
import com.geekbrains.dictionary.helpers.scheduler.AppSchedulers
import com.geekbrains.dictionary.states.SearchState
import io.reactivex.rxkotlin.plusAssign
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchInteractor: SearchInteractor,
) : BaseViewModel<SearchState>() {

    //найти перевод слова
    fun findWord(word: String) {
        coroutineScope.launch {
            liveData.postValue(SearchState.Success(searchInteractor.search(word)))
        }
    }

    //обработать ошибку запроса данных
    override fun handleCoroutineError(throwable: Throwable) {
        super.handleCoroutineError(throwable)
        liveData.postValue(SearchState.Error(throwable))
    }
}