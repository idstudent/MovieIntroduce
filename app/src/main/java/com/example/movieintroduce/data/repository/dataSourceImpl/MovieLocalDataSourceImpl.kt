package com.example.movieintroduce.data.repository.dataSourceImpl

import com.example.movieintroduce.data.db.MovieDAO
import com.example.movieintroduce.data.model.Movie
import com.example.movieintroduce.data.repository.dataSource.MovieLocalDataSource
import kotlinx.coroutines.flow.Flow

class MovieLocalDataSourceImpl(
    private val movieDAO: MovieDAO
) : MovieLocalDataSource {
    override fun getLikeMovies(): Flow<List<Movie>> {
        return movieDAO.selectMovies()
    }

    override suspend fun likeMovie(movie: Movie) {
        movieDAO.insertMovie(movie)
    }

    override suspend fun cancelLikeMovie(movie: Movie) {
        movieDAO.deleteMovie(movie)
    }

}