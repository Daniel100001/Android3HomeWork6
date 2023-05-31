package com.example.rickandmorty.ui.fragments.episode.detail

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentEpisodeDetailBinding
import com.example.rickandmorty.ui.fragments.episode.EpisodeViewModel
import kotlinx.coroutines.launch

class EpisodeFragmentDetail :
    BaseFragment<FragmentEpisodeDetailBinding, EpisodeViewModel>(R.layout.fragment_episode_detail) {

    override val binding by viewBinding(FragmentEpisodeDetailBinding::bind)
    override val viewModel by activityViewModels<EpisodeViewModel>()
    private val args by navArgs<EpisodeFragmentDetailArgs>()

    override fun initialize() {
        lifecycleScope.launch {
            viewModel.fetchSingleCharacter(args.id).collect {
                binding.episode.text = it?.episode
                binding.name.text = it?.name
            }
        }
    }
}