package com.example.rickandmorty.data.remote.apiservices

import com.example.rickandmorty.models.EpisodeModel
import com.example.rickandmorty.models.RickAndMortyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodeApiService {

    @GET("api/episode")
    suspend fun fetchEpisode(
        @Query("page") page: Int
    ): RickAndMortyResponse<EpisodeModel>
}