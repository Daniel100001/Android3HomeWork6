package com.example.rickandmorty

import androidx.room.TypeConverter
import com.google.gson.Gson

class LocationConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromObjectToString(location: Any): String {
        return gson.toJson(location)
    }

    @TypeConverter
    fun fromStringToObject(jsonString: String): Any {
        return gson.fromJson(jsonString, Any::class.java)
    }
}