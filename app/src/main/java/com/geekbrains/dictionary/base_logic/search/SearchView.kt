package com.geekbrains.dictionary.base_logic.search

import com.geekbrains.dictionary.base_logic.BaseActivityView
import com.geekbrains.dictionary.states.SearchState

interface SearchView:BaseActivityView {
    //обработать полученную информацию о загрузке данных по переводу
    fun renderData(state: SearchState)
}