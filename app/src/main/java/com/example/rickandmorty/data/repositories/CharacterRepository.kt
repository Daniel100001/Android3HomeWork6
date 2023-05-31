package com.example.rickandmorty.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.data.db.daos.CharacterDao
import com.example.rickandmorty.data.remote.apiservices.CharacterApiService
import com.example.rickandmorty.models.CharacterModel
import com.example.rickandmorty.models.RickAndMortyResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val characterApiService: CharacterApiService,
    private val characterDao: CharacterDao
){

    fun fetchCharacters(): MutableLiveData<RickAndMortyResponse<CharacterModel>> {
        val data: MutableLiveData<RickAndMortyResponse<CharacterModel>> = MutableLiveData()

        characterApiService.fetchCharacters()
            .enqueue(object : Callback<RickAndMortyResponse<CharacterModel>> {
                override fun onResponse(
                    call: Call<RickAndMortyResponse<CharacterModel>>,
                    response: Response<RickAndMortyResponse<CharacterModel>>
                ) {
                    if (response.isSuccessful && response.body() != null ){
                        data.value = response.body()

                        characterDao.insertAll(response.body()!!.results)
                    }
                }

                override fun onFailure(
                    call: Call<RickAndMortyResponse<CharacterModel>>,
                    t: Throwable
                ) {
                    Log.e("error", t.localizedMessage ?: "Error")
                }
            })

        return data
    }

    fun getAllCharacters() : LiveData<List<CharacterModel>> {
        return characterDao.getAll()

    }


    fun fetchSingleCharacter(id: Int): Flow<CharacterModel?> = callbackFlow {
        val callback = object : Callback<CharacterModel> {
            override fun onResponse(
                call: Call<CharacterModel>,
                response: Response<CharacterModel>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    trySend(response.body()).isSuccess
                } else {
                    trySend(null).isSuccess
                }
            }

            override fun onFailure(call: Call<CharacterModel>, t: Throwable) {
                trySend(null).isSuccess
                Log.e("error", t.localizedMessage ?: "Error")
            }
        }

        characterApiService.fetchSingleCharacter(id).enqueue(callback)

        // Отменить запрос при отмене потока
        awaitClose {
            // Отменить запрос, если активность завершается
            // незнаю что писать
        }
    }
}