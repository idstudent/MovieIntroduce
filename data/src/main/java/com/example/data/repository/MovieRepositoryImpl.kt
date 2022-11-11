package com.example.data.repository

import com.example.data.repository.dataSource.MovieLocalDataSource
import com.example.data.repository.dataSource.MovieRemoteDataSource
import com.example.domain.model.Movie
import com.example.domain.model.NowMoviesResponse
import com.example.domain.repository.MovieRepository
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource
) : MovieRepository {
    override suspend fun getIntroduceMovie(apiKey: String, country: String): Resource<NowMoviesResponse> {
        return responseToResource(movieRemoteDataSource.getIntroduceMovie(apiKey,country))
    }

    private fun responseToResource(response : Response<NowMoviesResponse>) : Resource<NowMoviesResponse> {
        if(response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
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