package com.geekbrains.dictionary.base_logic.search

interface SearchView {
    // показать детализацию по слову
    fun showWordDetail(id:Long)

    // сделать слово избранным
    fun changeFavorite(searchDataId:Long, favorite:Boolean)
}