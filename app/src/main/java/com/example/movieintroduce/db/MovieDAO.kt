package com.example.movieintroduce.db

import androidx.room.*
import com.example.movieintroduce.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie : Movie)

    @Delete
    suspend fun deleteMovie(movie : Movie)

    @Query("SELECT * FROM movie_table")
    fun selectMovies() : Flow<List<Movie>>
}