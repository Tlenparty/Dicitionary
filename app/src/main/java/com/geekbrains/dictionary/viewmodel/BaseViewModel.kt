package com.geekbrains.dictionary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.dictionary.states.BaseState
import com.geekbrains.dictionary.states.SearchState
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<S: SearchState>:ViewModel() {

    protected val liveData: MutableLiveData<S> = MutableLiveData()
    protected val disposables = CompositeDisposable()

    //получить данные, на которые выполнена подписка
    open fun getLiveData(): LiveData<S> = liveData

    override fun onCleared() {
        disposables.clear()
    }
}