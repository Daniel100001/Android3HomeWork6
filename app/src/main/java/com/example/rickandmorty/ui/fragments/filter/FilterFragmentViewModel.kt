package com.example.rickandmorty.ui.fragments.filter

import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.repositories.CommentDelete
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterFragmentViewModel @Inject constructor(
    private val commentDelete: CommentDelete
) : ViewModel() {

}