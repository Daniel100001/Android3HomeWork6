package com.example.rickandmorty.ui.fragments.character.detail


import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.example.rickandmorty.ui.fragments.character.SharedViewModel
import kotlinx.coroutines.launch

class CharacterDetailFragment : BaseFragment<FragmentCharacterDetailBinding,SharedViewModel>(R.layout.fragment_character_detail) {

    override val binding by viewBinding(FragmentCharacterDetailBinding::bind)
    override val viewModel: SharedViewModel by viewModels()
    private val args by navArgs<CharacterDetailFragmentArgs>()

    override fun initialize() {
        lifecycleScope.launch {
            viewModel.fetchSingleCharacter(args.myModel.id).collect {
                setUpObserves()
            }
        }
    }
    override fun setUpObserves() {
        binding.name.text = args.myModel.name
        Glide.with(binding.imageView).load(args.myModel.image)
            .into(binding.imageView)
    }
}
