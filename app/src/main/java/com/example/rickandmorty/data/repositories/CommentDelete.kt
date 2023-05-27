package com.example.rickandmorty.data.repositories

import com.example.rickandmorty.App
import com.example.rickandmorty.models.CommentDeleteModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentDelete {

    fun deleteComment(
        comment: CommentDeleteModel,
        onResponse: (comment: CommentDeleteModel) -> Unit,
        onFailure: (errorMassage: String) -> Unit
    ) {
        App.commentApi?.deleteComment(comment.toString())
            ?.enqueue(object : Callback<CommentDeleteModel> {
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