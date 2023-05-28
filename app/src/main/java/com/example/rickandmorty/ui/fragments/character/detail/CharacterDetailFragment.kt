package com.example.rickandmorty.ui.fragments.character.detail


import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.example.rickandmorty.ui.fragments.character.CharacterViewModel
import kotlinx.coroutines.launch

class CharacterDetailFragment : BaseFragment<FragmentCharacterDetailBinding,CharacterViewModel>(R.layout.fragment_character_detail) {

    override val binding by viewBinding(FragmentCharacterDetailBinding::bind)
    override val viewModel by activityViewModels<CharacterViewModel>()
    private val args by navArgs<CharacterDetailFragmentArgs>()

    override fun initialize() {
        lifecycleScope.launch {
            viewModel.fetchSingleCharacter(args.id).collect {
                binding.name.text = it?.name
                Glide.with(binding.imageView).load(binding.imageView)
                    .into(binding.imageView)
            }
        }
    }
}
