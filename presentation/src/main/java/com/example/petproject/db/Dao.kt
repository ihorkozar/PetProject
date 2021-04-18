package com.example.petproject.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChildren(vararg children: DatabaseChildren)

    @Query("select * from databasechildren")
    fun getChildren(): LiveData<List<DatabaseChildren>>
}