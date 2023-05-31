package com.example.rickandmorty.ui.fragments.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmorty.data.repositories.CharacterRepository
import com.example.rickandmorty.models.CharacterModel
import com.example.rickandmorty.models.RickAndMortyResponse
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    fun fetchCharacters(): MutableLiveData<RickAndMortyResponse<CharacterModel>>{
        return characterRepository.fetchCharacters()
    }

    fun getAll() : LiveData<List<CharacterModel>>? {
        return characterRepository.getAllCharacters()
    }

    fun fetchSingleCharacter(id: Int) = characterRepository.fetchSingleCharacter(id)

//    fun fetchCharacters() = characterRepository.fetchCharacters().cachedIn(viewModelScope)

}