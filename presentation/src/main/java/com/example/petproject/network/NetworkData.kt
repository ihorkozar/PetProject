package com.example.petproject.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkData(val children: List<NetworkChildren>)