package com.example.rickandmorty.data.remote.apiservices

import com.example.rickandmorty.models.CommentDeleteModel
import com.example.rickandmorty.models.CommentModel
import com.example.rickandmorty.models.CommentPutModel
import retrofit2.Call
import retrofit2.http.*

interface CommentsApiService {

    @POST
    fun addComment(
        @Url url: String = "https://jsonplaceholder.typicode.com/comments",
        @Body commentModel: CommentModel
    ) : Call<CommentModel>

    @PUT
    fun changeComment(
        @Url url: String = "https://jsonplaceholder.typicode.com/comments",
        @Body commentPutModel: CommentPutModel
    ) : Call<CommentPutModel>

    @DELETE
    fun deleteComment(
        @Url url: String = "https://jsonplaceholder.typicode.com/comments",
    ) : Call<CommentDeleteModel>
}