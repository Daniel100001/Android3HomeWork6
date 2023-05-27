package com.example.rickandmorty.ui.fragments.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmorty.data.repositories.LocationRepository

class LocationViewModel : ViewModel() {

    private val locationRepository = LocationRepository()

    fun fetchLocation() = locationRepository.fetchLocation().cachedIn(viewModelScope)

}