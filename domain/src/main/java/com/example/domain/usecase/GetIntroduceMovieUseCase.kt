package com.example.domain.usecase

import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

class GetIntroduceMovieUseCase(private val movieRepository : MovieRepository) {
    fun execute(apiKey : String, country : String) : Flow<List<Movie>> {
        return movieRepository.getIntroduceMovie(apiKey, country).catch { it.printStackTrace() }
    }
}
