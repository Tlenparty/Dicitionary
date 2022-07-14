package com.geekbrains.dictionary.data.db.DAO

import androidx.room.*
import com.geekbrains.dictionary.data.db.entities.SearchDataDB
import com.geekbrains.dictionary.data.db.entities.WordTranslates
import com.geekbrains.dictionary.consts.DB

@Dao
interface SearchDataDao {

    // сохранить найденное слово для перевода
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(searchDataList:SearchDataDB): Long

    // найти найденное слово
    @Query("select * " +
            "from ${DB.TABLE_SEARCH_DATA} " +
            "where ${DB.SEARCH_DATA_ID} = :searchDataId")
    suspend fun findSearchData(searchDataId: Long): WordTranslates?

    //найти слово
    @Query("select  *" +
            "from ${DB.TABLE_SEARCH_DATA} " +
            "where ${DB.FINED_TEXT} = :finedText")
    suspend fun findSearchDataIdOnFinedText(finedText: String): List<WordTranslates>

    //найти избранные слова
    @Query("select s.* " +
            "from ${DB.TABLE_SEARCH_DATA} s, " +
            "${DB.TABLE_FAVORITE} f " +
            "where s.${DB.SEARCH_DATA_ID} = f.${DB.SEARCH_DATA_ID}")
    suspend fun findFavorite(): List<WordTranslates>
}