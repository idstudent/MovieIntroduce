package com.example.movieintroduce.domain.usecase

import com.example.movieintroduce.data.model.Movie
import com.example.movieintroduce.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetLikeMovieUseCase(private val movieRepository: MovieRepository) {
    fun execute() : Flow<List<Movie>> {
        return movieRepository.getLikeMovies()
    }
}