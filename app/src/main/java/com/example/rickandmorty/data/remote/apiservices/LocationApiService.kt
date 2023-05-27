package com.example.rickandmorty.data.remote.apiservices

import com.example.rickandmorty.models.LocationModel
import com.example.rickandmorty.models.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApiService {

    @GET("api/location")
    suspend fun fetchLocation(
        @Query("page") page: Int
    ): RickAndMortyResponse<LocationModel>
}