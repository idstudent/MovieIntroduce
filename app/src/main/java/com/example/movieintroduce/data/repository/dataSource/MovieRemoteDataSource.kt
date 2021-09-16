package com.example.movieintroduce.data.repository.dataSource

import com.example.movieintroduce.data.model.NowMoviesResponse
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getIntroduceMovie(apiKey : String, country : String) : Response<NowMoviesResponse>
}