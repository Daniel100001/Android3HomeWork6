package com.example.rickandmorty.data.repositories

import com.example.rickandmorty.App
import com.example.rickandmorty.models.CommentModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CommentRepository {

    fun addComment(
        comment: CommentModel,
        onResponse: (comment: CommentModel) -> Unit,
        onFailure: (errorMassage: String) -> Unit
    ) {
        App.commentApi?.addComment(commentModel = comment)
            ?.enqueue(object : Callback<CommentModel> {
                override fun onResponse(
                    call: Call<CommentModel>,
                    response: Response<CommentModel>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        onResponse(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<CommentModel>, t: Throwable) {
                    onFailure(t.localizedMessage ?: "Error")
                }
            })
    }
}

