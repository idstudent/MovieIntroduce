package com.example.movieintroduce.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieintroduce.data.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDAO() : MovieDAO
}