package com.example.petproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseChildren::class, DatabasePostData::class], version = 1)
abstract class RedditDatabase : RoomDatabase() {
    abstract val dao: Dao
}

private lateinit var INSTANCE: RedditDatabase

fun getDatabase(context: Context): RedditDatabase {
    synchronized(RedditDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                RedditDatabase::class.java,
                "reddit").build()
        }
    }
    return INSTANCE
}