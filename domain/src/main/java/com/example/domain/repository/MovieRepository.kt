package com.example.domain.repository

import com.example.domain.model.Movie
import com.example.domain.model.NowMoviesResponse
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getIntroduceMovie(apiKey : String, country : String) : Resource<NowMoviesResponse>
    fun getLikeMovies() : Flow<List<Movie>>
    suspend fun likeMovie(movie : Movie)
    suspend fun cancelLikeMovie(movie : Movie)
}