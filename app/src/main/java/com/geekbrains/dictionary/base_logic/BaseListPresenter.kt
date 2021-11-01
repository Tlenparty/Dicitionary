package com.geekbrains.dictionary.base_logic

interface BaseListPresenter<T> {
    //привязка элемента к view
    fun bindView(view: T)

    //количество элементов в списке
    fun getCount(): Int
}