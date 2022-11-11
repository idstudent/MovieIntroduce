package com.example.data.repository.dataSource

import com.example.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun getLikeMovies() : Flow<List<Movie>>
    suspend fun likeMovie(movie : Movie)
    suspend fun cancelLikeMovie(movie : Movie)
}