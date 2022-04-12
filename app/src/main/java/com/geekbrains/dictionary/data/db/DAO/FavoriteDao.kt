package com.geekbrains.dictionary.data.db.DAO

import androidx.room.*
import com.geekbrains.dictionary.data.db.entities.Favorite
import com.geekbrains.dictionary.helpers.consts.DB

@Dao
interface FavoriteDao {
    //сохранить избранное слово
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: Favorite)

    //убрать отметку избранного
    @Delete
    suspend fun delete(favorite: Favorite)

    //получить избранные слова
    @Query("select ${DB.SEARCH_DATA_ID} from ${DB.TABLE_FAVORITE}")
    suspend fun getFavorites(): List<Long>
}