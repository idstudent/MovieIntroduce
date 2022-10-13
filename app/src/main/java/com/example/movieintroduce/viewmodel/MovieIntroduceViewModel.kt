package com.example.movieintroduce.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.movieintroduce.model.Movie
import com.example.movieintroduce.repository.MoviePagingRepository
import kotlinx.coroutines.flow.Flow

class MovieIntroduceViewModel : ViewModel() {
    private var moviePagingRepository = MoviePagingRepository()

    fun getMovieIntroduces() : Flow<PagingData<Movie>> {
        return moviePagingRepository.getMoviePaging()
    }
}