package com.example.rickandmorty.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.data.db.daos.EpisodeDao
import com.example.rickandmorty.data.remote.apiservices.EpisodeApiService
import com.example.rickandmorty.models.EpisodeModel
import com.example.rickandmorty.models.RickAndMortyResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class EpisodeRepository @Inject constructor(
    private val episodeApiService: EpisodeApiService,
    private val episodeDao: EpisodeDao
){

    fun fetchCharacters(): MutableLiveData<RickAndMortyResponse<EpisodeModel>> {
        val data: MutableLiveData<RickAndMortyResponse<EpisodeModel>> = MutableLiveData()

        episodeApiService.fetchEpisode()
            .enqueue(object : Callback<RickAndMortyResponse<EpisodeModel>> {
                override fun onResponse(
                    call: Call<RickAndMortyResponse<EpisodeModel>>,
                    response: Response<RickAndMortyResponse<EpisodeModel>>
                ) {
                    if (response.isSuccessful && response.body() != null ){
                        data.value = response.body()

                        episodeDao.insertAll(response.body()!!.results)
                    }
                }

                override fun onFailure(
                    call: Call<RickAndMortyResponse<EpisodeModel>>,
                    t: Throwable
                ) {
                    Log.e("error", t.localizedMessage ?: "Error")
                }
            })

        return data
    }

    fun getAllCharacters() : LiveData<List<EpisodeModel>> {
        return episodeDao.getAll()

    }

    fun fetchSingleCharacter(id: Int): Flow<EpisodeModel?> = callbackFlow {
        val callback = object : Callback<EpisodeModel> {
            override fun onResponse(
                call: Call<EpisodeModel>,
                response: Response<EpisodeModel>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    trySend(response.body()).isSuccess
                } else {
                    trySend(null).isSuccess
                }
            }

            override fun onFailure(call: Call<EpisodeModel>, t: Throwable) {
                trySend(null).isSuccess
                Log.e("error", t.localizedMessage ?: "Error")
            }
        }

        episodeApiService.fetchSingleCharacter(id).enqueue(callback)

        // Отменить запрос при отмене потока
        awaitClose {
            // Отменить запрос, если активность завершается
            // незнаю что писать
        }
    }
}
