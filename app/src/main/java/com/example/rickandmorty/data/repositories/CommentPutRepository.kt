package com.example.rickandmorty.data.repositories

import com.example.rickandmorty.data.remote.apiservices.CommentsApiService
import com.example.rickandmorty.models.CommentPutModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CommentPutRepository @Inject constructor(
    private val commentApi: CommentsApiService,
) {

    fun changeComment(
        comment: CommentPutModel,
        onResponse: (comment: CommentPutModel) -> Unit,
        onFailure: (errorMassage: String) -> Unit
    ) {
        commentApi.changeComment(commentPutModel = comment)
            .enqueue(object : Callback<CommentPutModel> {
                override fun onResponse(
                    call: Call<CommentPutModel>,
                    response: Response<CommentPutModel>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        onResponse(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<CommentPutModel>, t: Throwable) {
                    onFailure(t.localizedMessage ?: "Error")
                }
            })
    }
}