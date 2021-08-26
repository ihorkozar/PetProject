package com.example.petproject.network

import com.example.petproject.db.DatabaseChildren
import com.example.petproject.db.DatabasePostData
import com.example.petproject.domain.Models
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkPostResponse(val data: NetworkData)

@JsonClass(generateAdapter = true)
data class NetworkData(val children: List<NetworkChildren>)

@JsonClass(generateAdapter = true)
data class NetworkChildren(
    val kind: String,
    val data: NetworkPost
)

@JsonClass(generateAdapter = true)
data class NetworkPost(
    val id: String,
    val title: String,
    val author: String,
    val created_utc: Long,
    val thumbnail: String,
    val num_comments: Int,
    val url_overridden_by_dest: String = "1"//какая-то проблема, надо перечитать доку апи. Может это поле убрали.
)

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

fun NetworkPostResponse.asDomainModel(): Array<Models.Children> = data.children.map {
    Models.Children(
        kind = it.kind,
        postData = Models.PostData(
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
