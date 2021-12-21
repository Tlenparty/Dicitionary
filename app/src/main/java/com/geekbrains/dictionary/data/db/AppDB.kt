package com.geekbrains.dictionary.data.db

import androidx.room.*
import com.geekbrains.dictionary.data.db.DAO.*
import com.geekbrains.dictionary.data.db.entities.*
import com.geekbrains.dictionary.helpers.consts.DB

@Database(
    entities = [
        SearchHistory::class,
        SearchDataDB::class,
        MeaningDB::class,
        Favorite::class
    ], version = DB.VERSION, exportSchema = true
)

abstract class AppDB : RoomDatabase() {
    abstract fun searchHistoryDao(): SearchHistoryDao
    abstract fun searchDataDao(): SearchDataDao
    abstract fun meaningDao(): MeaningDao
    abstract fun favoriteDao(): FavoriteDao
}