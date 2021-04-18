package com.example.petproject.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.petproject.domain.Models

@Entity
data class DatabaseChildren constructor(
    @PrimaryKey
    val childrenId: Int,
    val kind: String,
    @Embedded
    val post: DatabasePost
)

@Entity
data class DatabasePost constructor(
    @PrimaryKey
    val id: Int,
    val title: String,
    val author: String,
    val created_utc: Long,
    val thumbnail: String,
    val num_comments: Int,
    val sourceUrl: String
)

fun List<DatabaseChildren>.asDomainModel(): List<Models.Children> = map {
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
}

