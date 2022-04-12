package com.geekbrains.dictionary.data.db.DAO

import androidx.room.*
import com.geekbrains.dictionary.data.db.entities.SearchHistory

@Dao
interface SearchHistoryDao {

    // сохарнить слово, для которого делатеся перевод
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(searchHistory: SearchHistory): Long
}