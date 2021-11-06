package com.geekbrains.dictionary.data.entities

import com.geekbrains.dictionary.helpers.Constants
import com.google.gson.annotations.SerializedName

data class Meaning(
    @SerializedName(Constants.ID)
    val id: Long,

    @SerializedName(Constants.PART_OF_SPEECH_CODE)
    val partOfSpeechCode: String,

    @SerializedName(Constants.TRANSLATION)
    val translation: Translation,

    @SerializedName(Constants.TRANSCRIPTION)
    val transcription: String

)
