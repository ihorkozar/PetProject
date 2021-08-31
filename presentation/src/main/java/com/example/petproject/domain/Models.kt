package com.example.petproject.domain

class Models {
    data class PostResponse(
        val data: Data
    )

    data class Data(
        val children: List<Children>
    )

    data class Children(
        val kind: String,
        val postData: PostData
    )

    data class PostData(
        val id: String,
        val title: String,
        val author: String,
        val created_utc: Long,
        val thumbnail: String,
        val num_comments: Int,
        val url_overridden_by_dest: String
    )
}