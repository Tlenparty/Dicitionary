package com.geekbrains.dictionary.states

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.geekbrains.dictionary.data.db.entities.WordTranslates

sealed class SearchDetailState : BaseState {
    data class Success(val data: WordTranslates) : SearchDetailState()
    data class Image(val data: RequestBuilder<Drawable>) : SearchDetailState()
    data class Error(val error: Throwable) : SearchDetailState()
    object Loading : SearchDetailState()
}