package com.example.petproject.network

import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {
    @GET("top.json")
    suspend fun getPostResponseAsync(
        @Query("after") after: String?,
        @Query("limit") limit: Int
    ): NetworkPostResponse
}