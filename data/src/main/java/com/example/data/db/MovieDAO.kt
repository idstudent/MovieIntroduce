package com.example.data.db

import androidx.room.*
import com.example.domain.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie : Movie)

    @Delete
    fun deleteMovie(movie : Movie)

    @Query("SELECT * FROM movie_table")
    fun selectMovies() : Flow<List<Movie>>
}