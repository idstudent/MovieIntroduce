package com.example.movieintroduce.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.movieintroduce.model.Movie
import com.example.movieintroduce.repository.MoviePagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieIntroduceViewModel @Inject constructor(
    private val moviePagingRepository: MoviePagingRepository
) : ViewModel() {
    fun getMovieIntroduces() : Flow<PagingData<Movie>> {
        return moviePagingRepository.getMoviePaging()
    }
}