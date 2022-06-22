package com.example.movieintroduce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.movieintroduce.model.Movie
import com.example.movieintroduce.repository.MoviePagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieIntroduceViewModel @Inject constructor(
    private val moviePagingRepository: MoviePagingRepository
) : ViewModel() {
    fun getMovieIntroduces() : LiveData<PagingData<Movie>>{
        return moviePagingRepository.getMoviePaging()
    }
}