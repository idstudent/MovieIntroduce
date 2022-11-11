package com.example.domain.usecase

import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository

class CancelLikeMovieUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(movie : Movie) {
        return movieRepository.cancelLikeMovie(movie)
    }
}