package com.example.movieintroduce.domain.repository

import com.example.movieintroduce.data.model.Movie
import com.example.movieintroduce.data.model.NowMoviesResponse
import com.example.movieintroduce.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getIntroduceMovie(apiKey : String, country : String) : Resource<NowMoviesResponse>
    fun getLikeMovies() : Flow<List<Movie>>
    suspend fun likeMovie(movie : Movie)
    suspend fun cancelLikeMovie(movie : Movie)
}