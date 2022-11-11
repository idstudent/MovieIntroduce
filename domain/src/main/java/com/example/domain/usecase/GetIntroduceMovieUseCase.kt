package com.example.domain.usecase

import com.example.domain.model.NowMoviesResponse
import com.example.domain.repository.MovieRepository
import com.example.domain.util.Resource

class GetIntroduceMovieUseCase(private val movieRepository : MovieRepository) {
    suspend fun execute(apiKey : String, country : String) : Resource<NowMoviesResponse> {
        return movieRepository.getIntroduceMovie(apiKey, country)
    }
}
