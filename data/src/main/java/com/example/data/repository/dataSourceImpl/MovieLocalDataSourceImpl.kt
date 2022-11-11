package com.example.data.repository.dataSourceImpl

import com.example.data.db.MovieDAO
import com.example.data.repository.dataSource.MovieLocalDataSource
import com.example.domain.model.Movie
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