package com.geekbrains.dictionary.data.entities

import com.google.gson.annotations.SerializedName

data class Meaning(
    @SerializedName("id")
    val id: Long,

    @SerializedName("partOfSpeechCode")
    val partOfSpeechCode: String,

    @SerializedName("translation")
    val translation: Translation,

    @SerializedName("transcription")
    val transcription: String

)
