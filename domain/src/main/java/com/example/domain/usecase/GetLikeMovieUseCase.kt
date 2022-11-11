package com.example.domain.usecase

import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetLikeMovieUseCase(private val movieRepository: MovieRepository) {
    fun execute() : Flow<List<Movie>> {
        return movieRepository.getLikeMovies()
    }
}