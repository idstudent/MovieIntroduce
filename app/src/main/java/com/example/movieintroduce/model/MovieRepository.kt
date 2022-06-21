package com.example.movieintroduce.model

import com.example.movieintroduce.api.ApiManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class MovieRepository {
    fun getMovies(position : Int) : Flow<Response<NowMoviesResponse>> {
        return flow {
            val data = ApiManager
                .getInstance()
                .getNowViewMovies("api_key", "ko", position)
            emit(data)
        }
    }
}