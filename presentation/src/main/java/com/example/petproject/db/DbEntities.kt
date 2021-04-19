package com.example.petproject.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.petproject.domain.Models

@Entity
data class DatabaseChildren constructor(
    @PrimaryKey
    val kind: String,
    @Embedded
    val postData: DatabasePostData
)

@Entity
data class DatabasePostData constructor(
    @PrimaryKey
    val id: String,
    val title: String,
    val author: String,
    val created_utc: Long,
    val thumbnail: String,
    val num_comments: Int
)

fun List<DatabaseChildren>.asDomainModel(): List<Models.Children> = map {
    Models.Children(
        kind = it.kind,
        postData = Models.PostData(
            id = it.postData.id,
            title = it.postData.title,
            author = it.postData.author,
            created_utc = it.postData.created_utc,
            thumbnail = it.postData.thumbnail,
            num_comments = it.postData.num_comments,
        )
    )
}

