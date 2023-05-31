package com.example.rickandmorty.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.models.EpisodeModel

@Dao
interface EpisodeDao {

    @Query("SELECT * FROM episodeModel")
    fun getAll(): LiveData<List<EpisodeModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(episodeModel: List<EpisodeModel>)
}