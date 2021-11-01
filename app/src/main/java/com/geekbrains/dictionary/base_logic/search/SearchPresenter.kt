package com.geekbrains.dictionary.base_logic.search

import com.geekbrains.dictionary.base_logic.BaseActivityPresenter
import com.geekbrains.dictionary.data.repositories.SearchRepository
import com.geekbrains.dictionary.states.SearchState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers


class SearchPresenter(private val searchRepository:SearchRepository):
    BaseActivityPresenter<SearchView>() {

    private var searchView: SearchView? = null

    //привязать presenter и activity
    override fun attach(view: SearchView) {
        this.searchView = view
    }

    //отвязать
    override fun detach() {
        searchView = null
        super.detach()
    }

    //выполнить поиск слова
    fun findWord(word: String) {
        disposables +=
            searchRepository
                .search(word)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()) //обработку делаем в отдельном потоке
                .subscribe(
                    { searchData -> searchView?.renderData(SearchState.Success(searchData)) },
                    { exception -> searchView?.renderData(SearchState.Error(exception)) }
                )
    }
}