package com.example.movieintroduce.data.repository.dataSourceImpl

import com.example.movieintroduce.data.api.ApiService
import com.example.movieintroduce.data.model.NowMoviesResponse
import com.example.movieintroduce.data.repository.dataSource.MovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val apiService: ApiService
) : MovieRemoteDataSource {
    override suspend fun getIntroduceMovie(apiKey: String, country: String): Response<NowMoviesResponse> {
        return apiService.getNowViewMovies(apiKey, country)
    }
}