package com.geekbrains.dictionary.viewmodel

import com.geekbrains.dictionary.data.interactors.SearchInteractor
import com.geekbrains.dictionary.helpers.scheduler.AppSchedulers
import com.geekbrains.dictionary.states.SearchState
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

// Dagger2 позволяет нам предоставлять зависимости без наличия @Provides методов.
// Это достигается путем наличия @Inject над конструктором у класса. (ч/з @Binds будем провайдить зависиомсти)

class SearchViewModel @Inject constructor(
    private val searchInteractor: SearchInteractor,
    private val appSchedulers: AppSchedulers
) : BaseViewModel<SearchState>() {

    //найти перевод слова
    fun findWord(word: String) {
        disposables +=
            searchInteractor
                .search(word)
                .observeOn(appSchedulers.main())
                .subscribeOn(appSchedulers.background()) //обработку делаем в отдельном потоке
                .doOnSubscribe { liveData.postValue(SearchState.Loading) }
                .subscribe(
                    { searchData -> liveData.postValue(SearchState.Success(searchData)) },
                    { exception -> liveData.postValue(SearchState.Error(exception)) }
                )
    }
}