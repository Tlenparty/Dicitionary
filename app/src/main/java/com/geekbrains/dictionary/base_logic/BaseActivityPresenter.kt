package com.geekbrains.dictionary.base_logic

import io.reactivex.disposables.CompositeDisposable


abstract class BaseActivityPresenter<V : BaseActivityView> {

    protected val disposables = CompositeDisposable()

    //привязать связку presenter и activity
    abstract fun attach(v: V)

    //отвязать связку
    open fun detach() {
        disposables.clear()
    }
}