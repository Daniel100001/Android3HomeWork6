package com.example.rickandmorty.data.remote.apiservices

import com.example.rickandmorty.models.CharacterModel
import com.example.rickandmorty.models.RickAndMortyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApiService {

    @GET("api/character")
    fun fetchCharacters(
    ): Call<RickAndMortyResponse<CharacterModel>>

    @GET("api/character/{id}")
    fun fetchSingleCharacter (
        @Path("id") id: Int
    ): Call<CharacterModel>
}