package com.geekbrains.dictionary.data.entities

import com.geekbrains.dictionary.data.db.entities.MeaningDB
import com.geekbrains.dictionary.helpers.consts.Constants
import com.google.gson.annotations.SerializedName

data class Meaning(
    @SerializedName(Constants.ID)
    val id: Long,

    @SerializedName(Constants.PART_OF_SPEECH_CODE)
    val partOfSpeechCode: String?,

    @SerializedName(Constants.TRANSLATION)
    val translation: Translation,

    @SerializedName(Constants.TRANSCRIPTION)
    val transcription: String?,

    @SerializedName(Constants.IMAGE_URL)
    val imageUrl: String?
) {
    constructor(meaningDB: MeaningDB) : this(
        id = meaningDB.meaningId,
        partOfSpeechCode = meaningDB.partOfSpeechCode,
        translation = Translation(meaningDB.text, meaningDB.note),
        transcription = meaningDB.transcription,
        imageUrl = meaningDB.imageUrl
    )
}
