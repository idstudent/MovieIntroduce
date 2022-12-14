package com.example.movieintroduce.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.movieintroduce.api.ApiManager
import com.example.movieintroduce.model.Movie
import com.example.movieintroduce.model.NowMoviesResponse
import com.example.movieintroduce.repository.MoviePagingRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class MovieIntroduceViewModel : ViewModel() {
    private var moviePagingRepository = MoviePagingRepository()

    fun getMovieIntroduces() : Flow<PagingData<Movie>> {
        return moviePagingRepository.getMoviePaging()
    }

    suspend fun test(page : Int): Response<NowMoviesResponse> {

        return ApiManager.getInstance()
            .getNowViewMovies("api_key", "ko", page)
    }
}