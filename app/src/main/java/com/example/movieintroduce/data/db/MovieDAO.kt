package com.example.movieintroduce.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.movieintroduce.data.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {
    @Insert
    suspend fun insertMovie(movie : Movie)

    @Delete
    suspend fun deleteMovie(movie : Movie)

    @Query("SELECT * FROM movie_table")
    fun selectMovies() : Flow<List<Movie>>
}