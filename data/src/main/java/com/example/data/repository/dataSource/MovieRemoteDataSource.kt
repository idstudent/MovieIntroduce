package com.example.data.repository.dataSource

import com.example.data.model.NowMoviesResponse

interface MovieRemoteDataSource {
    suspend fun getIntroduceMovie(apiKey : String, country : String) : NowMoviesResponse
}