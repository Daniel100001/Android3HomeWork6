package com.example.rickandmorty.ui.fragments.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.repositories.CommentDelete
import com.example.rickandmorty.data.repositories.CommentPutRepository
import com.example.rickandmorty.data.repositories.CommentRepository
import com.example.rickandmorty.models.CommentDeleteModel
import com.example.rickandmorty.models.CommentModel
import com.example.rickandmorty.models.CommentPutModel

class CommentViewModel : ViewModel() {

    // Этот фрагмент не считаеться
    private val _characterLiveData = MutableLiveData<CommentModel>()
    val commentLiveData: LiveData<CommentModel> get() = _characterLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    private val _characterLiveDataPut = MutableLiveData<CommentPutModel>()
    val commentLiveDataPut: LiveData<CommentModel> get() = _characterLiveData

    private val _characterLiveDataDelete = MutableLiveData<CommentDeleteModel>()
    val commentLiveDataDelete: LiveData<CommentDeleteModel> get() = _characterLiveDataDelete

    private val repositoryPut = CommentPutRepository()
    private val repository = CommentRepository()
    private val repositoryDelete = CommentDelete()

    fun addComment(comment: CommentModel) {
        repository.addComment(
            comment = comment,
            onResponse = { data ->
                _characterLiveData.value = data
            },
            onFailure = { message ->
                _errorLiveData.value = message
            }
        )
    }

    fun changeComment(comment: CommentPutModel) {
        repositoryPut.changeComment(
            comment = comment,
            onResponse = { data ->
                _characterLiveDataPut.value = data
            },
            onFailure = { message ->
                _errorLiveData.value = message
            }
        )
    }

    fun deleteComment(comment: CommentDeleteModel) {
        repositoryDelete.deleteComment(
            comment = comment,
            onResponse = { data ->
                _characterLiveDataDelete.value = data
            },
            onFailure = { message ->
                _errorLiveData.value = message
            }
        )
    }
}