package com.example.rickandmorty.data.remote.apiservices

import com.example.rickandmorty.models.CharacterModel
import com.example.rickandmorty.models.RickAndMortyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiService {

    @GET("api/character")
   suspend fun fetchCharacters(
        @Query("page") page: Int,
    ): RickAndMortyResponse<CharacterModel>

    @GET("api/character/{id}")
    fun fetchSingleCharacter (
        @Path("id") id: Int
    ): Call<CharacterModel>
}