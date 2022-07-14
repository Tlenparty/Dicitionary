package com.geekbrains.dictionary.data.db.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.geekbrains.dictionary.consts.DB

// Найденное слово со всеми переводами
data class WordTranslates(
    @Embedded
    val searchData: SearchDataDB,

    @Relation(parentColumn = DB.SEARCH_DATA_ID,
        entityColumn = DB.SEARCH_DATA_ID,
        entity = MeaningDB::class)
    val translation: List<MeaningDB>
)