package com.geekbrains.dictionary.data.db.DAO

import androidx.room.*
import com.geekbrains.dictionary.data.db.entities.MeaningDB

@Dao
interface MeaningDao {
    // созранить найденные переводы слова
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(meanings: List<MeaningDB>)
}