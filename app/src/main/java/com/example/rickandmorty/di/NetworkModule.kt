package com.example.rickandmorty.di

import com.example.rickandmorty.data.remote.RetrofitClient
import com.example.rickandmorty.data.remote.apiservices.CharacterApiService
import com.example.rickandmorty.data.remote.apiservices.CommentsApiService
import com.example.rickandmorty.data.remote.apiservices.EpisodeApiService
import com.example.rickandmorty.data.remote.apiservices.LocationApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    val retrofitClient = RetrofitClient()

    @Singleton
    @Provides
    fun provideCharacterApiService() : CharacterApiService {
        return retrofitClient.provideCharacterApiService()
    }

    @Singleton
    @Provides
    fun provideLocationApiService() : LocationApiService {
        return retrofitClient.provideLocationApiService()
    }

    @Singleton
    @Provides
    fun provideEpisodeApiService() : EpisodeApiService {
        return retrofitClient.provideEpisodeApiService()
    }

    @Singleton
    @Provides
    fun provideCommentApiService() : CommentsApiService {
        return retrofitClient.provideCommentApiService()
    }
}