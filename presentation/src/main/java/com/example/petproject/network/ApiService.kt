package com.example.petproject.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {
    @GET("top.json")
    fun getPostResponseAsync(
        @Query("after") after: String?,
        @Query("limit") limit: Int
    ): Deferred<NetworkPostResponse>
}