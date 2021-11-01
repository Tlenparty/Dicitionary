package com.geekbrains.dictionary.states

import com.geekbrains.dictionary.data.entities.SearchData

sealed class SearchState {
    data class Success(val data: List<SearchData>): SearchState()
    data class Error (val error: Throwable): SearchState()
    object Loading: SearchState()
}