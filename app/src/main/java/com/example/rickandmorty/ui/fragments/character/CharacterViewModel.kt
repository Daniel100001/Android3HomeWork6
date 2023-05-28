package com.example.rickandmorty.ui.fragments.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmorty.data.repositories.CharacterRepository

class CharacterViewModel : ViewModel() {

    private val characterRepository = CharacterRepository()
    fun fetchSingleCharacter(id: Int) = characterRepository.fetchSingleCharacter(id)
    fun fetchCharacters() = characterRepository.fetchCharacters().cachedIn(viewModelScope)

}