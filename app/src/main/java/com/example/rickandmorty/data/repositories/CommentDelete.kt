package com.example.rickandmorty.data.repositories

import com.example.rickandmorty.data.remote.apiservices.CommentsApiService
import com.example.rickandmorty.models.CommentDeleteModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CommentDelete @Inject constructor(
    private val commentApi: CommentsApiService,
) {

    fun deleteComment(
        comment: CommentDeleteModel,
        onResponse: (comment: CommentDeleteModel) -> Unit,
        onFailure: (errorMassage: String) -> Unit
    ) {
        commentApi.deleteComment(comment.toString())
            .enqueue(object : Callback<CommentDeleteModel> {
                override fun onResponse(
                    call: Call<CommentDeleteModel>,
                    response: Response<CommentDeleteModel>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        onResponse(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<CommentDeleteModel>, t: Throwable) {
                    onFailure(t.localizedMessage ?: "Error")
                }
            })
    }
}