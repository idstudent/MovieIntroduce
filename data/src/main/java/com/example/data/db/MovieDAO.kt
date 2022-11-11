package com.example.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.domain.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {
    @Insert
    fun insertMovie(movie : Movie)

    @Delete
    fun deleteMovie(movie : Movie)

    @Query("SELECT * FROM movie_table")
    fun selectMovies() : Flow<List<Movie>>
}