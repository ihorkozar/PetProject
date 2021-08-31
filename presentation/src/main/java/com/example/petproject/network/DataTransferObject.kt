package com.example.petproject.network

import com.example.petproject.db.DatabaseChildren
import com.example.petproject.db.DatabasePostData
import com.example.petproject.domain.Models
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkPostResponse(val data: NetworkData)

fun NetworkPostResponse.asDatabaseModel(): Array<DatabaseChildren> = data.children.map {
    DatabaseChildren(
        childrenId = 0,
        kind = it.kind,
        postData = DatabasePostData(
            id = it.data.id,
            title = it.data.title,
            author = it.data.author,
            created_utc = it.data.created_utc,
            thumbnail = it.data.thumbnail,
            num_comments = it.data.num_comments,
            url_overridden_by_dest = it.data.url_overridden_by_dest
        )
    )
}.toTypedArray()
