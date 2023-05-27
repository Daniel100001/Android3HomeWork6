package com.example.rickandmorty.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.App
import com.example.rickandmorty.data.repositories.pagingsources.LocationPagingSource
import com.example.rickandmorty.models.LocationModel
import kotlinx.coroutines.flow.Flow

class LocationRepository {

    fun fetchLocation(): Flow<PagingData<LocationModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                LocationPagingSource(App.locationApi!!)
            }
        ).flow
    }
}