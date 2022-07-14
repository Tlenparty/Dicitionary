package com.geekbrains.dictionary.data.db.entities

import androidx.room.*
import com.geekbrains.dictionary.data.entities.Meaning
import com.geekbrains.dictionary.consts.DB

@Entity(
    tableName = DB.TABLE_MEANING,
    indices = [Index(DB.SEARCH_DATA_ID)],
    foreignKeys = [ForeignKey(
        entity = SearchDataDB::class,
        parentColumns = [DB.SEARCH_DATA_ID],
        childColumns = [DB.SEARCH_DATA_ID],
        onDelete = ForeignKey.CASCADE
    )]
)
data class MeaningDB (
    @ColumnInfo(name = DB.MEANING_ID)
    @PrimaryKey
    val meaningId: Long,

    @ColumnInfo(name = DB.SEARCH_DATA_ID)
    val searchDataId: Long,

    @ColumnInfo(name = DB.PART_OF_SPEECH_CODE)
    val partOfSpeechCode: String?,

    @ColumnInfo(name = DB.TRANSCRIPTION)
    val transcription: String?,

    @ColumnInfo(name = DB.IMAGE_URL)
    val imageUrl: String?,

    @ColumnInfo(name = DB.TEXT)
    val text: String?,

    @ColumnInfo(name = DB.NOTE)
    val note: String?
) {
    constructor(searchDataId: Long, meaning: Meaning) : this(
        meaningId = meaning.id,
        searchDataId = searchDataId,
        partOfSpeechCode = meaning.partOfSpeechCode,
        transcription = meaning.transcription,
        imageUrl = meaning.imageUrl,
        text = meaning.translation.text,
        note = meaning.translation.note
    )
}
