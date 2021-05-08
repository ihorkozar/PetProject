package com.example.petproject.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DatabaseChildren::class, DatabasePostData::class], version = 1)
abstract class RedditDatabase : RoomDatabase() {
    abstract val dao: Dao
}