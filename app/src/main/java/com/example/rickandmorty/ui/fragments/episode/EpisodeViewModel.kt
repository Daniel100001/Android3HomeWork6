package com.example.rickandmorty.ui.fragments.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.repositories.EpisodeRepository
import com.example.rickandmorty.models.EpisodeModel
import com.example.rickandmorty.models.RickAndMortyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val episodeRepository: EpisodeRepository
) : ViewModel() {

    fun fetchCharacters(): MutableLiveData<RickAndMortyResponse<EpisodeModel>> {
        return episodeRepository.fetchCharacters()
    }

    fun getAll() : LiveData<List<EpisodeModel>> {
        return episodeRepository.getAllCharacters()
    }

    fun fetchSingleCharacter(id: Int) = episodeRepository.fetchSingleCharacter(id)

}