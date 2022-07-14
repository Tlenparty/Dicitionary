package com.geekbrains.dictionary.data.db.entities

import androidx.room.*
import com.geekbrains.dictionary.data.entities.SearchData
import com.geekbrains.dictionary.consts.DB

@Entity(
    tableName = DB.TABLE_SEARCH_DATA,
    indices = [Index(DB.SEARCH_HISTORY_ID)],
    foreignKeys = [ForeignKey(
        entity = SearchHistory::class,
        parentColumns = [DB.SEARCH_HISTORY_ID],
        childColumns = [DB.SEARCH_HISTORY_ID],
        onDelete = ForeignKey.CASCADE
    )]
)
data class SearchDataDB(
    @ColumnInfo(name = DB.SEARCH_DATA_ID)
    @PrimaryKey
    val searchDataId: Long,

    @ColumnInfo(name = DB.SEARCH_HISTORY_ID)
    val searchHistoryId: Long,

    @ColumnInfo(name = DB.FINED_TEXT)
    val finedText: String?

) {
    constructor(searchHistoryId: Long, searchData: SearchData) : this(
        searchDataId = searchData.id,
        searchHistoryId = searchHistoryId,
        finedText = searchData.finedText
    )
}
