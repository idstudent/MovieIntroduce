package com.example.movieintroduce.data.repository.dataSource

import com.example.movieintroduce.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun getLikeMovies() : Flow<List<Movie>>
    suspend fun likeMovie(movie : Movie)
    suspend fun cancelLikeMovie(movie : Movie)
}