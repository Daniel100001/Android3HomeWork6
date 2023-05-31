package com.example.rickandmorty.data

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.data.db.AppDataBase
import com.example.rickandmorty.data.db.daos.CharacterDao
import com.example.rickandmorty.data.db.daos.EpisodeDao
import com.example.rickandmorty.data.db.daos.LocationDao

class RoomClient {

    fun provideRoom(context: Context) = Room
        .databaseBuilder(context,AppDataBase::class.java, "rick")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

    fun provideCharacterDao(appDataBase: AppDataBase) : CharacterDao = appDataBase
        .characterDao()

    fun provideLocationDao(appDataBase: AppDataBase) : LocationDao = appDataBase
        .locationDao()

    fun provideEpisodeDao(appDataBase: AppDataBase) : EpisodeDao = appDataBase
        .episodeDao()

}