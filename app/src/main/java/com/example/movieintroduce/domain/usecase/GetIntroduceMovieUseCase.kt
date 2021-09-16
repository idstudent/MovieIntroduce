package com.example.movieintroduce.domain.usecase

import com.example.movieintroduce.data.model.NowMoviesResponse
import com.example.movieintroduce.data.util.Resource
import com.example.movieintroduce.domain.repository.MovieRepository

class GetIntroduceMovieUseCase(private val movieRepository : MovieRepository) {
    suspend fun execute(apiKey : String, country : String) : Resource<NowMoviesResponse> {
        return movieRepository.getIntroduceMovie(apiKey, country)
    }
}
