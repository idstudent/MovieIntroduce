package com.example.movieintroduce.model

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun lisToJson(value : List<Int>) : String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value : String) : List<Int> {
        return Gson().fromJson(value, Array<Int>::class.java).toList()
    }
}