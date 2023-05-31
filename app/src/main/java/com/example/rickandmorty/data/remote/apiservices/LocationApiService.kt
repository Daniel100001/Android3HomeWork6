package com.example.rickandmorty.data.remote.apiservices

import com.example.rickandmorty.models.LocationModel
import com.example.rickandmorty.models.RickAndMortyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationApiService {

    @GET("api/location")
     fun fetchLocation(
    ): Call<RickAndMortyResponse<LocationModel>>

    @GET("api/character/{id}")
    fun fetchSingleCharacter(
        @Path("id") id: Int
    ): Call<LocationModel>
}