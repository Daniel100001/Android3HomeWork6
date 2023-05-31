package com.example.rickandmorty.data.remote.apiservices

import com.example.rickandmorty.models.EpisodeModel
import com.example.rickandmorty.models.RickAndMortyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApiService {

    @GET("api/episode")
    fun fetchEpisode(
    ): Call<RickAndMortyResponse<EpisodeModel>>

    @GET("api/character/{id}")
    fun fetchSingleCharacter(
        @Path("id") id: Int
    ): Call<EpisodeModel>
}