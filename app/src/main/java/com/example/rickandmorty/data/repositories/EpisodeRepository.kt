package com.example.rickandmorty.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.App
import com.example.rickandmorty.data.repositories.pagingsources.EpisodePagingSource
import com.example.rickandmorty.models.EpisodeModel
import kotlinx.coroutines.flow.Flow


class EpisodeRepository {

    fun fetchEpisode(): Flow<PagingData<EpisodeModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                EpisodePagingSource(App.episodeApi!!)
            }
        ).flow
    }
}