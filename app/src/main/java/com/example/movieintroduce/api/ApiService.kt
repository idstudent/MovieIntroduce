package com.example.movieintroduce.api

import com.example.movieintroduce.db.NowMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    fun getNowViewMovies(
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ): Call<NowMoviesResponse>
}