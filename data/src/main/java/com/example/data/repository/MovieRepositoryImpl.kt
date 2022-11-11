package com.example.data.repository

import com.example.data.repository.dataSource.MovieLocalDataSource
import com.example.data.repository.dataSource.MovieRemoteDataSource
import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource
) : MovieRepository {
    override fun getIntroduceMovie(apiKey: String, country: String): Flow<List<Movie>> {
        return flow {
            emit(movieRemoteDataSource.getIntroduceMovie(apiKey, country).mapper())
        }
    }

    override fun getLikeMovies(): Flow<List<Movie>> {
        return movieLocalDataSource.getLikeMovies()
    }

    override suspend fun likeMovie(movie: Movie) {
        movieLocalDataSource.likeMovie(movie)
    }

    override suspend fun cancelLikeMovie(movie: Movie) {
        movieLocalDataSource.cancelLikeMovie(movie)
    }
}