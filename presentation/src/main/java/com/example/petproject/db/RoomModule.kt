package com.example.petproject.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

private lateinit var INSTANCE: RedditDatabase

@Module
@InstallIn(SingletonComponent::class)
class RoomModule{

    @Provides
    fun provideDatabase(@ApplicationContext context: Context)
            : RedditDatabase {
        synchronized(RedditDatabase::class.java) {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    RedditDatabase::class.java, "reddit"
                ).build()
            }
        }
        return INSTANCE
    }

    @Provides
    fun provideDao(db: RedditDatabase): Dao = db.dao
}