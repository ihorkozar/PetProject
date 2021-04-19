package com.example.petproject.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.petproject.db.RedditDatabase
import com.example.petproject.db.asDomainModel
import com.example.petproject.domain.Models
import com.example.petproject.network.Network
import com.example.petproject.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RedditRepository(private val db: RedditDatabase) {

    val childrenList: LiveData<List<Models.Children>> =
        Transformations.map(db.dao.getChildren()) {
            it.asDomainModel() //size = 1
        }

    suspend fun refreshChildren(){
        withContext(Dispatchers.IO){
            val postResponse = Network.redditService.getPostResponseAsync(null, LIMIT).await() //size = 50
            db.dao.insertChildren(*postResponse.asDatabaseModel())
        }
    }

    companion object{
        private const val LIMIT = 50
    }
}