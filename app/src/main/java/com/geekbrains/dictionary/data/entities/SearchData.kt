package com.geekbrains.dictionary.data.entities

import com.geekbrains.dictionary.data.db.entities.WordTranslates
import com.geekbrains.dictionary.helpers.consts.Constants
import com.google.gson.annotations.SerializedName

data class  SearchData(
    @SerializedName(Constants.ID)
    val id:Long,

    @SerializedName(Constants.TEXT)
    val finedText: String?,

    @SerializedName(Constants.MEANINGS)
    val translates: List<Meaning>,

    var favorite: Boolean = false
){
    constructor(wordTranslates: WordTranslates, favorite: Boolean) : this(
        id = wordTranslates.searchData.searchDataId,
        finedText = wordTranslates.searchData.finedText,
        translates = wordTranslates.translation.map { Meaning(it) }.toList(),
        favorite = favorite
    )
}
