package com.example.data.repository.dataSource

import com.example.domain.model.NowMoviesResponse
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getIntroduceMovie(apiKey : String, country : String) : Response<NowMoviesResponse>
}