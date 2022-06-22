package com.example.movieintroduce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.movieintroduce.model.Movie
import com.example.movieintroduce.repository.MoviePagingRepository

class MovieIntroduceViewModel : ViewModel() {
    private var moviePagingRepository = MoviePagingRepository()

    fun getMovieIntroduces() : LiveData<PagingData<Movie>>{
        return moviePagingRepository.getMoviePaging()
    }
}