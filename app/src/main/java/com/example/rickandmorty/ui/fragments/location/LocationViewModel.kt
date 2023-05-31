package com.example.rickandmorty.ui.fragments.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.data.repositories.LocationRepository
import com.example.rickandmorty.models.LocationModel
import com.example.rickandmorty.models.RickAndMortyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val characterRepository: LocationRepository
) : ViewModel() {

    fun fetchCharacters(): MutableLiveData<RickAndMortyResponse<LocationModel>> {
        return characterRepository.fetchCharacters()
    }

    fun getAll() : LiveData<List<LocationModel>> {
        return characterRepository.getAllCharacters()
    }

    fun fetchSingleCharacter(id: Int) = characterRepository.fetchSingleCharacter(id)

//    fun fetchLocation() = locationRepository.fetchLocation().cachedIn(viewModelScope)
}