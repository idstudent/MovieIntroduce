package com.example.domain.usecase

import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository

class LikeMovieUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(movie : Movie) {
        return movieRepository.likeMovie(movie)
    }
}