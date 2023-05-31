package com.example.rickandmorty.data.repositories

import com.example.rickandmorty.data.remote.apiservices.CommentsApiService
import com.example.rickandmorty.models.CommentModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class CommentRepository @Inject constructor(
    private val commentApi: CommentsApiService,
) {

    fun addComment(
        comment: CommentModel,
        onResponse: (comment: CommentModel) -> Unit,
        onFailure: (errorMassage: String) -> Unit
    ) {
        commentApi.addComment(commentModel = comment)
            .enqueue(object : Callback<CommentModel> {
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

