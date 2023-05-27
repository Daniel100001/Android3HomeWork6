package com.example.rickandmorty.models

data class CommentModel(
    val post: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String,
)

data class CommentPutModel(
    val id: Int,
    val title: String,
    val body: String? = null,
    val userId: Int
)

data class CommentDeleteModel(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String

)
