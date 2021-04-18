package com.example.petproject.domain

import com.example.petproject.db.DatabasePost

class Models {
    data class PostResponse(
        val data: Data
    )

    data class Data(
        val children: List<Children>
    )

    data class Children(
        val childrenId: Int,
        val kind: String,
        val post: Post
    )

    data class Post(
        val id: Int,
        val title: String,
        val author: String,
        val created_utc: Long,
        val thumbnail: String,
        val num_comments: Int,
        val sourceUrl: String
    )
}