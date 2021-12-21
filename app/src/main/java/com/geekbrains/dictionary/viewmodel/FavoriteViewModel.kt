package com.geekbrains.dictionary.viewmodel

import com.geekbrains.dictionary.data.db.AppDB
import com.geekbrains.dictionary.data.db.entities.Favorite
import com.geekbrains.dictionary.data.db.entities.WordTranslates
import com.geekbrains.dictionary.states.FavoriteState
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class FavoriteViewModel(
    private val db: AppDB
) : BaseViewModel<FavoriteState>() {

    //найти избранные слова
    fun findFavorite() {
        liveData.postValue(FavoriteState.Loading)
        coroutineScope.launch {
            val favorites: List<WordTranslates> = db.searchDataDao().findFavorite()

            //оповещаем слушателя о найденных словах
            if (favorites.isEmpty()) {
                liveData.postValue(FavoriteState.Error(RuntimeException("Нет данных для отображения")))
            } else {
                liveData.postValue(FavoriteState.Success(favorites))
            }
        }
    }

    //пометить слово избранным
    fun changeFavorite(searchDataId: Long, favorite: Boolean) {
        coroutineScope.launch {
            val favoriteDB = Favorite(searchDataId = searchDataId)
            if (favorite) {
                //слово уже помечено избранным, снимаем отметку
                db.favoriteDao().delete(favoriteDB)
            } else {
                //помечаем слово избранным
                db.favoriteDao().insert(favoriteDB)
            }

            liveData.postValue(FavoriteState.Favorite(searchDataId, !favorite))
        }
    }

    //обработку ошибку запроса данных
    override fun handleCoroutineError(throwable: Throwable) {
        super.handleCoroutineError(throwable)
        liveData.postValue(FavoriteState.Error(throwable))
    }
}