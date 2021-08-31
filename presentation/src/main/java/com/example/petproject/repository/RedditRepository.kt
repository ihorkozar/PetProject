package com.example.petproject.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.petproject.db.Dao
import com.example.petproject.db.asDomainModel
import com.example.petproject.domain.Models
import com.example.petproject.network.RedditApi
import com.example.petproject.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RedditRepository @Inject constructor(
    private val dao: Dao,
    private val apiService: RedditApi
) {

    val childrenList: LiveData<List<Models.Children>> =
        Transformations.map(
            dao.getChildren()
        ) {
            it.asDomainModel()
        }

    suspend fun refreshChildren() {
        withContext(Dispatchers.IO) {
            val postResponse = apiService.getPostResponseAsync(null, LIMIT)
            dao.insertChildren(*postResponse.asDatabaseModel())
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}