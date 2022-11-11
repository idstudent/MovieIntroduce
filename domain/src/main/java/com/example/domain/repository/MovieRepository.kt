package com.example.domain.repository

import com.example.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getIntroduceMovie(apiKey : String, country : String) : Flow<List<Movie>>
    fun getLikeMovies() : Flow<List<Movie>>
    suspend fun likeMovie(movie : Movie)
    suspend fun cancelLikeMovie(movie : Movie)
}