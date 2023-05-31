package com.example.rickandmorty.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.data.db.daos.LocationDao
import com.example.rickandmorty.data.remote.apiservices.LocationApiService
import com.example.rickandmorty.models.LocationModel
import com.example.rickandmorty.models.RickAndMortyResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val locationApi: LocationApiService,
    private val locationDao: LocationDao
) {

    fun fetchCharacters(): MutableLiveData<RickAndMortyResponse<LocationModel>> {
        val data: MutableLiveData<RickAndMortyResponse<LocationModel>> = MutableLiveData()

        locationApi.fetchLocation()
            .enqueue(object : Callback<RickAndMortyResponse<LocationModel>> {
                override fun onResponse(
                    call: Call<RickAndMortyResponse<LocationModel>>,
                    response: Response<RickAndMortyResponse<LocationModel>>
                ) {
                    if (response.isSuccessful && response.body() != null ){
                        data.value = response.body()

                        locationDao.insertAll(response.body()!!.results)
                    }
                }

                override fun onFailure(
                    call: Call<RickAndMortyResponse<LocationModel>>,
                    t: Throwable
                ) {
                    Log.e("error", t.localizedMessage ?: "Error")
                }
            })

        return data
    }

    fun getAllCharacters() : LiveData<List<LocationModel>> {
        return locationDao.getAll()

    }

    fun fetchSingleCharacter(id: Int): Flow<LocationModel?> = callbackFlow {
        val callback = object : Callback<LocationModel> {
            override fun onResponse(
                call: Call<LocationModel>,
                response: Response<LocationModel>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    trySend(response.body()).isSuccess
                } else {
                    trySend(null).isSuccess
                }
            }

            override fun onFailure(call: Call<LocationModel>, t: Throwable) {
                trySend(null).isSuccess
                Log.e("error", t.localizedMessage ?: "Error")
            }
        }

        locationApi.fetchSingleCharacter(id).enqueue(callback)

        // Отменить запрос при отмене потока
        awaitClose {
            // Отменить запрос, если активность завершается
            // незнаю что писать
        }
    }
}