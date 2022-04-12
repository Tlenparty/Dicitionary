package com.geekbrains.dictionary.states

import com.geekbrains.dictionary.data.db.entities.WordTranslates

sealed class FavoriteState:BaseState{
    data class Success(val data: List<WordTranslates>): FavoriteState()
    data class Favorite(val searchDataId: Long, val favorite: Boolean): FavoriteState()
    data class Error (val error: Throwable): FavoriteState()
    object Loading: FavoriteState()
}
