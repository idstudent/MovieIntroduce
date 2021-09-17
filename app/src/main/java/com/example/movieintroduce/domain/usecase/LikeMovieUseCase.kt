package com.example.movieintroduce.domain.usecase

import com.example.movieintroduce.data.model.Movie
import com.example.movieintroduce.domain.repository.MovieRepository

class LikeMovieUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(movie : Movie) {
        return movieRepository.likeMovie(movie)
    }
}