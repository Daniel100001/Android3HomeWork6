package com.example.rickandmorty.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmorty.data.db.daos.CharacterDao
import com.example.rickandmorty.data.db.daos.EpisodeDao
import com.example.rickandmorty.data.db.daos.LocationDao
import com.example.rickandmorty.models.CharacterModel
import com.example.rickandmorty.models.EpisodeModel
import com.example.rickandmorty.models.LocationModel

@Database(entities = [CharacterModel::class, EpisodeModel::class, LocationModel::class], version = 3)
abstract class AppDataBase : RoomDatabase(){
    abstract fun characterDao(): CharacterDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun locationDao(): LocationDao
}