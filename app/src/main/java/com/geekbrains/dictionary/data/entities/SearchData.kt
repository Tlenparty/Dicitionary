package com.geekbrains.dictionary.data.entities

import com.geekbrains.dictionary.helpers.consts.Constants
import com.google.gson.annotations.SerializedName

data class  SearchData(
    @SerializedName(Constants.TEXT)
    val finedText: String,

    @SerializedName(Constants.MEANINGS)
    val translates: List<Meaning>
)
