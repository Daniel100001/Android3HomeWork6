package com.example.rickandmorty.di

import android.content.Context
import com.example.rickandmorty.data.RoomClient
import com.example.rickandmorty.data.db.AppDataBase
import com.example.rickandmorty.data.db.daos.CharacterDao
import com.example.rickandmorty.data.db.daos.EpisodeDao
import com.example.rickandmorty.data.db.daos.LocationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    val roomClient = RoomClient()

    @Singleton
    @Provides
    fun provideRoom(
        @ApplicationContext context: Context
    ) : AppDataBase = roomClient.provideRoom(context)

    @Singleton
    @Provides
    fun provideCharacterDao(appDataBase: AppDataBase) : CharacterDao {
        return roomClient.provideCharacterDao(appDataBase)
    }

    @Singleton
    @Provides
    fun provideLocationDao(appDataBase: AppDataBase) : LocationDao {
        return roomClient.provideLocationDao(appDataBase)
    }

    @Singleton
    @Provides
    fun provideEpisodeDao(appDataBase: AppDataBase) : EpisodeDao {
        return roomClient.provideEpisodeDao(appDataBase)
    }
}