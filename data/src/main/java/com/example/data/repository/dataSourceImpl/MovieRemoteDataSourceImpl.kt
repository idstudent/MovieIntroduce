package com.example.data.repository.dataSourceImpl

import com.example.data.api.ApiService
import com.example.data.repository.dataSource.MovieRemoteDataSource
import com.example.data.model.NowMoviesResponse
import retrofit2.Response


class MovieRemoteDataSourceImpl(
    private val apiService: ApiService
) : MovieRemoteDataSource {
    override suspend fun getIntroduceMovie(apiKey: String, country: String): NowMoviesResponse {
        return apiService.getNowViewMovies(apiKey, country)
    }
}