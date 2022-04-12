package com.geekbrains.dictionary.viewmodel

import android.content.Context
import com.bumptech.glide.Glide
import com.geekbrains.dictionary.data.db.AppDB
import com.geekbrains.dictionary.states.SearchDetailState
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class SearchDetailViewModel(
    private val db: AppDB
) : BaseViewModel<SearchDetailState>() {

    //найти перевод слова
    fun findSearchData(searchDataId: Long?) {
        liveData.postValue(SearchDetailState.Loading)

        if (searchDataId == null) {
            liveData.postValue(SearchDetailState.Error(RuntimeException("Нет данных для отображения")))
            return
        }

        coroutineScope.launch {
            //получаем из базы перевод слов
            val searchData = db.searchDataDao().findSearchData(searchDataId)

            //оповещаем слушателей о найденных ответах
            searchData?.let {
                liveData.postValue(SearchDetailState.Success(it))
            }
                ?: let {
                    liveData.postValue(SearchDetailState.Error(RuntimeException("Нет данных для отображения")))
                }
        }
    }

    //загрузить изображение
    fun loadImage(context: Context, url: String) {
        coroutineScope.launch {
            liveData.postValue(SearchDetailState.Image(Glide.with(context).load(url)))
        }
    }

    //обработать ошибку запроса данных
    override fun handleCoroutineError(throwable: Throwable) {
        super.handleCoroutineError(throwable)
        liveData.postValue(SearchDetailState.Error(throwable))
    }
}