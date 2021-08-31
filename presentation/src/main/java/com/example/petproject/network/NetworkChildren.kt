package com.example.petproject.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkChildren(
    val kind: String,
    val data: NetworkPost
)