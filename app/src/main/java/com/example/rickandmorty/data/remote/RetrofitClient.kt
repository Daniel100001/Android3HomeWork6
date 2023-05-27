package com.example.rickandmorty.data.remote

import com.example.rickandmorty.data.remote.apiservices.CharacterApiService
import com.example.rickandmorty.data.remote.apiservices.CommentsApiService
import com.example.rickandmorty.data.remote.apiservices.EpisodeApiService
import com.example.rickandmorty.data.remote.apiservices.LocationApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private val okHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofitClient = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun provideCharacterApiService(): CharacterApiService {
        return retrofitClient.create(CharacterApiService::class.java)
    }

    fun provideLocationApiService(): LocationApiService {
        return retrofitClient.create(LocationApiService::class.java)
    }

    fun provideEpisodeApiService(): EpisodeApiService {
        return retrofitClient.create(EpisodeApiService::class.java)
    }

    fun provideCommentApiService(): CommentsApiService {
        return retrofitClient.create(CommentsApiService::class.java)
    }
}