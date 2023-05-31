package com.example.rickandmorty.ui.fragments.comment

import android.util.Log
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentCommentBinding
import com.example.rickandmorty.models.CommentDeleteModel
import com.example.rickandmorty.models.CommentModel
import com.example.rickandmorty.models.CommentPutModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentFragment :
    BaseFragment<FragmentCommentBinding, CommentViewModel>(R.layout.fragment_comment) {

    override val binding by viewBinding(FragmentCommentBinding::bind)
    override val viewModel: CommentViewModel by viewModels()

    override fun setUpRequests() {
        binding.buttonPost.setOnClickListener {
            val commentBody = binding.editText.text.toString().trim()
            val comment = CommentModel(101, 51, "Radin", "Dodic@gmail.com", commentBody)
            viewModel.addComment(comment = comment)
            Log.e("comment Post = ", comment.toString())
        }

        binding.buttonPut.setOnClickListener {
            val commentBody = binding.editText.text.toString().trim()
            val comment = CommentPutModel(1, title = "fds", body = commentBody, 1)
            viewModel.changeComment(comment = comment)
            Log.e("comment Put = ", comment.toString())
        }

        binding.buttonDelete.setOnClickListener {
            val comment = CommentDeleteModel(
                1,
                1,
                "id labore ex et quam laborum",
                "Eliseo@gardner.biz",
                "laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium"
            )
            viewModel.deleteComment(comment = comment)
            Log.e("Delete", comment.toString())
        }
    }
}