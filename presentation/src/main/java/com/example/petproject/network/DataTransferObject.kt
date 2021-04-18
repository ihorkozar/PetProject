package com.example.petproject.network

import com.example.petproject.db.DatabaseChildren
import com.example.petproject.db.DatabasePost
import com.example.petproject.domain.Models
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkPostResponse(val data: NetworkData)

@JsonClass(generateAdapter = true)
data class NetworkData(val children: List<NetworkChildren>)

@JsonClass(generateAdapter = true)
data class NetworkChildren(
    val childrenId: Int,
    val kind: String,
    val post: NetworkPost
)

@JsonClass(generateAdapter = true)
data class NetworkPost(
    val id: Int,
    val title: String,
    val author: String,
    val created_utc: Long,
    val thumbnail: String,
    val num_comments: Int,
    val sourceUrl: String
)

fun NetworkPostResponse.asDatabaseModel(): Array<DatabaseChildren> = data.children.map {
    DatabaseChildren(
        childrenId = it.childrenId,
        kind = it.kind,
        post = DatabasePost(
            id = it.post.id,
            title = it.post.title,
            author = it.post.author,
            created_utc = it.post.created_utc,
            thumbnail = it.post.thumbnail,
            num_comments = it.post.num_comments,
            sourceUrl = it.post.sourceUrl
        )
    )
}.toTypedArray()

fun NetworkPostResponse.asDomainModel(): Array<Models.Children> = data.children.map {
    Models.Children(
        childrenId = it.childrenId,
        kind = it.kind,
        post = Models.Post(
            id = it.post.id,
            title = it.post.title,
            author = it.post.author,
            created_utc = it.post.created_utc,
            thumbnail = it.post.thumbnail,
            num_comments = it.post.num_comments,
            sourceUrl = it.post.sourceUrl
        )
    )
}.toTypedArray()
