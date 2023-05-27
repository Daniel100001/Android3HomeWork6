package com.example.rickandmorty.ui.fragments.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmorty.data.repositories.EpisodeRepository


class EpisodeViewModel : ViewModel() {

    private val episodeRepository = EpisodeRepository()

    fun fetchEpisode() = episodeRepository.fetchEpisode().cachedIn(viewModelScope)

}