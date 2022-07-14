package com.geekbrains.dictionary.viewmodel

import com.geekbrains.dictionary.data.db.AppDB
import com.geekbrains.dictionary.data.db.entities.Favorite
import com.geekbrains.dictionary.data.db.entities.MeaningDB
import com.geekbrains.dictionary.data.db.entities.SearchDataDB
import com.geekbrains.dictionary.data.db.entities.SearchHistory
import com.geekbrains.dictionary.data.entities.SearchData
import com.geekbrains.dictionary.data.interactors.SearchInteractor
import com.geekbrains.core_helpers.consts.General
import com.geekbrains.dictionary.states.SearchState
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchInteractor: SearchInteractor,
    private val db: AppDB
): BaseViewModel<SearchState>() {

    //найти перевод слова
    fun findWord(word: String) {
        liveData.postValue(SearchState.Loading)
        coroutineScope.launch {
            //сперва сохраняем в базу слово для поиска
            val searchHistoryId = saveWordInDB(word)

            //получаем список избранных слов
            val favorites: Set<Long> = db
                .favoriteDao()
                .getFavorites()
                .toSet()

            //находим переводы и сразу сохраняем их в базу
            val searchDataList = searchInteractor.search(word)
            saveTranslateInDB(searchHistoryId, searchDataList)

            //выделяем избранные слова
            searchDataList.forEach {
                it.favorite = favorites.contains(it.id)
            }

            //оповещаем слушателей о найденных ответах
            if (searchDataList.isEmpty()) {
                liveData.postValue(SearchState.Error(RuntimeException(General.NO_DATA_FOUND)))
            } else {
                liveData.postValue(SearchState.Success(searchDataList))
            }
        }
    }

    //найти пеевод в базе
    fun findWordInDB(word: String) {
        liveData.postValue(SearchState.Loading)
        coroutineScope.launch {
            //получаем список избранных слов
            val favorites: Set<Long> = db
                .favoriteDao()
                .getFavorites()
                .toSet()

            val searchDataList = db.searchDataDao().findSearchDataIdOnFinedText(word)
            val result: List<SearchData> = searchDataList
                .map {
                    SearchData(it, favorites.contains(it.searchData.searchDataId))
                }
                .toList()

            //оповещаем слушателей о найденном слове
            if (result.isEmpty()) {
                liveData.postValue(SearchState.Error(RuntimeException(General.NO_DATA_FOUND)))
            } else {
                liveData.postValue(SearchState.Success(result))
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

            liveData.postValue(SearchState.Favorite(searchDataId, !favorite))
        }
    }

    //обработать ошибку запроса данных
    override fun handleCoroutineError(throwable: Throwable) {
        super.handleCoroutineError(throwable)
        liveData.postValue(SearchState.Error(throwable))
    }

    //сохранить найденные переводы в базу
    private suspend fun saveTranslateInDB(searchHistoryId: Long, searchDataList: List<SearchData>) {
        searchDataList.forEach { searchData ->
            //сперва сохраняем слово, которое нашлось
            val searchDataId = db.searchDataDao().insert(
                SearchDataDB(
                    searchHistoryId = searchHistoryId,
                    searchData = searchData
                )
            )

            //а затем все его переводы
            val meaningDB: List<MeaningDB> = searchData.translates
                .map { meaning ->
                    MeaningDB(searchDataId = searchDataId, meaning = meaning)
                }
                .toList()
            db.meaningDao().insert(meaningDB)
        }
    }

    //сохранить введенное слово в базу
    private suspend fun saveWordInDB(word: String): Long =
        db.searchHistoryDao().insert(SearchHistory(word = word))
}