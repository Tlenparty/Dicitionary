package com.geekbrains.dictionary.data.db.entities

import androidx.room.*
import com.geekbrains.dictionary.consts.DB

@Entity(
    tableName = DB.TABLE_SEARCH_HISTORY,
    indices = [
        Index(DB.SEARCH_HISTORY_ID, unique = true),
        Index(DB.WORD, unique = true),
        Index(DB.DATE)
    ]
)
data class SearchHistory(
    @ColumnInfo(name = DB.SEARCH_HISTORY_ID)
    @PrimaryKey
    val searchHistoryId: Long? = null,

    @ColumnInfo(name = DB.WORD)
    val word: String,

    @ColumnInfo(name = DB.DATE)
    val date: Long = System.currentTimeMillis()
)
