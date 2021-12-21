package com.geekbrains.dictionary.states

import com.geekbrains.dictionary.data.entities.SearchData

sealed class SearchState : BaseState {
    data class Success(val data: List<SearchData>) : SearchState()
    data class FinedInDB(val searchDataId: Long) : SearchState()
    data class Favorite(val searchDataId: Long, val favorite: Boolean) : SearchState()
    data class Error(val error: Throwable) : SearchState()
    object Loading : SearchState()
}