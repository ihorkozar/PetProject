package com.example.petproject.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.reddit.com/"

interface RedditApi {
    @GET("top.json")
    fun getPostResponseAsync(
        @Query("after") after: String?,
        @Query("limit") limit: Int
    ): Deferred<NetworkPostResponse>
}