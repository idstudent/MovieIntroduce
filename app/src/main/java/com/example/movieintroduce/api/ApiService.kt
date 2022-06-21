package com.example.movieintroduce.api

import com.example.movieintroduce.model.NowMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        val BASE_URL = "https://api.themoviedb.org/3/"
        val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }
    @GET("movie/now_playing")
    suspend fun getNowViewMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int

    ): Response<NowMoviesResponse>
}